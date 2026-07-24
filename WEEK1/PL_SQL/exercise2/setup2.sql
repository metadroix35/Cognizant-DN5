CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100),
    Balance NUMBER(10,2)
);

INSERT INTO Accounts VALUES (101,'John',15000);
INSERT INTO Accounts VALUES (102,'Alice',8000);

----------------------------------------------------

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    EmployeeName VARCHAR2(100),
    Salary NUMBER(10,2)
);

INSERT INTO Employees VALUES (1,'David',50000);
INSERT INTO Employees VALUES (2,'Emma',60000);

----------------------------------------------------

CREATE TABLE CustomerDetails (
    CustomerID NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100),
    Age NUMBER,
    Balance NUMBER(10,2)
);

INSERT INTO CustomerDetails VALUES (1,'John',65,12000);

COMMIT;