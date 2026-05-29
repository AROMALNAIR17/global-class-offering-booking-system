CREATE TABLE `course` (
`id` bigint NOT NULL AUTO_INCREMENT,
`description` varchar(255) DEFAULT NULL,
`title` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `offering` (
`id` bigint NOT NULL AUTO_INCREMENT,
`teacher_id` bigint DEFAULT NULL,
`teacher_timezone` varchar(255) DEFAULT NULL,
`title` varchar(255) DEFAULT NULL,
`course_id` bigint DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FK5u0uap1y9rcipagq6ogxyarhu` (`course_id`),
CONSTRAINT `FK5u0uap1y9rcipagq6ogxyarhu`
FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sessions` (
`id` bigint NOT NULL AUTO_INCREMENT,
`end_time_utc` datetime(6) DEFAULT NULL,
`start_time_utc` datetime(6) DEFAULT NULL,
`offering_id` bigint DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FK9hebcva46u1vmqprs2dwc553x` (`offering_id`),
CONSTRAINT `FK9hebcva46u1vmqprs2dwc553x`
FOREIGN KEY (`offering_id`) REFERENCES `offering` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `parents` (
`id` bigint NOT NULL AUTO_INCREMENT,
`name` varchar(255) DEFAULT NULL,
`timezone` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `booking` (
`id` bigint NOT NULL AUTO_INCREMENT,
`offering_id` bigint DEFAULT NULL,
`parent_id` bigint DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UKwf5ij3n3hwaqkuavp8hahst7` (`parent_id`,`offering_id`),
KEY `FKq9i42npfylv44tf3ktb2o1re` (`offering_id`),
CONSTRAINT `FK5tnnp379i5uafc488n3o16qvn`
FOREIGN KEY (`parent_id`) REFERENCES `parents` (`id`),
CONSTRAINT `FKq9i42npfylv44tf3ktb2o1re`
FOREIGN KEY (`offering_id`) REFERENCES `offering` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
