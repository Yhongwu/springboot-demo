#第一次启动通过该文件初始化一些数据
#运行后记得把文件重命名
insert into SYS_USER (id,username, password) values (1,'hongwu', '123456');
insert into SYS_USER (id,username, password) values (2,'tom', '654321');

insert into SYS_ROLE(id,name) values(1,'ROLE_ADMIN');
insert into SYS_ROLE(id,name) values(2,'ROLE_USER');

insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(1,1);
insert into SYS_USER_ROLES(SYS_USER_ID,ROLES_ID) values(2,2);