DROP DATABASE IF EXISTS `spotifi`;
CREATE DATABASE `spotifi`;
USE `spotifi`;

CREATE TABLE `country`(
	`country_id` int NOT NULL UNIQUE AUTO_INCREMENT,
    `country_name` varchar(255),
    PRIMARY KEY (`country_id`)
);

CREATE TABLE `genre`(
	`genre_id` int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    `genre_name` varchar(255)
);

CREATE TABLE `artist`(
	`artist_id` int NOT NULL UNIQUE PRIMARY KEY  AUTO_INCREMENT,
    `artist_name` varchar(255),
    `country_id` int,
    `genre_id` int,
    FOREIGN KEY (`country_id`) REFERENCES `country`(`country_id`),
    FOREIGN KEY (`genre_id`) REFERENCES `genre`(`genre_id`)
);

CREATE TABLE `song`(
	`song_id` int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    `song_name` varchar(255) NOT NULL,
	`song_duration` float,
    `genre_id` int,
    `artist_id` int,
    FOREIGN KEY (`artist_id`) REFERENCES `artist` (`artist_id`),
    foreign key (`genre_id`) REFERENCES `genre` (`genre_id`)
);

CREATE TABLE `artist_singles`(
    `artist_id` int NOT NULL,
    `song_id` int NOT NULL,
	FOREIGN KEY (`artist_id`) REFERENCES `artist` (`artist_id`),
    FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`)
);

CREATE TABLE IF NOT EXISTS `user`(
	`user_id` int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    `user_name` varchar(255),
    `user_birth` Date
);

CREATE TABLE IF NOT EXISTS `user_followed`(
	`user_id` int,
    `userF_id` int,
	FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    FOREIGN KEY (`userF_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `song_liked`(
	`songl_date` DATE,
    `user_id` int,
    `song_id` int,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`)
);

CREATE TABLE IF NOT EXISTS `album`(
	`album_id` int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    `album_name` varchar(255) NOT NULL,
    `artist_id` int,
    `genre_id` int,
    `album_length` int,
    foreign key (`artist_id`) references `artist` (`artist_id`),
    FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`)
);

CREATE TABLE IF NOT EXISTS `album_content`(
    `song_id` int,
    `album_id` int,
    FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
    FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`)
);

CREATE TABLE IF NOT EXISTS `playlist`(
	`playlist_id` int NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    `playlist_name` varchar(255),
    `playlist_date` Date,
    `user_id` int,
    foreign key (`user_id`) references `user` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `playlist_content`(
	`playlist_id` int,
    `song_id` int,
	FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`),
    foreign key (`song_id`) REFERENCES `song` (`song_id`)
);

CREATE TABLE IF NOT EXISTS `playlist_followed`(
	`user_id` int,
    `playlist_id` int,
	FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`)
);

INSERT INTO `country` (`country_name`) VALUES ("United States");
INSERT INTO `country` (`country_name`) VALUES ("Argentina");
INSERT INTO `country` (`country_name`) VALUES ("Israel");
INSERT INTO `country` (`country_name`) VALUES ("Germani");
INSERT INTO `country` (`country_name`) VALUES ("Peru");

INSERT INTO `genre` (`genre_name`) VALUES ("Trap");
INSERT INTO `genre` (`genre_name`) VALUES ("Psytrance");
INSERT INTO `genre` (`genre_name`) VALUES ("Heavy Metal");
INSERT INTO `genre` (`genre_name`) VALUES ("Rap");

INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Paco Amoroso", 2, 1);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Catriel", 2, 1);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Dillom", 2, 1);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Night Lovell", 1, 1);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Suicide Boys", 1, 1);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Lil Peep", 1, 1);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Bullet For My Valentine", 1, 3);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("Astrix", 3, 2);
INSERT INTO `artist` (`artist_name`, `country_id`, `genre_id`) VALUES ("T&k", 2, 4);

INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Chinga Sport", 2.14, 1, 1);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Para la Forever", 3.13, 1, 2);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("La primera", 3.19, 1, 3);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Dark Light", 3.26, 1, 4);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Black Beard", 2.38, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Broke(n)", 2.13, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("The Crescent Moon and the Rising Sun", 2.27, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Everest", 2.04, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Let 'Em Burn", 1.53, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Second Coming", 1.44, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Oracle", 1.50, 1, 5);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("your favourite dress", 2.57, 1, 6);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Alone", 5.56, 3, 7);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("He.art", 8.01, 2, 8);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("intro", 2.01, 4, 9);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("you dont know", 5.01, 4, 9);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("crema", 3.01, 4, 9);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("rela", 1.01, 4, 9);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Una letra mas", 3.21, 4, 9);
INSERT INTO `song` (`song_name`, `song_duration`, `genre_id`, `artist_id`) VALUES ("Wc", 2.21, 4, 9);

INSERT INTO `artist_singles` (`artist_id`, `song_id`) VALUES (6, 12);

INSERT INTO `user` (`user_name`, `user_birth`) VALUES ("Ramiro", 16/08/1999);
INSERT INTO `user` (`user_name`, `user_birth`) VALUES ("Manuel", 30/01/2004);
INSERT INTO `user` (`user_name`, `user_birth`) VALUES ("Sergey", 22/01/1997);

INSERT INTO `user_followed` (`user_id`, `userf_id`) VALUES (1, 2);
INSERT INTO `user_followed` (`user_id`, `userf_id`) VALUES (2, 1);
INSERT INTO `user_followed` (`user_id`, `userf_id`) VALUES (1, 3);

TRUNCATE TABLE `song_liked`;
INSERT INTO `song_liked` (`songl_date`, `user_id`, `song_id`) VALUES ("2021-12-11", 1, 11);
INSERT INTO `song_liked` (`songl_date`, `user_id`, `song_id`) VALUES ("2021-12-12", 2, 14);
INSERT INTO `song_liked` (`songl_date`, `user_id`, `song_id`) VALUES ("2021-12-16", 1, 13);
INSERT INTO `song_liked` (`songl_date`, `user_id`, `song_id`) VALUES ("2021-11-14", 1, 1);
INSERT INTO `song_liked` (`songl_date`, `user_id`, `song_id`) VALUES ("2021-09-14", 2, 4);
INSERT INTO `song_liked` (`songl_date`, `user_id`, `song_id`) VALUES ("2021-07-14", 2, 5);

INSERT INTO `album` (`album_name`, `artist_id`, `genre_id`, `album_length`) VALUES ("GREY DAYS", 5, 1, 7);
INSERT INTO `album` (`album_name`, `artist_id`, `genre_id`, `album_length`) VALUES ("Writing classics", 9, 4, 6);

INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (5, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (6, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (7, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (8, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (9, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (10, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (11, 1);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (15, 2);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (16, 2);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (17, 2);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (18, 2);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (19, 2);
INSERT INTO `album_content` (`song_id`, `album_id`) VALUES (20, 2);

INSERT INTO `playlist` (`playlist_name`, `playlist_date`, `user_id`) VALUES ("Play list Trap Hits", "2021-12-08", 1);
INSERT INTO `playlist` (`playlist_name`, `playlist_date`, `user_id`) VALUES ("Play list mix", "2021-02-13", 2);

INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (1, 1);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (1, 2);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (1, 3);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (1, 4);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (1, 5);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (1, 11);

INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (2, 1);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (2, 12);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (2, 13);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (2, 7);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (2, 9);
INSERT INTO `playlist_content` (`playlist_id`, `song_id`) VALUES (2, 4);

INSERT INTO `playlist_followed` (`user_id`, `playlist_id`) VALUES (2,1);