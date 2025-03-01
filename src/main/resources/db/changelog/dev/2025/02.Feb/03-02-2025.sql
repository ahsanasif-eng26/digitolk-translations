--liquibase formatted sql

--changeset muhammad.ahsan:create-users-table-01 context:staging
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);

--changeset muhammad.ahsan:create-translation-table-01 context:staging
CREATE TABLE translation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    locale VARCHAR(10) NOT NULL,
    tag VARCHAR(50),
    translation_key VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

--changeset muhammad.ahsan:add-user-01 context:staging
INSERT INTO user (id, username, password, role)
VALUES
    (1, 'admin@digitolk-transactions.com', '$2a$10$MqORaWvI9oNISRcvSHTR5OyZlSEmJv19Wvu3LNCfJnXlk01OA5iuO','ADMIN');

--changeset muhammad.ahsan:add-user-02 context:staging
INSERT INTO user (id, username, password, role)
VALUES
    (2, 'user@digitolk-transactions.com', '$2a$10$MqORaWvI9oNISRcvSHTR5OyZlSEmJv19Wvu3LNCfJnXlk01OA5iuO','USER');

