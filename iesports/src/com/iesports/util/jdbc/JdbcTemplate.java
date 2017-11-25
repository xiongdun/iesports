/**
 * 
 */
package com.iesports.util.jdbc;

import java.util.List;

import org.apache.log4j.Logger;

import com.iesports.util.DataList;
import com.iesports.util.DataPage;
import com.iesports.util.StringUtil;
import com.iesports.util.jdbc.session.Session;
import com.iesports.util.jdbc.session.SessionFactory;

/**
 * ������jdbc����ģ��
 * @author xiongdun
 * @created 2016��9��7�� ����8:38:39
 * @since 
 */
public class JdbcTemplate {
	
	private static Logger logger = Logger.getLogger(JdbcTemplate.class);
	
	private String datasourceId = "";
	private String generatedkeys = "";
	
	/**
	 * �������ݿ��������
	 */
	public JdbcTemplate() {
		
	}
	
	public JdbcTemplate(String datasourceId) {
		this.datasourceId = datasourceId;
	}
	
	/**
	 * ��������ȡsessionIdֵ
	 * @author xiongdun
	 * @created 2016��9��13�� ����8:34:34
	 * @since 
	 * @return ����Դ���Ӷ���
	 */
	private Session getSession() {
		if (StringUtil.isEmpty(datasourceId)) {
			return SessionFactory.getSession();
		} else {
			return SessionFactory.getSession(datasourceId);
		}
	}
	
