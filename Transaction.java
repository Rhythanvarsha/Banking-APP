package model;

import java.util.Date;

public abstract class Transaction {//initiailize it as abstract class because it need to implement two more classes so here no 
private int transactionId; //i.e deposit transacton and witthdraw transaction inherit this class//
//when ever there exit many subclasses we use abstract to implement aal the methods of the base class
private int accountId;
private String transactionType;
private double amount;
private Date trasactionDate;
abstract String getTransactionDetails();//if the method is abstract it should be implemented by sub class
public Transaction(int transactionId, int accountId, String transactionType, double amount, Date trasactionDate) {
	super();
	this.transactionId = transactionId;
	this.accountId = accountId;
	this.transactionType = transactionType;
	this.amount = amount;
	this.trasactionDate = trasactionDate;
}
public int getTransactionId() {
	return transactionId;
}
public void setTransactionId(int transactionId) {
	this.transactionId = transactionId;
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getTrasactionDate() {
	return trasactionDate;
}
public void setTrasactionDate(Date trasactionDate) {
	this.trasactionDate = trasactionDate;
}
}
