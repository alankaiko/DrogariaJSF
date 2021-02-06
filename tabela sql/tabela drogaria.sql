-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema drogaria
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `drogaria` ;

-- -----------------------------------------------------
-- Schema drogaria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `drogaria` DEFAULT CHARACTER SET utf8 ;
USE `drogaria` ;

-- -----------------------------------------------------
-- Table `drogaria`.`Fabricante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drogaria`.`Fabricante` (
  `codigo` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL,
  `descricao` VARCHAR(50) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drogaria`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drogaria`.`Produto` (
  `codigo` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(70) NULL,
  `preco` DECIMAL(7,2) UNSIGNED NULL,
  `quantidade` INT UNSIGNED NULL,
  `fabricante_codigo` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Produto_Fabricante_idx` (`fabricante_codigo` ASC),
  CONSTRAINT `fk_Produto_Fabricante`
    FOREIGN KEY (`fabricante_codigo`)
    REFERENCES `drogaria`.`Fabricante` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drogaria`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drogaria`.`Funcionario` (
  `codigo` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL,
  `cpf` VARCHAR(14) NULL,
  `nome` VARCHAR(45) NULL,
  `senha` VARCHAR(32) NULL,
  `funcao` VARCHAR(50) NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drogaria`.`Vendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drogaria`.`Vendas` (
  `codigo` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL,
  `horario` DATETIME NULL,
  `venda_valor_total` DECIMAL(7,2) UNSIGNED NULL,
  `funcionario_codigo` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Vendas_Funcionario1_idx` (`funcionario_codigo` ASC) ,
  CONSTRAINT `fk_Vendas_Funcionario1`
    FOREIGN KEY (`funcionario_codigo`)
    REFERENCES `drogaria`.`Funcionario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drogaria`.`Itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drogaria`.`Itens` (
  `codigo` BIGINT(20) UNSIGNED AUTO_INCREMENT NOT NULL,
  `quantidade` INT NOT NULL,
  `valor_parcial` DECIMAL(7,2) UNSIGNED NULL,
  `vendas_codigo` BIGINT(20) UNSIGNED NULL,
  `produto_codigo` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Itens_Vendas1_idx` (`vendas_codigo` ASC) ,
  INDEX `fk_Itens_Produto1_idx` (`produto_codigo` ASC) ,
  CONSTRAINT `fk_Itens_Vendas1`
    FOREIGN KEY (`vendas_codigo`)
    REFERENCES `drogaria`.`Vendas` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Itens_Produto1`
    FOREIGN KEY (`produto_codigo`)
    REFERENCES `drogaria`.`Produto` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
