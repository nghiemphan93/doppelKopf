-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema doppelkopf
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `doppelkopf` ;

-- -----------------------------------------------------
-- Schema doppelkopf
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `doppelkopf` DEFAULT CHARACTER SET utf8 ;
USE `doppelkopf` ;

-- -----------------------------------------------------
-- Table `doppelkopf`.`Player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doppelkopf`.`Player` ;

CREATE TABLE IF NOT EXISTS `doppelkopf`.`Player` (
  `Player_ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`Player_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doppelkopf`.`Card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doppelkopf`.`Card` ;

CREATE TABLE IF NOT EXISTS `doppelkopf`.`Card` (
  `Card_ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `suit` VARCHAR(5) NOT NULL,
  `rank` VARCHAR(6) NOT NULL,
  `strength` VARCHAR(2) NOT NULL,
  `point` INT NOT NULL,
  `FehlOrNot` TINYINT NOT NULL,
  `imageURL` VARCHAR(255) NULL,
  PRIMARY KEY (`Card_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doppelkopf`.`Game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doppelkopf`.`Game` ;

CREATE TABLE IF NOT EXISTS `doppelkopf`.`Game` (
  `Game_ID` INT NOT NULL AUTO_INCREMENT,
  `player1_ID` INT NOT NULL,
  `player2_ID` INT NOT NULL,
  `player3_ID` INT NOT NULL,
  `player4_ID` INT NOT NULL,
  `played_at` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`Game_ID`),
  CONSTRAINT `fk_Game_Player1`
    FOREIGN KEY (`player1_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Game_Player2`
    FOREIGN KEY (`player2_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Game_Player3`
    FOREIGN KEY (`player3_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Game_Player4`
    FOREIGN KEY (`player4_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_Game_Player1_idx` ON `doppelkopf`.`Game` (`player1_ID` ASC);

CREATE INDEX `fk_Game_Player2_idx` ON `doppelkopf`.`Game` (`player2_ID` ASC);

CREATE INDEX `fk_Game_Player3_idx` ON `doppelkopf`.`Game` (`player3_ID` ASC);

CREATE INDEX `fk_Game_Player4_idx` ON `doppelkopf`.`Game` (`player4_ID` ASC);


-- -----------------------------------------------------
-- Table `doppelkopf`.`CardsPlayed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doppelkopf`.`CardsPlayed` ;

CREATE TABLE IF NOT EXISTS `doppelkopf`.`CardsPlayed` (
  `CardsPlayedt_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `player_ID` INT NOT NULL,
  `card_ID` INT NOT NULL,
  PRIMARY KEY (`CardsPlayedt_ID`),
  CONSTRAINT `fk_CardsPlayed_Card`
    FOREIGN KEY (`card_ID`)
    REFERENCES `doppelkopf`.`Card` (`Card_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_CardsPlayed_Player1`
    FOREIGN KEY (`player_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_CardsPlayed_Card_idx` ON `doppelkopf`.`CardsPlayed` (`card_ID` ASC);

CREATE INDEX `fk_CardsPlayed_Player1_idx` ON `doppelkopf`.`CardsPlayed` (`player_ID` ASC);


-- -----------------------------------------------------
-- Table `doppelkopf`.`CardsCollected`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doppelkopf`.`CardsCollected` ;

CREATE TABLE IF NOT EXISTS `doppelkopf`.`CardsCollected` (
  `CardsCollected_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `card_ID` INT NOT NULL,
  `player_ID` INT NOT NULL,
  PRIMARY KEY (`CardsCollected_ID`),
  CONSTRAINT `fk_CardsCollected_Card1`
    FOREIGN KEY (`card_ID`)
    REFERENCES `doppelkopf`.`Card` (`Card_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_CardsCollected_Player1`
    FOREIGN KEY (`player_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_CardsCollected_Card1_idx` ON `doppelkopf`.`CardsCollected` (`card_ID` ASC);

CREATE INDEX `fk_CardsCollected_Player1_idx` ON `doppelkopf`.`CardsCollected` (`player_ID` ASC);


-- -----------------------------------------------------
-- Table `doppelkopf`.`GamesPlayed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doppelkopf`.`GamesPlayed` ;

CREATE TABLE IF NOT EXISTS `doppelkopf`.`GamesPlayed` (
  `GamesPlayed_ID` INT NOT NULL AUTO_INCREMENT,
  `hadKreuzQueen` TINYINT NOT NULL,
  `pointsWonInGame` INT NOT NULL,
  `wonOrNot` TINYINT NOT NULL,
  `player_ID` INT NOT NULL,
  `partnerInGame_ID` INT NULL,
  `game_ID` INT NOT NULL,
  PRIMARY KEY (`GamesPlayed_ID`),
  CONSTRAINT `fk_GamesPlayed_Player1`
    FOREIGN KEY (`player_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_GamesPlayed_Player2`
    FOREIGN KEY (`partnerInGame_ID`)
    REFERENCES `doppelkopf`.`Player` (`Player_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_GamesPlayed_Game1`
    FOREIGN KEY (`game_ID`)
    REFERENCES `doppelkopf`.`Game` (`Game_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_GamesPlayed_Player1_idx` ON `doppelkopf`.`GamesPlayed` (`player_ID` ASC);

CREATE INDEX `fk_GamesPlayed_Player2_idx` ON `doppelkopf`.`GamesPlayed` (`partnerInGame_ID` ASC);

CREATE INDEX `fk_GamesPlayed_Game1_idx` ON `doppelkopf`.`GamesPlayed` (`game_ID` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` (`Card_ID`, `name`, `suit`, `rank`, `strength`, `point`, `FehlOrNot`, `imageURL`) VALUES (1,'KARO BUBEN','KARO','BUBEN','5A',2,0,NULL),(2,'KREUZ BUBEN','KREUZ','BUBEN','5D',2,0,NULL),(3,'KARO ZEHN','KARO','ZEHN','4B',10,0,NULL),(4,'PIK KOENIG','PIK','KOENIG','2A',4,1,NULL),(5,'KREUZ DAMEN','KREUZ','DAMEN','6D',3,0,NULL),(6,'HERZ BUBEN','HERZ','BUBEN','5B',2,0,NULL),(7,'KARO BUBEN','KARO','BUBEN','5A',2,0,NULL),(8,'KREUZ ASS','KREUZ','ASS','1C',11,1,NULL),(9,'KARO ASS','KARO','ASS','4C',11,0,NULL),(10,'PIK KOENIG','PIK','KOENIG','2A',4,1,NULL),(11,'PIK ASS','PIK','ASS','2C',11,1,NULL),(12,'HERZ ASS','HERZ','ASS','3C',11,1,NULL),(13,'PIK ZEHN','PIK','ZEHN','2B',10,1,NULL),(14,'PIK DAMEN','PIK','DAMEN','6C',3,0,NULL),(15,'KREUZ ASS','KREUZ','ASS','1C',11,1,NULL),(16,'HERZ DAMEN','HERZ','DAMEN','6B',3,0,NULL),(17,'KREUZ KOENIG','KREUZ','KOENIG','1A',4,1,NULL),(18,'PIK ASS','PIK','ASS','2C',11,1,NULL),(19,'KREUZ KOENIG','KREUZ','KOENIG','1A',4,1,NULL),(20,'KARO ASS','KARO','ASS','4C',11,0,NULL),(21,'HERZ ZEHN','HERZ','ZEHN','7',10,0,NULL),(22,'KARO KOENIG','KARO','KOENIG','4A',4,0,NULL),(23,'HERZ KOENIG','HERZ','KOENIG','3A',4,1,NULL),(24,'PIK BUBEN','PIK','BUBEN','5C',2,0,NULL),(25,'KARO DAMEN','KARO','DAMEN','6A',3,0,NULL),(26,'HERZ ASS','HERZ','ASS','3C',11,1,NULL),(27,'KREUZ BUBEN','KREUZ','BUBEN','5D',2,0,NULL),(28,'KARO KOENIG','KARO','KOENIG','4A',4,0,NULL),(29,'HERZ BUBEN','HERZ','BUBEN','5B',2,0,NULL),(30,'PIK BUBEN','PIK','BUBEN','5C',2,0,NULL),(31,'HERZ ZEHN','HERZ','ZEHN','7',10,0,NULL),(32,'KREUZ ZEHN','KREUZ','ZEHN','1B',10,1,NULL),(33,'KREUZ ZEHN','KREUZ','ZEHN','1B',10,1,NULL),(34,'PIK DAMEN','PIK','DAMEN','6C',3,0,NULL),(35,'PIK ZEHN','PIK','ZEHN','2B',10,1,NULL),(36,'KARO ZEHN','KARO','ZEHN','4B',10,0,NULL),(37,'HERZ KOENIG','HERZ','KOENIG','3A',4,1,NULL),(38,'KARO DAMEN','KARO','DAMEN','6A',3,0,NULL),(39,'KREUZ DAMEN','KREUZ','DAMEN','6D',3,0,NULL),(40,'HERZ DAMEN','HERZ','DAMEN','6B',3,0,NULL);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` (`Player_ID`, `name`, `pass`, `created_at`) VALUES (1,'Phan','phan','2018-06-11 18:37:34'),(2,'Melanie','melanie','2018-06-11 18:38:33'),(3,'Sebastian','sebastian','2018-06-11 18:38:33'),(4,'Dominik','dominik','2018-06-11 18:38:33');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;