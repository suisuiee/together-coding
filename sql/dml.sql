-- DML 데이터 조작어 SELECT, INSERT, UPDATE, DELETE
-- SELECT --
-- User
SELECT * FROM TBL_USER;
SELECT * FROM TBL_POST;

select  count(*) from TBL_USER;

-- INSERT User
insert into tbl_user(name, password, email, phone, birthdate, role_id, grade_id)
values ('subin', '12345', 'subin@email.com', '01012345678','1992-09-08', '10', '10');

-- INSERT POST
insert into TBL_POST(user_id, title, content, ctg_id)
values ('1', '타이틀', '컨텐츠', '10');
insert into TBL_POST(user_id, title, content, ctg_id)
values ('1', '타이틀2', '컨텐츠2', '20');

-- UPDATE POST
update TBL_POST
set  title = '타이틀 수정',
     CONTENT = '컨텐츠 수정',
     CTG_ID = 20,
     ATTACHMENT = '첨부파일.pdf'
where id = 1;