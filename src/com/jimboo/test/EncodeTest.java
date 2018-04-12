package com.jimboo.test;

import com.jimboo.util.EncodeUtil;

public class EncodeTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//String s = "\\u4ee4\\u724c\\u4e0d\\u5b58\\u5728\\u6216\\u8005\\u5df2\\u7ecf\\u8fc7\\u671f";
		//String string = EncodeUtil.unicodeToString(s);
		System.out.println("转码开始\r");
		//System.out.println(string);
		String str = "海纳百川有容乃大";
		String str1 = "壁立千仞无欲则刚";
		System.out.println("汉字转Unicode");
		String string = EncodeUtil.stringToUnicode(str);
		System.out.println(str + " >>>> " + string);
		System.out.println("\rUnicode转汉字");
		String uStr = "\\ufeff\\u58c1\\u7acb\\u5343\\u4ede\\u65e0\\u6b32\\u5219\\u521a";
		System.out.println(uStr + " >>>> " + EncodeUtil.unicodeToString(uStr));
		
		System.out.println("\r汉字转UTF-8");
		System.out.println(str + " >>>> " + EncodeUtil.convertStringToUTF8(str));
		System.out.println("\rUTF-8转汉字");
		String utfStr = "E5A381E7AB8BE58D83E4BB9EE697A0E6ACB2E58899E5889A";
		System.out.println(utfStr + " >>>> " + EncodeUtil.convertUTF8ToString(utfStr));
	}
}
