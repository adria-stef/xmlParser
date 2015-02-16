package com.adii.parser.io;

import static com.adii.parser.constants.Constants.COMMENT;
import static com.adii.parser.constants.Constants.CONDITION_TAG;
import static com.adii.parser.constants.Constants.DUE_DATE_TAG;
import static com.adii.parser.constants.Constants.INSTRUCTIONS_TAG;
import static com.adii.parser.constants.Constants.INSTRUCTION_TAG;
import static com.adii.parser.constants.Constants.MAX_POINTS_TAG;
import static com.adii.parser.constants.Constants.NUMBER_TAG;
import static com.adii.parser.constants.Constants.SOLUTION_TAG;
import static com.adii.parser.constants.Constants.SUBJECT_TAG;
import static com.adii.parser.constants.Constants.TASKS;
import static com.adii.parser.constants.Constants.TASK_TAG;
import static com.adii.parser.constants.Constants.TOPIC_TAG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.adii.parser.task.Task;
import com.adii.parser.util.Util;

public class WriteXMLFileUtil {

	private Util util = new Util();

	public void writeXmlFile(String xmlPath) {

		Document document = getDocument();
		Element rootElement = createRootElement(document, TASKS);
		createComment(document, rootElement);

		List<String> instructions = new ArrayList<String>();
		instructions.add("instruction 1");
		instructions.add("instruction 2");
		Task task = new Task("WWW", "1", "2014-01-23", "HTML 5", instructions, "condition", "solution", "2");

		List<Task> tasksList = new ArrayList<Task>();
		tasksList.add(task);
		tasksList.add(task);
		tasksList.add(task);

		createTasksList(document, rootElement, tasksList);
		write(document, xmlPath);
	}

	private void createComment(Document document, Element rootElement) {
		Comment comment = document.createComment(COMMENT);
		rootElement.getParentNode().insertBefore(comment, rootElement);
	}

	private Document getDocument() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		Document document = docBuilder.newDocument();
		return document;
	}

	private Element createRootElement(Document document, String tagName) {
		Element rootElement = document.createElement(tagName);
		document.appendChild(rootElement);
		return rootElement;
	}

	private void createTasksList(Document document, Element rootElement, List<Task> tasksList) {
		for (Task task : tasksList) {
			createTask(document, rootElement, task);
		}
	}

	private void createTask(Document document, Element rootElement, Task task) {
		Element taskElement = createElement(document, rootElement, TASK_TAG);
		createAttributeWithValue(taskElement, SUBJECT_TAG, task.getSubject());

		createElementWithValue(document, taskElement, NUMBER_TAG, task.getNumber());
		createElementWithValue(document, taskElement, DUE_DATE_TAG, task.getDueDate());
		createElementWithValue(document, taskElement, TOPIC_TAG, task.getTopic());

		createListElement(document, task, taskElement, INSTRUCTIONS_TAG, INSTRUCTION_TAG);

		createElementWithValue(document, taskElement, CONDITION_TAG, task.getCondition());
		createElementWithValue(document, taskElement, SOLUTION_TAG, task.getSolution());
		createElementWithValue(document, taskElement, MAX_POINTS_TAG, task.getMaxPoints());
	}

	private void createAttributeWithValue(Element element, String tagName, String value) {
		element.setAttribute(tagName, value);
	}

	private void createListElement(Document document, Task task, Element taskElement, String outerTagName,
	        String innerTagName) {
		Element instructions = createElement(document, taskElement, outerTagName);
		for (String instruction : task.getInstructions()) {
			createElementWithValue(document, instructions, innerTagName, instruction);
		}
	}

	private Element createElement(Document document, Element rootElement, String tagName) {
		Element task = document.createElement(tagName);
		rootElement.appendChild(task);
		return task;
	}

	private void createElementWithValue(Document document, Element task, String tagName, String value) {
		Element element = document.createElement(tagName);
		element.appendChild(document.createTextNode(value));
		task.appendChild(element);
	}

	private void write(Document document, String xmlPath) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(util.getRealPath(xmlPath)));
			transformer.transform(source, result);
		} catch (TransformerException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
}