package com.jimboo.test;

import com.jimboo.util.SHA1Utils;

public class SHA1Test {
	public static void main(String[] args) {
		String str = "加密数据...";
		System.out.println("加密后:" + SHA1Utils.SHA1(str));
		
	}
}
