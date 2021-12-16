-- quantity of songs by artist
SELECT count(*), artist_name
	FROM artist
    INNER JOIN song
    ON song.artist_id = artist.artist_id
GROUP BY artist_name;

SELECT count(*), artist_name
	FROM artist
    INNER JOIN song
    ON song.artist_id = artist.artist_id
GROUP BY artist_name 
HAVING count(*) > 3;

-- sums name lengths of artist names that starts with the same letter (in this case paco amoroso + pepe = 16)
SELECT sum(length(artist_name)),  left(artist_name,1)
	FROM artist
GROUP BY left(artist_name,1);

SELECT sum(length(artist_name)) AS quantity,  left(artist_name,1)
	FROM artist
GROUP BY left(artist_name,1)
HAVING quantity > 10;

-- time duration of albums
SELECT sum(song_duration), album_name
	FROM album
    INNER JOIN album_content
    ON album_content.album_id = album.album_id
    INNER JOIN song
    ON song.song_id = album_content.song_id
GROUP BY album_name;

SELECT sum(song_duration) as song_duration, album_name
	FROM album
    INNER JOIN album_content
    ON album_content.album_id = album.album_id
    INNER JOIN song
    ON song.song_id = album_content.song_id
GROUP BY album_name
HAVING song_duration > 13.5;

-- % of song durations by artist
SELECT avg(song_duration), artist_name
	FROM artist
    INNER JOIN song
    ON song.artist_id = artist.artist_id
GROUP BY artist_name;

SELECT avg(song_duration) as duration, artist_name
	FROM artist
    INNER JOIN song
    ON song.artist_id = artist.artist_id
GROUP BY artist_name 
HAVING duration < 5;

-- songs per album
SELECT count(*), album_name
	FROM album
    INNER JOIN album_content
    ON album_content.album_id = album.album_id
    INNER JOIN song
    ON song.song_id = album_content.song_id
GROUP BY album_name;

SELECT count(*) as quantity_of_songs, album_name
	FROM album
    INNER JOIN album_content
    ON album_content.album_id = album.album_id
    INNER JOIN song
    ON song.song_id = album_content.song_id
GROUP BY album_name
HAVING quantity_of_songs = 7;

-- playlist per year
SELECT count(*), year(playlist_date)
	FROM playlist
GROUP BY year(playlist_date);

SELECT count(*), year(playlist_date)
	FROM playlist
GROUP BY year(playlist_date)
HAVING count(*) > 1;

-- playlist per year and month
SELECT count(*), year(playlist_date), month(playlist_date)
	FROM playlist
GROUP BY year(playlist_date), month(playlist_date)
ORDER BY year(playlist_date), month(playlist_date);

SELECT count(*), year(playlist_date), month(playlist_date)
	FROM playlist
GROUP BY year(playlist_date), month(playlist_date)
HAVING count(*) < 2
ORDER BY year(playlist_date), month(playlist_date);

-- longest song
SELECT max(song_duration) as song_duration
	FROM song;
    
-- shortest song
SELECT min(song_duration) as song_duration
	FROM song