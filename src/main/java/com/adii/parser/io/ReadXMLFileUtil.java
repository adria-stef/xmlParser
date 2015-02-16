package com.adii.parser.io;

import static com.adii.parser.constants.Constants.CONDITION_TAG;
import static com.adii.parser.constants.Constants.DUE_DATE_TAG;
import static com.adii.parser.constants.Constants.INSTRUCTIONS_TAG;
import static com.adii.parser.constants.Constants.MAX_POINTS_TAG;
import static com.adii.parser.constants.Constants.NUMBER_TAG;
import static com.adii.parser.constants.Constants.SOLUTION_TAG;
import static com.adii.parser.constants.Constants.SUBJECT_TAG;
import static com.adii.parser.constants.Constants.TOPIC_TAG;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.adii.parser.task.Task;

public class ReadXMLFileUtil {

	public List<Task> readXml(URI filePath) {
		File xmlFile = new File(filePath);
		Document doc = getDocument(xmlFile);
		List<Task> tasks = getTasks(doc);
		return tasks;
	}

	private Document getDocument(File xmlFile) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
			doc = documentBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return doc;
	}

	private List<Task> getTasks(Document doc) {
		List<Task> tasksList = new ArrayList<Task>();
		NodeList nodeList = doc.getElementsByTagName("task");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node node = nodeList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Task task = getTask(node);
				tasksList.add(task);
			}
		}
		return tasksList;
	}

	private Task getTask(Node node) {
		Element element = (Element) node;
		String subject = getAttribute(element, SUBJECT_TAG);
		String number = getElement(element, NUMBER_TAG);
		String dueDate = getElement(element, DUE_DATE_TAG);
		String topic = getElement(element, TOPIC_TAG);
		List<String> instructions = getInstructionsList(element, INSTRUCTIONS_TAG);
		String condition = getElement(element, CONDITION_TAG);
		String solution = getElement(element, SOLUTION_TAG);
		String maxPoints = getElement(element, MAX_POINTS_TAG);
		Task task = new Task(subject, number, dueDate, topic, instructions, condition, solution, maxPoints);
		return task;
	}

	private String getAttribute(Element element, String tagName) {
		NamedNodeMap attributes = element.getAttributes();
		Node attribute = attributes.item(0);
		return attribute.getNodeValue();
	}

	private String getElement(Element element, String tagName) {
		return element.getElementsByTagName(tagName).item(0).getTextContent();
	}

	private List<String> getInstructionsList(Element element, String tagName) {
		List<String> instructionsList = new ArrayList<String>();
		NodeList nodeList = element.getElementsByTagName(tagName);
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node node = nodeList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) node;
				NodeList childNodes = childElement.getChildNodes();
				for (int i = 0; i < childNodes.getLength(); i++) {
					instructionsList.add(childNodes.item(i).getTextContent());
				}
			}
		}
		return instructionsList;
	}
}