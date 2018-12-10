CREATE TABLE `myfile` (
  `uploaderId` varchar(10) NOT NULL,
  `fileId` varchar(100) NOT NULL,
  `fileUrl` varchar(100) NOT NULL,
  `fileName` varchar(100) NOT NULL,
  `fileFormats` varchar(10) NOT NULL,
  `fileSort` varchar(10) NOT NULL,
  `fileSize` bigint(20) NOT NULL,
  `uploadTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `downloadTimes` int(11) NOT NULL,
  PRIMARY KEY (`uploaderId`,`fileUrl`),
  KEY `uploadTime` (`uploadTime`),
  KEY `fileSort` (`fileSort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(10) NOT NULL,
  `userName` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `authority` varchar(1) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
