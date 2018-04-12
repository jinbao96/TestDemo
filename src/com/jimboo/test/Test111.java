package com.jimboo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test111 {
	public static void main(String[] args) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			System.out.println(sdf1.format(date));
			System.out.println(sdf1.parse(sdf1.format(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
