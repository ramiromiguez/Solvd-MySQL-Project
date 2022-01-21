package com.spotifi.app.model.objecttransformer;

import java.util.ArrayList;
import java.util.List;

import com.spotifi.app.controller.mybatisservice.ArtistServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.CountryServiceMyBatis;
import com.spotifi.app.controller.mybatisservice.GenreServiceMyBatis;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.ArtistAsDB;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.entities.Genre;

public class DBArtistToArtist {

    public Artist objectTransformer(int id) {
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	GenreServiceMyBatis genreService = new GenreServiceMyBatis();
	CountryServiceMyBatis countryService = new CountryServiceMyBatis();
	ArtistAsDB artistAsDB;
	Artist artist = new Artist();
	Genre genre;
	Country country;

	artistAsDB = artistService.getObject(id);
	genre = genreService.getObject(artistAsDB.getGenreId());
	country = countryService.getObject(artistAsDB.getCountryId());
	artist.setGenre(genre);
	artist.setCountry(country);
	return artist;
    }

    public List<Artist> objectListTransformer() {
	ArtistServiceMyBatis artistService = new ArtistServiceMyBatis();
	Artist artist;
	List<Artist> artistList = new ArrayList<>();

	List<ArtistAsDB> artistDBList = artistService.getObjectList();
	for (ArtistAsDB artistAsDB : artistDBList) {
	    artist = objectTransformer(artistAsDB.getId());
	    artistList.add(artist);
	}
	return artistList;
    }
}
