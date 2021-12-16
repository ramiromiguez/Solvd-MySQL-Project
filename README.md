# Solvd-MySQL-Project

Spotifi is a data base based on a music reproducer which is totally different from Spotify 🙄

## Explanation of tables

artist: Contains the info of the artist.

country: Contains a list of countrys.

song: Contains song info.

song_liked: contains the date which a songs was liked, its has the user_id which liked the song and the song_id.

album: Contains album info.

album_content: holds the content of an album, contains the song_id and the album_id.

artist_singles: Contains the songs from an artist that are not in an album.

genre: Contains the genre of music.

playlist: Contains playlist info.

playlist_content: holds the content of a playlist, contains the song id and the playlist id.

playlist_followed: playlist followed by users, contains the playlist id and the user id which follows the playlist

user: Contains user Info.

user_followed: user followed by another user, contains the user_id of the one who follows and the user_id of the followed user.


## Connections:

artist-> country, song, album, genre, artist_singles                  

country-> artist

song -> artist_singles, genre, artist, playlist_content
                
song_liked-> song, user     
          
album-> album_content, genre, artist
                                    
album_content-> album, artist            

artist_singles-> artist, song 
     
Genre ->  artist, album, song
            
playlist-> user, playlist_followed, playlist_content

playlist_followed-> playlist, user

user-> playlist, playlist_followed, user_followed, song liked

user_followed-> user
