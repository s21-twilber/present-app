INSERT INTO app.roles (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_COORDINATOR'), ('ROLE_ACCOUNTANT');

INSERT INTO app.statuses (id, name)
VALUES 	(1, 'черновик'), (2, 'на рассмотрении'), (3, 'согласовано'), (4, 'согласовано с координатором'),
          (5, 'согласовано с бухгалтером'), (6, 'отказано'), (7, 'выдано'), (8, 'получено');

INSERT INTO app.children (number)
VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

INSERT INTO app.positions (id, name)
VALUES 	(1, 'Должность_1'), (2, 'Должность_2'), (3, 'Должность_3'), (4, 'Должность_4'),
          (5, 'Должность_5'), (6, 'Должность_6'), (7, 'Должность_7'), (8, 'Должность_8');

INSERT INTO app.users (name, password, email)
VALUES ('user', 'pass', 'user@user');

