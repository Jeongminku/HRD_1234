create table song (
SONGNO number PRIMARY KEY,
TITLE varchar2(100) not null,
YADDRESS VARCHAR2(100) not null,
SINGER VARCHAR2(30) NOT NULL
);
select songno, title, singer from song where title like '%����ī%';
select songno, title, singer from song where title like '%����ī%';
DROP TABLE SONGREPLY;
drop table song;

CREATE TABLE SONGREPLY(
COMMENTNO NUMBER PRIMARY KEY,                           --��� ���� 1,2,3,4,5,6,7
SONGNO NUMBER NOT NULL,                                 --songno = Ư�� � ���� ����¡
CONSTRAINT FK_SONGNO FOREIGN KEY (SONGNO) REFERENCES SONG (SONGNO),
USERID VARCHAR2(20) NOT NULL,                           --�͸��̾ �ʿ���.
REP_CONTENT VARCHAR(200) NOT NULL,                      --��۳��� �ʿ���.
REP_DATE DATE                                           --sysdate
);

insert into songreply values (������, ?, ?, ?, sysdate);
rs.setInt(get)
insert into songreply (commentno, songno, userid, rep_content, rep_date ) values (12, 12300, 'userid', 'rep_content', sysdate);
select * from songreply;

select * from song;
insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12300, 'Ȳ�ݺ�', 'https://www.youtube.com/watch?v=2obiMmY-ctI', '������');
insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12345, '����ī', 'https://www.youtube.com/watch?v=JUEoW9_BAS8', '������');
insert into song (songno, title, yaddress, singer) values(12355, '����ī', 'https://www.youtube.com/watch?v=a-5qMx92Tj4', '�ſ���');
insert into song (songno, title, singer, yaddress) values (1,'2','3','4');
commit;
select * from song;
select songno, title, yaddress from song;
select songno, title, singer from song where title='Ȳ�ݺ�';

select songno, title, singer from song order by songno;