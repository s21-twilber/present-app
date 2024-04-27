
INSERT INTO app.roles (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_COORDINATOR'), ('ROLE_ACCOUNTANT');

INSERT INTO app.statuses (name)
VALUES 	('DRAFT'), ('UNDER_CONSIDERATION'), ('APPROVED'), ('APPROVED_BY_COORDINATOR'),
          ('APPROVED_BY_ACCOUNTANT'), ('DENIED'), ('GRANTED'), ('RECEIVED');

INSERT INTO app.children (number)
VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

INSERT INTO app.positions (id, name)
VALUES 	(1, 'Должность_1'), (2, 'Должность_2'), (3, 'Должность_3'), (4, 'Должность_4'),
          (5, 'Должность_5'), (6, 'Должность_6'), (7, 'Должность_7'), (8, 'Должность_8');


-- Create admin
INSERT INTO app.users (name, password, email, role)
VALUES ('admin', '$2a$10$6xnFuaslRfe9aqF1aWid5uxq.D7bdxi6r5alhTyGpkrUtD.Egd0Ai', 'admin', 'ROLE_ADMIN');

INSERT INTO app.users_role (user_id, role_id)
VALUES (1, 2);


-- Create coordinator
INSERT INTO app.users (name, password, email, role)
VALUES ('user', '$2a$10$6xnFuaslRfe9aqF1aWid5uxq.D7bdxi6r5alhTyGpkrUtD.Egd0Ai', 'user@user', 'ROLE_COORDINATOR');

INSERT INTO app.users_role (user_id, role_id)
VALUES (2, 3);


-- Create accountant
INSERT INTO app.users (name, password, email, role)
VALUES ('count', '$2a$10$6xnFuaslRfe9aqF1aWid5uxq.D7bdxi6r5alhTyGpkrUtD.Egd0Ai', 'count', 'ROLE_ACCOUNTANT');

INSERT INTO app.users_role (user_id, role_id)
VALUES (3, 4);


-- Create user
INSERT INTO app.users (name, password, email, role)
VALUES ('aa', '$2a$10$6xnFuaslRfe9aqF1aWid5uxq.D7bdxi6r5alhTyGpkrUtD.Egd0Ai', 'aa', 'ROLE_USER');

INSERT INTO app.users_role (user_id, role_id)
VALUES (4, 1);



