package model;

import java.util.Date;

public class DepositTransaction extends Transaction{
private String depositMethod;
	public DepositTransaction(int transactionId, int accountId, String transactionType, double amount,
			Date trasactionDate,String depositMethod) {
		super(transactionId, accountId, transactionType, amount, trasactionDate);
		this.depositMethod=depositMethod;
	}
	public String getDepositMethod() {
		return depositMethod;
	}
	public void setDepositMethod(String depositMethod) {
		this.depositMethod = depositMethod;
	}
	public String getTransactionDetails() {
		// TODO Auto-generated method stub
		return "Transaction type is+"+this.depositMethod;
	}
}
