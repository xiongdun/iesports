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
		// ��ǰ���� MappedStatement, BoundSql ��Sql ȡ��
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String originalSql = boundSql.getSql().trim();
		Object paratmeterObject = boundSql.getParameterObject();
		//Page�����ȡ������ʹ��������������  
	    Page page = searchPageWithXpath(boundSql.getParameterObject(),".","page","*/page");
	    
	    if (page != null) {
	    	// page������ڵĳ��ϣ���ʼ���з�ҳ����
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
	          
	        //��ҳ����  
	        page.setTotalRecord(totpage);  
	          
	        //��ԭʼSql׷��limit  
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
	 * ���������ݸ�����xPath��ѯPage����
	 * @author xiongdun
	 * @created 2016��12��21�� ����2:18:49
	 * @since 
	 * @param obj
	 * @param xPaths
	 * @return
	 */
	private Page searchPageWithXpath(Object obj, String... xPaths) {
		return null;
	}
	
	/**
	 * ����������MappedStatement����
	 * @author xiongdun
	 * @created 2016��12��21�� ����2:21:43
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
	 * ����������BoundSql����
	 * @author xiongdun
	 * @created 2016��12��21�� ����2:27:42
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
	 * ����������ԭSql����ȡ��Ӧ�Ĳ�ѯ�ܼ�¼����Sql��� 
	 * @author xiongdun
	 * @created 2016��12��21�� ����2:58:55
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
