USE `spotifi`;

UPDATE `country` SET `country_name` = "Germany" WHERE `country_id` = 4;
UPDATE `artist` SET `artist_name` = "$uicideBoy$" WHERE `artist_name` = "Suicide Boys";
UPDATE `artist` SET `artist_name` = "boys" WHERE `artist_name` LIKE "%$uici%";
UPDATE `artist` SET `artist_name` = "Cato" WHERE `artist_name` LIKE "%at%";
UPDATE `country` SET `country_name` = "Argen" WHERE length(`country_name`) > 5 AND `country_id` = 2;
UPDATE `song` SET `song_name` = "";
UPDATE `song` SET `song_name` = "deleted names" WHERE length(`song_name`) = 0;
UPDATE `song` SET `song_duration` = `song_duration` + 1;
UPDATE `song` SET `song_duration` = 999, `song_name` = "sergey" WHERE `song_duration` < 4;
UPDATE `genre` SET `genre_name` = "Fake rap" WHERE `genre_name` = "Trap";


