-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 24 juin 2022 à 22:17
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_ecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE IF NOT EXISTS `administrateur` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(14) NOT NULL,
  `username` varchar(60) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`admin_id`, `nom`, `prenom`, `email`, `telephone`, `username`, `mdp`) VALUES
(1, 'admin ', 'admin ', 'adminadmin@gmail.com', '076271299', 'admin', '123456'),
(12, 'ibrahim', 'essakine', 'ibrahimessakine@gmail.com', '06060606', 'brah', 'azerty');

-- --------------------------------------------------------

--
-- Structure de la table `annoce_controle`
--

DROP TABLE IF EXISTS `annoce_controle`;
CREATE TABLE IF NOT EXISTS `annoce_controle` (
  `id_annonce` int(11) NOT NULL AUTO_INCREMENT,
  `classe_id` int(11) NOT NULL,
  `date_controle` date NOT NULL,
  `description` varchar(300) NOT NULL,
  PRIMARY KEY (`id_annonce`),
  KEY `classe` (`classe_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annoce_controle`
--

INSERT INTO `annoce_controle` (`id_annonce`, `classe_id`, `date_controle`, `description`) VALUES
(1, 6, '2022-06-24', 'Par Mr/Mme Essakin Ibrahim\nCette annonce est publié par ibrahim'),
(2, 6, '2022-06-03', 'Par Mr/Mme lamhamdi anouar\nCEtte annance est publié par lamhamdi anouar'),
(3, 6, '2022-06-04', 'Par Mr/Mme allaoui rabha\nthe best project '),
(4, 6, '2022-06-18', 'Par Mr/Mme ALLAOUI Rabha\nexemple');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `id_classe` int(11) NOT NULL AUTO_INCREMENT,
  `nom_classe` varchar(40) NOT NULL,
  PRIMARY KEY (`id_classe`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id_classe`, `nom_classe`) VALUES
(6, 'TDI'),
(9, 'cp2'),
(10, 'EREE'),
(11, 'AGRO'),
(13, 'Génie informatique'),
(14, 'Mécatronique');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `cour_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cour` varchar(140) NOT NULL,
  `cour` longblob NOT NULL,
  `matiere_id` int(11) NOT NULL,
  PRIMARY KEY (`cour_id`),
  KEY `matiere_id` (`matiere_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `apogee` int(10) NOT NULL,
  `id_classe` int(11) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `username` varchar(56) NOT NULL,
  PRIMARY KEY (`apogee`),
  KEY `id_classe` (`id_classe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`apogee`, `id_classe`, `prenom`, `email`, `nom`, `telephone`, `mdp`, `username`) VALUES
(1, 6, 'Omar', 'omarouhga12@gmail.com', 'Ouhagua', '07626262', 'azerty', 'omarios'),
(3, 6, 'said', 'saidanaam@gmail.com', 'anaam', '078885554', 'azerty', 'saidos'),
(4, 6, 'Khadija', 'khadaji@gmail.com', 'aazabi', '06060606', 'azerty', 'khadija'),
(5, 6, 'meriem', 'mreiem@gmail.com', 'Berrada', '075896478', 'azerty', 'mery'),
(6, 6, 'khalid', 'khalid@gmail.com', 'anbri', '075896478', 'azerty', 'khalide'),
(7, 6, 'khalid', 'khalid@gmail.com', 'anbri', '075896478', 'azerty', 'khalide'),
(8, 6, 'oussama', 'oussama@gmail.com', 'bouya', '075896478', 'azerty', 'oussama'),
(9, 6, 'amine', 'amine@gmail.com', 'gaammaz', '075896478', 'azerty', 'amine'),
(10, 6, 'abderrahmane', 'abde@gmail.com', 'Labrakh', '075896478', 'azerty', 'abde'),
(12, 6, 'nisrin', 'nisrine@gmail.com', 'hacob', '075896478', 'azerty', 'nisrine'),
(13, 6, 'hajar', 'hajar@gmail.com', 'miqri', '075896478', 'azerty', 'hajar'),
(14, 6, 'touria', 'touria@gmail.com', 'lamtouni', '075896478', 'azerty', 'touria'),
(15, 6, 'mouhamed', 'momo@gmail.com', 'mounir', '075896478', 'azerty', 'mohamed'),
(14526, 10, 'prenom', 'zert', 'name', '0625485', 'azerty', 'azerty'),
(18000980, 6, 'ANOUAR', 'anouar.lamhamdi@gmail.com', 'LAMHAMDI', '0634863635', 'azerty', 'anwar_lm');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `matiere_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_matiere` varchar(60) NOT NULL,
  `classe_id` int(11) NOT NULL,
  PRIMARY KEY (`matiere_id`),
  KEY `classe_id` (`classe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`matiere_id`, `nom_matiere`, `classe_id`) VALUES
(31, 'POO', 6),
(32, 'ODOO', 6),
(33, 'Informatique industrielle', 6),
(34, 'Marketing Digitale', 6),
(38, 'Analyse 3', 9),
(39, 'Algèbre 3', 9),
(40, 'mécanique de solide', 9),
(41, 'Electronique de puissance', 10),
(42, 'Biochimé', 11),
(43, 'Chimie Organique', 11),
(44, 'poo2', 6);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `matiere_id` int(11) NOT NULL,
  `Cntrol1` double DEFAULT '0',
  `Cntrol2` double NOT NULL DEFAULT '0',
  `examfinale` double NOT NULL DEFAULT '0',
  `etudiant_id` int(11) NOT NULL,
  PRIMARY KEY (`note_id`),
  KEY `matiere_id` (`matiere_id`),
  KEY `etudiant_id` (`etudiant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`note_id`, `matiere_id`, `Cntrol1`, `Cntrol2`, `examfinale`, `etudiant_id`) VALUES
(6, 31, 15, 18, 19, 1),
(7, 32, 17, 20, 14.25, 1),
(8, 33, 0, 0, 0, 1),
(9, 34, 0, 0, 0, 1),
(11, 41, 0, 0, 0, 14526),
(20, 31, 18, 18, 0, 18000980),
(21, 32, 0, 0, 0, 18000980),
(22, 33, 0, 0, 0, 18000980),
(23, 34, 0, 0, 0, 18000980),
(24, 44, 0, 0, 0, 18000980);

-- --------------------------------------------------------

--
-- Structure de la table `proffesseur`
--

DROP TABLE IF EXISTS `proffesseur`;
CREATE TABLE IF NOT EXISTS `proffesseur` (
  `SOM` int(11) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `date_naissance` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `sexe` enum('Homme','Femme') NOT NULL,
  `username` varchar(56) NOT NULL,
  `mdp` varchar(56) NOT NULL,
  `classe_id` int(11) NOT NULL,
  `matiere_id` int(11) NOT NULL,
  PRIMARY KEY (`SOM`),
  UNIQUE KEY `matiere_id` (`matiere_id`),
  KEY `classe_id` (`classe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `proffesseur`
--

INSERT INTO `proffesseur` (`SOM`, `nom`, `prenom`, `date_naissance`, `email`, `telephone`, `sexe`, `username`, `mdp`, `classe_id`, `matiere_id`) VALUES
(1, 'Essakin', 'Ibrahim', '2002-06-09', 'ibrahim@gmail.com', '078596554', 'Homme', 'ibrahim', 'azerty', 6, 32),
(2, 'anaam', 'said', '2000-06-01', 'saidanaam@gmail.com', '0762271299', 'Homme', 'said anaam', 'azerty', 11, 42),
(14, 'lamhamdi', 'anouar', '2001-06-09', 'lamhamdi@gmail.com', '0755968547', 'Homme', 'lamhamdi', 'azerty', 6, 33),
(147852, 'ALLAOUI', 'Rabha', '1980-06-04', 'R.ALLAOUi@gmail.', '0796666', 'Femme', 'ranha', 'azerty', 6, 31);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annoce_controle`
--
ALTER TABLE `annoce_controle`
  ADD CONSTRAINT `annoce_controle_ibfk_1` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `cours_ibfk_1` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`matiere_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `matiere_ibfk_1` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`matiere_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `note_ibfk_3` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`apogee`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `proffesseur`
--
ALTER TABLE `proffesseur`
  ADD CONSTRAINT `proffesseur_ibfk_1` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`matiere_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proffesseur_ibfk_2` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
