package com.adii.parser.validator;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.adii.parser.error.handling.MyErrorHandler;

public class DtdValidator {

	public boolean isXMLValidatedAginstDTD(URI xmlPath) {
		File file = new File(xmlPath);
		DocumentBuilder documentBuilder = getDocumentBuilder();
		ErrorHandler errorHandler = new MyErrorHandler();
		documentBuilder.setErrorHandler(errorHandler);
		try {
			documentBuilder.parse(file);
		} catch (SAXException | IOException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

	private DocumentBuilder getDocumentBuilder() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return documentBuilder;
	}
}