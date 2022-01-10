package com.spotifi.app.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class GenreHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    @Override
    public void startDocument() {
	System.out.println("Start Document");
    }

    @Override
    public void endDocument() {
	System.out.println("End Document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

	// reset the tag value
	currentValue.setLength(0);

	System.out.printf("Start Element : %s%n", qName);

	if (qName.equalsIgnoreCase("genre")) {
	    // get tag's attribute by name
	    String id = attributes.getValue("id");
	    System.out.printf("Genre id : %s%n", id);
	}

    }

    @Override
    public void endElement(String uri, String localName, String qName) {

	System.out.printf("End Element : %s%n", qName);

	if (qName.equalsIgnoreCase("name")) {
	    System.out.printf("Name : %s%n", currentValue.toString());
	}
    }

    @Override
    public void characters(char ch[], int start, int length) {
	currentValue.append(ch, start, length);

    }

}
