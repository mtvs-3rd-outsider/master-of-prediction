DROP TABLE IF EXISTS `TBL_APPEAL`;

DROP TABLE IF EXISTS `TBL_ATTACHMENT`;

DROP TABLE IF EXISTS `TBL_BETTING_ORDER`;

DROP TABLE IF EXISTS `TBL_CATEGORY`;

DROP TABLE IF EXISTS `TBL_COMMENT`;

DROP TABLE IF EXISTS `TBL_COMMENT_REPLY`;

DROP TABLE IF EXISTS `TBL_INQUIRY`;

DROP TABLE IF EXISTS `TBL_INQUIRY_REPLY`;

DROP TABLE IF EXISTS `TBL_NOTICE`;

DROP TABLE IF EXISTS `TBL_POINT_HISTORY`;

DROP TABLE IF EXISTS `TBL_SUBJECT`;

DROP TABLE IF EXISTS `TBL_TIER`;

DROP TABLE IF EXISTS `TBL_USER`;

CREATE TABLE `TBL_APPEAL`
(
    `appeal_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '이의신청번호',
    `appeal_image_url`    VARCHAR(255) NOT NULL COMMENT '이미지주소',
    `appeal_content`    VARCHAR(255) NOT NULL COMMENT '내용',
    `appeal_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '이의신청등록 타임스탬프',
    `appeal_user_no`    BIGINT NOT NULL COMMENT '신청자',
    `appeal_subject_no`    BIGINT NOT NULL COMMENT '베팅상품번호',
    `appeal_response`    VARCHAR(255) COMMENT '이의신청답변',
    `appeal_response_timeStamp`    TIMESTAMP COMMENT '이의신청답변타임스탬프',
    PRIMARY KEY ( `appeal_no` )
) COMMENT = '이의신청';


CREATE TABLE `TBL_ATTACHMENT`
(
    `attachment_file_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '첨부파일번호',
    `attachment_file_address`    VARCHAR(255) NOT NULL COMMENT '첨부파일주소',
    `attachment_regist_user_no`    BIGINT NOT NULL COMMENT '등록자',
    `answer_no`    BIGINT COMMENT '답변번호',
    `inquiry_no`    BIGINT COMMENT '문의번호',
    `attachment_user_no`    BIGINT COMMENT '회원번호',
    `subject_no`    BIGINT COMMENT '베팅상품번호',
    `notice_no`    BIGINT COMMENT '공지사항번호',
    `tier_no`    BIGINT COMMENT '티어 번호',
    PRIMARY KEY ( `attachment_file_no` )
) COMMENT = '첨부파일';


CREATE TABLE `TBL_BETTING_ORDER`
(
    `order_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '주문번호',
    `order_amount`    INTEGER DEFAULT 0 NOT NULL COMMENT '소모 포인트',
    `order_choice`    VARCHAR(10) DEFAULT '0' NOT NULL COMMENT '선택',
    `order_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '구매 타임스탬프',
    `order_subject_no`    BIGINT NOT NULL COMMENT '베팅상품번호',
    `order_user_no`    BIGINT NOT NULL COMMENT '베팅회원번호',
    PRIMARY KEY ( `order_no` )
) COMMENT = '베팅 주문 내역';


CREATE TABLE `TBL_CATEGORY`
(
    `category_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '카테고리 번호',
    `category_name`    VARCHAR(51) NOT NULL COMMENT '카테고리 이름',
    PRIMARY KEY ( `category_no` )
) COMMENT = '상품 카테고리';


CREATE TABLE `TBL_COMMENT`
(
    `comment_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '댓글번호',
    `comment_content`    VARCHAR(255) NOT NULL COMMENT '내용',
    `comment_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '댓글작성 타임스탬프',
    `comment_user_no`    BIGINT NOT NULL COMMENT '댓글 작성자',
    `comment_subject_no`    BIGINT NOT NULL COMMENT '베팅상품번호',
    PRIMARY KEY ( `comment_no` )
) COMMENT = '댓글';


CREATE TABLE `TBL_COMMENT_REPLY`
(
    `reply_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '답글번호',
    `reply_content`    VARCHAR(255) NOT NULL COMMENT '내용',
    `reply_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '답글작성 타임스탬프',
    `reply_user_no`    BIGINT NOT NULL COMMENT '답글 작성자',
    `reply_comment_no`    BIGINT NOT NULL COMMENT '댓글번호',
    PRIMARY KEY ( `reply_no` )
) COMMENT = '답글';


