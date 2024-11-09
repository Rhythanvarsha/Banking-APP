package model;

public class Bank {//pascal case
 private int bank_id;//encapsulation-giving protectio without complete hiding of data;//camel case
 private String bankName;
 private String bankBranch;

 public Bank(int bank_id, String bankName, String bankBranch) {
	//super();
	this.bank_id = bank_id;
	this.bankName = bankName;
	this.bankBranch = bankBranch;
}
//GETTERS and SETTERS
 public int getBankId() {
	 return bank_id;
 }
 public void setBankId(int id) {
	 bank_id=id;
 }
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
public String getBankBranch() {
	return bankBranch;
}
public void setBankBranch(String bankBranch) {
	this.bankBranch = bankBranch;
}
public int getBank_id() {
	// TODO Auto-generated method stub
	return 0;
}
 
 
}
