CREATE TABLE IF NOT EXISTS login_auth (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    is_registered BOOLEAN NOT NULL,
    login VARCHAR(25),
    user_id VARCHAR(36));
