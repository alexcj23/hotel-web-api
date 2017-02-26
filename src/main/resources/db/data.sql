INSERT INTO categoria_cuarto (nombre,descripcion,precio)
    VALUES ('Individual', 'Ideal para quienes viajan solos.',50.0);
INSERT INTO categoria_cuarto (nombre,descripcion,precio)
    VALUES ('Negocio', 'Ideal para quienes viajan por negocios.',60.0);
    
INSERT INTO cuarto (numero, descripcion,categoria)
    VALUES(1,'Vista a la piscina',1);
INSERT INTO cuarto (numero, descripcion,categoria)
  VALUES(2,'Remodelado recientemente',1);
INSERT INTO cuarto (numero, descripcion,categoria)
  VALUES(3,'Habitacion clientes de negocio',2);
  
INSERT INTO huesped (nombre, email, telefono)
  VALUES('Agustín Rocha','eldemente@gmail.com','99998888');

 INSERT INTO huesped (nombre, email, telefono)
  VALUES('Ariel Sanchez','elotrodemente@gmail.com','99998888');
 
INSERT INTO reservacion (desde, hasta, cuarto, huesped)
  VALUES({ts '2017-02-24 00:00:00.0'},{ts '2017-02-26 00:00:00.0'}, 1, 1);
  
INSERT INTO reservacion (desde, hasta, cuarto, huesped)
  VALUES({ts '2017-02-25 00:00:00.0'},{ts '2017-02-26 00:00:00.0'}, 2, 2);
  