package model;

import java.util.Date;

public class WithdrawTransaction extends Transaction{
private String withdrawalMethod;
	public String getWithdrawalMethod() {
	return withdrawalMethod;
}

	public WithdrawTransaction(int transactionId, int accountId, String transactionType, double amount,
			Date trasactionDate,String withdrawalMethod) {
		super(transactionId, accountId, transactionType, amount, trasactionDate);
	this.withdrawalMethod=withdrawalMethod;	
	}
	public void setWithdrawalMethod(String withdrawalMethod) {
		this.withdrawalMethod = withdrawalMethod;
	}
	@Override//polymorphism->object,variable,method takes many forms;override->change the implementation in differnr=t forms
	public String getTransactionDetails() {
		// TODO Auto-generated method stub
		return "Transaction type is+"+this.withdrawalMethod;
	}

}
//abstract
/*in this may abstarct or non abstract method can implemented a nd abstract metod should implemented*/