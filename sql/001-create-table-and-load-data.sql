DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS belonging;
DROP TABLE IF EXISTS schedule;

CREATE TABLE users (
 id INT unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 code VARCHAR(20) NOT NULL UNIQUE,
 password VARCHAR(100) NOT NULL,
 PRIMARY KEY(id)
 );

CREATE TABLE groups (
 id INT unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 code VARCHAR(20) NOT NULL UNIQUE,
 password VARCHAR(100) NOT NULL,
 PRIMARY KEY(id)
 );

CREATE TABLE belonging (
 id INT unsigned AUTO_INCREMENT,
 user_id INT unsigned NOT NULL,
 group_id INT unsigned NOT NULL,
 PRIMARY KEY(id),
 FOREIGN KEY(user_id) REFERENCES users(id),
 FOREIGN KEY(group_id) REFERENCES `groups(id)`
 );

CREATE TABLE schedule (
 id INT unsigned AUTO_INCREMENT,
 user_id INT unsigned NOT NULL,
 group_id INT unsigned NOT NULL,
 title VARCHAR(20) NOT NULL,
 skd_date DATE NOT NULL,
 s-time TIME,
 e-time TIME,
 comment VARCHAR(100),
 PRIMARY KEY(schedule_id),
 FOREIGN KEY(user_id) REFERENCES users(id),
 FOREIGN KEY(group_id) REFERENCES groups(id)
 );

 INSERT INTO users (id, name, password) VALUES
 (1, "山田", "taro1"), (2, "加藤", "hanako2"), (3, "鈴木", "yusuke3");

 INSERT INTO groups (id, name, password) VALUES
 (1, "A", "groupA"),(2, "B", "groupB");

 INSERT INTO belonging (id, user_id, group_id) VALUES
 (1, 1, 1), (2, 2, 1), (3, 3, 2), (4, 1, 2);

 INSERT INTO schedule (id, user_id, group_id, title, skd_date, s_time, e_time, comment) VALUES
 (1, 1, 1, "game", "2024-01-30", "13:00", "14:00", "Let's enjoy!"),
 (2, 1, 2, "chat", "2024-02-04", null, null, null);
