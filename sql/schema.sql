CREATE TABLE books (
       book_id VARCHAR(255) PRIMARY KEY,
       book_title VARCHAR(255) NOT NULL,
       author_name VARCHAR(255) NOT NULL,
       publisher VARCHAR(255) NOT NULL,
       publication_date date NOT NULL,              -- 'YYYY-MM-DD'
       purchase_date date NOT NULL,
       category VARCHAR(100) NOT NULL,
       is_domestic bool NOT NULL
);
