package com.spotifi.app.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SongParser {

    private static final String FILENAME = "src/main/resources/song.xml";

    public static void main(String[] args) {

	SAXParserFactory factory = SAXParserFactory.newInstance();

	try {

	    SAXParser saxParser = factory.newSAXParser();

	    SongHandler handler = new SongHandler();
	    saxParser.parse(FILENAME, handler);

	} catch (ParserConfigurationException | SAXException | IOException e) {
	    e.printStackTrace();
	}

    }
}
