CREATE DATABASE  recursos_humanos_udi;
USE recursos_humanos_udi;


CREATE TABLE  NivelEstudio (
    nivelEstudioId INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE  Universidad (
    universidadId INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE  Funcionario (
    tipoIdentificacion VARCHAR(20) NOT NULL,
    numeroIdentificacion VARCHAR(20) PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    estadoCivil VARCHAR(20),
    sexo CHAR(1),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    fechaNacimiento DATE,
    nivelEstudioId INT,
    universidadId INT,
    FOREIGN KEY (nivelEstudioId) REFERENCES NivelEstudio(nivelEstudioId),
    FOREIGN KEY (universidadId) REFERENCES Universidad(universidadId)
);


CREATE TABLE  GrupoFamiliar (
    grupoFamiliarId INT AUTO_INCREMENT PRIMARY KEY,
    numeroIdentificacionFuncionario VARCHAR(20) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fechaNacimiento DATE,
    rol VARCHAR(50) NOT NULL,
    FOREIGN KEY (numeroIdentificacionFuncionario) REFERENCES Funcionario(numeroIdentificacion) ON DELETE CASCADE
);