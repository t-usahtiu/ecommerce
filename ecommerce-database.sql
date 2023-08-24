CREATE DATABASE  IF NOT EXISTS `ecommerce-database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce-database`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce-database
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Ho Chi Minh City','VietNam','Quan 5','Nguyen Hue'),(2,'Ho Chi Minh City','VietNam','Quan 5','Nguyen Hue'),(3,'Ha Noi','VietNam','Nam Tu Liem','My Dinh'),(4,'Ha Noi','VietNam','Nam Tu Liem','My Dinh'),(5,'Ha Noi','VietNam','Nam Tu Liem','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(6,'Ha Noi','VietNam','Nam Tu Liem','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(7,'Ha Noi','VietNam','Cầu Giấy','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(8,'Ha Noi','VietNam','Cầu Giấy','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(9,'Ha Noi','VietNam','Cầu Giấy','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(10,'Ha Noi','VietNam','Cầu Giấy','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(11,'Ha Noi','VietNam','Nam Tu Liem','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy'),(12,'Ha Noi','VietNam','Nam Tu Liem','15B ngách 56 ngõ 105 doãn kế thiện, mai dịch, cầu giấy');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `cart_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cart_id` (`cart_id`),
  KEY `FK_product_cart_id` (`product_id`),
  CONSTRAINT `FK_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`),
  CONSTRAINT `FK_product_cart_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (21,1,24.99,1,2),(33,4,1499.99,16,1);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total_quantity` int DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_user_id` (`user_id`),
  CONSTRAINT `FK_user_cart_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,4,5999.96,1,NULL,'2023-08-04 15:48:13.185000','2023-08-18 19:35:05.755000'),(2,3,64.97,2,NULL,'2023-08-04 16:49:13.185000','2023-08-19 12:29:31.068000'),(3,0,0.00,3,NULL,'2023-08-05 11:39:16.362000','2023-08-11 17:09:13.331000'),(4,0,0.00,4,NULL,'2023-08-05 18:11:33.100000','2023-08-05 18:11:33.100000'),(5,0,0.00,5,NULL,'2023-08-08 15:59:12.041000','2023-08-18 16:45:46.074000'),(6,0,0.00,6,NULL,'2023-08-08 16:07:24.041000','2023-08-11 17:11:01.100000'),(7,0,0.00,7,NULL,'2023-08-08 16:09:57.842000','2023-08-08 16:09:57.842000'),(8,0,0.00,8,NULL,'2023-08-10 21:40:09.869000','2023-08-10 21:40:09.869000'),(9,0,0.00,9,NULL,'2023-08-11 11:49:08.471000','2023-08-11 17:10:33.821000'),(10,0,0.00,10,NULL,'2023-08-16 16:36:35.022000','2023-08-16 16:43:37.551000');
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `country_id` smallint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_country` (`country_id`),
  CONSTRAINT `fk_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Ha Noi',1),(2,'Ho Chi Minh City',1),(3,'Hai Phong',1),(4,'Da Nang',1),(5,'Can Tho',1),(6,'Quang Ninh',1),(7,'Ha Nam',1),(8,'Bac Ninh',1),(9,'Nam Dinh',1),(10,'Thai Binh',1),(11,'Thanh Hoa',1),(12,'Hue',1),(13,'Quang Tri',1),(14,'Dak Lak',1),(15,'Long An',1),(16,'Alberta',2),(17,'British Columbia',2),(18,'Manitoba',2),(19,'New Brunswick',2),(20,'Newfoundland and Labrador',2),(21,'Northwest Territories',2),(22,'Nova Scotia',2),(23,'Nunavut',2),(24,'Ontario',2),(25,'Prince Edward Island',2),(26,'Baden-Württemberg',3),(27,'Bavaria',3),(28,'Berlin',3),(29,'Brandenburg',3),(30,'Bremen',3),(31,'Hamburg',3),(32,'Hesse',3),(33,'Lower Saxony',3),(34,'Mecklenburg-Vorpommern',3),(35,'North Rhine-Westphalia',3),(36,'Andhra Pradesh',4),(37,'Arunachal Pradesh',4),(38,'Assam',4),(39,'Bihar',4),(40,'Chhattisgarh',4),(41,'Goa',4),(42,'Gujarat',4),(43,'Haryana',4),(44,'Himachal Pradesh',4),(45,'Jammu & Kashmir',4),(46,'Jharkhand',5),(47,'Karnataka',5),(48,'Kerala',5),(49,'Madhya Pradesh',5),(50,'Maharashtra',5),(51,'Manipur',5),(52,'Meghalaya',5),(53,'Mizoram',5),(54,'Nagaland',5),(55,'Odisha',5),(56,'Washington',6),(57,'New York',6),(58,'Arizona',6),(59,'Arkansas',6),(60,'California',6),(61,'Colorado',6),(62,'Connecticut',6),(63,'Delaware',6),(64,'District Of Columbia',6),(65,'Florida',6),(66,'Georgia',6),(67,'Hawaii',6);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'VN','VietNam'),(2,'SI','Singapore'),(3,'GE','Germany'),(4,'US','United States'),(5,'JP','Japan'),(6,'KO','Korea');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_order_id` (`order_id`),
  KEY `FK_product_order_id` (`product_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_product_order_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'https://cf.shopee.ph/file/f6f74e9a15b099f09d1dda3b139c5134',1,249.99,1,21),(2,'https://ansancosmetics.com/fileman/Uploads/son_kem_li_s_girl_m_o_i_cosmetic.jpg',1,35.99,1,6),(3,'https://imgs.viettelstore.vn/Images/Product/ProductImage/dien-thoai/Apple/iPhone%2014%20Pro%20Max%20128/iPhone-14-Pro-Max-1.jpg',1,1499.99,1,16),(4,'https://bizweb.dktcdn.net/100/331/067/products/315420771-2393079724181029-7080855780144156298-n-1.jpg?v=1668224035747',1,99.99,1,22),(5,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9eOBCWgmZcoqoK1dkGx-DQsnOkrurrTrvQA&usqp=CAU',1,24.99,2,1),(6,'https://m.media-amazon.com/images/I/31KAxJ0CRTS.jpg',1,29.99,2,3),(7,'https://imgs.viettelstore.vn/Images/Product/ProductImage/dien-thoai/Apple/iPhone%2014%20Pro%20Max%20128/iPhone-14-Pro-Max-1.jpg',1,1499.99,3,16),(8,'https://ansancosmetics.com/fileman/Uploads/son_kem_li_s_girl_m_o_i_cosmetic.jpg',1,35.99,4,6),(9,'https://www.thekooples.com/dw/image/v2/BGQL_PRD/on/demandware.static/-/Sites-skp-master-catalog/default/dw2b40f499/images/hi-res/FP1448BNAV01_F.jpg?sw=900&sh=1105',1,74.99,5,25),(10,'https://www.thekooples.com/dw/image/v2/BGQL_PRD/on/demandware.static/-/Sites-skp-master-catalog/default/dw2b40f499/images/hi-res/FP1448BNAV01_F.jpg?sw=900&sh=1105',1,74.99,6,25),(11,'https://imgs.viettelstore.vn/Images/Product/ProductImage/dien-thoai/Apple/iPhone%2014%20Pro%20Max%20128/iPhone-14-Pro-Max-1.jpg',1,1499.99,6,16),(12,'https://bizweb.dktcdn.net/100/331/067/products/315420771-2393079724181029-7080855780144156298-n-1.jpg?v=1668224035747',1,99.99,6,22);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_tracking_number` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `user_info_id` bigint NOT NULL,
  `billing_address_id` bigint NOT NULL,
  `shipping_address_id` bigint NOT NULL,
  `status` varchar(128) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_billing_address_id` (`billing_address_id`),
  UNIQUE KEY `UK_shipping_address_id` (`shipping_address_id`),
  UNIQUE KEY `UK_user_info_id` (`user_info_id`) /*!80000 INVISIBLE */,
  KEY `K_user_info_id` (`user_info_id`),
  CONSTRAINT `FK_billing_address_id` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_shipping_address_id` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_user_info_id` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'99c02697-14b4-4c9d-81dc-43d7d99c8095',1885.96,4,1,1,2,NULL,'2023-08-15 21:27:39.591000','2023-08-15 21:27:39.591000'),(2,'8ce55a60-23a3-4f11-979e-6001a808d2b0',54.98,2,2,3,4,NULL,'2023-08-15 21:32:49.115000','2023-08-15 21:32:49.115000'),(3,'55a4cc3a-c03a-46d4-8634-3ef53662c02c',1499.99,1,3,5,6,NULL,'2023-08-16 16:37:42.550000','2023-08-16 16:37:42.550000'),(4,'80fec49c-9a65-437e-b8b2-2c3e48779d0e',35.99,1,4,7,8,NULL,'2023-08-16 16:41:32.447000','2023-08-16 16:41:32.447000'),(5,'83a35c98-a472-4042-af59-4c448342b901',74.99,1,5,9,10,NULL,'2023-08-16 16:43:36.095000','2023-08-16 16:43:36.095000'),(6,'d91f1826-4fb0-4683-b0e4-74ec803056cf',1674.97,3,6,11,12,NULL,'2023-08-18 16:45:45.223000','2023-08-18 16:45:45.223000');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sku` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity_in_stock` int DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'BOOK-1000','AngularJS Framework','This is a good book',24.99,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9eOBCWgmZcoqoK1dkGx-DQsnOkrurrTrvQA&usqp=CAU',68,'2023-08-09 17:17:59.366000','2023-08-09 17:17:59.366000',1),(3,'BOOK-1002','Spring Framework Tutorial','Learn Spring',29.99,'https://m.media-amazon.com/images/I/31KAxJ0CRTS.jpg',62,'2023-08-09 17:23:09.260000','2023-08-09 17:23:09.260000',1),(4,'BOOK-1003','The Go Programming Language: A to Z','Learn Go',27.99,'https://go.dev/images/learn/get-programming-with-go.jpeg',37,'2023-08-09 17:25:23.866000','2023-08-09 17:25:23.866000',1),(5,'BOOK-1004','NodeJS form zero to hero','Learn NodeJS',26.99,'https://m.media-amazon.com/images/I/41lDk1HZLqL.jpg',42,'2023-08-09 17:27:40.629000','2023-08-09 17:27:40.629000',1),(6,'COS-1000','Lipstick','This is a good cosmetic',35.99,'https://ansancosmetics.com/fileman/Uploads/son_kem_li_s_girl_m_o_i_cosmetic.jpg',48,'2023-08-09 17:50:49.748000','2023-08-10 11:50:12.858000',4),(16,'SMP-1000','Iphone 14 Pro Max 1T','This is a great smartphone!',1499.99,'https://imgs.viettelstore.vn/Images/Product/ProductImage/dien-thoai/Apple/iPhone%2014%20Pro%20Max%20128/iPhone-14-Pro-Max-1.jpg',12,'2023-08-10 11:52:07.758000','2023-08-10 11:54:57.995000',2),(18,'COS-1001','Face Cream','This is a good face cream!',44.99,'https://shindy.vn/wp-content/uploads/2020/11/ZEN_0646.jpg',48,'2023-08-10 17:14:58.926000','2023-08-15 21:41:16.414000',4),(20,'SMP-1001','Samsung S22 Ultra','This is a great smartphone!',1299.99,'https://mybroadband.co.za/news/wp-content/uploads/2022/02/Galaxy-S22-headline-.jpg',78,'2023-08-10 23:37:59.214000','2023-08-10 23:37:59.214000',2),(21,'KCH-1000','Kitchen houseware','It\'s good stuff!',249.99,'https://cf.shopee.ph/file/f6f74e9a15b099f09d1dda3b139c5134',23,'2023-08-10 23:46:01.029000','2023-08-10 23:46:01.029000',5),(22,'JKT-1000','Jacket YG ','It is a good jacket!',99.99,'https://bizweb.dktcdn.net/100/331/067/products/315420771-2393079724181029-7080855780144156298-n-1.jpg?v=1668224035747',75,'2023-08-10 23:48:40.233000','2023-08-10 23:48:40.233000',3),(23,'PFM-1000','Perfume BearDo','This is a good pefume',149.99,'https://beardo.in/cdn/shop/products/ThugLifePerfumeCombo2160x2160.jpg?v=1681753600&width=1946',45,'2023-08-10 23:55:25.408000','2023-08-10 23:55:25.408000',4),(24,'KCH-1001','The cutting board','This is a good cutting board!',69.99,'https://www.foodandwine.com/thmb/vGP1uSRAmViVYIINnkGtlu3Vwq8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/1-5d21737fc6b146e9884924572f0c325a.jpg',75,'2023-08-15 21:46:54.222000','2023-08-15 21:46:54.222000',5),(25,'JKT-1001','Trouser','This is a best trouser!',74.99,'https://www.thekooples.com/dw/image/v2/BGQL_PRD/on/demandware.static/-/Sites-skp-master-catalog/default/dw2b40f499/images/hi-res/FP1448BNAV01_F.jpg?sw=900&sh=1105',64,'2023-08-15 21:50:28.643000','2023-08-15 21:50:28.643000',3),(26,'ELD-1000','Music speaker','This is a good speaker!',89.99,'https://salt.tikicdn.com/ts/product/21/f6/c2/2de4041390b2ca0b863bd57e955d0ce1.jpg',71,'2023-08-16 16:47:28.893000','2023-08-16 16:47:28.893000',6);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'Books'),(2,'Smartphones'),(3,'Clothings'),(4,'Cosmetics'),(5,'Housewares'),(6,'Electric Devices');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role_id` (`user_id`),
  CONSTRAINT `FK_user_role_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER',1),(2,'ROLE_ADMIN',1),(3,'ROLE_USER',2),(4,'ROLE_USER',3),(5,'ROLE_USER',4),(6,'ROLE_USER',5),(7,'ROLE_USER',6),(8,'ROLE_USER',7),(9,'ROLE_USER',8),(10,'ROLE_USER',9),(11,'ROLE_USER',10);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `K_user_id` (`user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'Maria','Rivera','0987936210','maria@gmail.com',2),(2,'Mark','Faulo','0912754632','mark@gmail.com',5),(3,'Tú','Tiêu','0987936210','tukaka057@gmail.com',10),(4,'Tú','Tiêu','0987936210','tukaka057@gmail.com',10),(5,'Tú','Tiêu','0987936210','tukaka057@gmail.com',10),(6,'Tú','Tiêu','0987936210','tukaka057@gmail.com',5);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tusanh','$2a$10$rfdIE3QqU7ULE/kilUC0E.XEfL0e4xz4mQtcnMTdgu2qMj8cWDbca',NULL,'0987936210','tuanhtieu02@gmail.com'),(2,'maria','$2a$10$p0S8LPdQMIqMrJcA0EXsw.tu3RPIhEvJsk1YS8eKD38ISvyhsV7ti',NULL,NULL,NULL),(3,'kelly','$2a$10$7g4QXC5Gz5QRtfoH7LuwgO8AilEbTacaRbjouP4HctF/YFtdhsna2',NULL,NULL,NULL),(4,'adamo','$2a$10$VCPIeHuMGjpc/qOZdqWBoeGhz1VlrBxKg2MWE4zQF1vstF78sryIG',NULL,NULL,NULL),(5,'mark','$2a$10$mJMJA8C5i5sRYNKhlOoX8uDIudDULRn82635h3A/52kWsCI9wu2oS',NULL,NULL,NULL),(6,'bobbee','$2a$10$WWgIsDvsT61vCE8n5UEXVOx3qFSmN.2PbWLz5X8eY0NV1vqzmuJL6',NULL,NULL,NULL),(7,'test','$2a$10$2Q3f2nwaJpOOAjl/1JLbyucgsDS18kqXrkZox3WO6wbMpy7bpy.9u',NULL,NULL,NULL),(8,'mary','$2a$10$nb3J2Wfzi8hL4h/W.bda3u0y/WeGcoASAo8v1YEFum7jCduxXrvpO',NULL,NULL,NULL),(9,'hoon','$2a$10$ejyqkUO7p4cqIGxnBB9EuejmfNwF.6L/6.euSCNO.XWqR.E/0vmPW',NULL,NULL,NULL),(10,'chienpc','$2a$10$SobIfptJ1NijV5x1wCfW2Oqmgye/XP/Fx7f047jaaqbhZviNBex66',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-22 15:54:09
