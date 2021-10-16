// DESARROLLO DE SOFTWARE EMPRESARIAL
// PAOLA RAQUEL JIMÉNEZ TORREZ JT180795
// Instrucciones para creación de la base de datos en MySQL 

CREATE DATABASE EmpresaXYZ ;

USE EmpresaXYZ;

CREATE  TABLE `Empleado` (
 
  `id` INT NOT NULL AUTO_INCREMENT ,
 
  `nombre` VARCHAR(100) NULL ,
 
  `apellido` VARCHAR(100) NULL ,
  
  `cargo` VARCHAR(100) NULL ,

  `edad` INT NULL ,

  `direccion` VARCHAR(100) NULL ,

  `telefono` VARCHAR(8) NULL ,
 
  PRIMARY KEY (`id`) 
 );


// Instrucciones para pruebas del funcionamiento del CRUD

TRUNCATE TABLE empleado;

SELECT * FROM Empleado;

DROP DATABASE EmpresaXYZ;