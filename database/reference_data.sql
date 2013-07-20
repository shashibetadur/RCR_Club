insert into CATEGORY(id,name) values(1,'MEMBERSHIP-FEES');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(1,1,'MEMBER_ID');


insert into CATEGORY(id,name) values(2,'MEMBER-BILL');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(2,2,'MEMBER_ID');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(7,2,'GRACE_AMOUNT');

insert into CATEGORY(id,name) values(3,'PURCHASE-ORDER');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(3,3,'PURCHASE_ORDER_ID');

insert into CATEGORY(id,name) values(4,'EXPENSE');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(4,4,'EXPESNE_TYPE');


insert into CATEGORY(id,name) values(5,'TRANSFER');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(5,5,'FROM-ACCOUNT_ID');
insert into CATEGORY_ATTRIBUTE(id,CATEGORY_ID,name) VALUES(6,5,'TO-ACCOUNT_ID');

