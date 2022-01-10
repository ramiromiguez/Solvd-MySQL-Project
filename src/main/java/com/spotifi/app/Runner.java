package com.spotifi.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spotifi.app.dao.AlbumDAO;
import com.spotifi.app.objects.Album;
import com.spotifi.app.objects.Artist;
import com.spotifi.app.objects.Country;
import com.spotifi.app.objects.Genre;
import com.spotifi.app.objects.Playlist;
import com.spotifi.app.objects.PlaylistFollowed;
import com.spotifi.app.objects.Song;
import com.spotifi.app.objects.SongLiked;
import com.spotifi.app.objects.User;
import com.spotifi.app.objects.UserFollowed;
import com.spotifi.app.service.AlbumService;
import com.spotifi.app.service.ArtistService;
import com.spotifi.app.service.CountryService;
import com.spotifi.app.service.GenreService;
import com.spotifi.app.service.PlaylistFollowedService;
import com.spotifi.app.service.PlaylistService;
import com.spotifi.app.service.SongLikedService;
import com.spotifi.app.service.SongService;
import com.spotifi.app.service.UserFollowedService;
import com.spotifi.app.service.UserService;

public class Runner {

    private static <T> void printList(List<T> list) {
	list.forEach(x -> LOG.log(Level.INFO, x.toString()));
    }

    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public static void main(String[] args) throws SQLException {

	AlbumService albumService = new AlbumService();
	ArtistService artistService = new ArtistService();
	CountryService countryService = new CountryService();
	GenreService genreService = new GenreService();
	PlaylistService playlistService = new PlaylistService();
	PlaylistFollowedService playlistFollowedService = new PlaylistFollowedService();
	SongService songService = new SongService();
	SongLikedService songLikedService = new SongLikedService();
	UserService userService = new UserService();
	UserFollowedService userFollowedService = new UserFollowedService();

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

	artistList = artistService.getObjectList();
	printList(artistList);
	albumList = albumService.getObjectList();
	printList(albumList);
	countryList = countryService.getObjectList();
	printList(countryList);
	genreList = genreService.getObjectList();
	printList(genreList);
	playlistList = playlistService.getObjectList();
	printList(playlistList);
	playlistFollowedList = playlistFollowedService.getObjectList();
	printList(playlistFollowedList);
	songList = songService.getObjectList();
	printList(songList);
	songLikedList = songLikedService.getObjectList();
	printList(songLikedList);
	userList = userService.getObjectList();
	printList(userList);
	userFollowedList = userFollowedService.getObjectList();
	printList(userFollowedList);

    }
}
