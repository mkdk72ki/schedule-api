DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS users-and-groups;
DROP TABLE IF EXISTS schedule;

CREATE TABLE users (
 user_id INT unsigned AUTO_INCREMENT,
 user_name VARCHAR(20) NOT NULL,
 user_password VARCHAR(20) NOT NULL,
 PRIMARY KEY(user_id)
 );

CREATE TABLE groups (
 group_id INT unsigned AUTO_INCREMENT,
 group_name VARCHAR(20) NOT NULL UNIQUE,
 group_password VARCHAR(20) NOT NULL,
 PRIMARY KEY(group_id)
 );

CREATE TABLE belonging (
 belonging_id INT unsigned AUTO_INCREMENT,
 user_id INT unsigned NOT NULL,
 group_id INT unsigned NOT NULL,
 PRIMARY KEY(belonging_id),
 FOREIGN KEY(user_id) REFERENCES users(user_id),
 FOREIGN KEY(group_id) REFERENCES `groups(group_id)`
 );

CREATE TABLE schedule (
 schedule_id INT unsigned AUTO_INCREMENT,
 user_id INT unsigned NOT NULL,
 group_id INT unsigned NOT NULL,
 title VARCHAR(20) NOT NULL,
 schedule_date DATE NOT NULL,
 start_time TIME,
 end_time TIME,
 comment VARCHAR(100),
 PRIMARY KEY(schedule_id),
 FOREIGN KEY(user_id) REFERENCES users(user_id),
 FOREIGN KEY(group_id) REFERENCES groups(group_id)
 );

 INSERT INTO users (user_id, user_name, user_password) VALUES
 (1, "山田", "taro1"), (2, "加藤", "hanako2"), (3, "鈴木", "yusuke3");

 INSERT INTO groups (group_id, group_name, group_password) VALUES
 (1, "A", "groupA"),(2, "B", "groupB");

 INSERT INTO belonging (belonging_id, user_id, group_id) VALUES
 (1, 1, 1), (2, 2, 1), (3, 3, 2), (4, 1, 2);

 INSERT INTO schedule (schedule_id, user_id, group_id, title, schedule_date, start_time, end_time, comment) VALUES
 (1, 1, 1, "game", "2024-01-30", "13:00", "14:00", "Let's enjoy!"),
 (2, 1, 2, "chat", "2024-02-04", null, null, null);
