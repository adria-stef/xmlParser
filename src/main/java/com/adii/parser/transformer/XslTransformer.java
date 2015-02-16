package com.adii.parser.transformer;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.adii.parser.util.Util;

public class XslTransformer {

	Util util = new Util();

	public void createHtml(String xslPath, String xmlPath, String htmlPath) {
		transform(getSource(xmlPath), xslPath, htmlPath);
	}

	private DOMSource getSource(String xmlPath) {
		File file = new File(util.getRealPath(xmlPath));
		DocumentBuilder documentBuilder = getDocumentBuilder();
		Document document = null;
		try {
			document = documentBuilder.parse(file);
		} catch (SAXException | IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return new DOMSource(document);
	}

	private DocumentBuilder getDocumentBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return documentBuilder;
	}

	private void transform(DOMSource source, String xslPath, String htmlPath) {
		File file = new File(util.getRealPath(xslPath));
		TransformerFactory transfomerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transfomerFactory.newTransformer(new StreamSource(file));
			StreamResult result = new StreamResult(new File(util.getRealPath(htmlPath)));
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
