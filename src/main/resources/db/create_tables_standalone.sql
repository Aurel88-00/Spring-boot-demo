-- Standalone SQL script to create AUTHOR and BOOK tables
-- Based on Java domain models: Author.java and Book.java
-- Run this script directly in SQL*Plus, SQL Developer, or any Oracle SQL client

-- Drop tables if they exist
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE book CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE author CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

-- Create AUTHOR table
-- Fields: id (UUID), firstName, lastName, birthDate
CREATE TABLE author (
    id RAW(16) NOT NULL,
    first_name VARCHAR2(100) NOT NULL,
    last_name VARCHAR2(100) NOT NULL,
    birth_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_author PRIMARY KEY (id)
);

COMMENT ON TABLE author IS 'Stores author information';
COMMENT ON COLUMN author.id IS 'UUID primary key (stored as RAW(16))';
COMMENT ON COLUMN author.first_name IS 'Author first name';
COMMENT ON COLUMN author.last_name IS 'Author last name';
COMMENT ON COLUMN author.birth_date IS 'Author birth date';

-- Create BOOK table
-- Fields: id (UUID), title, author_id (FK to AUTHOR), genre (enum), publishedDate, language, price
CREATE TABLE book (
    id RAW(16) NOT NULL,
    title VARCHAR2(255) NOT NULL,
    author_id RAW(16) NOT NULL,
    genre VARCHAR2(50) NOT NULL,
    published_date DATE,
    language VARCHAR2(50),
    price VARCHAR2(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_book PRIMARY KEY (id),
    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE,
    CONSTRAINT chk_book_genre CHECK (genre IN (
        'SCIENCE_FICTION', 'FANTASY', 'MYSTERY', 'HORROR',
        'ROMANCE', 'BIOGRAPHY', 'HISTORY', 'TRAVEL',
        'COOKING', 'SELF_HELP', 'OTHER'
    ))
);

COMMENT ON TABLE book IS 'Stores book information';
COMMENT ON COLUMN book.id IS 'UUID primary key (stored as RAW(16))';
COMMENT ON COLUMN book.title IS 'Book title';
COMMENT ON COLUMN book.author_id IS 'Foreign key to AUTHOR table (one-to-many relationship)';
COMMENT ON COLUMN book.genre IS 'Book genre from Genres enum';
COMMENT ON COLUMN book.published_date IS 'Book publication date';
COMMENT ON COLUMN book.language IS 'Book language';
COMMENT ON COLUMN book.price IS 'Book price (stored as String)';

-- Create indexes for better query performance
CREATE INDEX idx_author_name ON author(last_name, first_name);
CREATE INDEX idx_book_title ON book(title);
CREATE INDEX idx_book_genre ON book(genre);
CREATE INDEX idx_book_author_id ON book(author_id);

-- Create triggers to automatically update updated_at timestamp
CREATE OR REPLACE TRIGGER trg_author_updated_at
BEFORE UPDATE ON author
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER trg_book_updated_at
BEFORE UPDATE ON book
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

-- Display success message
SELECT 'Tables AUTHOR and BOOK created successfully' AS status FROM DUAL;

-- Insert mock data for AUTHOR table
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440000'), 'J.K.', 'Rowling', DATE '1965-07-31');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440001'), 'George', 'Orwell', DATE '1903-06-25');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440002'), 'Jane', 'Austen', DATE '1775-12-16');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440003'), 'Stephen', 'King', DATE '1947-09-21');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440004'), 'Agatha', 'Christie', DATE '1890-09-15');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440005'), 'J.R.R.', 'Tolkien', DATE '1892-01-03');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440006'), 'Isaac', 'Asimov', DATE '1920-01-02');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440007'), 'Ernest', 'Hemingway', DATE '1899-07-21');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440008'), 'Mark', 'Twain', DATE '1835-11-30');
INSERT INTO author (id, first_name, last_name, birth_date) VALUES
(HEXTORAW('550E8400E29B41D4A716446655440009'), 'Virginia', 'Woolf', DATE '1882-01-25');

COMMIT;

