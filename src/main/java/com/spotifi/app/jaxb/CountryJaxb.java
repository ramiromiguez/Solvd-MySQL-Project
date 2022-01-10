package com.spotifi.app.jaxb;

import java.io.File;

import com.spotifi.app.objects.Country;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class CountryJaxb {
    public static void main(String[] args) throws JAXBException {

	JAXBContext context;

	context = JAXBContext.newInstance(Country.class);

	Country country = new Country();
	country.setId(1);
	country.setName("United States");
	Marshaller marshaller = context.createMarshaller();
	marshaller.marshal(country, new File("src/main/resources/countryJaxb.xml"));
	Unmarshaller unmarshaller = context.createUnmarshaller();
	Object unmarshalled = unmarshaller.unmarshal(new File("src/main/resources/countryJaxb.xml"));
	System.out.println(unmarshalled);

    }
}
