--users
select * from users;

--insert
insert into users values(user_seq.nextval, 'sun','sun@naver.com','123','female');

--delete
delete from users;

commit;


--select (login)

select no, name from users where email='asd@daum.net' and password='asd'; 

--board

--view
select no, title, content from  board where no=2;
update board set hit = hit+1 where no = 2; --조회수 늘리기 

--list
select count(*) from board;

 select *
 from(select rownum as rn, no, title, hit , reg_date, name
	 from (select b.no, b.title, b.hit, to_char(reg_date,'yyyy-mm-dd hh:mi:ss') as reg_date, u.name, b.USERS_NO
		 from board b, USERS u
		 where u.NO=b.USERS_NO
		  -- and title like '%kwd%' or content like '%kwd%'  search인 경우에 쓰임. 찾을 필요가 없으면 빼도 됨..
		 order by group_no desc, order_no asc))
			--나중에 자료가 많으면 달라짐
 where (2-1)*5+1 <= rn and  rn <= 2*5;
--where(3-1)*5+1 <= rownum -- 3은 current page 현재페이지 5가 페이지 사이즈  

--insert1 (새글)
select * from board;
select * from users;
select max(group_no) from board;

insert into board values(board_seq.nextval, '안녕','안녕하세요',sysdate,0 , nvl((select max(group_no) from board),0)+1, 1, 0, 11);  

insert into board values(board_seq.nextval, '뭐먹지?','무무무무냉무',sysdate,0 ,
nvl((select max(group_no) from board),0)+1, 1, 0, 2);
  
insert into board values(board_seq.nextval, '그만하자','냉무',sysdate,0 ,
nvl((select max(group_no) from board),0)+1, 1, 0, 21);
-- 답글
update board set order_no = order_no +1 where group_no =2 and order_no >1;

insert into board values(board_seq.nextval, '난 탕수육','냉무',sysdate,0 ,
 2, --부모글의 그룹
 2, --부모글의 순서 +1
 1, --부모글 깊이 +1 
 1);

update board set order_no = order_no +1 where group_no =2 and order_no >2;

insert into board values(board_seq.nextval, '짬뽕','냉무',sysdate,0 ,
 2, --부모글의 그룹
 3, --부모글의 순서 +1
 2, --부모글 깊이 +1 
 21);

update board set order_no = order_no +1 where group_no =2 and order_no >1; --부모글순서

insert into board values(board_seq.nextval, '안먹어!!!!','냉무',sysdate,0 ,
 2, --부모글의 그룹
 2, --부모글의 순서 +1
 1, --부모글 깊이 +1 
 1);
 
delete from board;

rollback;
commit;