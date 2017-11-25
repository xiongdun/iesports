/**
 * 
 */
package com.iesports.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Properties;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * ������Xml�ļ���ȡ�ʹ���Ĺ�����
 * 
 * @author xiongdun
 * @created 2016��9��1�� ����10:09:19
 * @since
 */
public class XMLHelper {

	private static Logger logger = Logger.getLogger(XMLHelper.class);

	/**
	 * ������xml תΪ xsl�ļ�
	 * @author xiongdun
	 * @created 2016��9��27�� ����10:20:26
	 * @since 
	 * @param xml xml �ļ�����
	 * @param xsl xsl �ļ���ַ
	 * @return
	 * @throws Exception
	 */
	public static String xml2xsl(String xml, URL xsl) throws Exception {
		if (StringUtil.isEmpty(xml)) {
			throw new Exception("xml string is empty");
		}
		if (xsl == null) {
			throw new Exception("xsl string is empty");
		}

		StringWriter writer = new StringWriter();
		Source xmlSource = null;
		Source xslSource = null;
		Result result = null;
		String str = null;
		try {
			xmlSource = new StreamSource(new StringReader(xml));
			xslSource = new StreamSource(xsl.openStream());
			result = new StreamResult(writer);

			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xslSource);
			trans.transform(xmlSource, result);
			str = writer.toString();

			return str;
		} catch (Exception ex) {
		} finally {
			writer.close();
			writer = null;
			xmlSource = null;
			xslSource = null;
			result = null;
		}
		return str;
	}

	/**
	 * ������xml תΪ xsl�ļ�
	 * @author xiongdun
	 * @created 2016��9��27�� ����10:20:04
	 * @since 
	 * @param xmlFilePath
	 * @param xsl
	 * @return
	 * @throws Exception
	 */
	public static String xml2xsl(String xmlFilePath, String xsl)
			throws Exception {
		if (StringUtil.isEmpty(xmlFilePath)) {
			throw new Exception("xml string is empty");
		}
		if (StringUtil.isEmpty(xsl)) {
			throw new Exception("xsl string is empty");
		}

		String str = null;
		StringWriter writer = new StringWriter();
		Source xmlSource = new StreamSource(new File(xmlFilePath));
		Source xslSource = new StreamSource(new File(xsl));
		Result result = new StreamResult(writer);
		try {
			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xslSource);
			Properties properties = trans.getOutputProperties();
			properties.setProperty("encoding", "gb2312");
			properties.put("method", "html");
			trans.setOutputProperties(properties);

			trans.transform(xmlSource, result);
			str = writer.toString();
			return str;
		} catch (Exception ex) {
		} finally {
			writer.close();
			writer = null;

			xmlSource = null;
			xslSource = null;
			result = null;
		}
		return str;
	}
	
	/**
	 * ���ļ������ļ�·����ȡ�ļ����� ������
	 * 
	 * @author xiongdun
	 * @created 2016��9��1�� ����3:52:53
	 * @since
	 * @return
	 * @throws Exception
	 */
	public static Document getDocument(String filename) throws Exception {
		if (StringUtil.isEmpty(filename)) {
			logger.error("�ļ���Ϊ�գ�");
			return null;
		}
		File file = null;
		SAXReader saxReader = new SAXReader();
		file = new File(filename);
		Document document = saxReader.read(file);
		return document;
	}

	/**
	 * ������ֱ�Ӷ�ȡ�ļ�����
	 * @author xiongdun
	 * @created 2016��9��1�� ����4:09:35
	 * @since 
	 * @param file
	 * @return
	 */
	public static Document getDocument(File file) {
		try {
			Document document = null;
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(file);
			return document;
		} catch (Exception ex) {
			logger.error("��ȡxml�����ļ������ļ������ڻ��𻵣�", ex);
		}
		return null;
	}
	
	/**
	 * ������ָ�������࣬��ȡ�ļ�����
	 * @author xiongdun
	 * @created 2016��9��1�� ����4:12:49
	 * @since 
	 * @param clazz
	 * @param xmlStr
	 * @return
	 */
	public static Document getDocument(Class clazz, String xmlStr) {
		if (StringUtil.isEmpty(xmlStr)) {
			return null;
		}
		Document document = null;
		File file = FileHelper.guessPropFile(clazz, xmlStr);
		if ((file != null) && (file.exists()) && (file.isFile())) {
			document = getDocument(file);
		} else {
			InputStream is = null;
			try {
				ClassLoader loader = clazz.getClassLoader();
				if (loader != null) {
					is = loader.getResourceAsStream(xmlStr);
				}
				if (is != null) {
					SAXReader saxReader = new SAXReader();
					document = saxReader.read(file);
				}
				
			} catch (Exception e) {
				logger.error("��ȡXML�ļ�����", e);
			} finally {
				if (is != null) {
					try {
						is.close();
						is = null;
					} catch (IOException e) {
						logger.error("�ر��ļ�����", e);
					}
				}
			}
		}
		return document;
	}
	
	/**
	 * ��������String�ַ����ж�ȡ�ļ�
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:07:44
	 * @since 
	 * @param xmlString
	 * @return
	 */
	public static Document getDocumentFromString(String xmlString) {
		if (StringUtil.isEmpty(xmlString))
			return null;
		try {
			SAXReader saxReader = new SAXReader();
			return saxReader.read(new StringReader(xmlString));
		} catch (Exception ex) {
			logger.error("����xml�ַ�����������null", ex);
		}
		return null;
	}

	/**
	 * 
	 * ������xml�ļ�תΪhtml�ļ�
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:12:22
	 * @since 
	 * @param xmlDoc
	 * @param xslFile
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String xml2html(String xmlDoc, String xslFile, String encoding)
			throws Exception {
		if (StringUtil.isEmpty(xmlDoc)) {
			throw new Exception("xml string is empty");
		}
		if (StringUtil.isEmpty(xslFile)) {
			throw new Exception("xslt file is empty");
		}

		StringWriter writer = new StringWriter();
		Source xmlSource = null;
		Source xslSource = null;
		Result result = null;
		String html = null;
		try {
			xmlSource = new StreamSource(new StringReader(xmlDoc));
			xslSource = new StreamSource(new File(xslFile));

			result = new StreamResult(writer);

			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xslSource);
			Properties properties = trans.getOutputProperties();
			properties.put("method", "html");
			properties.setProperty("encoding", encoding);
			trans.setOutputProperties(properties);

			trans.transform(xmlSource, result);

			html = writer.toString();
			writer.close();

			String str1 = html;

			return str1;
		} catch (Exception ex) {
		} finally {
			writer = null;

			xmlSource = null;
			xslSource = null;
			result = null;
		}
		return html;
	}

	/**
	 * ������xmlFile תΪHtml
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:14:31
	 * @since 
	 * @param xmlFile
	 * @param xslFile
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String xmlFile2html(String xmlFile, String xslFile,
			String encoding) throws Exception {
		if (StringUtil.isEmpty(xmlFile)) {
			throw new Exception("xml string is empty");
		}
		if (StringUtil.isEmpty(xslFile)) {
			throw new Exception("xslt file is empty");
		}

		StringWriter writer = new StringWriter();
		Source xmlSource = null;
		Source xslSource = null;
		Result result = null;
		String html = null;
		try {
			xmlSource = new StreamSource(new File(xmlFile));
			xslSource = new StreamSource(new File(xslFile));

			result = new StreamResult(writer);

			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xslSource);
			Properties properties = trans.getOutputProperties();
			properties.put("method", "html");
			properties.setProperty("encoding", encoding);
			trans.setOutputProperties(properties);

			trans.transform(xmlSource, result);

			html = writer.toString();
			writer.close();

			String str1 = html;

			return str1;
		} catch (Exception ex) {
		} finally {
			writer = null;

			xmlSource = null;
			xslSource = null;
			result = null;
		}
		return html;
	}

	public static String getString(String name, Element element) {
		return ((element.valueOf(name) == null) ? "" : element.valueOf(name));
	}

	public static boolean savaToFile(Document doc, String filePathName,
			OutputFormat format) {
		try {
			String filePath = FilenameUtils.getFullPath(filePathName);

			if ((!(FileHelper.exists(filePath)))
					&& (!(FileHelper.createDirectory(filePath)))) {
				return false;
			}

			XMLWriter writer = new XMLWriter(new FileWriter(new File(
					filePathName)), format);

			writer.write(doc);
			writer.close();
			return true;
		} catch (IOException ex) {
			logger.error("д�ļ�����", ex);
		}

		return false;
	}

	/**
	 * ������д��xml�ļ�
	 * @author xiongdun
	 * @created 2016��9��27�� ����10:19:19
	 * @since 
	 * @param filePathName
	 * @param doc
	 * @return
	 */
	public static boolean writeToXml(String filePathName, Document doc) {
		OutputFormat format = OutputFormat.createCompactFormat();
		format.setEncoding("GBK");
		return savaToFile(doc, filePathName, format);
	}

}
