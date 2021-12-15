-- gets all the countrys even if theres is no artist with that nationality
SELECT country_name, artist_name
FROM country 
	LEFT JOIN artist
    ON country.country_id = artist.country_id;

SELECT song_name, genre_name
FROM song
	RIGHT JOIN genre
    ON genre.genre_id = song.genre_id;
    
SELECT song_name, genre_name
FROM song
	FULL JOIN genre
    ON genre.genre_id = song.genre_id;