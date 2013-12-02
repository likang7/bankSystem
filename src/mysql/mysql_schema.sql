create database if not exists bank;
use bank;

drop table if exists Account;
drop table if exists Card;
drop table if exists Department;
drop table if exists Employee;
drop table if exists Enterprise;
drop table if exists EnterpriseAccount;
drop table if exists EnterpriseUser;
drop table if exists IndividualUser;
drop table if exists `Log`;
drop table if exists `Sequence`;
drop table if exists VIPAccount;
drop table if exists VIPUser;

create table if not exists Account (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	`type` VARCHAR(20) NOT NULL,
	balance DECIMAL NOT NULL,
	openDate TIMESTAMP
)DEFAULT CHARSET=latin1;

create table if not exists Card (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	accountId VARCHAR(20),
	userId VARCHAR(50)
)DEFAULT CHARSET=latin1;

create table if not exists Department (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	name VARCHAR(20) default ''
)DEFAULT CHARSET=latin1;

create table if not exists Employee (
	username VARCHAR(50) NOT NULL PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	position VARCHAR(20),
	departmentId VARCHAR(20) DEFAULT NULL,
	superiorId VARCHAR(50) DEFAULT NULL
)DEFAULT CHARSET=latin1;

create table if not exists Enterprise (
	id VARCHAR(50) NOT NULL PRIMARY KEY,
	enterpriseName VARCHAR(30) DEFAULT NULL,
	balanceLimit DECIMAL DEFAULT 10000.0
)DEFAULT CHARSET=latin1;

create table if not exists EnterpriseAccount (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	`type` VARCHAR(20) NOT NULL,
	balance DECIMAL NOT NULL,
	openDate TIMESTAMP,
	enterpriseId VARCHAR(20) DEFAULT NULL
)DEFAULT CHARSET=latin1;

create table if not exists EnterpriseUser (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	name VARCHAR(20) DEFAULT NULL,
	enterpriseId VARCHAR(20) DEFAULT NULL,
	isSuper BOOLEAN DEFAULT FALSE
)DEFAULT CHARSET=latin1;

create table if not exists IndividualUser (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	name VARCHAR(20) DEFAULT NULL
)DEFAULT CHARSET=latin1;

create table if not exists `Log` (
	`time` TIMESTAMP NOT NULL,
	`operation` VARCHAR(20) DEFAULT NULL,
	`operator` VARCHAR(20) DEFAULT NULL,
	cardId VARCHAR(20) DEFAULT NULL,
	accountId VARCHAR(20) DEFAULT NULL,
	accountType VARCHAR(20) DEFAULT NULL,
	income DECIMAL DEFAULT 0,
	expenditure DECIMAL DEFAULT 0,
	balance DECIMAL DEFAULT 0
)DEFAULT CHARSET=latin1;

create table if not exists `Sequence` (
	nextid int NOT NULL DEFAULT 1000000,
	name VARCHAR(20) NOT NULL PRIMARY KEY
)DEFAULT CHARSET=latin1;

create table if not exists VIPACCount(
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	`type` VARCHAR(20) NOT NULL,
	balance DECIMAL NOT NULL,
	openDate TIMESTAMP,
	excessStart TIMESTAMP,
	isFrozen BOOLEAN DEFAULT FALSE,
	accBalanceThisMonth DECIMAL DEFAULT 0,
	accFailMonths SMALLINT DEFAULT 0
)DEFAULT CHARSET=latin1;

create table if not exists VIPUser(
	id VARCHAR(20) NOT NULL PRIMARY KEY,
	name VARCHAR(20) DEFAULT NULL	
)DEFAULT CHARSET=latin1;