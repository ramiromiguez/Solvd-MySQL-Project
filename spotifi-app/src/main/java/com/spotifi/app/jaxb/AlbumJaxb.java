package com.spotifi.app.jaxb;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.spotifi.app.objects.Album;

@XmlRootElement
public class AlbumJaxb {

    public void marshal() throws JAXBException, IOException {
	Album album = new Album();
	album.setId(3);
	album.setName("practice");
	album.setArtistId(2);
	album.setGenreId(2);
	album.setLength(3);

	JAXBContext context = JAXBContext.newInstance(Album.class);
	Marshaller mar = context.createMarshaller();
	mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	mar.marshal(album, new File("src/main/resources/albumjaxb.xml"));
    }
}
