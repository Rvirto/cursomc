package com.renatovirto.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> decodeIntList(String string) {
		String[] vet = string.split(",");
		List<Integer> lista = new ArrayList<>();
		for (int i =0; i < vet.length; i++) {
			lista.add(Integer.parseInt(vet[i]));
		}
		return lista;
	}
	
	public static String decodeParam(String string) {
		try {
			return URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
