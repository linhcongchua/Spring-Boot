insert into category(id , code, name, description) values
(1, 'DL','Dien lanh',''),
(2, 'DT','Dien tu',''),
(3, 'MP','Hang My Pham',''),
(4, 'TD','Hang tieu dung','');

insert into staff(id, address, dob, email, fullname, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, phone, user_role, username, note, created_date)
values
(1, 'Ha Bang - Thach That', '2000-7-25', 'dovanlinh257200@gmail.com', 'do van linh', true, true, true, true, '$2a$10$z0rUUfcxWDHy6DHtMC6RZe43C/3B01Wf8Y2/bCvkeTNrJPVa5tsjS', '0971741127', 'MANAGER', 'admin', 'is C.O', '2021-12-20');