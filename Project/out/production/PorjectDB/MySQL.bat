cd C:\Program Files\MySQL\MySQL Server 5.7\bin
mysql -u root -p -h localhost


use mydatabase;


show variables like '%char%';

CREATE DATABASE dbname DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE author (
    name char(20) NOT NULL,
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;



CREATE TABLE author (name char(20) NOT NULL) DEFAULT CHARSET=utf8;

INSERT INTO card (front,back) VALUES ("§A¦n", "Hello");
