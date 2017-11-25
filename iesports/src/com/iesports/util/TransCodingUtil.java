/**
 * 
 */
package com.iesports.util;

import java.io.UnsupportedEncodingException;

/**
 * 描述：字符编码转换工具类
 * @author xiongdun
 * @created 2016年9月14日 上午9:08:22
 * @since 
 */
public class TransCodingUtil {
	 /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块      */
    public static final String US_ASCII = "US-ASCII";
    /** ISO拉丁字母表 No.1，也叫做ISO-LATIN-1     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** 8 位 UCS 转换格式     */
    public static final String UTF_8 = "UTF-8";
    /** 16 位 UCS 转换格式，Big Endian(最低地址存放高位字节）字节顺序     */
    public static final String UTF_16BE = "UTF-16BE";
    /** 16 位 UCS 转换格式，Litter Endian（最高地址存放地位字节）字节顺序     */
    public static final String UTF_16LE = "UTF-16LE";
    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识     */
    public static final String UTF_16 = "UTF-16";
    /** 中文超大字符集     **/
    public static final String GBK = "GBK";
    
    public static final String GB2312 = "GB2312";
    
    /** 将字符编码转换成US-ASCII码     */
    public static String toASCII(String str) throws UnsupportedEncodingException {
        return changeCharset(str, US_ASCII);
    }
    
    /** 将字符编码转换成ISO-8859-1     */
    public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return changeCharset(str, ISO_8859_1);
    }
    
    /** 将字符编码转换成UTF-8     */
    public static String toUTF_8(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_8);
    }
    
    /** 将字符编码转换成UTF-16BE     */
    public static String toUTF_16BE(String str) throws UnsupportedEncodingException{
        return changeCharset(str, UTF_16BE);
    }
    
    /** 将字符编码转换成UTF-16LE     */
    public static String toUTF_16LE(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_16LE);
    }
    
    /** 将字符编码转换成UTF-16     */
    public static String toUTF_16(String str) throws UnsupportedEncodingException {
        return changeCharset(str, UTF_16);
    }
    
    /** 将字符编码转换成GBK     */
    public static String toGBK(String str) throws UnsupportedEncodingException {
        return changeCharset(str, GBK);
    }
    
    /** 将字符编码转换成GB2312     */
    public static String toGB2312(String str) throws UnsupportedEncodingException {
        return changeCharset(str,GB2312);
    }
    
    /**
     * 字符串编码转换的实现方法
     * @param str    待转换的字符串
     * @param newCharset    目标编码
     */
    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if(str != null) {
            //用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);    //用新的字符编码生成字符串
        }
        return null;
    }
    
    /**
     * 字符串编码转换的实现方法
     * @param str    待转换的字符串
     * @param oldCharset    源字符集
     * @param newCharset    目标字符集
     */
    public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        if(str != null) {
            //用源字符编码解码字符串
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        }
        return null;
    }
}
