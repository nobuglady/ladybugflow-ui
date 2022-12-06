
CREATE TABLE `tuser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(400) DEFAULT NULL,
  `email` varchar(400) DEFAULT NULL,
  `passwd` varchar(100) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  `admin_flag` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

CREATE TABLE `trole` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(400) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(400) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `permission_flow` (
  `permission_id` int(11) NOT NULL,
  `flow_id` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`permission_id`,`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `user_category` (
  `user_id` int(11) NOT NULL,
  `category_id` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `category` (
  `id` varchar(100) NOT NULL,
  `category_name` varchar(400) NOT NULL,
  `category_type` varchar(100) NOT NULL,
  `parent` varchar(100) NOT NULL,
  `disabled` varchar(1000) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

insert into tuser(user_name,email,passwd,remarks,admin_flag,create_time,update_time) values ('admin','admin','{bcrypt}$2a$10$OI5XecOy3r/ne1CFQpaHU.T3hVqZQL6sffWvUfBis4QbboDtsQxr6','',1,now(),now())

insert into category(id,category_name,category_type,parent,disabled,create_time,update_time) values ('1','root','default','#','false',now(),now());
insert into category(id,category_name,category_type,parent,disabled,create_time,update_time) values ('17b1fbef5b43b0','demo','default','1','false',now(),now());
insert into category(id,category_name,category_type,parent,disabled,create_time,update_time) values ('17b1fbf2cb013a','demo','file','17b1fbef5b43b0','false',now(),now());
