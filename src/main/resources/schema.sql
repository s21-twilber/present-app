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

CREATE TABLE if not exists app.users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    date_of_birth VARCHAR(50),
    email VARCHAR(50) NOT NULL UNIQUE,
    ph_number VARCHAR(50),
    department VARCHAR(50),
    position VARCHAR(50) default 'Должность_1',
    address VARCHAR(100),
    employee_date DATE NOT NULL default current_date,
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


CREATE TABLE if not exists app.present
(
    id SERIAL PRIMARY KEY UNIQUE,
    employee_id BIGINT,
    num_children INT NOT NULL default 0,
    child_name VARCHAR(100),
    files_ref VARCHAR(100) UNIQUE,
    comment_children VARCHAR(200),
    final_photo VARCHAR(100),
    coordinator_id BIGINT,
    accountant_id BIGINT,
    app_type VARCHAR(30) default 'gifts',
    status VARCHAR(30) NOT NULL default 'UNDER_CONSIDERATION',
    comment_status VARCHAR(200),
    app_date VARCHAR(50) default current_date,

    FOREIGN KEY (employee_id) REFERENCES app.users(id),
    FOREIGN KEY (status) REFERENCES app.statuses(name)
);


CREATE TABLE if not exists app.files
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    reference VARCHAR(200)
);

CREATE TABLE if not exists app.present_files_ref
(
    present_id BIGINT,
    files_ref VARCHAR(200),

    FOREIGN KEY (present_id) REFERENCES app.present(id)
);