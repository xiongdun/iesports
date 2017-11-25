/**
 * 
 */
package com.iesports.util.jdbc.session;

import java.sql.Connection;
import java.util.List;

import com.iesports.util.DataList;
import com.iesports.util.DataPage;

/**
 * ��������ȡ�Ựsession
 * @author xiongdun
 * @created 2016��9��7�� ����8:40:30
 * @since 
 */
public interface Session {
	
	/**
	 * ���ػỰ�е�connection
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����8:57:10
	 * @since 
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * �������ݿ�����ͣ����Ͷ�����DatabaseType����
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����8:57:47
	 * @since 
	 * @return
	 */
	public int getDatabaseType();
	
	/**
	 * ��ָ�����в���һ����¼
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:02:25
	 * @since 
	 * @param tableName ��Ҫ�����¼�ı���
	 * @param data ��Ҫ���������
	 * @return
	 */
	public int insert(String tableName, DataList data);
	
	/**
	 * ��ָ�����ݱ��и���һ���¼
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:08:07
	 * @since 
	 * @param tableName ָ���ı���
	 * @param data ��Ҫ���������
	 * @param identify ���¼�¼������ID
	 * @param identifyValue ���µļ�¼ֵ
	 * @return
	 */
	public int update(String tableName, DataList data, String identify, Object identifyValue);
	
	/**
	 * ���±��¼
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:11:22
	 * @since 
	 * @param tableName ָ�������ݱ���
	 * @param data ��Ҫ���µ�ֵ
	 * @param identifies �ֶ���
	 * @param identifiesValue �ֶ�ֵ
	 * @return
	 */
	public int update(String tableName, DataList data, String[] identifies, Object[] identifiesValue);
	
	/**
	 * �h��ָ�����еļ�¼
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:16:31
	 * @since 
	 * @param tableName ָ�������ݱ���
	 * @param identify �ֶ���
	 * @param identifyValue �ֶ�ֵ
	 * @return
	 */
	public int delete(String tableName, String identify, Object identifyValue);
	
	/**
	 * ʹ��ָ����sql �������ݱ��¼
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:20:49
	 * @since 
	 * @param sql ָ����Sql���
	 * @return ��Ӱ�������
	 */
	public int update(String sql);
	
	/**
	 * 
	 * ������ʹ��ָ����sql �������ݱ��¼
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:22:44
	 * @since 
	 * @param sql
	 * @param args ��Ҫ�޸ĵĲ���ֵ
	 * @return
	 */
	public int update(String sql, Object[] args);
	
	/**
	 * ִ����������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:26:01
	 * @since 
	 * @param sqlArray  ����������Ҫ��sql��������
	 * @return
	 */
	public int[] batchUpdate(String[] sqlArray);
	
	/**
	 * ִ����������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:27:24
	 * @since 
	 * @param sql ָ����Sql���
	 * @param args ��Ҫ���µĲ�������
	 * @return
	 */
	public int[] batchUpdate(String sql, Object[][] args);
	
	/**
	 * ��ѯһ�����ͽ��
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:29:30
	 * @since 
	 * @param sql ָ����sql���
	 * @return
	 */
	public int queryInt(String sql);
	
	/**
	 * ��ѯһ�����ͽ����
	 *
	 * @param sql  SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶε�����ֵ��
	 */
	public int queryInt(String sql, Object[] args);
	
	/**
	 * ����һ����������
	 *
	 * @param sql SQL���
	 * @return ��ѯ�Ķ�����¼��һ���ֶε�����ֵ��
	 */
	public int[] queryIntArray(String sql);
	
	/**
	 * ����һ����������
	 *
	 * @param sql  SQL���
	 * @param args �����е�ֵ*
	 * @return ��ѯ�Ķ�����¼��һ���ֶε�����ֵ��
	 */
	public int[] queryIntArray(String sql, Object[] args);
	
	/**
	 * ��ѯһ������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:31:05
	 * @since 
	 * @param sql ָ����sql���
	 * @return
	 */
	public long queryLong(String sql);
	
	/**
	 * ��ѯһ�����������ݽ��
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:32:12
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public long queryLong(String sql, Object[] args);
	
	/**
	 * ��ѯһ����������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:34:00
	 * @since 
	 * @param sql
	 * @return
	 */
	public long[] queryLongArray(String sql);
	
	/**
	 * ��ѯһ����������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:34:52
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public long[] queryLongArray(String sql, Object[] args);
	
	/**
	 * ��ѯһ��˫���ȸ���������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:38:25
	 * @since 
	 * @param sql
	 * @return
	 */
	public double queryDouble(String sql);
	
