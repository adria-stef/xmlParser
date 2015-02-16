package com.adii.parser.util;

import java.net.URI;
import java.net.URISyntaxException;

import com.adii.parser.io.WriteXMLFileUtil;

public class Util {

	public URI getRealPath(String fileName) {
		URI path = null;
		try {
			path = WriteXMLFileUtil.class.getResource("/" + fileName).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
}
