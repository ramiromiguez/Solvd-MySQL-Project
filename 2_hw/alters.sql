USE `spotifi`;

ALTER TABLE `song` MODIFY `song_name` text(200);
ALTER TABLE `song` MODIFY `song_name` varchar(100);
ALTER TABLE playlist MODIFY playlist_date time;
ALTER TABLE playlist MODIFY playlist_date date;
ALTER TABLE playlist ADD playlist_length int;
ALTER TABLE playlist DROP COLUMN playlist_length;


