package com.spotifi.app.sax;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Validator {

    public static void main(String[] args) {

	Logger LOG = Logger.getLogger(Validator.class.getName());
	File xsdFile = new File("src/main/resources/song.xsd");

	try {

	    Path xmlPath = Paths.get("src/main/resources/song.xml");
	    Reader reader = Files.newBufferedReader(xmlPath);

	    String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
	    SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
	    Schema schema = factory.newSchema(xsdFile);

	    javax.xml.validation.Validator validator = schema.newValidator();

	    SAXSource source = new SAXSource(new InputSource(reader));
	    validator.validate(source);

	    LOG.log(Level.INFO, "The document validation is ok");

	} catch (SAXException ex) {
	    LOG.log(Level.SEVERE, "The document failed to validate");
	    LOG.log(Level.SEVERE, ex.getMessage(), ex);
	} catch (IOException ex) {
	    LOG.log(Level.SEVERE, ex.getMessage(), ex);
	}
    }
}