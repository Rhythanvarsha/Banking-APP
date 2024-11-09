package dao;

import java.sql.SQLException;

import exception.BankingException;
import model.Account;
import model.Bank;

public interface BankDAO {

Bank getBankId(int bankId) throws SQLException, BankingException;
}
