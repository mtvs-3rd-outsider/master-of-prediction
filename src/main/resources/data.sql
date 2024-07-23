
INSERT INTO TBL_USER  (user_name,user_email, user_password) SELECT 'John Doe', '1@gmail.com' , '1'
    WHERE NOT EXISTS (SELECT * FROM TBL_USER WHERE user_email = 'john@example.com');

INSERT INTO TBL_USER  (user_name,user_email, user_password) SELECT 'Jane Doe', 'jane@example.com' , 'password123'
    WHERE NOT EXISTS (SELECT * FROM TBL_USER WHERE user_email = 'jane@example.com');
-- TBL_TIER 테이블에 조건부 데이터 삽입
INSERT INTO TBL_TIER (tier_no, tier_name, tier_content, tier_begin, tier_end)
SELECT 1, 'Novice', '초보자', 0, 20
    WHERE NOT EXISTS (SELECT 1 FROM TBL_TIER WHERE tier_no = 1);

INSERT INTO TBL_TIER (tier_no, tier_name, tier_content, tier_begin, tier_end)
SELECT 2, 'Apprentice', '견습생', 21, 40
    WHERE NOT EXISTS (SELECT 1 FROM TBL_TIER WHERE tier_no = 2);

INSERT INTO TBL_TIER (tier_no, tier_name, tier_content, tier_begin, tier_end)
SELECT 3, 'Seer', '선견자', 41, 60
    WHERE NOT EXISTS (SELECT 1 FROM TBL_TIER WHERE tier_no = 3);

INSERT INTO TBL_TIER (tier_no, tier_name, tier_content, tier_begin, tier_end)
SELECT 4, 'Oracle', '오라클', 61, 80
    WHERE NOT EXISTS (SELECT 1 FROM TBL_TIER WHERE tier_no = 4);

INSERT INTO TBL_TIER (tier_no, tier_name, tier_content, tier_begin, tier_end)
SELECT 5, 'Prophet', '예언자', 81, 90
    WHERE NOT EXISTS (SELECT 1 FROM TBL_TIER WHERE tier_no = 5);

INSERT INTO TBL_TIER (tier_no, tier_name, tier_content, tier_begin, tier_end)
SELECT 6, 'Nostradamus', '노스트라다무스', 91, 100
    WHERE NOT EXISTS (SELECT 1 FROM TBL_TIER WHERE tier_no = 6);
-- tbl_attachment 테이블에 조건부 데이터 삽입
INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT '초보자.svg', 1, 1
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = '초보자.svg' AND attachment_regist_user_no = 1 AND tier_no = 1
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT '견습생.svg', 1, 2
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = '견습생.svg' AND attachment_regist_user_no = 1 AND tier_no = 2
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT '선견자.svg', 1, 3
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = '선견자.svg' AND attachment_regist_user_no = 1 AND tier_no = 3
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT '오라클.svg', 1, 4
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = '오라클.svg' AND attachment_regist_user_no = 1 AND tier_no = 4
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT '예언자.svg', 1, 5
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = '예언자.svg' AND attachment_regist_user_no = 1 AND tier_no = 5
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT '노스트라다무스.svg', 1, 6
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = '노스트라다무스.svg' AND attachment_regist_user_no = 1 AND tier_no = 6
);

-- TblInquiry 테이블에 데이터 삽입
-- 세션 변수를 사용하여 이메일을 기준으로 user_no를 조회
SET @user_no = (SELECT user_no FROM TBL_USER WHERE user_email = '1@gmail.com');
SET @user_no2 = (SELECT user_no FROM TBL_USER WHERE user_email = 'jane@example.com');

-- TblAttachment 테이블에 데이터 삽입 (조건부)
INSERT INTO TBL_ATTACHMENT (attachment_user_no, attachment_regist_user_no, attachment_file_address)
SELECT @user_no, @user_no, 'https://lh3.googleusercontent.com/a/ACg8ocKCH9UgnTgbHfYEi5WGYXXlaMd-uz3gSyTyXUz-w78BUCC38XA=s96-c'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_ATTACHMENT WHERE attachment_user_no = @user_no
);

