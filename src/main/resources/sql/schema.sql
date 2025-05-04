CREATE TABLE ISSUE
(
    issue_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    status      VARCHAR(255), -- 这个字段对应的是枚举类型 IssueStatus
    createdAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);