-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 06, 2020 at 05:35 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rooms2`
--

-- --------------------------------------------------------

--
-- Table structure for table `room_data`
--

CREATE TABLE `room_data` (
  `Room` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `Occupied` int(11) NOT NULL,
  `Checkin` date NOT NULL,
  `Checkout` date NOT NULL,
  `Bill` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Mobile` varchar(12) NOT NULL,
  `Resident` int(11) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Beds` varchar(20) NOT NULL,
  `Ac` varchar(20) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room_data`
--

INSERT INTO `room_data` (`Room`, `floor`, `Occupied`, `Checkin`, `Checkout`, `Bill`, `Name`, `Mobile`, `Resident`, `Type`, `Beds`, `Ac`, `Price`) VALUES
(101, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Double', 'Ac', 512),
(102, 1, 1, '2018-08-07', '2018-08-13', 1372, 'Simon Belmont', '66642069', 1, 'Normal', 'Double', 'Ac', 512),
(103, 1, 1, '2018-08-21', '2018-08-29', 712, 'Juji Chee', '809456894', 2, 'Normal', 'Double', 'Non Ac', 512),
(104, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Single', 'Ac', 512),
(105, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Single', 'Ac', 512),
(106, 1, 1, '2018-07-05', '2018-08-21', 2394, 'Hassan', '1876154188', 6, 'Family', 'Double x2', 'Ac', 1024),
(107, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Ac', 1024),
(108, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Ac', 1024),
(109, 1, 2, '2020-07-09', '0000-00-00', 0, 'Colnel Mustang', '98514267', 6, 'Family', 'Double x2', 'Non Ac', 1024),
(110, 1, 1, '2019-07-11', '2019-08-01', 4374, 'Vijay Kumar', '1641653418', 5, 'Family', 'Double x2', 'Non Ac', 1024),
(111, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Deulux', 'Double', 'Ac', 2048),
(112, 1, 1, '2020-07-02', '2020-08-18', 4278, 'Dunkey', '98765321', 3, 'Deulux', 'Double', 'Ac', 2048),
(113, 1, 1, '2020-07-16', '2020-09-08', 5138, 'Edward Elric', '985136447', 2, 'Deulux', 'Double', 'Ac', 2048),
(114, 1, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Super Deulux', 'Double', 'Ac', 4096),
(115, 1, 1, '2018-07-12', '2020-09-30', 6126, 'Neal Caffery', '9876541325', 2, 'Super Deulux', 'Double', 'Ac', 4096),
(201, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Double', 'Ac', 512),
(202, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Double', 'Ac', 512),
(203, 2, 1, '2018-09-12', '2018-09-27', 1022, 'Franz Fernandez', '654031084', 6, 'Normal', 'Double', 'Non Ac', 512),
(204, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Single', 'Ac', 512),
(205, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Single', 'Ac', 512),
(206, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Ac', 1024),
(207, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Ac', 1024),
(208, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Ac', 1024),
(209, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Non Ac', 1024),
(210, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Non Ac', 1024),
(211, 2, 2, '2019-07-11', '0000-00-00', 0, 'Izuku', '925651777', 5, 'Deulux', 'Double', 'Ac', 2048),
(212, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Deulux', 'Double', 'Ac', 2048),
(213, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Deulux', 'Double', 'Ac', 2048),
(214, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Super Deulux', 'Double', 'Ac', 4096),
(215, 2, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Super Deulux', 'Double', 'Ac', 4096),
(301, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Double', 'Ac', 512),
(302, 3, 1, '2020-07-09', '2020-07-31', 1232, 'Samad', '6282429845', 4, 'Normal', 'Double', 'Ac', 512),
(303, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Double', 'Non Ac', 512),
(304, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Single', 'Ac', 512),
(305, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Normal', 'Single', 'Ac', 512),
(306, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Ac', 1024),
(307, 3, 1, '2018-07-19', '2019-07-31', 1884, 'Billy Batson', '56561897', 2, 'Family', 'Double x2', 'Ac', 1024),
(308, 3, 1, '2020-07-08', '2020-07-31', 1234, 'Aaron', '984165414', 4, 'Family', 'Double x2', 'Ac', 1024),
(309, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Non Ac', 1024),
(310, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Family', 'Double x2', 'Non Ac', 1024),
(311, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Deulux', 'Double', 'Ac', 2048),
(312, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Deulux', 'Double', 'Ac', 2048),
(313, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Deulux', 'Double', 'Ac', 2048),
(314, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Super Deulux', 'Double', 'Ac', 4096),
(315, 3, 0, '0000-00-00', '0000-00-00', 0, '', '', 0, 'Super Deulux', 'Double', 'Ac', 4096);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
