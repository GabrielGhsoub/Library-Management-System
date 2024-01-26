ALTER TABLE borrowing_records 
DROP FOREIGN KEY `borrowing_records_ibfk_1`;

ALTER TABLE borrowing_records
ADD CONSTRAINT `borrowing_records_ibfk_1`
FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE SET NULL;


ALTER TABLE borrowing_records 
DROP FOREIGN KEY `borrowing_records_ibfk_2`;

ALTER TABLE borrowing_records
ADD CONSTRAINT `borrowing_records_ibfk_2`
FOREIGN KEY (patron_id) REFERENCES patrons(id) ON DELETE SET NULL;
