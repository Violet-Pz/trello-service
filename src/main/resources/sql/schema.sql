CREATE TABLE Issue
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    status      VARCHAR(255),
    createdAt   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);