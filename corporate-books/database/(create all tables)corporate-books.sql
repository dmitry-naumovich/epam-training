CREATE TABLE books 
(id INT PRIMARY KEY NOT NULL UNIQUE,
brief VARCHAR(50) NOT NULL,
publish_year INT NOT NULL,
author VARCHAR(255) NOT NULL);

CREATE TABLE employee
(id INT PRIMARY KEY NOT NULL UNIQUE,
e_name VARCHAR(255) NOT NULL,
date_of_birth DATE,
email VARCHAR(50) NOT NULL);

CREATE TABLE employee_book
(book_id INT NOT NULL,
employee_id INT NOT NULL,
id INT,
CONSTRAINT pk_employee_book
	PRIMARY KEY (book_id, employee_id),
CONSTRAINT fk_book
	FOREIGN KEY (book_id)
		REFERENCES books.id,
CONSTRAINT fk_employee
	FOREIGN KEY (employee_id)
		REFERENCES employee.id);