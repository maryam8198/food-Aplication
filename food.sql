-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 16, 2022 at 09:26 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `food`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id-cart` int(50) NOT NULL,
  `id_product` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `id_user` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `price` int(100) NOT NULL,
  `pic_product` varchar(250) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id_order` int(50) NOT NULL,
  `id_user` int(50) NOT NULL,
  `id_product` int(5) NOT NULL,
  `price` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `poroduct`
--

CREATE TABLE `poroduct` (
  `id_product` int(50) NOT NULL,
  `name_product` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `price_product` int(100) NOT NULL,
  `dis_product` varchar(150) COLLATE utf8_persian_ci NOT NULL,
  `pic_product` varchar(250) COLLATE utf8_persian_ci NOT NULL,
  `type_product` varchar(50) COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `poroduct`
--

INSERT INTO `poroduct` (`id_product`, `name_product`, `price_product`, `dis_product`, `pic_product`, `type_product`) VALUES
(1, 'میکس', 75000, '200گرم گوشت خالص و مرغ، نان طمع دار شده، سس ناجو، سیب زمینی نتوری', 'https://irancook.ir/wp-content/uploads/2018/12/Chicken-Burger.jpg', 'برگر'),
(2, 'بیف', 58000, '150 گرم گوشت خالص، نان طمع دار شده ، سس ناجو', 'https://gymito.com/blog/wp-content/uploads/2019/07/%D8%B7%D8%B1%D8%B2-%D8%AA%D9%87%DB%8C%D9%87-%D9%87%D9%85%D8%A8%D8%B1%DA%AF%D8%B1.jpg', 'برگر'),
(3, 'چیز برگر ', 91000, '200 گرم گوشت خالص، سس مخصوص ، نان طمع دار شده، پنیر، سیب زمینی تنوری', 'https://saednews.com/storage/media-center/images/ac-image-Ht1597552103mY.jpeg', 'برگر'),
(4, 'ماشروم', 78000, '150 گرم گوشت خالص ،نان طمع دار شده، قارچ، سس مخصوص', 'https://ashmazi.com/wp-content/uploads/2020/11/%D8%B7%D8%B1%D8%B2-%D8%AA%D9%87%DB%8C%D9%87-%D9%88-%D8%AF%D8%B3%D8%AA%D9%88%D8%B1-%D9%BE%D8%AE%D8%AA-%D9%87%D9%85%D8%A8%D8%B1%DA%AF%D8%B1-%D9%85%D8%AE%D8%B5%D9%88%D8%B5.jpg', 'برگر'),
(5, 'پیتزا پپرونی', 82000, 'پنیر، سس مخصوص ، پپرونی، قارچ', 'https://static1.shomanews.com/servev2/MWlq475A4clB/b54EPYiYwLU,/Mo2Wr7xUdsel.jpg', 'پیتزا'),
(6, 'سبزیجات', 62000, 'سبزیجات، پنیر، سس چیکن پستو، قارچ', 'https://gffood.ir/wp-content/uploads/american-pizza-royal.jpg', 'پیتزا'),
(7, 'سزار', 71000, 'سس سزار، فیله مرغ، قارچ، پنیر', 'https://static3.bartarinha.ir/servev2/jVhYmVlYTBhO/5Uwvb7W7Zm0,/file.jpg', 'پیتزا'),
(8, 'ناپولی', 91000, 'راسته گوساله رست شده، پنیر،زیتون، گوجه گیلاسی، سس مخصوص', 'http://www.mainchimaill.ir/files/user/1577561187.jpg', 'پیتزا'),
(9, 'فیله استیک', 156000, '200 گرم فیله گوساله ، سبزیجات گریل شده، پوره سیب زمینی، سیب زمنی تنوری، سس مخصوص', 'https://dehkadehprotein.com/wp-content/uploads/2021/01/300-3522-100602114106-8562-display.jpg', 'استیک'),
(10, 'وایت استیک', 105000, '150 گرم سینه مرغ طمع دار شده، سبزیجات گریل شده، سیب زمینی تنوری، قارچ و پنیر', 'https://blog.okala.com/wp-content/uploads/2020/03/steak-compressor.jpg', 'استیک'),
(11, 'گرین استیک', 21500, '400 گرم گوشت گوساله گریل شده، سبزیجات گریل شده، پودر پسته، سیب زمینی تنوری، پنیر', 'https://chishi.ir/wp-content/uploads/2020/07/steack-goosht.jpg', 'استیک'),
(12, 'ریب ای استیک ', 175000, '220  گرم گنده گوساله، سبزیجات گریل شده، پوره سیب زمینی ، سس کچاب ، سس مخصوص', 'https://www.talab.org/wp-content/uploads/2015/01/1513122601-talab-ir.jpg', 'استیک');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(50) NOT NULL,
  `user_name` varchar(50) COLLATE utf8_persian_ci NOT NULL,
  `display_name` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(12) COLLATE utf8_persian_ci NOT NULL,
  `password` int(32) NOT NULL,
  `adress` text COLLATE utf8_persian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `user_name`, `display_name`, `phone`, `password`, `adress`) VALUES
(9, 'hana', 'حنانه حسینی', '0913258369', 123, 'میدان جمهوری خیابان رباط کوچه کریمی');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id-cart`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id_order`);

--
-- Indexes for table `poroduct`
--
ALTER TABLE `poroduct`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id-cart` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id_order` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `poroduct`
--
ALTER TABLE `poroduct`
  MODIFY `id_product` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
