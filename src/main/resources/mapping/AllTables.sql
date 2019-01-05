DROP TABLE IF EXISTS `myfile`;
CREATE TABLE `myfile` (
  `uploaderId` varchar(50) NOT NULL,
  `fileId` varchar(100) NOT NULL,
  `fileUrl` varchar(200) NOT NULL,
  `fileOriginName` varchar(200) NOT NULL,
  `fileName` varchar(200) NOT NULL,
  `fileFormats` varchar(10) NOT NULL,
  `fileSort` varchar(10) NOT NULL,
  `fileSize` bigint(20) NOT NULL,
  `uploadTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `downloadTimes` int(11) NOT NULL,
  `isShared` varchar(1) NOT NULL,
  PRIMARY KEY (`uploaderId`,`fileUrl`),
  KEY `uploadTime` (`uploadTime`),
  KEY `fileSort` (`fileSort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `point` int(11) NOT NULL DEFAULT '100',
  `authority` varchar(1) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
