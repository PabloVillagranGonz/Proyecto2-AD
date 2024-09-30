DROP DATABASE IF EXISTS Conciertos;
CREATE DATABASE Conciertos;
Use Conciertos;


CREATE TABLE Grupos(
                       Id int unsigned auto_increment primary key,
                       presupuesto varchar(20),
                       nombre varchar(20),
                       descripcion varchar(100),
                       estilo enum('Pop','Rock','Regueton','Indie')

);
INSERT INTO Grupos  VALUES
                        (1, "1200", "Los Mejias",
                         "Grupo de rock, sede oficial en Coca (Segovia)", "Pop"),
                       (2, "1500", "La Liga",
                        "Grupo de versiones, sede oficial en Nava de la Asuncion (Segovia)", "Rock"),
                       (3, "2000", "Arranca Marea",
                        "Grupo de indie, sede oficial en Salamanca", "Indie"),
                       (4, "3000","La regadera",
                        "Grupo de regueton con mezcla de pop, sede oficial en Burgos", "Regueton");

