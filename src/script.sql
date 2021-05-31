CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     login VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(50) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100) NOT NULL,
    role INT NOT NULL,
    status INT NOT NULL DEFAULT 0
    );