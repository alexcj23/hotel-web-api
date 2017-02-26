/*password:admin001*/
INSERT INTO usuarios(username,password,enabled)
VALUES ('ucem','$2a$04$g4RD48tIeBW9T8B9W5GeCeUA1PRQvQ2LbfIAyGiHIkbuKLboZTAWW', true);

/*password:1234*/
INSERT INTO usuarios(username,password,enabled)
VALUES ('jperez','$2a$04$g4RD48tIeBW9T8B9W5GeCeUA1PRQvQ2LbfIAyGiHIkbuKLboZTAWW', true);

INSERT INTO usuarios_roles (username, role)
VALUES ('ucem', 'ROLE_ADMIN');

INSERT INTO usuarios_roles (username, role)
VALUES ('jperez', 'ROLE_USER');