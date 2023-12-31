-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Jun 2023 pada 06.02
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sivour`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mhs`
--

CREATE TABLE `mhs` (
  `nama` varchar(255) NOT NULL,
  `nim` varchar(50) NOT NULL,
  `aa` varchar(2) NOT NULL,
  `aok` varchar(2) NOT NULL,
  `opk` varchar(2) NOT NULL,
  `pbo` varchar(2) NOT NULL,
  `pkn` varchar(2) NOT NULL,
  `ppbo` varchar(2) NOT NULL,
  `pscpk` varchar(2) NOT NULL,
  `rpl` varchar(2) NOT NULL,
  `scpk` varchar(2) NOT NULL,
  `techno` varchar(2) NOT NULL,
  `ips` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mhs`
--

INSERT INTO `mhs` (`nama`, `nim`, `aa`, `aok`, `opk`, `pbo`, `pkn`, `ppbo`, `pscpk`, `rpl`, `scpk`, `techno`, `ips`) VALUES
('Sanaaaaaya', '123200024', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 3.5),
('Gara', '123210001', 'B', 'C+', 'B+', 'C+', 'A', 'B+', 'C', 'B+', 'B', 'A', 3.1875),
('Al Ikhsan ', '123210012', 'B', 'C+', 'A', 'C+', 'A', 'B', 'B+', 'B+', 'A', 'A', 3.41667),
('Muh Aditya Dwijaya', '123210027', 'E', 'B', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 'B+', 3.14583),
('Tegar Wibisana', '123210030', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'B+', 'B+', 'C', 3.625);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `nama` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`nama`, `username`, `password`) VALUES
('agil', 'desyna', 'agil123'),
('Al Ikhsan Akbar', 'ikhsan', '234'),
('Muhammad Abdanul Ikhlas', 'KLaZ', 'klaz123'),
('harish', 'pey', '12345'),
('Garaaaaaaya', 'yb', '12345'),
('Tegar Wibisana', 'ybgara', '1234');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mhs`
--
ALTER TABLE `mhs`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
