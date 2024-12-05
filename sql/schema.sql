CREATE TABLE books (
       book_id VARCHAR(255) PRIMARY KEY,                 -- 책 ID, 고유 식별자
       book_title VARCHAR(255) NOT NULL,                 -- 책 제목
       author_name VARCHAR(255) NOT NULL,                -- 저자 이름
       publisher VARCHAR(255) NOT NULL,                  -- 출판사
       publication_date VARCHAR(255) NOT NULL,           -- 출판일 (형식: 'YYYY-MM-DD')
       purchase_date VARCHAR(255) NOT NULL,              -- 구매일 (형식: 'YYYY-MM-DD')
       category VARCHAR(100) NOT NULL,                   -- 카테고리 (예: 문학, 과학 등)
       domestic VARCHAR(255) NOT NULL                    -- 국내/해외 (기본값: 'NO')
);
