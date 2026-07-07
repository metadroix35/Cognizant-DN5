SET SERVEROUTPUT ON;

---------------------------------------------------------
-- Scenario 1 : SafeTransferFunds
---------------------------------------------------------

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_fromAccount NUMBER,
    p_toAccount NUMBER,
    p_amount NUMBER
)
AS
    v_balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_fromAccount;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001,'Insufficient Funds');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_fromAccount;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_toAccount;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully.');

EXCEPTION

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer Failed : ' || SQLERRM);

END;
/

---------------------------------------------------------
-- Scenario 2 : UpdateSalary
---------------------------------------------------------

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_empId NUMBER,
    p_percentage NUMBER
)
AS
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary*p_percentage/100)
    WHERE EmployeeID = p_empId;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE NO_DATA_FOUND;
    END IF;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary Updated Successfully.');

EXCEPTION

    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Employee ID does not exist.');

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error : ' || SQLERRM);

END;
/

---------------------------------------------------------
-- Scenario 3 : AddNewCustomer
---------------------------------------------------------

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id NUMBER,
    p_name VARCHAR2,
    p_age NUMBER,
    p_balance NUMBER
)
AS
BEGIN

    INSERT INTO CustomerDetails
    VALUES(p_id,p_name,p_age,p_balance);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer Added Successfully.');

EXCEPTION

    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Customer ID already exists.');

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error : ' || SQLERRM);

END;
/

---------------------------------------------------------
-- Testing
---------------------------------------------------------

EXEC SafeTransferFunds(101,102,5000);

EXEC UpdateSalary(1,10);

EXEC AddNewCustomer(2,'Bob',45,9000);

SELECT * FROM Accounts;

SELECT * FROM Employees;

SELECT * FROM CustomerDetails;