	/**
	 * �������������ݿ��¼
	 * @author xiongdun
	 * @created 2016��9��13�� ����8:34:58
	 * @since 
	 * @param tableName ���ݿ����
	 * @param data ����ļ�¼ֵ
	 * @return ��Ӱ�������
	 */
	public int insert(String tableName, DataList data) {
		Session session = null;
		try {
			session = getSession();
			int result = session.insert(tableName, data);
			generatedkeys = session.getGeneratedKeys();
			return result;
		} catch (Exception e) {
			logger.error("���ݲ���ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}

	/**
	 * ������ɾ�����ݿ��¼
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:05:23
	 * @since 
	 * @param tableName ���ݿ����
	 * @param identify ��Ҫɾ�����ֶ���
	 * @param identifyValue ��Ҫɾ�����ֶ�ֵ
	 * @return
	 */
	public int delete(String tableName, String identify, Object identifyValue) {
		Session session = null;
		try {
			session = getSession();
			int result = session.delete(tableName, identify, identifyValue);
			generatedkeys = session.getGeneratedKeys();
			return result;
		} catch (Exception e) {
			logger.error("����ɾ��ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ����������һ�����¼
	 * @author xiongdun
	 * @created 2016��9��13�� ����2:16:05
	 * @since 
	 * @param tableName ����
	 * @param data ���µ�����
	 * @param identify �ֶ���
	 * @param identifyValue �ֶ�ֵ
	 * @return
	 */
	public int update(String tableName, DataList data, String identify, String identifyValue) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(tableName, data, identify, identifyValue);
			generatedkeys = session.getGeneratedKeys();
			return result;
		} catch (Exception e) {
			logger.error("ִ�����ݿ���²���ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ����������һ�����¼
	 * @author xiongdun
	 * @created 2016��9��13�� ����2:17:56
	 * @since 
	 * @param tableName ���� 
	 * @param data ���µ�����
	 * @param identifies �ֶ���
	 * @param identifiesValue �ֶ�ֵ
	 * @return
	 */
	public int update(String tableName, DataList data, String[] identifies, String[] identifiesValue) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(tableName, data, identifies, identifiesValue);
			return result;
		} catch (Exception e) {
			logger.error("ִ�����ݿ���²���ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ������	ʹ��ָ����sql�����±��¼����������Ӱ�������
	 * @author xiongdun
	 * @created 2016��9��13�� ����2:19:46
	 * @since 
	 * @param sql ָ����sql���
	 * @return ��Ӱ�������
	 */
	public int update(String sql) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(sql);
			return result;
		} catch (Exception e) {
			logger.error("ִ�����ݿ���²���ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ������ʹ��ָ����sql�����±��¼����������Ӱ�������
	 * @author xiongdun
	 * @created 2016��9��13�� ����2:20:59
	 * @since 
	 * @param sql ָ����sql���
	 * @param args ��Ҫ���µĲ���ֵ
	 * @return ��Ӱ�������
	 */
	public int udpate(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("ִ�����ݿ���²���ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ����������ִ�и��²�����ÿ�εĸ��¼�¼��
	 * @author xiongdun
	 * @created 2016��9��13�� ����2:25:00
	 * @since 
	 * @param sqlArray SQL�������
	 * @return ÿ��ִ�и��µļ�¼������
	 */
	public int[] batchUpdate(String[] sqlArray) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.batchUpdate(sqlArray);
			return result;
		} catch (Exception e) {
			logger.error("���ݿ�����������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ����������ִ�и��²�����ÿ�εĸ��¼�¼��
	 * @author xiongdun
	 * @created 2016��9��13�� ����4:50:03
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ÿ��ִ�и��µļ�¼������
	 */
	public int[] batchUpdate(String sql, Object[][] args) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.batchUpdate(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("���ݿ�����������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�����ͽ��
	 * @author xiongdun
	 * @created 2016��9��13�� ����4:50:52
	 * @since 
	 * @param sql SQL���
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶε�����ֵ��
	 */
	public int queryInt(String sql) {
		Session session = null;
		try {
			session = getSession();
			int result = session.queryInt(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ��������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ��������ѯһ�����ͽ��
	 * @author xiongdun
	 * @created 2016��9��13�� ����4:51:54
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶε�����ֵ��
	 */
	public int queryInt(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			int result = session.queryInt(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ��������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ��������ѯһ����������
	 * @author xiongdun
	 * @created 2016��9��13�� ����4:52:31
	 * @since 
	 * @param sql SQL���
	 * @return ��ѯ�Ķ�����¼��һ���ֶε�����ֵ��
	 */
	public int[] queryIntArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.queryIntArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ������������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ����������
	 * @author xiongdun
	 * @created 2016��9��13�� ����4:53:19
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ*
	 * @return ��ѯ�Ķ�����¼��һ���ֶε�����ֵ��
	 */
	public int[] queryIntArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.queryIntArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ������������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������ͽ����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:57
	 * @since 
	 * @param sql SQL���
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶεĳ�����ֵ��
	 */
	public long queryLong(String sql) {
		Session session = null;
		try {
			session = getSession();
			long result = session.queryLong(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ����������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ��������ѯһ�������ͽ����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:53
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶεĳ�����ֵ��
	 */
	public long queryLong(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			long result = session.queryLong(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ����������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ����������һ������������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:48
	 * @since 
	 * @param sql SQL���
	 * @return  ��ѯ�Ķ�����¼��һ���ֶεĳ�����ֵ��
	 */
	public long[] queryLongArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			long[] result = session.queryLongArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ��������������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ����������һ������������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:44
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ*
	 * @return ��ѯ�Ķ�����¼��һ���ֶεĳ�����ֵ��
	 */
	public long[] queryLongArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			long[] result = session.queryLongArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯ��������������ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯ˫���ȸ���������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:41
	 * @since 
	 * @param sql SQL���
	 * @return double����
	 */
	@Deprecated
	public double queryDouble(String sql) {
		return 0;
	}
	
	/**
	 * ��������ѯ˫���ȸ���������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:35
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return double����
	 */
	@Deprecated
	public double queryDouble(String sql, Object[] args) {
		return 0;
	}
	
	/**
	 * ��������ѯdouble���͵�����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:28
	 * @since 
	 * @param sql SQL���
	 * @return double��������
	 */
	public double[] queryDoubleArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			double[] result = session.queryDoubleArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯdouble����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯdouble���͵�����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:28
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return double��������
	 */
	public double[] queryDoubleArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			double[] result = session.queryDoubleArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯdouble����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯfloat��������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:21
	 * @since 
	 * @param sql SQL���
	 * @return float���͵�����
	 */
	public float queryFloat(String sql) {
		Session session = null;
		try {
			session = getSession();
			float result = session.queryFloat(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯfloat����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ��������ѯfloat��������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:17
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return float���͵�ֵ
	 */
	public float queryFloat(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			float result = session.queryFloat(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯfloat����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * ������float�������ݵ�����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:13
	 * @since 
	 * @param sql SQL���
	 * @return float���͵���������
	 */
	public float[] queryFloatArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			float[] result = session.queryFloatArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯfloat����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ������float�������ݵ�����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:09
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return float���͵���������
	 */
	public float[] queryFloatArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			float[] result = session.queryFloatArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯfloat����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯ�ַ���ֵ
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:05
	 * @since 
	 * @param sql SQL���
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶε��ַ���ֵ��
	 */
	public String queryString(String sql) {
		Session session = null;
		try {
			session = getSession();
			String result = session.queryString(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯString����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ���ַ��������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:17:01
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ�ĵ�һ�еĵ�һ���ֶε��ַ���ֵ��
	 */
	public String queryString(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			String result = session.queryString(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯString����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ����������һ���ַ�������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:16:56
	 * @since  
	 * @param sql SQL���
	 * @return ��ѯ�Ķ�����¼��һ���ֶε��ַ���ֵ��
	 */
	public String[] queryStringArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			String[] result = session.queryStringArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯString����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ����������һ���ַ�������
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:16:52
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ�Ķ�����¼��һ���ֶε��ַ���ֵ��
	 */
	public String[] queryStringArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			String[] result = session.queryStringArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯString����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ����¼����������ΪDataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:16:33
	 * @since 
	 * @param sql SQL���
	 * @return ��ѯ�ĵ�һ�еĽ��,���ؽ���е��ֶ�����ΪСд
	 */
	public DataList queryMap(String sql) {
		Session session = null;
		try {
			session = getSession();
			DataList result = session.queryMap(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ����¼����������ΪDataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:16:00
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ�ĵ�һ�еĽ��,���ؽ���е��ֶ�����ΪСд��
	 */
	public DataList queryMap(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			DataList result = session.queryMap(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:15:28
	 * @since 
	 * @param sql SQL���
	 * @return ��ѯ���н���б�
	 */
	public List query(String sql) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:13:17
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @return ��ѯ���н����
	 */
	public List query(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:12:50
	 * @since 
	 * @param sql SQL���
	 * @param rows ���صļ�¼����
	 * @return ��ѯ�̶��ļ�¼��
	 */
	public List query(String sql, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, rows);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:12:20
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @param rows ���صļ�¼����*
	 * @return ��ѯ�̶��ļ�¼��
	 */
	public List query(String sql, Object[] args, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, args, rows);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:11:37
	 * @since 
	 * @param sql SQL���
	 * @param startRows ��ʼ������
	 * @param rows ��¼������
	 * @return ��ѯ���н������
	 */
	public List query(String sql, int startRows, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, startRows, rows);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ�������б���,�б��е�ÿһ��Ϊһ��DataRow��
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:10:52
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @param startRows ��ʼ������
	 * @param rows ��¼������
	 * @return ��ѯ���н����
	 */
	public List query(String sql, Object[] args, int startRows, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, args, startRows, rows);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataList����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ����ҳ�б�����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:10:13
	 * @since 
	 * @param sql SQL���
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ�ļ�¼��
	 * @return ��ҳ����
	 */
	public DataPage queryPage(String sql, int curPage, int numPerPage) {
		Session session = null;
		try {
			session = getSession();
			DataPage result = session.queryPage(sql, curPage, numPerPage);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataPage����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * ��������ѯһ����ҳ�б�����
	 * @author xiongdun
	 * @created 2016��9��13�� ����9:09:08
	 * @since 
	 * @param sql SQL���
	 * @param args �����е�ֵ
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ�ļ�¼��
	 * @return ��ҳ����
	 */
	public DataPage queryPage(String sql, Object[] args, int curPage, int numPerPage) {
		Session session = null;
		try {
			session = getSession();
			DataPage result = session.queryPage(sql, args, curPage, numPerPage);
			return result;
		} catch (Exception e) {
			logger.error("��ѯDataPage����ʧ�ܣ�", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	
	/**
	 * ��������ȡ����Դid
	 * @author xiongdun
	 * @created 2016��9��13�� ����8:33:12
	 * @since 
	 * @return
	 */
	private String getDatasourceId() {
		return datasourceId;
	}

	/**
	 * �������������ݾ�ԴId
	 * @author xiongdun
	 * @created 2016��9��13�� ����8:33:29
	 * @since 
	 * @param datasourceId
	 */
	private void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	/**
	 * ��������ȡ��������
	 * @author xiongdun
	 * @created 2016��9��13�� ����8:32:08
	 * @since 
	 * @return
	 */
	public String getGeneratedkeys() {
		return generatedkeys;
	}

	/**
	 * ������������������
	 * @author xiongdun
	 * @created 2016��9��13�� ����8:32:23
	 * @since 
	 * @param generatedkeys
	 */
	private void setGeneratedkeys(String generatedkeys) {
		this.generatedkeys = generatedkeys;
	}
	
}
