CREATE TABLE members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,               -- 회원 ID (자동 증가)
    full_name VARCHAR(255) NOT NULL,                        -- 회원 이름
    phone_number VARCHAR(20) NOT NULL,                      -- 전화번호
    street_address VARCHAR(255) NOT NULL,                   -- 주소
    email_address VARCHAR(255) UNIQUE NOT NULL,             -- 이메일 (고유)
    withdrawn_at DATE DEFAULT NULL,                         -- 탈퇴 날짜 (NULL 가능)
    is_vip_member BOOLEAN DEFAULT FALSE,                    -- VIP 멤버 여부 (기본값 FALSE)
    account_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 계정 생성 날짜 (현재 날짜 기본값)
);
