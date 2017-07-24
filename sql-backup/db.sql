CREATE TABLE `Survey` (
  `id` bigint(20) NOT NULL,
  `creationTime` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `pesel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `preferedDay` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `preferedSpecialization` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `User` (
  `id` bigint(20) NOT NULL,
  `admin` tinyint(1) DEFAULT '0',
  `creationTime` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e6gkqunxajvyxl5uctpl2vl2p` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `doctorSurname` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `doctorSpecialization` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `doctorCalendar` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `term` datetime DEFAULT NULL,
  `selected` tinyint(4) DEFAULT '0',
  `survey_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5qfuttqg50us2cy0wa3jkg34j` (`survey_id`),
  CONSTRAINT `FK5qfuttqg50us2cy0wa3jkg34j` FOREIGN KEY (`survey_id`) REFERENCES `Survey` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
