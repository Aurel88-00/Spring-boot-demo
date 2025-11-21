# Database Schema Creation Scripts

This directory contains PL/SQL scripts to create the `AUTHOR` and `BOOK` tables based on the Java domain models.

## Table Structures

### AUTHOR Table
- `id` (RAW(16)) - UUID primary key
- `first_name` (VARCHAR2(100)) - Author's first name
- `last_name` (VARCHAR2(100)) - Author's last name
- `birth_date` (DATE) - Author's birth date
- `created_at` (TIMESTAMP) - Record creation timestamp
- `updated_at` (TIMESTAMP) - Record last update timestamp

### BOOK Table
- `id` (RAW(16)) - UUID primary key
- `title` (VARCHAR2(255)) - Book title
- `author_id` (RAW(16)) - Foreign key to AUTHOR table (one-to-many relationship)
- `genre` (VARCHAR2(50)) - Book genre (constrained to enum values)
- `published_date` (DATE) - Publication date
- `language` (VARCHAR2(50)) - Book language
- `price` (VARCHAR2(50)) - Book price (stored as String)
- `created_at` (TIMESTAMP) - Record creation timestamp
- `updated_at` (TIMESTAMP) - Record last update timestamp

## Available Scripts

### 1. `create_tables.sql` - PL/SQL Procedure Version
Wraps table creation in a stored procedure with error handling.

**Usage:**
```sql
-- In SQL*Plus or SQL Developer
@create_tables.sql
```

Or execute directly:
```sql
EXEC create_book_author_tables;
```

### 2. `create_tables_standalone.sql` - Standalone SQL Script
Simple SQL script that can be run directly.

**Usage:**
```sql
-- In SQL*Plus or SQL Developer
@create_tables_standalone.sql
```

Or copy-paste into any Oracle SQL client.

## Features

- **Idempotent**: Scripts drop existing tables before creating new ones
- **Indexes**: Creates indexes on frequently queried columns
- **Constraints**: 
  - Primary keys on both tables
  - Check constraint on BOOK.genre to match Java enum values
- **Triggers**: Automatic `updated_at` timestamp updates
- **Comments**: Table and column documentation
- **Mock Data**: Includes sample data for testing:
  - 10 authors (J.K. Rowling, George Orwell, Jane Austen, Stephen King, Agatha Christie, J.R.R. Tolkien, Isaac Asimov, Ernest Hemingway, Mark Twain, Virginia Woolf)
  - 20 books across various genres (Fantasy, Science Fiction, Romance, Horror, Mystery, History, Other)

## Mock Data

Both scripts include sample data for immediate testing:

**Authors (10 records):**
- J.K. Rowling, George Orwell, Jane Austen, Stephen King, Agatha Christie
- J.R.R. Tolkien, Isaac Asimov, Ernest Hemingway, Mark Twain, Virginia Woolf

**Books (20 records):**
- Multiple books per author across different genres
- Genres include: FANTASY, SCIENCE_FICTION, ROMANCE, HORROR, MYSTERY, HISTORY, OTHER
- Realistic publication dates, prices, and languages

The mock data uses fixed UUIDs (converted to RAW(16)) for consistency across runs.

## Relationship

**One-to-Many Relationship:**
- One `AUTHOR` can have many `BOOK` records
- The `BOOK.author_id` column is a foreign key referencing `AUTHOR.id`
- Foreign key constraint: `fk_book_author` with `ON DELETE CASCADE`
  - Deleting an author will automatically delete all their books
- Index on `author_id` for efficient joins and lookups

## Notes

- UUIDs are stored as `RAW(16)` for efficient storage (16 bytes vs 36 characters)
- The `BOOK.author_id` field is a foreign key to the `AUTHOR` table (one-to-many relationship)
- Genre values must match the Java `Genres` enum exactly
- Both scripts include error handling for missing tables
- Mock data is inserted after table creation and committed automatically
- Foreign key ensures referential integrity between authors and books

## Mapping Java Types to Oracle Types

| Java Type | Oracle Type | Notes |
|-----------|-------------|-------|
| UUID | RAW(16) | Efficient binary storage |
| String | VARCHAR2(n) | Variable length string |
| LocalDate | DATE | Date without time |
| Enum | VARCHAR2(50) | Stored as string with check constraint |

