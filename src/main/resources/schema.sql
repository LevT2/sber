create table BANK
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255)
);

create table ACCOUNT_TYPE
(
   ID BIGINT AUTO_INCREMENT PRIMARY KEY,
   NAME VARCHAR(255),
   BANK_ID BIGINT NOT NULL
);

create table CLIENTS
(
   ID BIGINT AUTO_INCREMENT PRIMARY KEY,
   NAME VARCHAR(255),
   DATE_ACC DATETIME,
   ACCOUNT_TYPE_ID BIGINT NOT NULL
);

ALTER TABLE ACCOUNT_TYPE ADD FOREIGN KEY (BANK_ID) REFERENCES BANK(ID);
ALTER TABLE CLIENTS ADD FOREIGN KEY (ACCOUNT_TYPE_ID) REFERENCES ACCOUNT_TYPE(ID);
