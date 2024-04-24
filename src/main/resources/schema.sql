CREATE EXTENSION if not exists "uuid-ossp";

DROP schema if exists app cascade;
CREATE schema if not exists app;


CREATE TABLE if not exists app.positions
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE if not exists app.roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE if not exists app.statuses
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE if not exists app.children
(
    number INT NOT NULL PRIMARY KEY
);

CREATE TABLE if not exists app.users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    password VARCHAR(100),
    date_of_birth VARCHAR(50),
    email VARCHAR(50) NOT NULL UNIQUE,
    tel_number VARCHAR(50),
    position VARCHAR(50) NOT NULL default 'Должность_1',
    emp_date DATE NOT NULL default current_date,
    role VARCHAR(30) default 'ROLE_USER',

    FOREIGN KEY (position) REFERENCES app.positions(name),
    FOREIGN KEY (role) REFERENCES app.roles(name)
);

CREATE TABLE if not exists app.users_role
(
    user_id BIGINT,
    role_id INT,

    FOREIGN KEY (user_id) REFERENCES app.users(id),
    FOREIGN KEY (role_id) REFERENCES app.roles(id)
);


CREATE TABLE if not exists app.present_application
(
    id SERIAL PRIMARY KEY UNIQUE,
    emp_id BIGINT,
    emp_name VARCHAR(100) NOT NULL,
    emp_date_of_birth VARCHAR(50),
    emp_email VARCHAR(50) NOT NULL,
    emp_tel_number VARCHAR(50) NOT NULL,
    emp_position VARCHAR(30) NOT NULL default 'Должность_1',
    emp_date DATE NOT NULL default current_date,
    num_children INT NOT NULL default 0,
    files_ref TEXT[],
    addfiles_ref TEXT[],
    comment_children VARCHAR(200),
    final_photo TEXT[],
    responsible_id BIGINT,
    comment_status VARCHAR(200),
    app_type VARCHAR(30) default 'gifts',
    status VARCHAR(30) NOT NULL default 'under_consideration',
    app_date DATE NOT NULL default current_date,

    FOREIGN KEY (num_children) REFERENCES app.children(number),
    FOREIGN KEY (emp_id) REFERENCES app.users(id),
    FOREIGN KEY (status) REFERENCES app.statuses(name),
    FOREIGN KEY (emp_position) REFERENCES app.positions(name)
);


CREATE TABLE if not exists app.files
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    reference TEXT[],
    addreference TEXT[]
);