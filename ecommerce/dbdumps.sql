-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2020 at 11:09 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` bigint(20) NOT NULL,
  `total_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cart_id`, `total_price`) VALUES
(1, 38260),
(2, 1356);

-- --------------------------------------------------------

--
-- Table structure for table `cartitem`
--

CREATE TABLE `cartitem` (
  `cart_item_id` bigint(20) NOT NULL,
  `cart_item_price` double DEFAULT NULL,
  `cart_item_quantity` int(11) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cartitem`
--

INSERT INTO `cartitem` (`cart_item_id`, `cart_item_price`, `cart_item_quantity`, `cart_id`, `product_id`) VALUES
(9, 452, 5, 1, 1),
(10, 18000, 2, 1, 4),
(11, 452, 3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `email_id` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`email_id`, `age`, `delivery_address`, `gender`, `name`, `password`, `cart_id`) VALUES
('pulkitguptasonu@gmail.com', 24, 'KKR', 'Male', 'Pulkit Gupta', '6789', 1),
('shubhamkaushal@gmail.com', 24, 'Yamunagar', 'Male', 'Shubham Kaushal', '1256', 2);

-- --------------------------------------------------------

--
-- Table structure for table `item_details_for_order`
--

CREATE TABLE `item_details_for_order` (
  `id` bigint(20) NOT NULL,
  `order_item_quantity` int(11) DEFAULT NULL,
  `orders_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_details_for_order`
--

INSERT INTO `item_details_for_order` (`id`, `order_item_quantity`, `orders_id`, `product_id`) VALUES
(2, 3, 1, 5),
(3, 4, 1, 2),
(4, 5, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL,
  `actual_date` date DEFAULT NULL,
  `advance_payment` bit(1) DEFAULT NULL,
  `expected_date` date DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `actual_date`, `advance_payment`, `expected_date`, `payment_method`, `status`, `total_price`, `email_id`) VALUES
(1, NULL, b'0', '2020-12-22', 'Cash', 'PENDING', 40260, 'pulkitguptasonu@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL,
  `product_category` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_category`, `product_name`, `product_price`) VALUES
(1, 'Perfume', 'Nivea', 452),
(2, 'Perfume', 'Fog', 500),
(3, 'Mobile', 'Samsung M21', 15000),
(4, 'Mobile', 'Samsung M31', 18000),
(5, 'Mobile', 'OPPO', 12000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`);

--
-- Indexes for table `cartitem`
--
ALTER TABLE `cartitem`
  ADD PRIMARY KEY (`cart_item_id`),
  ADD KEY `FKcj0jvvlv7mum72m5qblw5m1tc` (`cart_id`),
  ADD KEY `FK1h6e1eb88o4qyku4y34inyr72` (`product_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`email_id`),
  ADD KEY `FKam4cgy6fxmjm52m8otoph84m3` (`cart_id`);

--
-- Indexes for table `item_details_for_order`
--
ALTER TABLE `item_details_for_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc17e5bir2th5k22u7txfdijiv` (`orders_id`),
  ADD KEY `FKi5ik2ju7qcjaieequ9tryu7tc` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FKqhrffdfnm7yy3tg01qklb5ps0` (`email_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `cartitem`
--
ALTER TABLE `cartitem`
  MODIFY `cart_item_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `item_details_for_order`
--
ALTER TABLE `item_details_for_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cartitem`
--
ALTER TABLE `cartitem`
  ADD CONSTRAINT `FK1h6e1eb88o4qyku4y34inyr72` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  ADD CONSTRAINT `FKcj0jvvlv7mum72m5qblw5m1tc` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FKam4cgy6fxmjm52m8otoph84m3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`);

--
-- Constraints for table `item_details_for_order`
--
ALTER TABLE `item_details_for_order`
  ADD CONSTRAINT `FKc17e5bir2th5k22u7txfdijiv` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `FKi5ik2ju7qcjaieequ9tryu7tc` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKqhrffdfnm7yy3tg01qklb5ps0` FOREIGN KEY (`email_id`) REFERENCES `customer` (`email_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