CREATE TABLE `TBL_INQUIRY`
(
    `inquiry_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '문의번호',
    `inquiry_title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `inquiry_content`    VARCHAR(255) NOT NULL COMMENT '내용',
    `inquiry_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '문의일시',
    `inquiry_reply_status`    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '답변여부',
    `inquiry_user_no`    BIGINT NOT NULL COMMENT '작성자',
    PRIMARY KEY ( `inquiry_no` )
) COMMENT = '문의내역';


CREATE TABLE `TBL_INQUIRY_REPLY`
(
    `answer_no`    BIGINT NOT NULL AUTO_INCREMENT COMMENT '답변번호',
    `answer_content`    VARCHAR(255) NOT NULL COMMENT '답변내용',
    `answer_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '답변일시',
    `answer_inquiry_no`    BIGINT NOT NULL COMMENT '문의번호',
    `answer_user_no`    BIGINT NOT NULL COMMENT '답변자',
    `answer_title`    VARCHAR(255) NOT NULL COMMENT '답변제목',
    PRIMARY KEY ( `answer_no` )
) COMMENT = '답변';


CREATE TABLE `TBL_NOTICE`
(
    `notice_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '공지사항번호',
    `notice_title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `notice_content`    VARCHAR(255) NOT NULL COMMENT '내용',
    `notice_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '공지등록 타임스탬프',
    `notice_user_no`    BIGINT NOT NULL COMMENT '작성자',
    PRIMARY KEY ( `notice_no` )
) COMMENT = '공지사항';


CREATE TABLE `TBL_POINT_HISTORY`
(
    `change_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '변경내역번호',
    `change_amount`    DOUBLE DEFAULT 0 NOT NULL COMMENT '증감량',
    `change_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '포인트변경 타임스탬프',
    `change_reason`    VARCHAR(255) NOT NULL COMMENT '사유',
    `change_user_no`    BIGINT NOT NULL COMMENT '회원번호',
    PRIMARY KEY ( `change_no` )
) COMMENT = '포인트변경 내역';


CREATE TABLE `TBL_SUBJECT`
(
    `subject_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '베팅상품번호',
    `subject_title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `subject_total_yes_point`    INTEGER DEFAULT 0 NOT NULL COMMENT 'Yes 총 합계',
    `subject_status`    VARCHAR(100) DEFAULT '진행중' NOT NULL COMMENT '상태',
    `subject_register_timestamp`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '베팅등록 일시',
    `subject_finish_timestamp`    TIMESTAMP COMMENT '베팅종료 일시',
    `subject_category_no`    BIGINT NOT NULL COMMENT '카테고리 번호',
    `subject_register_user_no`    BIGINT NOT NULL COMMENT '등록자',
    `subject_finish_result`    VARCHAR(10) COMMENT '배팅승리결과',
    `subject_total_no_point`    INTEGER DEFAULT 0 NOT NULL COMMENT 'No 총 합계',
    `subject_settlement_timestamp`    TIMESTAMP NOT NULL COMMENT '배팅마감 일시',
    PRIMARY KEY ( `subject_no` )
) COMMENT = '베팅상품';


CREATE TABLE `TBL_TIER`
(
    `tier_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '티어 번호',
    `tier_name`    VARCHAR(20) NOT NULL COMMENT '티어 이름',
    `tier_content`    VARCHAR(255) NOT NULL COMMENT '티어 내용',
    `tier_begin`    INTEGER NOT NULL COMMENT '티어 조건 시작',
    `tier_end`    INTEGER NOT NULL COMMENT '티어 조건 마지막',
    PRIMARY KEY ( `tier_no` )
) COMMENT = '티어';


CREATE TABLE `TBL_USER`
(
    `user_no`    BIGINT NOT NULL AUTO_INCREMENT
        COMMENT '회원번호',
    `user_name`    VARCHAR(100) NOT NULL COMMENT '이름',
    `user_email`    VARCHAR(100) NOT NULL COMMENT '이메일',
    `user_password`    VARCHAR(255) NOT NULL COMMENT '비밀번호',
    `user_join_date`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '가입일',
    `user_authority`    VARCHAR(100) DEFAULT 'ROLE_USER' NOT NULL COMMENT '권한',
    `user_withdrawal_status`    TINYINT DEFAULT 0 COMMENT '탈퇴여부',
    `user_point`    DECIMAL DEFAULT 30000 COMMENT '보유포인트',
    `tier_no`    BIGINT DEFAULT 0 NOT NULL COMMENT '티어 번호',
    PRIMARY KEY ( `user_no` )
) COMMENT = '회원';


