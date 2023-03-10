create table song (
SONGNO number PRIMARY KEY,
TITLE varchar2(100) not null,
YADDRESS VARCHAR2(100) not null,
SINGER VARCHAR2(30) NOT NULL
);

insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12300, '황금별', '2obiMmY-ctI', '정동원');
insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12345, '레베카', 'JUEoW9_BAS8', '옥주현');
insert into song (songno, title, yaddress, singer) values(12355, '레베카', 'a-5qMx92Tj4', '신영숙');
insert into song (songno, title, yaddress, singer) values(12365, '사건의 지평선', 'mnpQsM-tqQU', '윤하');
insert into song (songno, title, yaddress, singer) values(12375, '빌런', 'yVEx-Dpd38k', '스텔라장');
insert into song (songno, title, yaddress, singer) values(12385, '시차', 'H04mbKV44so', '우원재');
insert into song (songno, title, yaddress, singer) values(12395, '상사화', 'f1okXgx7NvU', '안예은');

select * from song;

CREATE SEQUENCE AUTOPLUS INCREMENT BY 1 START WITH 1 MAXVALUE 9999 MINVALUE 1 NOCYCLE NOCACHE NOORDER;

CREATE TABLE SONGREPLY(
COMMENTNO NUMBER PRIMARY KEY,                           --댓글 순서 1,2,3,4,5,6,7
SONGNO NUMBER NOT NULL,                                 --songno = 특정 곡에 따른 페이징
CONSTRAINT FK_SONGNO FOREIGN KEY (SONGNO) REFERENCES SONG (SONGNO),
USERID VARCHAR2(20) NOT NULL,                           --익명이어도 필요함.
REP_CONTENT VARCHAR(200) NOT NULL,                      --댓글내용 필요함.
REP_DATE DATE                                           --sysdate
);

insert into songreply values(AUTOPLUS.nextval, 12300, '익명유저', '꼬마가 노래도 잘부르네', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12345, '신영숙', '내가 더 잘부르는듯 ', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12355, '옥주현', '내가 더 잘하는듯', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12365, '유나', '잘듣고갑니다', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12375, '조커', '내가 빌런', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12385, '외국인', '지금 몇신가', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12395, '예은이', '이노래 너무 슬퍼요', sysdate);
commit;
