package com.spotifi.app.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.controller.jbdcservice.AlbumService;
import com.spotifi.app.controller.jbdcservice.ArtistService;
import com.spotifi.app.controller.jbdcservice.CountryService;
import com.spotifi.app.controller.jbdcservice.GenreService;
import com.spotifi.app.controller.jbdcservice.PlaylistService;
import com.spotifi.app.controller.jbdcservice.SongService;
import com.spotifi.app.controller.jbdcservice.UserService;
import com.spotifi.app.model.entities.Album;
import com.spotifi.app.model.entities.Artist;
import com.spotifi.app.model.entities.Country;
import com.spotifi.app.model.entities.Genre;
import com.spotifi.app.model.entities.Playlist;
import com.spotifi.app.model.entities.PlaylistFollowed;
import com.spotifi.app.model.entities.Song;
import com.spotifi.app.model.entities.SongLiked;
import com.spotifi.app.model.entities.User;
import com.spotifi.app.model.entities.UserFollowed;

public class DaoRunner {

    private static <T> void printList(List<T> list) {
	list.forEach(x -> LOG.log(Level.INFO, x.toString()));
    }

    private static final Logger LOG = Logger.getLogger(DaoRunner.class.getName());

    public static void main(String[] args) throws SQLException {

	AlbumService albumService = new AlbumService();
	ArtistService artistService = new ArtistService();
	CountryService countryService = new CountryService();
	GenreService genreService = new GenreService();
	PlaylistService playlistService = new PlaylistService();
	SongService songService = new SongService();
	UserService userService = new UserService();

	Album album = new Album();
	Artist artist = new Artist();
	Country country = new Country();
	Genre genre = new Genre();
	Playlist playlist = new Playlist();
	PlaylistFollowed playlistFollowed = new PlaylistFollowed();
	Song song = new Song();
	SongLiked songLiked = new SongLiked();
	User user = new User();
	UserFollowed userFollowed = new UserFollowed();

	List<Album> albumList = new ArrayList<>();
	List<Artist> artistList = new ArrayList<>();
	List<Country> countryList = new ArrayList<>();
	List<Genre> genreList = new ArrayList<>();
	List<Playlist> playlistList = new ArrayList<>();
	List<PlaylistFollowed> playlistFollowedList = new ArrayList<>();
	List<Song> songList = new ArrayList<>();
	List<SongLiked> songLikedList = new ArrayList<>();
	List<User> userList = new ArrayList<>();
	List<UserFollowed> userFollowedList = new ArrayList<>();

	albumList = albumService.getObjectList();
	songList = songService.getObjectList();
	playlistList = playlistService.getObjectList();
	playlist = playlistService.getObject(1);
	userList = userService.getObjectList();
	printList(userList);
	System.out.println(playlist.toString());

    }
}
