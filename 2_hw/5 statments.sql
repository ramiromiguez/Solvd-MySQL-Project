-- gets all the countrys even if theres is no artist with that nationality
SELECT country_name, artist_name
FROM country 
	LEFT JOIN artist
    ON country.country_id = artist.country_id;

-- union the left and right join
SELECT country_name, artist_name, 'left_join'
FROM country 
	LEFT JOIN artist
    ON country.country_id = artist.country_id
UNION
SELECT country_name, artist_name, 'right_join'
FROM country 
	RIGHT JOIN artist
    ON country.country_id = artist.country_id;

-- gets all the genres even if theres is no song to match
SELECT song_name, genre_name
FROM song
	RIGHT JOIN genre
    ON genre.genre_id = song.genre_id;
    
-- gets all the playlist an user has
SELECT `user_name`, `playlist_name`
FROM `user`
	INNER JOIN playlist
    ON user.user_id = playlist.user_id;

-- gets all the users with their corresponding playlist if they have one
SELECT `user_name`, `playlist_name`
FROM `user`
	LEFT JOIN playlist
    ON user.user_id = playlist.user_id;
    
-- gets all the users with their corresponding playlist if the user dont have one it will not show up
SELECT `user_name`, `playlist_name`
FROM `user`
	RIGHT JOIN playlist
    ON user.user_id = playlist.user_id;

