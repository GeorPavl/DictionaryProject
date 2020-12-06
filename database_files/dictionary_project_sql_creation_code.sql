-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dictionary_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dictionary_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dictionary_project` DEFAULT CHARACTER SET utf8 ;
USE `dictionary_project` ;

-- -----------------------------------------------------
-- Table `dictionary_project`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dictionary_project`.`user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `dictionary_project`.`dictionary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dictionary_project`.`dictionary` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `language` VARCHAR(45) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_dictionary_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_dictionary_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `dictionary_project`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dictionary_project`.`word`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dictionary_project`.`word` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `original` VARCHAR(45) NOT NULL,
  `dictionary_id` BIGINT UNSIGNED NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
  `is_examed` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_word_dictionary1_idx` (`dictionary_id` ASC) VISIBLE,
  CONSTRAINT `fk_word_dictionary1`
    FOREIGN KEY (`dictionary_id`)
    REFERENCES `dictionary_project`.`dictionary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dictionary_project`.`meaning`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dictionary_project`.`meaning` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `definition` VARCHAR(45) NOT NULL,
  `example` VARCHAR(255) NULL,
  `word_id` BIGINT UNSIGNED NOT NULL,
  `type` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_meaning_word1_idx` (`word_id` ASC) VISIBLE,
  CONSTRAINT `fk_meaning_word1`
    FOREIGN KEY (`word_id`)
    REFERENCES `dictionary_project`.`word` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
