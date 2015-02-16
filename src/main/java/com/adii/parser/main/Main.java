package com.adii.parser.main;

import static com.adii.parser.constants.Constants.DOM_PARSED_HTML_FILE;
import static com.adii.parser.constants.Constants.TASKS_EMBEDDED_XML_FILE;
import static com.adii.parser.constants.Constants.TASKS_XML_FILE;
import static com.adii.parser.constants.Constants.TASKS_XSD_FILE;
import static com.adii.parser.constants.Constants.TASKS_XSL_FILE;
import static com.adii.parser.constants.Constants.WRITE_TASKS_XML_FILE;

import java.util.List;

import com.adii.parser.io.ReadXMLFileUtil;
import com.adii.parser.io.WriteXMLFileUtil;
import com.adii.parser.task.Task;
import com.adii.parser.transformer.XslTransformer;
import com.adii.parser.util.Util;
import com.adii.parser.validator.DtdValidator;
import com.adii.parser.validator.XsdValidator;

public class Main {

	private static Util util = new Util();

	public static void main(String[] args) {
		readXMLFile(TASKS_XML_FILE);
		writeXMLFile(WRITE_TASKS_XML_FILE);
		xslTransform(TASKS_XSL_FILE, TASKS_XML_FILE, DOM_PARSED_HTML_FILE);
		dtdValidate(TASKS_EMBEDDED_XML_FILE);
		xsdValidate(TASKS_XSD_FILE, TASKS_XML_FILE);
	}

	private static void readXMLFile(String xmlFile) {
		ReadXMLFileUtil readXMLFile = new ReadXMLFileUtil();
		List<Task> tasks = readXMLFile.readXml(util.getRealPath(xmlFile));
		for (Task task : tasks) {
			System.out.println(task);
		}
	}

	private static void writeXMLFile(String xmlFile) {
		WriteXMLFileUtil writeXMLFileUtil = new WriteXMLFileUtil();
		writeXMLFileUtil.writeXmlFile(xmlFile);
	}

	private static void xslTransform(String xslPath, String xmlPath, String htmlPath) {
		XslTransformer xslTransformer = new XslTransformer();
		xslTransformer.createHtml(xslPath, xmlPath, htmlPath);
	}

	private static void dtdValidate(String xmlPath) {
		DtdValidator dtdValidator = new DtdValidator();
		boolean isXmlValid = dtdValidator.isXMLValidatedAginstDTD(util.getRealPath(xmlPath));
		System.out.println("xml validates against dtd? " + isXmlValid);
	}

	private static void xsdValidate(String xsdPath, String xmlPath) {
		XsdValidator xsdValidator = new XsdValidator();
		boolean isXmlValid = xsdValidator.validateXMLSchema(xsdPath, xmlPath);
		System.out.println("xml validates against xsd? " + isXmlValid);
	}
}
