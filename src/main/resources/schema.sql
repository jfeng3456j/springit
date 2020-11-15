CREATE DATABASE IF NOT EXISTS `springit`;
USE `springit`;


--
--Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `created_by` varchar(255) DEFAULT NULL,
    `creation_date` datetime DEFAULT NULL,
    `last_modified_by` VARCHAR(255) DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    `body` varchar(255) DEFAULT NULL,
    `link_id` bigint(20) DEFAULT NULL,
    primary key (`id`),
    KEY `FKKENKKFKJ908IKDKFvmfkdke` (`link_id`)
) ENGINE = myISAM DEFAULT charset=UTF8MB4 collate=UTF8MB4_0900_ai_ci;


--
--Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;

CREATE TABLE `link` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `created_by` varchar(255) DEFAULT NULL,
    `creation_date` datetime DEFAULT NULL,
    `last_modified_by` VARCHAR(255) DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    `title` varchar(255) DEFAULT NULL,
    `url` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = myISAM DEFAULT charset=UTF8MB4 collate=UTF8MB4_0900_ai_ci;