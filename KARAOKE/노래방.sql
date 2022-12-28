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
COMMENTNO NUMBER PRIMARY KEY,
SONGNO NUMBER NOT NULL,
CONSTRAINT FK_SONGNO FOREIGN KEY (SONGNO) REFERENCES SONG (SONGNO),
COMMENTID NUMBER NOT NULL,
USERID VARCHAR2(20) NOT NULL,
REP_CONTENT VARCHAR(90) NOT NULL,
REP_DATE DATE
);
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