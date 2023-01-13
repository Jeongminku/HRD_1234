create table song (
SONGNO number PRIMARY KEY,
TITLE varchar2(100) not null,
YADDRESS VARCHAR2(100) not null,
SINGER VARCHAR2(30) NOT NULL
);

insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12300, 'Ȳ�ݺ�', '2obiMmY-ctI', '������');
insert into song (SONGNO, TITLE, YADDRESS, SINGER) values(12345, '����ī', 'JUEoW9_BAS8', '������');
insert into song (songno, title, yaddress, singer) values(12355, '����ī', 'a-5qMx92Tj4', '�ſ���');
insert into song (songno, title, yaddress, singer) values(12365, '����� ����', 'mnpQsM-tqQU', '����');
insert into song (songno, title, yaddress, singer) values(12375, '����', 'yVEx-Dpd38k', '���ڶ���');
insert into song (songno, title, yaddress, singer) values(12385, '����', 'H04mbKV44so', '�����');
insert into song (songno, title, yaddress, singer) values(12395, '���ȭ', 'f1okXgx7NvU', '�ȿ���');

select * from song;

CREATE SEQUENCE AUTOPLUS INCREMENT BY 1 START WITH 1 MAXVALUE 9999 MINVALUE 1 NOCYCLE NOCACHE NOORDER;

CREATE TABLE SONGREPLY(
COMMENTNO NUMBER PRIMARY KEY,                           --��� ���� 1,2,3,4,5,6,7
SONGNO NUMBER NOT NULL,                                 --songno = Ư�� � ���� ����¡
CONSTRAINT FK_SONGNO FOREIGN KEY (SONGNO) REFERENCES SONG (SONGNO),
USERID VARCHAR2(20) NOT NULL,                           --�͸��̾ �ʿ���.
REP_CONTENT VARCHAR(200) NOT NULL,                      --��۳��� �ʿ���.
REP_DATE DATE                                           --sysdate
);

insert into songreply values(AUTOPLUS.nextval, 12300, '�͸�����', '������ �뷡�� �ߺθ���', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12345, '�ſ���', '���� �� �ߺθ��µ� ', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12355, '������', '���� �� ���ϴµ�', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12365, '����', '�ߵ���ϴ�', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12375, '��Ŀ', '���� ����', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12385, '�ܱ���', '���� ��Ű�', sysdate);
insert into songreply values(AUTOPLUS.nextval, 12395, '������', '�̳뷡 �ʹ� ���ۿ�', sysdate);
commit;
