/**
 * 
 */
package com.iesports.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * �������ļ���ȡ�ʹ����ͨ�ù�����
 * 
 * @author xiongdun
 * @created 2016��9��1�� ����10:09:19
 * @since
 */
public class FileHelper {

	private static Logger logger = Logger.getLogger(FileHelper.class);

	private static final int KB_1 = 1024;

	/**
	 * ���������ļ����ݻ�ȡ�ļ���CRCCodeֵ
	 * @author xiongdun
	 * @created 2016��9��1�� ����4:28:41
	 * @since
	 * @param file �ļ�
	 * @return
	 * @throws Exception �ļ�����ȡ�쳣
	 */
	public static String getFileCRCCode(File file) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(file);
		CRC32 crc32 = new CRC32();
		CheckedInputStream checkedInputStream = new CheckedInputStream(
				fileInputStream, crc32);
		byte[] buffer = new byte[KB_1];
		while (checkedInputStream.read(buffer) != -1);
		fileInputStream.close();
		buffer = null;
		return Long.toHexString(crc32.getValue());
	}
	
	/**
	 * ��������ȡstr�ַ���CRCCodeֵ
	 * @author xiongdun
	 * @created 2016��9��1�� ����4:39:50
	 * @since 
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static String getStringCRCCode(String str) throws Exception {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes());
		CRC32 crc32 = new CRC32();
		CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, crc32);
		byte[] buffer = new byte[KB_1];
		while(checkedInputStream.read(buffer) != -1);
		inputStream.close();
		buffer = null;
		return Long.toHexString(crc32.getValue());
	}

	/**
	 * ���������ָ�����ļ�Ŀ¼
	 * @author xiongdun
	 * @created 2016��9��1�� ����4:48:11
	 * @since 
	 * @param directory
	 * @return
	 */
	public static boolean cleanDirectory(File directory) {
		try {
			FileUtils.cleanDirectory(directory);
			return true;
		} catch (IOException e) {
			logger.error("���ָ���ļ�Ŀ¼����", e);
		}
		return false;
	}
	
	/**
	 * �������˴��������˲�������common.io�����п���ֱ����
	 * @author xiongdun
	 * @created 2016��9��1�� ����4:37:47
	 * @since 
	 * @param basePath
	 * @param fullFilenameToAdd
	 * @return
	 */
	public static String concat(String basePath, String fullFilenameToAdd) {
		return FilenameUtils.concat(basePath, fullFilenameToAdd);
	}
	public static boolean copyDirectory(File srcDir, File destDir) {
		return copyDirectory(srcDir, destDir, true);
	}

	public static boolean copyDirectory(String srcDir, String destDir) {
		return copyDirectory(new File(srcDir), new File(destDir));
	}

	public static boolean copyDirectory(File srcDir, File destDir,
			boolean preserveFileDate) {
		try {
			FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
			return true;
		} catch (IOException ex) {
			logger.error("����Ŀ¼����", ex);
		}
		return false;
	}

	public static boolean copyDirectoryToDirectory(File srcDir, File destDir) {
		try {
			FileUtils.copyDirectoryToDirectory(srcDir, destDir);
			return true;
		} catch (IOException ex) {
			logger.error("����Ŀ¼����", ex);
		}
		return false;
	}

	public static boolean copyDirectoryToDirectory(String srcDir, String destDir) {
		return copyDirectoryToDirectory(new File(srcDir), new File(destDir));
	}

	public static boolean copyFile(File srcFile, File destFile) {
		return copyFile(srcFile, destFile, true);
	}

	public static boolean copyFile(String srcFile, String destFile) {
		return copyFile(new File(srcFile), new File(destFile));
	}

	public static boolean copyFile(File srcFile, File destFile,
			boolean preserveFileDate) {
		try {
			FileUtils.copyFile(srcFile, destFile, preserveFileDate);
			return true;
		} catch (IOException ex) {
			logger.error("�����ļ�����", ex);
		}
		return false;
	}

	public static boolean copyFileToDirectory(File srcFile, File destDir) {
		try {
			FileUtils.copyFileToDirectory(srcFile, destDir);
			return true;
		} catch (IOException ex) {
			logger.error("�����ļ�����", ex);
		}
		return false;
	}

	public static boolean copyFileToDirectory(String srcFile, String destDir) {
		return copyFileToDirectory(new File(srcFile), new File(destDir));
	}

	public static boolean deleteDirectory(String directory) {
		try {
			FileUtils.deleteDirectory(new File(directory));
			return true;
		} catch (IOException ex) {
			logger.error("ɾ��Ŀ¼����", ex);
		}
		return false;
	}

	public static boolean deleteFile(String file) {
		try {
			FileUtils.forceDelete(new File(file));
			return true;
		} catch (IOException ex) {
			logger.error("ɾ���ļ�����", ex);
		}
		return false;
	}

	public static boolean createDirectory(String directory) {
		try {
			FileUtils.forceMkdir(new File(directory));
			return true;
		} catch (IOException ex) {
			logger.error("����Ŀ¼����", ex);
		}
		return false;
	}

	public static byte[] readFileToByteArray(String file) {
		try {
			byte[] bytes = FileUtils.readFileToByteArray(new File(file));
			return bytes;
		} catch (IOException ex) {
			logger.error("��ȡ�ļ�����", ex);
		}
		return null;
	}

	public static String readFileToString(String file, String encoding) {
		try {
			if (StringUtil.isEmpty(encoding)) {
				encoding = "GBK";
			}
			String content = FileUtils.readFileToString(new File(file),
					encoding);
			return content;
		} catch (IOException ex) {
			logger.error("��ȡ�ļ�����", ex);
		}
		return "";
	}

	public static String readFileToString(String file) {
		return readFileToString(file, "GBK");
	}

	public static List readLines(String file) {
		return readLines(file, "GBK");
	}

	public static List readLines(String file, String encoding) {
		try {
			if (StringUtil.isEmpty(encoding)) {
				encoding = "GBK";
			}
			List lineList = FileUtils.readLines(new File(file), encoding);
			return lineList;
		} catch (IOException ex) {
			logger.error("��ȡ�ļ�����", ex);
		}
		return null;
	}

	public static List readLines(URL url, String encoding) {
		if (url == null) {
			throw new RuntimeException("��ȡ�ļ����ݵ�urlΪ��");
		}
		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
			List lineList = IOUtils.readLines(inputStream, encoding);
			//�����ﶨ��һ��List���������ļ�������
			List localList = lineList;
			return localList;
		} catch (Exception e) {
			logger.error("��ȡ�ļ�����", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e2) {
					logger.error("�ļ���" + url +"�ر��쳣��", e2);
				}
			}
		}
		return null;
	}
	
	public static List readLines(URL url) {
		return readLines(url, "GBK");
	}
	
	public static long sizeOfDirectory(String directory) {
		return FileUtils.sizeOfDirectory(new File(directory));
	}
	
	public static boolean writeToFile(String file, byte[] data) {
		try {
			FileUtils.writeByteArrayToFile(new File(file), data);
			return true;
		} catch (IOException ex) {
			logger.error("д�ļ�����", ex);
		}
		return false;
	}

	public static boolean writeToFile(String file, String data) {
		return writeToFile(file, data, "GBK");
	}

	public static boolean writeToFile(String file, String data, String encoding) {
		try {
			if ((encoding == null) || ("".equals(encoding))) {
				encoding = "GBK";
			}
			FileUtils.writeStringToFile(new File(file), data, encoding);
			return true;
		} catch (IOException ex) {
			logger.error("д�ļ�����", ex);
		}
		return false;
	}

	public static void writeToResponse(HttpServletResponse response,
			String filePath) {
		writeToResponse(response, filePath, 1024);
	}

	public static void writeToResponse(HttpServletResponse response,
			String filePath, int byteLength) {
		try {
			byte[] buffer = new byte[byteLength];
			BufferedOutputStream output = null;
			BufferedInputStream input = null;
			try {
				output = new BufferedOutputStream(response.getOutputStream());
				input = new BufferedInputStream(new FileInputStream(new File(
						filePath)));
				int n = -1;
				while ((n = input.read(buffer, 0, 4096)) > -1) {
					output.write(buffer, 0, n);
				}
				response.flushBuffer();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				if (input != null)
					input.close();
				if (output != null)
					output.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static boolean createNewFile(String filePathName) {
		String filePath = FilenameUtils.getFullPath(filePathName);

		if ((!(exists(filePath))) && (!(createDirectory(filePath)))) {
			return false;
		}

		try {
			File file = new File(filePathName);
			return file.createNewFile();
		} catch (IOException ex) {
			logger.error("�����ļ�����", ex);
		}
		return false;
	}

	/**
	 * �������ж��ļ��Ƿ����
	 * @author xiongdun
	 * @created 2016��9��1�� ����5:18:36
	 * @since 
	 * @param filePath
	 * @return
	 */
	public static boolean exists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * �������ж��Ƿ�Ϊһ���ļ�
	 * @author xiongdun
	 * @created 2016��9��1�� ����5:18:26
	 * @since 
	 * @param filePath
	 * @return
	 */
	public static boolean isFile(String filePath) {
		File file = new File(filePath);
		return file.isFile();
	}

	/**
	 * �������ж��Ƿ�Ϊһ��Ŀ¼
	 * @author xiongdun
	 * @created 2016��9��1�� ����5:18:16
	 * @since 
	 * @param filePath
	 * @return
	 */
	public static boolean isDirectory(String filePath) {
		File file = new File(filePath);
		return file.isDirectory();
	}

	/**
	 * ���������ļ�������
	 * @author xiongdun
	 * @created 2016��9��1�� ����5:18:05
	 * @since 
	 * @param srcFile
	 * @param destFile
	 * @return
	 */
	public static boolean renameTo(String srcFile, String destFile) {
		File file = new File(srcFile);
		return file.renameTo(new File(destFile));
	}
	
	/**
	 * ���������ļ�����д�뵽XML�ļ���
	 * @author xiongdun
	 * @created 2016��9��1�� ����5:21:17
	 * @since 
	 * @param filename �ļ���
	 * @param document xml �ļ�
	 * @param encoding �����ʽ
	 * @return
	 */
	public static boolean writeToXMLFile(String filename, Document document, String encoding) {
		createNewFile(filename);
		boolean success = false;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);//�����ļ������ʽ
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream(new File(filename)), format);
			writer.write(document);
			writer.flush();
			success = true;
		} catch (Exception e) {
			logger.error("�ļ�д�����", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					logger.error("Convert code Error:" + e.getMessage(), e);
				}
			}
		}
		return success;
	}
	
	/**
	 * ������������ܵ���������ļ�
	 * @author xiongdun
	 * @created 2016��9��1�� ����5:37:26
	 * @since 
	 * @param clazz �����
	 * @param propFile
	 * @return
	 */
	public static File guessPropFile(Class clazz, String propFile) {
		
		File file = null;
		try {
			ClassLoader loader = clazz.getClassLoader();
			URL url = loader.getResource(propFile);
			if (url != null) {
				file = new File(url.getPath());
				if (file != null && file.isFile() && file.exists()) {
					return file;
				}
			}
			
			Package pkg = clazz.getPackage();
			if (pkg != null) {
				String packageName = pkg.getName();
				String path = "";
				if (packageName.indexOf(".") < 0) {
					path = packageName + "/";
				} else {
					int start = 0;
					int end = 0;
					end = packageName.indexOf(".");
					while (end != -1) {
						path = path + packageName.substring(start, end) + "/";
						start = end + 1;
						end = packageName.indexOf(".", start);
					}
					path = path + packageName.substring(start) + "/";
				}
				url = loader.getResource(path + propFile);
				if (url != null) {
					file = new File(url.getPath());
					if ((file != null) && (file.exists()) && (file.isFile())) {
						return file;
					}
				}
			}
			String curDir = System.getProperty("user.dir");
			file = new File(curDir, propFile);
			if ((file != null) && (file.exists()) && (file.isFile())) {
				return file;
			}

			String classpath = System.getProperty("java.class.path");
			String[] cps = classpath
					.split(System.getProperty("path.separator"));
			for (int i = 0; i < cps.length; ++i) {
				file = new File(cps[i], propFile);
				if ((file != null) && (file.exists()) && (file.isFile())) {
					break;
				}

				file = null;
			}
			
			
		} catch (Exception e) {
			logger.error("��ȡ������ļ�Ϊ�ҵ�  ����null", e);
			file = null;
		}
		
		return file;
	}

	/**
	 * �������ж�·���Ƿ�ΪĿ¼�������򴴽�
	 * @author xiongdun
	 * @created 2016��11��28�� ����11:13:16
	 * @since 
	 * @param dirName
	 * @return
	 */
	public static File generateDirectory(String dirName) {
		if (StringUtil.isEmpty(dirName)) {
			return null;
		}
		File file = new File(dirName);
		if (file.isDirectory() && !file.exists()) {
			file.mkdir();
		}
		return file;
	}
	
}
