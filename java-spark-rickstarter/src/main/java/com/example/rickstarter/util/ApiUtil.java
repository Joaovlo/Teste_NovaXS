package com.example.rickstarter.util;

import okhttp3.Response;

public class ApiUtil {

	public static boolean hasError(Response resp, String url) {
		if (!resp.isSuccessful()) {
			System.err.println("❌ Erro na API: " + resp.code() + " na URL: " + url);
			return true;
		}
		return false;
	}
	
	public static void showCode(Response resp, String url) {
		System.out.println("Status API: " + resp.code() + " URL: " + url);
	}
	
	public static void showBody(String body) {
		System.out.println("CORPO: " + body);
	}
}
