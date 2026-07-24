Exercise 2 - Error Handling

Objective
---------
Learn exception handling in PL/SQL using predefined and user-defined exceptions while maintaining database consistency.

Scenarios Implemented
---------------------
1. SafeTransferFunds
   Transfers money between accounts and rolls back the transaction if insufficient funds or any other error occurs.

2. UpdateSalary
   Updates an employee's salary by a specified percentage and handles invalid employee IDs.

3. AddNewCustomer
   Inserts a new customer record and prevents duplicate customer IDs using exception handling.

PL/SQL Concepts Used
--------------------
- CREATE PROCEDURE
- EXCEPTION block
- NO_DATA_FOUND
- DUP_VAL_ON_INDEX
- RAISE_APPLICATION_ERROR
- SQL%ROWCOUNT
- COMMIT
- ROLLBACK
- DBMS_OUTPUT.PUT_LINE

Learning Outcomes
-----------------
- Learned to create stored procedures.
- Understood predefined exceptions.
- Used user-defined exceptions with RAISE_APPLICATION_ERROR.
- Maintained transaction integrity using COMMIT and ROLLBACK.
- Displayed meaningful error messages using DBMS_OUTPUT.