-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 31, 2020 at 11:11 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion`
--

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE `compte` (
  `ID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `mot_passe` varchar(50) NOT NULL,
  `niveau` tinyint(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`ID`, `username`, `mot_passe`, `niveau`) VALUES
(1, 'DVilleda', 'Danny123', 1),
(2, 'admin', 'master', 2);

-- --------------------------------------------------------

--
-- Table structure for table `compte_reunion`
--

CREATE TABLE `compte_reunion` (
  `compte_reunion_id` int(11) NOT NULL,
  `Compte_ID` int(11) NOT NULL,
  `Reunion_ID` int(11) NOT NULL,
  `Status` varchar(50) NOT NULL DEFAULT 'Absent'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compte_reunion`
--

INSERT INTO `compte_reunion` (`compte_reunion_id`, `Compte_ID`, `Reunion_ID`, `Status`) VALUES
(1, 1, 1, 'Present'),
(2, 1, 2, 'Absent'),
(3, 2, 1, 'Present');

-- --------------------------------------------------------

--
-- Table structure for table `contenu`
--

CREATE TABLE `contenu` (
  `ID` int(11) NOT NULL,
  `Contenu` text NOT NULL,
  `Date_Modifie` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Compte_ID` int(11) DEFAULT NULL,
  `Dossier_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contenu`
--

INSERT INTO `contenu` (`ID`, `Contenu`, `Date_Modifie`, `Compte_ID`, `Dossier_ID`) VALUES
(1, 'Le projet du département n\'est pas encore décidé. Les option vont dépendre de la demande du Gestionnaire des départements.', '2020-05-14 19:03:04', 1, 1),
(2, 'L\'efficacité du départment n\'est pas à la hauteur des attentes. Il faudra planifier une nouvelle distribution des tâches pour mieux répondre à ces demandes.', '2020-05-14 19:32:04', 1, 2),
(6, 'Le departement a decider d\'attendre que l\'audit soit fini avant de prendre une decision.', '2020-05-25 01:19:51', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `dossier`
--

CREATE TABLE `dossier` (
  `ID` int(11) NOT NULL,
  `Titre` varchar(50) NOT NULL DEFAULT '',
  `estActif` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dossier`
--

INSERT INTO `dossier` (`ID`, `Titre`, `estActif`) VALUES
(1, 'Planification Projets', 1),
(2, 'Restructuration du departement', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pointordre`
--

CREATE TABLE `pointordre` (
  `ID` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description_point` text NOT NULL,
  `reunion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pointordre`
--

INSERT INTO `pointordre` (`ID`, `nom`, `description_point`, `reunion_id`) VALUES
(1, 'Presence', 'Aucun abscent', 1),
(2, 'Discussion sur les plans', 'Tout le monde semble en accord avec les actions.', 1),
(3, 'Direction que le departement doit prendre.', '', 2);

-- --------------------------------------------------------

--
-- Table structure for table `reunion`
--

CREATE TABLE `reunion` (
  `ID` int(11) NOT NULL,
  `Titre` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Heure` int(11) DEFAULT NULL,
  `Minute` int(11) DEFAULT NULL,
  `Duree_min` smallint(6) DEFAULT NULL,
  `Reunion_ouverte` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reunion`
--

INSERT INTO `reunion` (`ID`, `Titre`, `Date`, `Heure`, `Minute`, `Duree_min`, `Reunion_ouverte`) VALUES
(1, 'Reunion departemental de la semaine du 8 juin', '2020-05-29', 13, 0, 50, 1),
(2, 'Reunion departemental de la semaine du 15 juin', '2020-06-17', 13, 0, 45, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `username_2` (`username`);

--
-- Indexes for table `compte_reunion`
--
ALTER TABLE `compte_reunion`
  ADD PRIMARY KEY (`compte_reunion_id`),
  ADD KEY `Compte_ID` (`Compte_ID`),
  ADD KEY `Reunion_ID` (`Reunion_ID`);

--
-- Indexes for table `contenu`
--
ALTER TABLE `contenu`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `contenu_ibfk_1` (`Compte_ID`),
  ADD KEY `contenu_ibfk_2` (`Dossier_ID`);

--
-- Indexes for table `dossier`
--
ALTER TABLE `dossier`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Titre` (`Titre`);

--
-- Indexes for table `pointordre`
--
ALTER TABLE `pointordre`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `reunion_id` (`reunion_id`);

--
-- Indexes for table `reunion`
--
ALTER TABLE `reunion`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Titre` (`Titre`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `compte`
--
ALTER TABLE `compte`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `compte_reunion`
--
ALTER TABLE `compte_reunion`
  MODIFY `compte_reunion_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `contenu`
--
ALTER TABLE `contenu`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `dossier`
--
ALTER TABLE `dossier`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pointordre`
--
ALTER TABLE `pointordre`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `reunion`
--
ALTER TABLE `reunion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `compte_reunion`
--
ALTER TABLE `compte_reunion`
  ADD CONSTRAINT `Compte_FK` FOREIGN KEY (`Compte_ID`) REFERENCES `compte` (`ID`),
  ADD CONSTRAINT `ReunionCompte_FK` FOREIGN KEY (`Reunion_ID`) REFERENCES `reunion` (`ID`);

--
-- Constraints for table `contenu`
--
ALTER TABLE `contenu`
  ADD CONSTRAINT `contenu_ibfk_1` FOREIGN KEY (`Compte_ID`) REFERENCES `compte` (`ID`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `contenu_ibfk_2` FOREIGN KEY (`Dossier_ID`) REFERENCES `dossier` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pointordre`
--
ALTER TABLE `pointordre`
  ADD CONSTRAINT `pointordre_ibfk_1` FOREIGN KEY (`reunion_id`) REFERENCES `reunion` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
