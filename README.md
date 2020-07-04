# OnlineShopBE
## Database setup
 1. Open MySQL  Workbench 
 2. Execute the following query: ``Create Database OnlineShop``
 3. Be sure you are on the right schema, the name of the schema should be bolded.
 4. Run the following queries: ``CREATE TABLE product_category( 
                                 id int NOT NULL PRIMARY KEY,
                                 category_name varchar(8),
                                 category_type int,
                                 create_time time,
                                 update_time time );
                                 CREATE TABLE product_info( 
                                 id int NOT NULL PRIMARY KEY,
                                 category_type int,
                                 create_time time,
                                 product_description varchar(45),
                                 product_name varchar(10),
                                 product_price int;
                                 product_status varchar(2),
                                 product_stock varchar(10),
                                 update_time time );
                                 CREATE TABLE cart( 
                                 id int NOT NULL PRIMARY KEY);
                                 CREATE TABLE user( 
                                 id int(8) NOT NULL PRIMARY KEY,
                                 user_active boolean,
                                 user_addres varchar(13),
                                 user_name  varchar(20),
                                 user_password varchar(20),
                                 user_phone varchar(12),
                                 user_role varchar(2));
                                 CREATE TABLE product_in_order( 
                                 id int NOT NULL PRIMARY KEY,
                                 catgory_type int,
                                 product_description varchar(45),
                                 product_id varchar(3),
                                 product_name varchar(10),
                                 product_price int,
                                 product_status varchar(2),
                                 product_stock varchar(10),
                                 cart_user_id int,
                                 order_id int);
                                 CREATE TABLE order_main(
                                 id int NOT NULL PRIMARY KEY,
                                 buyer_address varchar(8),
                                 buyer_email varchar(100),
                                 buyer_name varchar(100),
                                 buyer_phone varchar(8),
                                 create_time time,
                                 catgory_type int,
                                 order_amount int,
                                 order_status int,
                                 update_time time);
 ``