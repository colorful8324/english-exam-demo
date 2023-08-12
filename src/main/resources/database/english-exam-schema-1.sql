-- SQLINES LICENSE FOR EVALUATION USE ONLY
drop database if exists `sql6634723`;
create database `sql6634723`;
use sql6634723;

drop table if exists `USERS`;
create table `USERS`
(
    US_ID            INT AUTO_INCREMENT not null,
    US_USERNAME      VARCHAR(45)                                                      not null
        unique,
    US_PASSWORD      VARCHAR(120)                                                     not null,
    US_FULLNAME      VARCHAR(45)                                                      not null,
    US_ADDRESS       VARCHAR(45),
    US_MOBILE_NUMBER VARCHAR(10) unique,
    US_EMAIL         VARCHAR(45) unique,
    US_CREATED_DATE  DATETIME,
    US_STATUS        TINYINT,
    US_GROUP         DOUBLE,
    primary key (US_ID)
)
;

drop table if exists `LOGS`;
create table `LOGS`
(
    LG_ID       INT AUTO_INCREMENT not null,
    LG_NAME     VARCHAR(120)                                                     not null,
    LG_STATUS   TINYINT,
    LG_DATETIME DATETIME,
    US_ID       INT,
    primary key (LG_ID),
    constraint `LOGS_USERS_US_ID_fk`
        foreign key `US_ID-fk` (US_ID) references USERS (US_ID)
        on delete cascade on update cascade
)
;

drop table if exists `TOPICS`;
create table TOPICS
(
    TP_ID          INT AUTO_INCREMENT not null,
    TP_NAME        VARCHAR(120)                                                     not null,
    TP_DESCRIPTION VARCHAR(200),
    primary key (TP_ID)
)
;

drop table if exists `EXAMS`;
create table EXAMS
(
    EX_ID          INT AUTO_INCREMENT not null,
    EX_PERCENT     DOUBLE                                                            not null,
    TP_ID          INT                                                            not null,
    EX_QUESTION_NO INT,
    EX_TIME        DATETIME,
    primary key (EX_ID)
)
;

drop table if exists `RESULTS`;
create table RESULTS
(
    RS_ID      INT AUTO_INCREMENT not null,
	primary key (RS_ID),
    RS_SCORE   DOUBLE                                                            not null,
    RS_TIME    DATETIME,
    USER_US_ID INT                                                            not null,
    constraint `RESULTS_USERS_US_ID_fk`
        foreign key `USER_US_ID-fk` (USER_US_ID) references USERS (US_ID),
    EXAM_EX_ID INT                                                            not null,
    constraint `RESULTS_EXAMS_EX_ID_fk`
        foreign key `EXAM_EX_ID-fk` (EXAM_EX_ID) references EXAMS (EX_ID)
)
;

drop table if exists `QUESTIONS`;
create table QUESTIONS
(
    QU_ID      INT AUTO_INCREMENT not null,
    primary key (QU_ID),
    QU_CONTENT VARCHAR(500)                                                     not null,
    QU_OPTION1 VARCHAR(200),
    QU_OPTION2 VARCHAR(200),
    QU_OPTION3 VARCHAR(200),
    QU_OPTION4 VARCHAR(200),
    QU_ANSWER  TINYINT                                                         not null
        check (qu_answer IN (1, 2, 3, 4)),
    QU_EXPLAIN LONGTEXT,
    QU_TYPE    TINYINT,
    TP_ID      INT                                                            not null,
        constraint `QUESTIONS_TOPICS_TP_ID_fk`
        foreign key `TP_ID-fk` (TP_ID) references TOPICS (TP_ID),
    LV_ID      INT                                                            not null
)
;

drop table if exists `EXAMS_QUESTIONS`;
create table EXAMS_QUESTIONS
(
    EQ_ID      INT AUTO_INCREMENT not null,
	primary key (EQ_ID),
    EX_ID      INT                                                            not null,
	constraint `EXAMS_QUESTIONS_EXAMS_EX_ID_fk`
		foreign key `EX_ID-fk` (EX_ID) references EXAMS (EX_ID),
    QU_ID      INT                                                            not null,
	constraint `EXAMS_QUESTIONS_QUESTIONS_TP_ID_fk`
		foreign key `QU_ID-fk` (QU_ID) references QUESTIONS (QU_ID)
)
;

drop table if exists `EXAM_TOPIC`;
create table EXAM_TOPIC
(
    ET_ID      INT AUTO_INCREMENT not null,
	primary key (ET_ID),
    EX_ID      INT                                                            not null,
	constraint `EXAM_TOPIC_EXAMS_EX_ID_fk`
		foreign key `EX_ID-fk` (EX_ID) references EXAMS (EX_ID),
    TP_ID      INT                                                            not null,
	constraint `EXAM_TOPIC_TOPICS_TP_ID_fk`
		foreign key `TP_ID-fk` (TP_ID) references TOPICS (TP_ID),
    ET_PERCENT DOUBLE
)
;

SET FOREIGN_KEY_CHECKS=0;
truncate table USERS;
SET FOREIGN_KEY_CHECKS=1;
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO USERS(us_username, us_password, US_FULLNAME, us_address, us_mobile_number, us_email, us_created_date,
                  us_status, US_GROUP)
values ('root', '$2a$10$w3iJtF51PJWsTW.WR/reKu5d3GSoX6dPBfyE2M7KZVCILJFDxxPDW', 'My Admin', 'MyAddress', '0123456789',
        'myemail@gmail.com', SYSDATE(), 1, 0);
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO USERS(us_username, us_password, US_FULLNAME, us_address, us_mobile_number, us_email, us_created_date,
                  us_status, US_GROUP)
values ('user1', '$2a$10$Zx0CwehPR9vMnjyLMHuALu3PSqjTUHHaLU60PxaTjtU.8DSwZZixW', 'My User', 'MyAddress', '0123456798',
        'myuser@gmail.com', SYSDATE(), 1, 1);
-- data for topics table
-- INSERT INTO `english_exam`.`topics` (`TP_ID`, `TP_NAME`) 
-- VALUES ('1', 'stress'),
-- ('2', 'communication');        

-- data for questions table
-- INSERT INTO `english_exam`.`questions` (`QU_ID`, `QU_CONTENT`, `QU_OPTION1`, `QU_OPTION2`, `QU_OPTION3`, `QU_OPTION4`, `QU_ANSWER`, `QU_EXPLAIN`, `QU_TYPE`, `TP_ID`, `LV_ID`) 
-- VALUES ('1', ' After the flash flood, all the drains were overflowing _____ storm water.', 'from', 'with', 'for', 'by', '2', 'explain', '1', '1', '1'),
-- ('2', 'The ideas ______ in this essay are not particularly original.', 'are discussed', 'discussing', 'discussed', 'which are discussing', '3', 'explain', '1', '1', '1');


commit;