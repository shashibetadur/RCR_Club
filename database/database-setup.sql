/*
	Execute these commands as root user
*/

// Create database
CREATE DATABASE IF NOT EXISTS rcr_raichur  

//Create user
CREATE USER 'rcr'@'localhost' IDENTIFIED BY 'rcr';

//Grant privileges to user
GRANT ALL PRIVILEGES ON rcr_raichur.* TO 'rcr'@'localhost';