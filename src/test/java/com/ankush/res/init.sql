CREATE TABLE `users` (
    id INT NOT NULL AUTO_INCREMENT,
    name varchar(100),
    marks INT,
    PRIMARY KEY (`id`)

);
INSERT INTO `users` (`name`,`marks`) values ("A",90);
INSERT INTO `users` (`name`,`marks`) values ("B",80);