package com.spotifi.app.model.jsonmapper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCreator {

    public <T> void Creator(T t, String jsonPlacement) {
	ObjectMapper mapper = new ObjectMapper();

	try {

	    // Java objects to JSON file
	    mapper.writeValue(new File(jsonPlacement), t);

	    // Java objects to JSON string - compact-print
	    String jsonString = mapper.writeValueAsString(t);

	    System.out.println(jsonString);

	    // Java objects to JSON string - pretty-print
	    String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);

	    System.out.println(jsonInString2);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
