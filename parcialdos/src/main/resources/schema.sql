DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          account_number VARCHAR(100) NOT NULL UNIQUE,
                          owner_name VARCHAR(255) NOT NULL,
                          balance DECIMAL(19,2) NOT NULL,
                          active BOOLEAN NOT NULL DEFAULT true
);
