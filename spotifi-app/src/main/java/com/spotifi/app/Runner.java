package com.spotifi.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.spotifi.app.connection.DBConnection;
import com.spotifi.app.dao.AlbumDAO;
import com.spotifi.app.dao.ArtistDAO;
import com.spotifi.app.dao.SongDAO;
import com.spotifi.app.objects.Album;
import com.spotifi.app.objects.Artist;
import com.spotifi.app.objects.Song;
import com.spotifi.app.service.AlbumService;
import com.spotifi.app.service.ArtistService;
import com.spotifi.app.service.SongService;

public class Runner {
    private static final Logger LOG = Logger.getLogger(AlbumDAO.class.getName());

    public static void main(String[] args) throws SQLException {
	AlbumService albumService = new AlbumService(new AlbumDAO(new DBConnection()));
	ArtistService artistService = new ArtistService(new ArtistDAO(new DBConnection()));
	SongService songService = new SongService(new SongDAO(new DBConnection()));

	Artist artist = new Artist();
	List<Artist> artistList = new ArrayList<>();
	Album album = new Album();
	List<Album> albumList = new ArrayList<>();
	Song song = new Song();
	List<Song> songList = new ArrayList<>();

	artistService.delete(5);

	/*
	 * // printing every artist artistList = artistService.getObjectList();
	 * artistList = artistService.getObjectList();
	 * artistList.forEach(System.out::println); LOG.log(Level.INFO, "");
	 * 
	 * // adding new artist artist.setName("Kis"); artist.setGenreId(1);
	 * artist.setName("Kis"); artist.setCountryId(1); artist.setGenreId(2);
	 * artistService.add(artist); artistList = artistService.getObjectList();
	 * artistList.forEach(System.out::println);
	 * 
	 * LOG.log(Level.INFO, ""); // updating artist name kis to kiss artist =
	 * artistService.getObject(11); artist.setName("kiss");
	 * artistService.update(artist); artistList = artistService.getObjectList();
	 * artistList.forEach(System.out::println); LOG.log(Level.INFO, "");
	 * 
	 * // delete artist kiss-- need to fix artistService.delete(2); artist =
	 * artistService.getObject(11); artistService.delete(1); artistList =
	 * artistService.getObjectList(); artistList.forEach(System.out::println);
	 * LOG.log(Level.INFO, "");
	 */

	/*
	 * // printing every album albumList = albumService.getObjectList();
	 * albumList.forEach(x -> LOG.log(Level.INFO, x.toString()));
	 * System.out.println("--------------------");
	 * 
	 * // adding new album album.setName("new album"); album.setGenreId(2);
	 * album.setArtistId(1); albumService.add(album); albumList =
	 * albumService.getObjectList(); albumList.forEach(x -> LOG.log(Level.INFO,
	 * x.toString())); System.out.println("--------------------");
	 * 
	 * // updating new album album = albumService.getObject(3);
	 * album.setName("updated album"); albumService.update(album); albumList =
	 * albumService.getObjectList(); albumList.forEach(x -> LOG.log(Level.INFO,
	 * x.toString())); System.out.println("--------------------"); // deleting new
	 * album albumService.delete(3); albumList = albumService.getObjectList();
	 * albumList.forEach(x -> LOG.log(Level.INFO, x.toString()));
	 * System.out.println("--------------------");
	 */

	/*
	 * // printing every song
	 * System.out.println("--------------------SONG----------------"); songList =
	 * songService.getObjectList(); songList.forEach(x -> LOG.log(Level.INFO,
	 * x.toString())); System.out.println("--------------------");
	 * 
	 * // adding new song song.setName("new song"); song.setGenreId(2);
	 * song.setArtistId(1); song.setDuration(2.2f); songService.add(song); songList
	 * = songService.getObjectList(); songList.forEach(x -> LOG.log(Level.INFO,
	 * x.toString())); System.out.println("--------------------");
	 * 
	 * // updating new song song = songService.getObject(21);
	 * System.out.println(song.getName()); song.setName("updated song");
	 * songService.update(song); songList = songService.getObjectList();
	 * songList.forEach(x -> LOG.log(Level.INFO, x.toString()));
	 * System.out.println("--------------------"); // deleting new song
	 * songService.delete(7); songList = songService.getObjectList();
	 * songList.forEach(x -> LOG.log(Level.INFO, x.toString()));
	 * System.out.println("--------------------");
	 */

    }
}
