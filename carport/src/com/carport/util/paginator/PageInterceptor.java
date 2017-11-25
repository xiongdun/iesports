package com.carport.util.paginator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


@Intercepts({@Signature(type = Executor.class, method = "query",
args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })})
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 当前环境 MappedStatement, BoundSql 及Sql 取得
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String originalSql = boundSql.getSql().trim();
		Object paratmeterObject = boundSql.getParameterObject();
		//Page对象获取，“信使”到达拦截器！  
	    Page page = searchPageWithXpath(boundSql.getParameterObject(),".","page","*/page");
	    
	    if (page != null) {
	    	// page对象存在的场合，开始进行分页处理
	    	String countSql = getCountSql(originalSql);
	    	Connection connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
	    	PreparedStatement countStmt = connection.prepareStatement(countSql);
	    	BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql, countSql);
	    	DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paratmeterObject, countBS);  
	        parameterHandler.setParameters(countStmt);  
	        ResultSet rs = countStmt.executeQuery();  
	        int totpage=0;  
	        if (rs.next()) {    
	          totpage = rs.getInt(1);    
	        }  
	        rs.close();    
	        countStmt.close();    
	        connection.close();  
	          
	        //分页计算  
	        page.setTotalRecord(totpage);  
	          
	        //对原始Sql追加limit  
	        int offset = (page.getPageNo() - 1) * page.getPageSize();  
	        StringBuffer sb = new StringBuffer();  
	        sb.append(originalSql).append(" limit ").append(offset).append(",").append(page.getPageSize());  
	        BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, sb.toString());  
	        MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));    
	        invocation.getArgs()[0]= newMs;    
	    }  
	    return invocation.proceed();
	}

	@Override
	public Object plugin(Object obj) {
		return Plugin.wrap(obj, this);
	}

	@Override
	public void setProperties(Properties props) {
		
	}
	
	/**
	 * 描述：根据给定的xPath查询Page对象
	 * @author xiongdun
	 * @created 2016年12月21日 下午2:18:49
	 * @since 
	 * @param obj
	 * @param xPaths
	 * @return
	 */
	private Page searchPageWithXpath(Object obj, String... xPaths) {
		return null;
	}
	
	/**
	 * 描述：复制MappedStatement对象
	 * @author xiongdun
	 * @created 2016年12月21日 下午2:21:43
	 * @since 
	 * @param ms
	 * @param newSqlSource
	 * @return
	 */
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getDatabaseId());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.keyProperty(ms.getKeyProperties()[0]);  
	    builder.timeout(ms.getTimeout());  
	    builder.parameterMap(ms.getParameterMap());  
	    builder.resultMaps(ms.getResultMaps());  
	    builder.resultSetType(ms.getResultSetType());  
	    builder.cache(ms.getCache());  
	    builder.flushCacheRequired(ms.isFlushCacheRequired());  
	    builder.useCache(ms.isUseCache());
		return builder.build();
	}
	
	/**
	 * 描述：复制BoundSql对象
	 * @author xiongdun
	 * @created 2016年12月21日 下午2:27:42
	 * @since 
	 * @return
	 */
	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapper : boundSql.getParameterMappings()) {
			String prop = mapper.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}
	
	/**
	 * 描述：根据原Sql语句获取对应的查询总记录数的Sql语句 
	 * @author xiongdun
	 * @created 2016年12月21日 下午2:58:55
	 * @since 
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql) {
		return "SELECT count(*) FROM (" + sql + " aliasForPage ";
	}
	
	private class BoundSqlSqlSource implements SqlSource {
		
		BoundSql boundSql;
		
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object arg0) {
			return boundSql;
		}
	}
}
