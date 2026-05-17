create database sociedad_almas;
use  sociedad_almas;

create table personaje (
    id_personaje INT  PRIMARY KEY,
    nombre_personaje VARCHAR(100) NOT NULL
);

CREATE TABLE USUARIO (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL,
    id_personaje INT,
    FOREIGN KEY (id_personaje) REFERENCES PERSONAJE(id_personaje)
);

CREATE TABLE PARTIDA (
    id_partida INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_personaje_seleccionado INT NOT NULL,
    puntuacion INT NOT NULL,
    vida_restante INT NOT NULL,
    resultado VARCHAR(50) NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_personaje_seleccionado) REFERENCES PERSONAJE(id_personaje)
);