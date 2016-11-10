--sequenc

drop SEQUENCE USER_SEQ;

create SEQUENCE user_seq
start with 1
increment by 1
MAXVALUE 9999999999;

drop SEQUENCE guestbook_SEQ;

create SEQUENCE guestbook_SEQ
start with 1
increment by 1
MAXVALUE 9999999999;

drop SEQUENCE board_seq;

create SEQUENCE board_seq
start with 1
increment by 1
MAXVALUE 9999999999;

drop SEQUENCE GALLERY_SEQ;

create SEQUENCE gallery_seq
start with 1
increment by 1
MAXVALUE 9999999999;


