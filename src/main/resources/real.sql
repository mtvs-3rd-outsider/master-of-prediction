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
SELECT 'novice.jpeg', 1, 1
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = 'novice.jpeg' AND attachment_regist_user_no = 1 AND tier_no = 1
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT 'apprentice.jpeg', 1, 2
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = 'apprentice.jpeg' AND attachment_regist_user_no = 1 AND tier_no = 2
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT 'seer.jpeg', 1, 3
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = 'seer.jpeg' AND attachment_regist_user_no = 1 AND tier_no = 3
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT 'oracle.jpeg', 1, 4
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = 'oracle.jpeg' AND attachment_regist_user_no = 1 AND tier_no = 4
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT 'prophet.jpeg', 1, 5
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = 'prophet.jpeg' AND attachment_regist_user_no = 1 AND tier_no = 5
);

INSERT INTO tbl_attachment (attachment_file_address, attachment_regist_user_no, tier_no)
SELECT 'nostradamus.jpeg', 1, 6
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_attachment WHERE attachment_file_address = 'nostradamus.jpeg' AND attachment_regist_user_no = 1 AND tier_no = 6
);

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