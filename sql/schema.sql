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

CREATE TABLE members (
        member_id VARCHAR(255) PRIMARY KEY,
        full_name VARCHAR(255) NOT NULL,
        phone_number VARCHAR(20) NOT NULL,
        street_address VARCHAR(255) NOT NULL,
        email_address VARCHAR(255) NOT NULL,
        is_vip BOOLEAN DEFAULT FALSE NOT NULL,
        is_withdrawn BOOLEAN DEFAULT FALSE NOT NULL
);
