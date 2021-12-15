SELECT song_id, song_name, song_duration, artist_name, genre_name, country_name
FROM song 
	INNER JOIN artist 	
    ON artist.artist_id = song.artist_id
    INNER JOIN genre
    ON genre.genre_id = song.genre_id
    INNER JOIN country
    ON country.country_id = artist.country_id
ORDER BY song_name;

SELECT song.song_id, song_name, song_duration, artist_name, genre_name, country_name, playlist_name, user_name, playlist_date AS "created in", album_name
FROM song 
	INNER JOIN artist 	
    ON artist.artist_id = song.artist_id
    INNER JOIN genre
    ON genre.genre_id = song.genre_id
    INNER JOIN country
    ON country.country_id = artist.country_id
    INNER JOIN playlist_content
    ON playlist_content.song_id = song.song_id
    INNER JOIN playlist
    ON playlist.playlist_id = playlist_content.playlist_id
    INNER JOIN `user`
    ON user.user_id = playlist.user_id
    INNER JOIN album_content
    ON album_content.song_id = song.song_id
    INNER JOIN album
    ON album_content.album_id = album.album_id
ORDER BY song_name;

SELECT song.song_id, song_name, song_duration, artist_name, genre_name, country_name, playlist_name, user.user_name, playlist_date AS "created in", album_name, us1.user_name
FROM song 
	INNER JOIN artist 	
    ON artist.artist_id = song.artist_id
    INNER JOIN genre
    ON genre.genre_id = song.genre_id
    INNER JOIN country
    ON country.country_id = artist.country_id
    INNER JOIN playlist_content
    ON playlist_content.song_id = song.song_id
    INNER JOIN playlist
    ON playlist.playlist_id = playlist_content.playlist_id
    INNER JOIN `user`
    ON user.user_id = playlist.user_id
    INNER JOIN album_content
    ON album_content.song_id = song.song_id
    INNER JOIN album
    ON album_content.album_id = album.album_id
    INNER JOIN user_followed
    ON user_followed.user_id = user.user_id
    INNER JOIN `user` as us1
    ON us1.user_id = user_followed.userf_id
ORDER BY song_name;