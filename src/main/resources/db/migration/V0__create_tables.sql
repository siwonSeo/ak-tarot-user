-- tarot.tarot_card definition

CREATE TABLE `tarot_card` (
  `card_id` int NOT NULL COMMENT '카드 아이디',
  `card_number` int NOT NULL COMMENT '번호',
  `card_type` varchar(10) NOT NULL COMMENT '카드 타입',
  `card_number_name` varchar(20) NOT NULL COMMENT '번호명',
  `card_name` varchar(50) NOT NULL COMMENT '카드 이름',
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `cardConstraint` (`card_type`,`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_category definition

CREATE TABLE `tarot_card_category` (
  `category_code` char(255) NOT NULL COMMENT '카테고리 코드',
  `category_name` varchar(255) NOT NULL COMMENT '카테고리 명',
  PRIMARY KEY (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_interpretation definition

CREATE TABLE `tarot_card_interpretation` (
  `card_id` int NOT NULL COMMENT '카드 아이디',
  `interpretation_id` int NOT NULL COMMENT '해석 아이디',
  `is_reversed` bit(1) NOT NULL COMMENT '역방향 여부',
  `category_code` char(255) NOT NULL COMMENT '카테고리 코드',
  `content` varchar(255) NOT NULL COMMENT '내용',
  PRIMARY KEY (`interpretation_id`),
  KEY `FK8x4o4aba0oc547jdfd26hqe1e` (`card_id`),
  KEY `FKawk6b7bkdd3cjymv82m7k6e8r` (`category_code`),
  CONSTRAINT `FK8x4o4aba0oc547jdfd26hqe1e` FOREIGN KEY (`card_id`) REFERENCES `tarot_card` (`card_id`),
  CONSTRAINT `FKawk6b7bkdd3cjymv82m7k6e8r` FOREIGN KEY (`category_code`) REFERENCES `tarot_card_category` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_keyword definition

CREATE TABLE `tarot_card_keyword` (
  `card_id` int NOT NULL COMMENT '카드 아이디',
  `is_reversed` bit(1) NOT NULL COMMENT '역방향 여부',
  `keyword_id` int NOT NULL COMMENT '키워드 아이디',
  `keyword` varchar(30) NOT NULL COMMENT '키워드',
  PRIMARY KEY (`keyword_id`),
  UNIQUE KEY `cardKeyWordConstraint` (`card_id`,`is_reversed`,`keyword`),
  CONSTRAINT `FK8urxypuetwf5ptj54wjtvr1d1` FOREIGN KEY (`card_id`) REFERENCES `tarot_card` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_reading_method definition

CREATE TABLE `tarot_card_reading_method` (
  `card_count` int NOT NULL COMMENT '카드 갯수',
  `method_id` int NOT NULL COMMENT '방법 아이디',
  `method_order` int NOT NULL COMMENT '방법 순서',
  `description` text NOT NULL COMMENT '설명',
  `method_name` varchar(255) NOT NULL COMMENT '방법명',
  PRIMARY KEY (`method_id`),
  UNIQUE KEY `readingMethodConstraint` (`card_count`,`method_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_reading_method_position definition

CREATE TABLE `tarot_card_reading_method_position` (
  `method_id` int NOT NULL COMMENT '방법 아이디',
  `position_id` int NOT NULL COMMENT '위치 아이디',
  `position_order` int NOT NULL COMMENT '위치 순서',
  `position_name` varchar(255) NOT NULL COMMENT '위치명',
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `readingMethodPositionConstraint` (`method_id`,`position_order`),
  CONSTRAINT `FKi12q8krt6y26cwdfey3adgdup` FOREIGN KEY (`method_id`) REFERENCES `tarot_card_reading_method` (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.user_base definition

CREATE TABLE `user_base` (
  `id` int NOT NULL COMMENT '유저 아이디',
  `email` varchar(255) NOT NULL COMMENT '이메일',
  `name` varchar(255) NOT NULL COMMENT '이름',
  `picture` varchar(255) NOT NULL COMMENT '사진',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userConstraint` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.user_base_interpretation definition

CREATE TABLE `user_base_interpretation` (
  `card_count` int NOT NULL COMMENT '선택 카드 갯수',
  `id` int NOT NULL COMMENT '해설 아이디',
  `is_reverse_on` bit(1) NOT NULL COMMENT '역방향 포함 여부',
  `user_id` int NOT NULL COMMENT '유저 ID',
  `created_at` datetime(6) DEFAULT NULL,
  `category_code` char(255) NOT NULL COMMENT '카테고리 코드',
  `search_cards` json NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm599yuobkein4b2c9i7r9lqb` (`user_id`),
  CONSTRAINT `FKm599yuobkein4b2c9i7r9lqb` FOREIGN KEY (`user_id`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tarot.tarot_card_interpretation_seq definition

CREATE TABLE `tarot_card_interpretation_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_reading_method_position_seq definition

CREATE TABLE `tarot_card_reading_method_position_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.tarot_card_reading_method_seq definition

CREATE TABLE `tarot_card_reading_method_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.user_base_interpretation_seq definition

CREATE TABLE `user_base_interpretation_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- tarot.user_base_seq definition

CREATE TABLE `user_base_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