-- TblInquiry 테이블에 데이터 삽입 (조건부)
INSERT INTO TBL_INQUIRY (inquiry_user_no, inquiry_title, inquiry_content, inquiry_timestamp)
SELECT @user_no, '포인트 관련', '포인트가 안올라가용 수정해줘요', CURRENT_TIMESTAMP
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_INQUIRY WHERE inquiry_user_no = @user_no AND inquiry_title = '포인트 관련'
);
INSERT INTO TBL_INQUIRY (inquiry_user_no, inquiry_title, inquiry_content, inquiry_timestamp)
SELECT @user_no, '랭킹 관련', '랭킹이 안올라가용 수정해줘요', CURRENT_TIMESTAMP
WHERE NOT EXISTS (
    SELECT 1 FROM TBL_INQUIRY WHERE inquiry_user_no = @user_no AND inquiry_title = '랭킹 관련'
);
SET @inquiry_no = (SELECT inquiry_no FROM TBL_INQUIRY WHERE inquiry_title = '포인트 관련');

-- TBL_CATEGORY 테이블에 데이터 삽입 (조건부)

INSERT INTO TBL_CATEGORY ( category_name)
SELECT   '사회'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_CATEGORY WHERE   category_name = '사회'
);


INSERT INTO TBL_CATEGORY ( category_name)
SELECT   '스포츠'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_CATEGORY WHERE   category_name = '스포츠'
);
INSERT INTO TBL_CATEGORY ( category_name)
SELECT   '일상'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_CATEGORY WHERE   category_name = '일상'
);
INSERT INTO TBL_CATEGORY ( category_name)
SELECT   '연예'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_CATEGORY WHERE   category_name = '연예'
);
INSERT INTO TBL_CATEGORY ( category_name)
SELECT   '세계'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_CATEGORY WHERE   category_name = '세계'
);
-- TBL_SUBJECT 테이블에 데이터 삽입 (조건부)

SET @category_no = (SELECT category_no FROM TBL_CATEGORY WHERE category_name = '사회');

INSERT INTO TBL_SUBJECT (subject_title, subject_category_no, subject_register_user_no,subject_settlement_timestamp ,subject_register_timestamp)
SELECT  '트럼프 vs 바이든', @category_no , @user_no, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 5 DAY) , CURRENT_TIMESTAMP
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_SUBJECT WHERE subject_register_user_no = @user_no AND subject_category_no = @category_no AND subject_title = '트럼프 vs 바이든'
);
SET @subject_no = (SELECT subject_no FROM TBL_SUBJECT WHERE subject_title = '트럼프 vs 바이든');

INSERT INTO TBL_ATTACHMENT (subject_no, attachment_regist_user_no, attachment_file_address)
SELECT @subject_no, @user_no, '트럼프.jpg'
    WHERE NOT EXISTS (
    SELECT 1 FROM TBL_ATTACHMENT WHERE subject_no = @subject_no
);

INSERT INTO tbl_betting_order (order_amount, order_choice, order_subject_no,order_user_no)
values (10,'YES',@subject_no, @user_no);


INSERT INTO tbl_comment (comment_content, comment_user_no, comment_subject_no)
values ('트럼프가 무조건 이기지', @user_no, @subject_no );

UPDATE TBL_SUBJECT SET subject_total_yes_point = 10 WHERE subject_no = @subject_no;
UPDATE TBL_SUBJECT SET subject_total_no_point = 10 WHERE subject_no = @subject_no;
UPDATE TBL_SUBJECT SET subject_finish_result = 'YES' WHERE subject_no = @subject_no;

INSERT INTO TBL_INQUIRY_REPLY (answer_title, answer_content, answer_user_no, answer_inquiry_no)
values ('[답변]포인트가 안올라감','문제 시정했습니다. 확인해주세요', @user_no2, @inquiry_no  );

UPDATE TBL_INQUIRY SET inquiry_reply_status = 1 WHERE inquiry_user_no  = @user_no AND inquiry_no = @inquiry_no ;
