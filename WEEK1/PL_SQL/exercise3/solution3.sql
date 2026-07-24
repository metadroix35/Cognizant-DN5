SET SERVEROUTPUT ON;

-----------------------------------------------------
-- Scenario 1 : ProcessMonthlyInterest
-----------------------------------------------------

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN

    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Monthly interest processed successfully.');

END;
/

-----------------------------------------------------
-- Scenario 2 : UpdateEmployeeBonus
-----------------------------------------------------

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(

    p_department VARCHAR2,
    p_bonus NUMBER

)
AS
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus / 100)
    WHERE Department = p_department;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Employee bonus updated successfully.');

END;
/

-----------------------------------------------------
-- Scenario 3 : TransferFunds
-----------------------------------------------------

CREATE OR REPLACE PROCEDURE TransferFunds(

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
        RAISE_APPLICATION_ERROR(-20001,'Insufficient Balance');
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
        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END;
/

-----------------------------------------------------
-- Testing
-----------------------------------------------------

EXEC ProcessMonthlyInterest;

EXEC UpdateEmployeeBonus('IT',10);

EXEC TransferFunds(101,102,3000);

-----------------------------------------------------
-- Display Results
-----------------------------------------------------

SELECT * FROM Accounts;

SELECT * FROM Employees;

