----------------------------------------------------
-- Accounts Table
----------------------------------------------------

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100),
    AccountType VARCHAR2(20),
    Balance NUMBER(10,2)
);

INSERT INTO Accounts VALUES (101,'John','Savings',15000);
INSERT INTO Accounts VALUES (102,'Alice','Savings',10000);
INSERT INTO Accounts VALUES (103,'Bob','Current',12000);

----------------------------------------------------
-- Employees Table
----------------------------------------------------

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    EmployeeName VARCHAR2(100),
    Department VARCHAR2(50),
    Salary NUMBER(10,2)
);

INSERT INTO Employees VALUES (1,'David','IT',50000);
INSERT INTO Employees VALUES (2,'Emma','IT',60000);
INSERT INTO Employees VALUES (3,'Sophia','HR',45000);

COMMIT;

ALTER TABLE Accounts
ADD AccountType VARCHAR2(20);

ALTER TABLE Employees
ADD Department VARCHAR2(50);

UPDATE Accounts
SET AccountType = 'Savings'
WHERE AccountID IN (101,102);

UPDATE Employees
SET Department = 'IT'
WHERE EmployeeID = 1;

UPDATE Employees
SET Department = 'HR'
WHERE EmployeeID = 2;

COMMIT;