DROP DATABASE IF EXISTS xiaoyi8003;
CREATE DATABASE xiaoyi8003 CHARACTER SET UTF8;
USE xiaoyi8003;
CREATE TABLE dept (
    deptno		BIGINT 		AUTO_INCREMENT,
    dname			VARCHAR(50),
    loc			VARCHAR(50),
    CONSTRAINT pk_deptno PRIMARY KEY(deptno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO dept(dname,loc) VALUES ('开发部', database());
INSERT INTO dept(dname,loc) VALUES ('财务部', database());
INSERT INTO dept(dname,loc) VALUES ('市场部', database());
INSERT INTO dept(dname,loc) VALUES ('后勤部', database());
INSERT INTO dept(dname,loc) VALUES ('公关部', database());
COMMIT;