drop database if exists hit_group;
create database hit_group;
use hit_group;
create table t_group
(
	group_id bigint AUTO_INCREMENT,
	name varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY (group_id)
);
create table t_user
(
	user_id bigint AUTO_INCREMENT,
	username varchar(255) NOT NULL UNIQUE,
	password varchar(255) NOT NULL,
	sex varchar(255),
	exp int NOT NULL,
	membered int NOT NULL,
	thumbnail varchar(255),
	realname varchar(255),
	age int,
	major varchar(255),
	address varchar(255),
	email varchar(255) NOT NULL,
	qq varchar(255),
	phone varchar(255),
	watched int NOT NULL,
	banned int NOT NULL,
	PRIMARY KEY(user_id)
);
create table t_friends
(
	user_id bigint,
	friend_id bigint,
	PRIMARY KEY(user_id, friend_id),
	CONSTRAINT t_friends_user_user_id_fk FOREIGN KEY(user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_friends_friend_user_id_fk FOREIGN KEY(friend_id) REFERENCES t_user(user_id)
);
create table t_watchers
(
	user_id bigint,
	watcher_id bigint,
	PRIMARY KEY(user_id, watcher_id),
	CONSTRAINT t_watchers_user_user_id_fk FOREIGN KEY(user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_watchers_watcher_user_id_fk FOREIGN KEY(watcher_id) REFERENCES t_user(user_id)
);
create table t_news_category
(
	news_category_id bigint AUTO_INCREMENT,
	category varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY(news_category_id)
);
create table t_news
(
	news_id bigint AUTO_INCREMENT,
	title varchar(255) NOT NULL,
	content text NOT NULL,
	create_time datetime NOT NULL,
	newsCategory_news_category_id bigint NOT NULL,
	PRIMARY KEY(news_id),
	CONSTRAINT t_news_newsCategory_news_category_id_fk FOREIGN KEY(newsCategory_news_category_id) REFERENCES t_news_category(news_category_id)
);
create table t_news_reply
(
	news_reply_id bigint AUTO_INCREMENT,
	content text NOT NULL,
	news_news_id bigint,
	user_user_id bigint,
	PRIMARY KEY(news_reply_id),
	CONSTRAINT t_news_reply_news_news_id_fk FOREIGN KEY(news_news_id) REFERENCES t_news(news_id),
	CONSTRAINT t_news_reply_user_user_id_fk FOREIGN KEY(user_user_id) REFERENCES t_user(user_id)
);
create table t_chat
(
	chat_id bigint AUTO_INCREMENT,
	content text NOT NULL,
	create_time datetime NOT NULL,
	readed int NOT NULL,
	deleted int NOT NULL,
	fromUser_user_id bigint NOT NULL,
	toUser_user_id bigint NOT NULL,
	PRIMARY KEY(chat_id),
	CONSTRAINT t_chat_fromUser_user_id_fk FOREIGN KEY(fromUser_user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_chat_toUser_user_id_fk FOREIGN KEY(toUser_user_id) REFERENCES t_user(user_id)
);
create table t_notice
(
	notice_id bigint AUTO_INCREMENT,
	content varchar(255) NOT NULL,
	create_time datetime NOT NULL,
	actived int NOT NULL,
	user_user_id bigint NOT NULL,
	PRIMARY KEY(notice_id),
	CONSTRAINT t_notice_user_user_id_fk FOREIGN KEY(user_user_id) REFERENCES t_user(user_id)
);
create table t_privilege
(
	privilege_id bigint AUTO_INCREMENT,
	privilege varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY(privilege_id)
);
create table t_user_privilege
(
	user_id bigint,
	privilege_id bigint,
	PRIMARY KEY(user_id, privilege_id),
	CONSTRAINT t_user_privilege_user_user_id_fk FOREIGN KEY(user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_user_privilege_privilege_privilege_id_fk FOREIGN KEY(privilege_id) REFERENCES t_privilege(privilege_id)
);
create table t_topic
(
	topic_id bigint AUTO_INCREMENT,
	title varchar(255) NOT NULL,
	content text NOT NULL,
	create_time datetime NOT NULL,
	last_modified_time datetime NOT NULL,
	deleted int NOT NULL,
	user_user_id bigint NOT NULL,
	group_id bigint NOT NULL,
	PRIMARY KEY(topic_id),
	CONSTRAINT t_topic_user_user_id_fk FOREIGN KEY(user_user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_topic_group_group_id_fk FOREIGN KEY (group_id) REFERENCES t_group(group_id)
);
create table t_topic_reply
(
	topic_reply_id bigint AUTO_INCREMENT,
	floor int NOT NULL,
	content text NOT NULL,
	deleted int NOT NULL,
	user_user_id bigint NOT NULL,
	topic_topic_id bigint NOT NULL,
	parentTopicReply_topic_reply_id bigint,
	PRIMARY KEY(topic_reply_id),
	CONSTRAINT t_topic_reply_user_user_id_fk FOREIGN KEY(user_user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_topic_reply_topic_topic_id_fk FOREIGN KEY(topic_topic_id) REFERENCES t_topic(topic_id),
	CONSTRAINT t_topic_reply_parentTopicReply_topic_reply_id_fk FOREIGN KEY(parentTopicReply_topic_reply_id) REFERENCES t_topic_reply(topic_reply_id)
);
create table t_friends_validate
(
	id bigint AUTO_INCREMENT,
	from_user_id bigint,
	to_user_id bigint,
	PRIMARY KEY(id)
);
create table t_user_group
(
	user_id bigint,
	group_id bigint,
	PRIMARY KEY (user_id, group_id),
	CONSTRAINT t_user_group_user_user_id_fk FOREIGN KEY (user_id) REFERENCES t_user(user_id),
	CONSTRAINT t_user_group_group_group_id_fk FOREIGN KEY (group_id) REFERENCES t_group(group_id)
);
insert into t_privilege(privilege) values ('user');
insert into t_privilege(privilege) values ('administrator');
insert into t_news_category(category) values ('热点新闻');
insert into t_news_category(category) values ('热点话题');
insert into t_news_category(category) values ('热点人物');