	/**
	 * ��ѯһ��˫���ȸ���������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:38:55
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public double queryDouble(String sql, Object[] args);
	
	/**
	 * ��ѯһ��˫���ȸ�������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:38:55
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public double[] queryDoubleArray(String sql);
	
	/**
	 * ��ѯһ��˫���ȸ�������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:38:55
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public double[] queryDoubleArray(String sql, Object[] args);
	
	/**
	 * ��ѯһ������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:41:55
	 * @since 
	 * @param sql
	 * @return
	 */
	public float queryFloat(String sql);
	
	/**
	 * ��ѯһ������������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:42:08
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public float queryFloat(String sql, Object[] args);
	
	/**
	 * ��������ѯһ����������������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:44:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public float[] queryFloatArray(String sql);
	
	/**
	 * ��������ѯһ����������������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:44:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public float[] queryFloatArray(String sql, Object[] args);
	
	/**
	 * ��ѯһ���ַ������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String queryString(String sql);
	
	/**
	 * ��ѯһ���ַ������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String queryString(String sql, Object[] args);
	
	/**
	 * ��ѯһ���ַ����������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String[] queryStringArray(String sql);
	
	/**
	 * ��ѯһ���ַ����������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String[] queryStringArray(String sql, Object[] args);
	
	/**
	 * ��ѯһ�����ݱ��¼��ʹ��DataList��װ����
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:50:47
	 * @since 
	 * @param sql
	 * @return
	 */
	public DataList queryMap(String sql);
	
	/**
	 * ��ѯһ�����ݱ��¼��ʹ��DataList��װ����
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����9:50:47
	 * @since 
	 * @param sql
	 * @return
	 */
	public DataList queryMap(String sql, Object[] args);
	
	/**
	 * ��ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 *
	 * @param sql      SQL���
	 * @param args     �����е�ֵ
	 * @param startRow ��ʼ������
	 * @param rows     ��¼������
	 * @return ��ѯ���н������
	 */
	public List query(String sql);
	
	/**
	 * ��ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 *
	 * @param sql  SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ���н����
	 */
	public List query(String sql, Object[] args);
	
	/**
	 * ��ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 *
	 * @param sql  SQL���
	 * @param rows ���صļ�¼����
	 * @return ��ѯ�̶��ļ�¼��
	 */
	public List query(String sql, int rows);
	
	/**
	 * ��ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 *
	 * @param sql  SQL���
	 * @param args �����е�ֵ
	 * @param rows ���صļ�¼����*
	 * @return ��ѯ�̶��ļ�¼��
	 */
	public List query(String sql, Object[] args, int rows);
	
	/**
	 * ��ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 *
	 * @param sql      SQL���
	 * @param args     �����е�ֵ
	 * @param startRow ��ʼ������
	 * @param rows     ��¼������
	 * @return ��ѯ���н������
	 */
	public List query(String sql, int startRow, int rows);
	
	/**
	 * ��ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 *
	 * @param sql      SQL���
	 * @param args     �����е�ֵ
	 * @param startRow ��ʼ������
	 * @param rows     ��¼������
	 * @return ��ѯ���н������
	 */
	public List query(String sql, Object[] args, int startRow, int rows);
	
	/**
	 * ��ѯһ����ҳ�б��������ص����ݵ�ÿһ����������ΪDataRow
	 *
	 * @param sql        SQL���
	 * @param args       �����е�ֵ
	 * @param curPage    ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ�ļ�¼��
	 * @return ��ҳ����
	 */
	public DataPage queryPage(String sql, int curPage, int numPerPage);
	
	/**
	 * ��ѯһ����ҳ�б��������ص����ݵ�ÿһ����������ΪDataRow
	 *
	 * @param sql        SQL���
	 * @param args       �����е�ֵ
	 * @param curPage    ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ�ļ�¼��
	 * @return ��ҳ����
	 */
	public DataPage queryPage(String sql, Object[] args, int curPage, int numPerPage);
	
	/**
	 * ����ʼ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:08:22
	 * @since
	 */
	public void beginTrans();
	
	/**
	 * �����ύ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:08:16
	 * @since
	 */
	public void commitTrans();
	
	/**
	 * ����ع�
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:08:08
	 * @since
	 */
	public void rollbackTrans();
	
	/**
	 * �Ự�ر�
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:08:01
	 * @since
	 */
	public void close();
	
	/**
	 * ��ȡ����IDֵ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:07:11
	 * @since 
	 * @return
	 */
	public String getGeneratedKeys();
}