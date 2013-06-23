CREATE TABLE PERSON (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	FIRST_NAME VARCHAR(255)  NULL,
	LAST_NAME VARCHAR(255)  NULL,
	DESIGNATION VARCHAR(255)  NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE ADDRESS (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	PERSON_ID INTEGER UNSIGNED  NOT NULL,
	ADDRESS_LINE_ONE VARCHAR(255)  NULL,
	ADDRESS_LINE_TWO VARCHAR(255)  NULL,
	LOCALITY VARCHAR(127)  NULL,
	CITY VARCHAR(127)  NULL,
	PIN_CODE  VARCHAR(10)  NULL,
	STATE VARCHAR(127)  NULL,
	LANDMARK VARCHAR(255)  NULL,
	ADDRESS_TYPE CHAR(16)  NULL,
	NOTES VARCHAR(255)  NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(PERSON_ID)  REFERENCES PERSON(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE PHONE (
	ID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PERSON_ID INTEGER UNSIGNED  NOT NULL,
	PHONE_TYPE VARCHAR(16)  NULL,
	PHONE_NUMBER VARCHAR(16)  NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE MEMBERSHIP_TYPE (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	NAME VARCHAR(255)  NOT NULL,
	VALIDITY LONG  NOT NULL,
	ENROLLMENT_FEES DOUBLE  NOT NULL,
	FEES DOUBLE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE MEMBER (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	PERSON_ID INTEGER UNSIGNED  NOT NULL,
	STATUS VARCHAR(255)  NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT UC_MEMBER_PERSON_ID UNIQUE (PERSON_ID)	
);

CREATE TABLE MEMBERSHIP_DETAIL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	MEMBER_ID INTEGER UNSIGNED  NOT NULL,
	MEMBERSHIP_TYPE_ID INTEGER UNSIGNED  NOT NULL,
	START_DATE DATETIME  NOT NULL,
	END_DATE DATETIME  NOT NULL,
	ENROLLMENT_FEES DOUBLE  NOT NULL,
	FEES DOUBLE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(MEMBER_ID) REFERENCES MEMBER(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(MEMBERSHIP_TYPE_ID) REFERENCES MEMBERSHIP_TYPE(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE MEMBER_VISIT (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	MEMBER_ID INTEGER UNSIGNED  NOT NULL,
	GUEST_COUNT LONG NOT NULL,
	VISIT_DATE DATE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(MEMBER_ID) REFERENCES MEMBER(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE MATERIAL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	NAME VARCHAR(512)  NOT NULL,
	DESCRIPTION VARCHAR(512)  NOT NULL,
	UNIT VARCHAR(512)  NOT NULL,
	TYPE CHAR(1)  NOT NULL,
	PRICE DOUBLE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE ITEM (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	MATERIAL_ID INTEGER UNSIGNED  NOT NULL,
	NAME VARCHAR(512)  NOT NULL,
	DESCRIPTION VARCHAR(512)  NOT NULL,
	ITEM_TYPE CHAR(1),
	PRICE DOUBLE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE INVENTORY (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	MATERIAL_ID INTEGER UNSIGNED  NOT NULL,
	DATE DATETIME  NOT NULL,
	ENTRY_DATE  DATETIME NOT NULL,
	CURRENT_STOCK LONG NOT NULL,
	QUANTITY LONG NOT NULL,
	CREDIT_DEBIT_FLAG CHAR  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(MATERIAL_ID) REFERENCES MATERIAL(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE PURCHASE_ORDER (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	DATE DATETIME  NOT NULL,
	AMOUNT DOUBLE NOT NULL,
	STATUS VARCHAR(255)  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE PURCHASE_ORDER_DETAIL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	PURCHASE_ORDER_ID INTEGER UNSIGNED  NOT NULL,
	MATERIAL_ID INTEGER UNSIGNED  NOT NULL,
	INVENTORY_ID INTEGER UNSIGNED  NOT NULL,
	QUANTITY LONG NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(MATERIAL_ID) REFERENCES MATERIAL(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(PURCHASE_ORDER_ID) REFERENCES PURCHASE_ORDER(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE BILL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	MEMBER_ID INTEGER UNSIGNED  NOT NULL,
	DATE DATETIME  NOT NULL,
	AMOUNT DOUBLE NOT NULL,
	PREVIOUS_BALANCE DOUBLE NOT NULL,
	STATUS VARCHAR(255)  NOT NULL,
	GUEST_COUNT LONG NULL,
	DELETE_FLAG CHAR  NULL,
	ACCOUNT_TRANSACTION_ID INTEGER UNSIGNED NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(MEMBER_ID) REFERENCES MEMBER(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(ACCOUNT_TRANSACTION_ID) REFERENCES ACCOUNT_TRANSACTION(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE BILL_DETAIL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	BILL_ID INTEGER UNSIGNED  NOT NULL,
	ITEM_ID INTEGER UNSIGNED  NOT NULL,
	QUANTITY LONG NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(ITEM_ID) REFERENCES ITEM(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(BILL_ID) REFERENCES BILL(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE CATEGORY (
	ID INTEGER   NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255) NOT NULL,
	DESCRIPTION VARCHAR(255) NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE CATEGORY_ATTRIBUTE (
	ID INTEGER   NOT NULL AUTO_INCREMENT,
	CATEGORY_ID INTEGER   NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE ACCOUNT (
	ID INTEGER   NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255) NOT NULL,
	ACCOUNT_NUMBER VARCHAR(255) NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE ACCOUNT_TRANSACTION (
	ID INTEGER   NOT NULL AUTO_INCREMENT,
	ACCOUNT_ID INTEGER   NOT NULL,
	TRANSACTION_DATE DATE NOT NULL,
	CREDIT_DEBIT CHAR NOT NULL,
	AMOUNT DOUBLE NOT NULL,
	NOTES VARCHAR(255) NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE ACCOUNT_TRANSACTION_DETAIL (
	ID INTEGER   NOT NULL AUTO_INCREMENT,
	CATEGORY_ATTRIBUTE_ID INTEGER   NOT NULL,
	ACCOUNT_TRANSACTION_ID INTEGER   NOT NULL,
	VALUE VARCHAR(255) NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(ACCOUNT_TRANSACTION_ID) REFERENCES ACCOUNT_TRANSACTION(ID)  ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(CATEGORY_ATTRIBUTE_ID) REFERENCES CATEGORY_ATTRIBUTE(ID)  ON DELETE NO ACTION ON UPDATE NO ACTION
 );

CREATE TABLE TAX (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	TAX_TYPE  VARCHAR(255)  NULL,
	ORDER_TYPE  VARCHAR(255)  NULL,
	TAX_PERCENTAGE DOUBLE UNSIGNED  NOT NULL,
	DESCRIPTION  VARCHAR(255)  NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE ORDER_TAX_DETAIL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	PURCHASE_ORDER_ID INTEGER UNSIGNED  NOT NULL,
	TAX_ID INTEGER UNSIGNED  NOT NULL,
	TAX_VALUE DOUBLE  NOT NULL,
	TAX_AMOUNT DOUBLE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	INDEX ORDER_TAX_DETAILS_FKINDEX1(PURCHASE_ORDER_ID)  ,
	INDEX ORDER_TAX_DETAILS_FKINDEX2(TAX_ID),
	FOREIGN KEY(PURCHASE_ORDER_ID) REFERENCES PURCHASE_ORDER(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(TAX_ID) REFERENCES TAX(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE BILL_TAX_DETAIL (
	ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	BILL_ID INTEGER UNSIGNED  NOT NULL,
	TAX_ID INTEGER UNSIGNED  NOT NULL,
	TAX_VALUE DOUBLE  NOT NULL,
	TAX_AMOUNT DOUBLE  NOT NULL,
	DELETE_FLAG CHAR  NULL,
	PRIMARY KEY(ID),
	INDEX BILL_TAX_DETAILS_FKINDEX1(BILL_ID)  ,
	INDEX BILL_TAX_DETAILS_FKINDEX2(TAX_ID),
	FOREIGN KEY(BILL_ID) REFERENCES BILL(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY(TAX_ID) REFERENCES TAX(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);