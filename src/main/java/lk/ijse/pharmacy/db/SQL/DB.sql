create database pharmacy;
       use pharmacy;
create table user(
                     userId varchar(50)primary key,
                     name varchar(50),
                     password varchar(50)
);


create table customer(
                         customerId varchar(50)primary key,
                         name varchar(50),
                         address varchar(50),
                         tel varchar(50),
                         userId varchar(50),
                         foreign key(userId) references user(userId) on update cascade on delete cascade

);

create table shelf(
                      shelfId varchar(50)primary key,
                      capacity varchar(50)
);

create table medicine(
                         medicineId varchar(50)primary key,
                         name varchar(50),
                         description varchar(50),
                         qtyOnHand varchar(50),
                         price decimal(10,2),
                         shelfId varchar(50),
                         foreign key(shelfId) references shelf(shelfId) on update cascade on delete cascade

);



create table supplier(
                         supplierId varchar(50)primary key,
                         supplierName varchar(50),
                         companyName varchar(50),
                         address varchar(50),
                         tel varchar(50)
);


create table payment(
                        paymentId varchar(50)primary key,
                        amount decimal(10,2),
                        paymentMethod varchar(50),
                        date varchar(20)
);


create table orders(
                       ordersId varchar(50)primary key,
                       date varchar(50),
                       paymentId varchar(50),
                       customerId varchar(50),
                       foreign key(paymentId) references payment(paymentId) on update cascade on delete cascade,
                       foreign key(customerId) references customer(customerId) on update cascade on delete cascade

);



create table employee(
                         employeeId varchar(50)primary key,
                         name varchar(50),
                         salary decimal(10,2),
                         address varchar(20),
                         tel varchar(50),
                         userId varchar(50),
                         foreign key(userId) references user(userId) on update cascade on delete cascade

);



create table ordersMedicineDetail(
                                     ordersId varchar(50),
                                     medicineId varchar(50),
                                     foreign key(ordersId) references orders(ordersId) on update cascade on delete cascade,
                                     foreign key(medicineId) references medicine(medicineId) on update cascade on delete cascade

);

create table medicineSupplierDetail(
                                       medicineId varchar(50),
                                       supplierId varchar(50),
                                       foreign key(medicineId) references medicine(medicineId) on update cascade on delete cascade,
                                       foreign key(supplierId) references supplier(supplierId) on update cascade on delete cascade

);







