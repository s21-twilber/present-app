
INSERT INTO app.roles (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_COORDINATOR'), ('ROLE_ACCOUNTANT');

INSERT INTO app.statuses (name)
VALUES 	('draft'), ('under_consideration'), ('approved'), ('approved_by_coordinator'),
          ('approved_by_accountant'), ('denied'), ('granted'), ('received');

INSERT INTO app.children (number)
VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

INSERT INTO app.positions (id, name)
VALUES 	(1, 'Должность_1'), (2, 'Должность_2'), (3, 'Должность_3'), (4, 'Должность_4'),
          (5, 'Должность_5'), (6, 'Должность_6'), (7, 'Должность_7'), (8, 'Должность_8');


INSERT INTO app.users (name, password, email, role)
VALUES ('user', '$2a$10$6xnFuaslRfe9aqF1aWid5uxq.D7bdxi6r5alhTyGpkrUtD.Egd0Ai', 'user@user', 'ROLE_COORDINATOR');

