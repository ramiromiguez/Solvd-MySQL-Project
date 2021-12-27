package com.spotifi.app.domparse;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParse {
    public static void main(String[] args) {
	try {
	    File inputFile = new File("src/main/resources/artist.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(inputFile);
	    doc.getDocumentElement().normalize();
	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    NodeList nList = doc.getElementsByTagName("artist");
	    System.out.println("----------------------------");

	    for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    Element eElement = (Element) nNode;
		    System.out.println("Artist id : " + eElement.getAttribute("id"));
		    System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
		    System.out.println(
			    "Country id: " + eElement.getElementsByTagName("countryId").item(0).getTextContent());
		    System.out
			    .println("Genre id : " + eElement.getElementsByTagName("genreId").item(0).getTextContent());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
