-- -------------------------------------------------------------
-- TablePlus 5.3.6(496)
--
-- https://tableplus.com/
--
-- Database: swoshdb
-- Generation Time: 2023-06-02 21:51:34.7440
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) DEFAULT NULL,
  `balance` decimal(10,0) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_account` varchar(50) DEFAULT NULL,
  `receiver_account` varchar(50) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `personal_number` varchar(12) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `account` (`id`, `account_name`, `balance`, `user_id`, `created`) VALUES
(1, '99', 350, 1, '2023-05-30 10:49:01'),
(3, '100', 550, 4, '2023-06-01 11:21:19'),
(4, '101', 550, 4, '2023-06-01 11:21:40'),
(5, '104', 450, 5, '2023-06-01 11:23:47');

INSERT INTO `transactions` (`id`, `sender_account`, `receiver_account`, `amount`, `timestamp`, `user_id`) VALUES
(1, '99', '100', 150, '2023-05-01 12:37:23', 4),
(2, '100', '101', 300, '2023-05-10 20:43:45', 4),
(3, '100', '99', 150, '2023-05-11 20:43:54', 1),
(4, '100', '102', 300, '2023-06-02 20:44:03', 1),
(5, '101', '104', 200, '2023-06-02 21:37:02', 4);

INSERT INTO `users` (`id`, `name`, `personal_number`, `address`, `phone_number`, `password`, `email`, `created`) VALUES
(1, 'Max', '9404285332', 'Hedvägen 1', '0766443344', '123', 'max@email.se', '2023-05-29 22:38:44'),
(4, 'Sebbe', '123', 'Staffanstorp', '1231231231', '123', 'sebbe@mail.se', '2023-05-30 11:08:35'),
(5, 'Pontus', '9804053030', 'Malmö', '0708091233', '123', 'pontus@mail.se', '2023-06-01 11:22:57');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;