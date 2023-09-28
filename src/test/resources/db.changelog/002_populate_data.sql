--liquibase formatted sql
--changeset pfigura:1

INSERT INTO car_marks VALUES (1, 'OPEL');
INSERT INTO car_marks VALUES (2, 'VOLVO');
INSERT INTO car_marks (mark) VALUES ('mark');

insert into cities values (1, 'WARSZAWA');
insert into cities values (2, 'KRAKOW');

insert into cars VALUES (1,'astra', '1.4l', 'diesel', true, null, null, 1,1,1 );
insert into cars VALUES (2,'corsa', '2.4l', 'diesel', true, null, null, 2,2,2 );
insert into cars VALUES (3,'omega', '3.4l', 'diesel', true, null, null, 1,2,3 );

insert into plate_numbers values (1, 'pierwszy');
insert into plate_numbers values (2, 'drugi');
insert into plate_numbers values (3, 'trzeci');
