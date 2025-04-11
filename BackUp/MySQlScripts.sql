
-- CREATE TABLE FOR APPOINTMENTS
CREATE TABLE `appointments` (
  `appointment_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`)
);
-- CREATE TABLE  FOR CLIENT FOLLOW-UP
CREATE TABLE `client_followup` (
  `follow_up_id` int NOT NULL AUTO_INCREMENT,
  `owner_id` varchar(100) NOT NULL,
  `follow_up_type` varchar(100) NOT NULL,
  `follow_up_date` date NOT NULL,
  `remarks` text,
  PRIMARY KEY (`follow_up_id`)
);
-- CREATE TABLE FOR COMPANY
CREATE TABLE `company` (
  `companyId` int NOT NULL AUTO_INCREMENT,
  `companyCode` varchar(5) NOT NULL,
  `companyName` varchar(50) NOT NULL,
  `companyAddress` varchar(50) NOT NULL,
  `companyContactNo` varchar(50) NOT NULL,
  `companyRegisteredDate` varchar(50) NOT NULL,
  `companyLogoPath` varchar(255) NOT NULL,
  PRIMARY KEY (`companyId`)
);
-- CREATE TABLE FOR OWNERS
CREATE TABLE `owners` (
  `owner_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `contact_no` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `pet_nickname` varchar(100) NOT NULL,
  `pet_breed` varchar(100) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  PRIMARY KEY (`owner_id`)
);
-- CREATE TABLE FOR STAFF
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `Staff_Name` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Contact_No` varchar(10) DEFAULT NULL,
  `Staff_Type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
);
-- CREATE TABLE FOR USERS
CREATE TABLE `users` (
  `User_Id` int DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `UserType` varchar(50) DEFAULT NULL,
  `MainId` int DEFAULT NULL
);
-- CREATE TABLE VET STAFF ASSIGNEMENT
CREATE TABLE `vet_staff_assignment` (
  `vet_Assign_id` int NOT NULL AUTO_INCREMENT,
  `ownerId` varchar(100) NOT NULL,
  `staffid` varchar(50) DEFAULT NULL,
  `additional_remarks` varchar(500) DEFAULT NULL,
  `Assigned_Date` date DEFAULT NULL,
  PRIMARY KEY (`vet_Assign_id`)
);

-- INSERT ADMIN USER (MANDATORY Run)
INSERT INTO USERS (User_Id, Username, Password, UserType, MainId)
VALUES ('1','admin', 'admin@123','Admin', NULL)


