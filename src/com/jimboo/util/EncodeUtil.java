package com.jimboo.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodeUtil {
	/**
	 * 
	 * 字符转Unicode
	 * 
	 * @param s
	 * @return
	 */
	public static String stringToUnicode(String s) {  
	    try {  
	        StringBuffer out = new StringBuffer("");  
	        //直接获取字符串的unicode二进制  
	        byte[] bytes = s.getBytes("unicode");  
	        //然后将其byte转换成对应的16进制表示即可  
	        for (int i = 0; i < bytes.length - 1; i += 2) {  
	            out.append("\\u");  
	            String str = Integer.toHexString(bytes[i + 1] & 0xff);  
	            for (int j = str.length(); j < 2; j++) {  
	                out.append("0");  
	            }  
	            String str1 = Integer.toHexString(bytes[i] & 0xff);  
	            out.append(str1);  
	            out.append(str);  
	        }  
	        return out.toString();  
	    } catch (UnsupportedEncodingException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	}  
	
	/**
	 * 
	 * Unicode转汉字
	 * 
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {  
		  
	    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");  
	    Matcher matcher = pattern.matcher(str);  
	    char ch;  
	    while (matcher.find()) {  
	        //group 6728  
	        String group = matcher.group(2);  
	        //ch:'木' 26408  
	        ch = (char) Integer.parseInt(group, 16);  
	        //group1 \u6728  
	        String group1 = matcher.group(1);  
	        str = str.replace(group1, ch + "");  
	    }  
	    return str;  
	}  
	
	
	
	/**
	 * 
	 * 汉字转utf-8
	 * 
	 * @param s
	 * @return
	 */
	public static String convertStringToUTF8(String s) {  
	    if (s == null || s.equals("")) {  
	        return null;  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    try {  
	        char c;  
	        for (int i = 0; i < s.length(); i++) {  
	            c = s.charAt(i);  
	            if (c >= 0 && c <= 255) {  
	                sb.append(c);  
	            } else {  
	                byte[] b;  
	                b = Character.toString(c).getBytes("utf-8");  
	                for (int j = 0; j < b.length; j++) {  
	                    int k = b[j];  
	                    //转换为unsigned integer  无符号integer  
	                    /*if (k < 0) 
	                        k += 256;*/  
	                    k = k < 0? k+256:k;  
	                    //返回整数参数的字符串表示形式 作为十六进制（base16）中的无符号整数  
	                    //该值以十六进制（base16）转换为ASCII数字的字符串  
	                    sb.append(Integer.toHexString(k).toUpperCase());  
	  
	                    // url转置形式  
	                    // sb.append("%" +Integer.toHexString(k).toUpperCase());  
	                }  
	            }  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return sb.toString();  
	}  
	
	
	/**
	 * 
	 * utf-8转汉字
	 * 
	 * @param s
	 * @return
	 */
	public static String convertUTF8ToString(String s) {  
	    if (s == null || s.equals("")) {  
	        return null;  
	    }  
	    try {  
	        s = s.toUpperCase();  
	        int total = s.length() / 2;  
	        //标识字节长度  
	        int pos = 0;  
	        byte[] buffer = new byte[total];  
	        for (int i = 0; i < total; i++) {  
	            int start = i * 2;  
	            //将字符串参数解析为第二个参数指定的基数中的有符号整数。  
	            buffer[i] = (byte) Integer.parseInt(s.substring(start, start + 2), 16);  
	            pos++;  
	        }  
	        //通过使用指定的字符集解码指定的字节子阵列来构造一个新的字符串。  
	        //新字符串的长度是字符集的函数，因此可能不等于子数组的长度。  
	        return new String(buffer, 0, pos, "UTF-8");  
	    } catch (UnsupportedEncodingException e) {  
	        e.printStackTrace();  
	    }  
	    return s;  
	}  
	
	/**
	 * 
	 * Unicode转utf-8
	 * 
	 * @param s
	 * @return
	 */
	public static String unicodeToUTF8(String s) {
		String str = convertStringToUTF8(unicodeToString(s));
		return str;
	}
	
	/**
	 * 
	 * utf8转Unicode
	 * 
	 * @param s
	 * @return
	 */
	public static String utf8ToUnicode(String s) {
		String str = stringToUnicode(convertUTF8ToString(s));
		return str;
	}
	
}
