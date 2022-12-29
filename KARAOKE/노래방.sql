create table song (
SONGNO number PRIMARY KEY,
TITLE varchar2(100) not null,
YADDRESS VARCHAR2(100) not null,
SINGER VARCHAR2(30) NOT NULL
);
select songno, title, singer from song where title like '%레베카%';
select songno, title, singer from song where title like '%레베카%';
DROP TABLE SONGREPLY;
drop table song;

CREATE TABLE SONGREPLY(
COMMENTNO NUMBER PRIMARY KEY,                           --댓글 순서 1,2,3,4,5,6,7
SONGNO NUMBER NOT NULL,                                 --songno = 특정 곡에 따른 페이징
CONSTRAINT FK_SONGNO FOREIGN KEY (SONGNO) REFERENCES SONG (SONGNO),
USERID VARCHAR2(20) NOT NULL,                           --익명이어도 필요함.
REP_CONTENT VARCHAR(200) NOT NULL,                      --댓글내용 필요함.
REP_DATE DATE                                           --sysdate
);

insert into songreply values (시퀀스, ?, ?, ?, sysdate);
rs.setInt(get)
insert into songreply (commentno, songno, userid, rep_content, rep_date ) values (12, 12300, 'userid', 'rep_content', sysdate);
select * from songreply;

select * from song;
insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12300, '황금별', 'https://www.youtube.com/watch?v=2obiMmY-ctI', '정동원');
insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12345, '레베카', 'https://www.youtube.com/watch?v=JUEoW9_BAS8', '옥주현');
insert into song (songno, title, yaddress, singer) values(12355, '레베카', 'https://www.youtube.com/watch?v=a-5qMx92Tj4', '신영숙');
insert into song (songno, title, singer, yaddress) values (1,'2','3','4');
commit;
select * from song;
select songno, title, yaddress from song;
select songno, title, singer from song where title='황금별';

select songno, title, singer from song order by songno;