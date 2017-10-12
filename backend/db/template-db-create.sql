CREATE SCHEMA `templatewebapp` ;

use `templatewebapp`;

CREATE TABLE `contenttype` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `shortName` varchar(16) NOT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `templatewebapp`.`contenttype` (`id`, `shortName`, `description`) VALUES ('1', 'text', 'A Text-only post. Limited to 256 standard unicode characters.');
INSERT INTO `templatewebapp`.`contenttype` (`id`, `shortName`, `description`) VALUES ('2', 'link', 'A URL linking to some external site.');
INSERT INTO `templatewebapp`.`contenttype` (`id`, `shortName`, `description`) VALUES ('3', 'image', 'An image link. Must point directly to an image, hosted wherever. If this image fails to load, the post will be deleted.');

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `credential` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `templatewebapp`.`user` (`id`, `credential`) VALUES ('1', 'test.user.first@gmail.com');
INSERT INTO `templatewebapp`.`user` (`id`, `credential`) VALUES ('2', 'some.other.user@gmail.com');
INSERT INTO `templatewebapp`.`user` (`id`, `credential`) VALUES ('3', 'grandma.cookie@yahoo.com');

CREATE TABLE `post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `contentType_id` bigint(20) unsigned NOT NULL,
  `contentValue` varchar(256) DEFAULT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `dateCreated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `contentType_fk_idx` (`contentType_id`),
  KEY `user_fk_idx` (`user_id`),
  CONSTRAINT `post_contentType_fk` FOREIGN KEY (`contentType_id`) REFERENCES `contenttype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `post_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('1', '1', 'When the day had ended, the rainbow colors blended, their minds remained unbended', '2', '2017-02-27 13:53:31');
INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('2', '1', 'Hello grandson, how is school going?', '3', '2017-02-27 13:53:31');
INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('3', '1', 'Ability loss exaggerated extremely and nine declared errors received.', '1', '2017-02-27 13:53:31');
INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('4', '1', 'Join all yodeling', '1', '2017-02-27 13:53:31');
INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('5', '1', 'Vice and negligence don\'t excuse reclusiveness', '1', '2017-02-27 13:53:31');
INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('6', '1', 'Wasted output under duress excused', '1', '2017-02-27 13:53:31');
INSERT INTO `templatewebapp`.`post` (`id`, `contentType_id`, `contentValue`, `user_id`, `dateCreated`) VALUES ('7', '1', 'what did they mean by this???', '2', '2017-02-27 13:53:31');
