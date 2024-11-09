use bankingapp;
DELIMITER %
CREATE PROCEDURE transfer_funds(IN from_account INT, IN to_account INT, IN amount DECIMAL(15,2))
BEGIN
    DECLARE from_acc_balance DECIMAL(15,2);
    
    SELECT balance INTO from_acc_balance 
    FROM account 
    WHERE account_id = from_account;

    IF from_acc_balance >= amount THEN
       
        UPDATE account
        SET balance = balance - amount
        WHERE account_id = from_account;

       
        UPDATE account
        SET balance = balance + amount
        WHERE account_id = to_account;
        
         INSERT INTO transaction(account_id, transaction_type, amount)
        VALUES (from_account, 'debit', amount);

        INSERT INTO transaction(account_id, transaction_type, amount)
        VALUES (to_account, 'credit', amount);
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the source account';
    END IF;
END %

DELIMITER ;
DELIMITER %
CREATE PROCEDURE deposit_funds(IN account_id INT, IN amount DECIMAL(15,2))
BEGIN
    DECLARE account_exists INT DEFAULT 0;


    SELECT COUNT(*) INTO account_exists
    FROM account
    WHERE account_id = account_id;

    IF account_exists = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Account does not exist';
    ELSE
      
        UPDATE account
        SET balance = balance + amount
        WHERE account_id = account_id;
        
        INSERT INTO Transaction(account_id, transaction_type, amount)
        VALUES (account_id, 'credit', amount);
    END IF;
END %
DELIMITER ;
DELIMITER %
CREATE PROCEDURE withdraw_funds(IN account_id INT, IN amount DECIMAL(15,2))
BEGIN
    DECLARE account_exists INT DEFAULT 0;
    DECLARE acc_balance DECIMAL(15,2);

  
    SELECT COUNT(*) INTO account_exists
    FROM account
    WHERE account_id = account_id;

    IF account_exists = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Account does not exist';
    ELSE
     
        SELECT balance INTO acc_balance
        FROM account
        WHERE account_id = account_id;

        IF acc_balance >= amount THEN
       
            UPDATE account
            SET balance = balance - amount
            WHERE account_id = account_id;withdraw_transactions
            
            
            INSERT INTO Transaction(account_id, transaction_type, amount)
            VALUES (account_id, 'debit', amount);
        ELSE
          
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the account';
        END IF;
    END IF;
END %
DELIMITER ;





use bankingapp;
select * from withdraw_transactions;
select * from Account;
DELETE FROM Account where account_id=2;
desc Account;

insert into Account(customer_id,bank_id,account_type,balance)values(1,1,'savings',1000),(2,2,'current',2000);





