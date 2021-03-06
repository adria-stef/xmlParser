package com.adii.parser.error.handling;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("Warning: ");
		printInfo(e);
	}

	public void error(SAXParseException e) throws SAXException {
		System.out.println("Error: ");
		printInfo(e);
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("Fattal error: ");
		printInfo(e);
	}

	private void printInfo(SAXParseException e) {
		System.out.println("   Public ID: " + e.getPublicId());
		System.out.println("   System ID: " + e.getSystemId());
		System.out.println("   Line number: " + e.getLineNumber());
		System.out.println("   Column number: " + e.getColumnNumber());
		System.out.println("   Message: " + e.getMessage());
	}
}