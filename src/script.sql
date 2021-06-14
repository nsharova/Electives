CREATE TABLE IF NOT EXISTS electives.users (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               login VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(50) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    role INT NOT NULL,
    isLocked boolean DEFAULT false
    );


CREATE TABLE IF NOT EXISTS electives.courses
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    duration    LONG         NOT NULL,
    ownerId     INT          NOT NULL,
    level       INT          NOT NULL
    );

CREATE TABLE IF NOT EXISTS electives.themes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS electives.courseAssigment
(
    user_id INT REFERENCES users (id) ON DELETE CASCADE,
    course_id INT REFERENCES courses (id) ON DELETE CASCADE,
    courseStatus INT NOT NULL,
    mark INT NOT NULL,
    UNIQUE (user_id, course_id)
    );
