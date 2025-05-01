-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2024 at 08:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `retail`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountlist`
--

CREATE TABLE `accountlist` (
  `idAccount` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accountlist`
--

INSERT INTO `accountlist` (`idAccount`, `nama`, `password`) VALUES
(1, 'mufid', 'sarnas'),
(2, 'galang', 'tegar');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE `orderdetails` (
  `idOrderDetails` int(11) NOT NULL,
  `quantityOrder` int(10) NOT NULL,
  `priceTotal` int(100) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `idTransaksi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderdetails`
--

INSERT INTO `orderdetails` (`idOrderDetails`, `quantityOrder`, `priceTotal`, `idProduct`, `idTransaksi`) VALUES
(1, 3, 3000, 1, 3),
(2, 2, 3000, 1, 4),
(3, 3, 3000, 1, 7),
(4, 1, 7000, 2, 7),
(5, 1, 10000, 6, 7),
(6, 3, 3000, 1, 8),
(7, 2, 8000, 3, 8),
(8, 1, 3000, 1, 10),
(9, 1, 7000, 2, 10),
(10, 1, 3000, 1, 11),
(11, 1, 3000, 1, 12),
(12, 1, 7000, 2, 13),
(13, 1, 10000, 6, 13),
(14, 1, 5000, 35, 14),
(15, 1, 4500, 34, 14),
(16, 1, 3000, 33, 14),
(17, 1, 8000, 15, 15),
(18, 1, 16500, 20, 15),
(19, 1, 75000, 19, 15),
(20, 1, 13500, 21, 16),
(21, 1, 3000, 22, 16),
(22, 1, 2500, 17, 16),
(23, 1, 3000, 22, 17),
(24, 1, 13500, 21, 17),
(25, 1, 2500, 16, 17),
(26, 1, 3000, 1, 18),
(27, 1, 12000, 13, 19),
(28, 1, 3000, 1, 20),
(29, 2, 3000, 1, 21),
(30, 1, 2500, 17, 21),
(31, 1, 2500, 16, 22),
(32, 1, 2500, 16, 23);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `idPayment` int(11) NOT NULL,
  `paymentDate` date NOT NULL,
  `amount` int(100) NOT NULL,
  `idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`idPayment`, `paymentDate`, `amount`, `idAccount`) VALUES
(1, '2024-06-13', 10000, 2),
(2, '2024-06-13', 6000, 2),
(3, '2024-06-13', 30000, 2),
(4, '2024-06-13', 25000, 2),
(5, '2024-06-13', 10000, 2),
(6, '2024-06-13', 3000, 2),
(7, '2024-06-13', 3000, 2),
(8, '2024-06-13', 17000, 2),
(9, '2024-06-13', 12500, 2),
(10, '2024-06-13', 100000, 2),
(11, '2024-06-13', 20000, 2),
(12, '2024-06-13', 20000, 2),
(13, '2024-06-13', 4000, 2),
(14, '2024-06-13', 1000000, 2),
(15, '2024-06-13', 3000, 2),
(16, '2024-06-13', 8500, 2),
(17, '2024-06-13', 3000, 2),
(18, '2024-06-13', 3000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `idProduct` int(11) NOT NULL,
  `namaProduct` varchar(50) NOT NULL,
  `hargaSatuan` int(10) NOT NULL,
  `stock` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`idProduct`, `namaProduct`, `hargaSatuan`, `stock`) VALUES
(1, 'Indomie', 3000, 27),
(2, 'Lemonilo', 7000, 22),
(3, 'Chitato', 8000, 28),
(4, 'Oatbits', 7000, 20),
(5, 'Haribo', 13000, 30),
(6, 'Madurasa', 10000, 23),
(7, 'Krobe', 12000, 30),
(8, 'Energen', 3500, 30),
(9, 'Milo Cereal', 25000, 15),
(10, 'Milo sachet', 4000, 25),
(11, 'Mi gelas', 3000, 35),
(12, 'Nissin Wafer', 8500, 20),
(13, 'Tao kae nori', 12000, 24),
(14, 'Monde egg rolls', 20000, 20),
(15, 'ABC Jus', 8000, 29),
(16, 'Torabika susu', 2500, 27),
(17, 'Nutrisari', 2500, 38),
(18, 'Hilo', 50000, 15),
(19, 'L men', 75000, 9),
(20, 'Teh Tong Tji box', 16500, 19),
(21, 'Buavita jus', 13500, 23),
(22, 'Aqua', 3000, 48),
(23, 'Le mineral', 3500, 40),
(24, 'Piattos', 4500, 35),
(25, 'GuriBee', 6000, 30),
(26, 'Qtela', 6000, 25),
(27, 'Good Time', 8000, 25),
(28, 'Beng beng', 3500, 70),
(29, 'Nabati waffer', 4000, 30),
(30, 'Tango wafer', 4500, 30),
(31, 'Pocky', 8500, 20),
(32, 'Fitbar', 8500, 25),
(33, 'Floridina', 3000, 39),
(34, 'Frestea', 4500, 29),
(35, 'Sprite', 5000, 39),
(36, 'Coca cola', 5000, 40),
(37, 'Millkita', 2500, 5),
(38, 'permen', 1500, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tambahbarang`
--

CREATE TABLE `tambahbarang` (
  `idTambahBarang` int(11) NOT NULL,
  `jumlahIsi` int(10) NOT NULL,
  `tanggalTambah` date NOT NULL,
  `idProduct` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tambahbarang`
--

INSERT INTO `tambahbarang` (`idTambahBarang`, `jumlahIsi`, `tanggalTambah`, `idProduct`) VALUES
(1, 10, '2024-06-12', 5),
(2, 35, '2024-06-13', 28),
(3, 5, '2024-06-13', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idTransaksi` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `priceTotal` int(100) NOT NULL,
  `idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idTransaksi`, `orderDate`, `priceTotal`, `idAccount`) VALUES
(3, '2024-06-13', 9000, 2),
(4, '2024-06-13', 6000, 2),
(7, '2024-06-13', 26000, 2),
(8, '2024-06-13', 25000, 2),
(10, '2024-06-13', 10000, 2),
(11, '2024-06-13', 3000, 2),
(12, '2024-06-13', 3000, 2),
(13, '2024-06-13', 17000, 2),
(14, '2024-06-13', 12500, 2),
(15, '2024-06-13', 99500, 2),
(16, '2024-06-13', 19000, 2),
(17, '2024-06-13', 19000, 2),
(18, '2024-06-13', 3000, 2),
(19, '2024-06-13', 12000, 2),
(20, '2024-06-13', 3000, 2),
(21, '2024-06-13', 8500, 2),
(22, '2024-06-13', 2500, 2),
(23, '2024-06-13', 2500, 2);

-- --------------------------------------------------------

--
-- Table structure for table `userlog`
--

CREATE TABLE `userlog` (
  `idUserLog` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `dateAktif` date NOT NULL,
  `idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userlog`
--

INSERT INTO `userlog` (`idUserLog`, `nama`, `dateAktif`, `idAccount`) VALUES
(1, 'galang', '2024-06-13', 2),
(2, 'mufid', '2024-06-13', 1),
(3, 'galang', '2024-06-13', 2),
(4, 'galang', '2024-06-13', 2),
(5, 'galang', '2024-06-13', 2),
(6, 'galang', '2024-06-13', 2),
(7, 'galang', '2024-06-13', 2),
(8, 'galang', '2024-06-13', 2),
(9, 'galang', '2024-06-13', 2),
(10, 'galang', '2024-06-13', 2),
(11, 'galang', '2024-06-13', 2),
(12, 'galang', '2024-06-13', 2),
(13, 'galang', '2024-06-13', 2),
(14, 'galang', '2024-06-13', 2),
(15, 'galang', '2024-06-13', 2),
(16, 'galang', '2024-06-13', 2),
(17, 'galang', '2024-06-13', 2),
(18, 'galang', '2024-06-13', 2),
(19, 'galang', '2024-06-13', 2),
(20, 'galang', '2024-06-13', 2),
(21, 'galang', '2024-06-13', 2),
(22, 'galang', '2024-06-13', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountlist`
--
ALTER TABLE `accountlist`
  ADD PRIMARY KEY (`idAccount`);

--
-- Indexes for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`idOrderDetails`),
  ADD KEY `idProduct` (`idProduct`),
  ADD KEY `idTransaksi` (`idTransaksi`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`idPayment`),
  ADD KEY `payment_ibfk_1` (`idAccount`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idProduct`);

--
-- Indexes for table `tambahbarang`
--
ALTER TABLE `tambahbarang`
  ADD PRIMARY KEY (`idTambahBarang`),
  ADD KEY `idProduct` (`idProduct`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idTransaksi`),
  ADD KEY `transaksi_ibfk_1` (`idAccount`);

--
-- Indexes for table `userlog`
--
ALTER TABLE `userlog`
  ADD PRIMARY KEY (`idUserLog`),
  ADD KEY `idAccount` (`idAccount`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accountlist`
--
ALTER TABLE `accountlist`
  MODIFY `idAccount` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `idOrderDetails` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `idPayment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `idProduct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `tambahbarang`
--
ALTER TABLE `tambahbarang`
  MODIFY `idTambahBarang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `idTransaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `userlog`
--
ALTER TABLE `userlog`
  MODIFY `idUserLog` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`),
  ADD CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`idTransaksi`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`idAccount`) REFERENCES `accountlist` (`idAccount`);

--
-- Constraints for table `tambahbarang`
--
ALTER TABLE `tambahbarang`
  ADD CONSTRAINT `tambahbarang_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`idAccount`) REFERENCES `accountlist` (`idAccount`);

--
-- Constraints for table `userlog`
--
ALTER TABLE `userlog`
  ADD CONSTRAINT `userlog_ibfk_1` FOREIGN KEY (`idAccount`) REFERENCES `accountlist` (`idAccount`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
