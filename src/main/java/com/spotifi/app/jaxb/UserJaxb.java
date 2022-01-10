package com.spotifi.app.jaxb;

import java.io.File;
import java.time.LocalDate;

import com.spotifi.app.objects.User;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class UserJaxb {
    public static void main(String[] args) throws JAXBException {

	JAXBContext context;

	context = JAXBContext.newInstance(User.class);

	LocalDate localDate = LocalDate.of(1999, 8, 16);
	User user = new User();
	user.setBirth(localDate);
	user.setId(1);
	user.setName("Ramiro");

	Marshaller marshaller = context.createMarshaller();
	marshaller.marshal(user, new File("src/main/resources/userJaxb.xml"));
	Unmarshaller unmarshaller = context.createUnmarshaller();
	Object unmarshalled = unmarshaller.unmarshal(new File("src/main/resources/userJaxb.xml"));
	System.out.println(unmarshalled);

    }
}