-- Insert mock data for BOOK table
-- Note: author_id references the author.id from the AUTHOR table
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440000'), 'Harry Potter and the Philosopher''s Stone', HEXTORAW('550E8400E29B41D4A716446655440000'), 'FANTASY', DATE '1997-06-26', 'English', '19.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440001'), '1984', HEXTORAW('550E8400E29B41D4A716446655440001'), 'SCIENCE_FICTION', DATE '1949-06-08', 'English', '14.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440002'), 'Pride and Prejudice', HEXTORAW('550E8400E29B41D4A716446655440002'), 'ROMANCE', DATE '1813-01-28', 'English', '12.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440003'), 'The Shining', HEXTORAW('550E8400E29B41D4A716446655440003'), 'HORROR', DATE '1977-01-28', 'English', '16.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440004'), 'Murder on the Orient Express', HEXTORAW('550E8400E29B41D4A716446655440004'), 'MYSTERY', DATE '1934-01-01', 'English', '13.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440005'), 'The Lord of the Rings', HEXTORAW('550E8400E29B41D4A716446655440005'), 'FANTASY', DATE '1954-07-29', 'English', '24.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440006'), 'Foundation', HEXTORAW('550E8400E29B41D4A716446655440006'), 'SCIENCE_FICTION', DATE '1951-05-01', 'English', '15.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440007'), 'The Old Man and the Sea', HEXTORAW('550E8400E29B41D4A716446655440007'), 'OTHER', DATE '1952-09-01', 'English', '11.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440008'), 'The Adventures of Huckleberry Finn', HEXTORAW('550E8400E29B41D4A716446655440008'), 'HISTORY', DATE '1884-12-10', 'English', '10.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440009'), 'To the Lighthouse', HEXTORAW('550E8400E29B41D4A716446655440009'), 'OTHER', DATE '1927-05-05', 'English', '13.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A71644665544000A'), 'Harry Potter and the Chamber of Secrets', HEXTORAW('550E8400E29B41D4A716446655440000'), 'FANTASY', DATE '1998-07-02', 'English', '19.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A71644665544000B'), 'Animal Farm', HEXTORAW('550E8400E29B41D4A716446655440001'), 'SCIENCE_FICTION', DATE '1945-08-17', 'English', '12.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A71644665544000C'), 'Sense and Sensibility', HEXTORAW('550E8400E29B41D4A716446655440002'), 'ROMANCE', DATE '1811-10-30', 'English', '12.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A71644665544000D'), 'It', HEXTORAW('550E8400E29B41D4A716446655440003'), 'HORROR', DATE '1986-09-15', 'English', '18.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A71644665544000E'), 'And Then There Were None', HEXTORAW('550E8400E29B41D4A716446655440004'), 'MYSTERY', DATE '1939-11-06', 'English', '14.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A71644665544000F'), 'The Hobbit', HEXTORAW('550E8400E29B41D4A716446655440005'), 'FANTASY', DATE '1937-09-21', 'English', '16.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440010'), 'I, Robot', HEXTORAW('550E8400E29B41D4A716446655440006'), 'SCIENCE_FICTION', DATE '1950-12-02', 'English', '14.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440011'), 'For Whom the Bell Tolls', HEXTORAW('550E8400E29B41D4A716446655440007'), 'HISTORY', DATE '1940-10-21', 'English', '15.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440012'), 'The Adventures of Tom Sawyer', HEXTORAW('550E8400E29B41D4A716446655440008'), 'HISTORY', DATE '1876-12-01', 'English', '11.99');
INSERT INTO book (id, title, author_id, genre, published_date, language, price) VALUES
(HEXTORAW('660E8400E29B41D4A716446655440013'), 'Mrs. Dalloway', HEXTORAW('550E8400E29B41D4A716446655440009'), 'OTHER', DATE '1925-05-14', 'English', '12.99');

COMMIT;

-- Display success message with record counts
SELECT 'Mock data inserted successfully' AS status FROM DUAL;
SELECT COUNT(*) AS author_count FROM author;
SELECT COUNT(*) AS book_count FROM book;

