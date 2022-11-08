SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `Files` (
  `id` int(11) NOT NULL,
  `modId` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `path` text NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ModAuthorLink` (
  `id` int(11) NOT NULL,
  `modId` varchar(25) NOT NULL,
  `user` int(11) DEFAULT NULL,
  `author` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Mods` (
  `id` varchar(25) NOT NULL,
  `authors` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `shortDesc` varchar(100) NOT NULL,
  `longDesc` mediumtext NOT NULL,
  `isLegacy` tinyint(1) NOT NULL,
  `baseSlot` int(11) NOT NULL,
  `isModpack` tinyint(1) NOT NULL,
  `tags` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `modTags` (
  `id` int(11) NOT NULL,
  `name` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `User` (
  `id` varchar(50) NOT NULL,
  `username` varchar(25) NOT NULL,
  `keycloakId` varchar(50) NOT NULL,
  `discord` varchar(25) DEFAULT NULL,
  `youtube` varchar(25) DEFAULT NULL,
  `github` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `Files`
  ADD PRIMARY KEY (`id`),
  ADD KEY `files_mod` (`modId`);

ALTER TABLE `ModAuthorLink`
  ADD PRIMARY KEY (`id`),
  ADD KEY `modAuthor_mod` (`modId`),
  ADD KEY `modAuthor_author` (`author`);

ALTER TABLE `Mods`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mods_tags` (`tags`);

ALTER TABLE `modTags`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `User`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `Files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `ModAuthorLink`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `modTags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `Files`
  ADD CONSTRAINT `files_mod` FOREIGN KEY (`modId`) REFERENCES `Mods` (`id`);

ALTER TABLE `ModAuthorLink`
  ADD CONSTRAINT `modAuthor_author` FOREIGN KEY (`author`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `modAuthor_mod` FOREIGN KEY (`modId`) REFERENCES `Mods` (`id`);

ALTER TABLE `Mods`
  ADD CONSTRAINT `mods_tags` FOREIGN KEY (`tags`) REFERENCES `modTags` (`id`);
COMMIT;