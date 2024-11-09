package model;

public class CurrentAccount extends Account {
	private int overdraft_Limit;
public CurrentAccount(int accountId, int customerId, Bank bankId, String accountType, double balance,double overdraft) {
		super(accountId, customerId, bankId, accountType, balance);
		this.setOverdraft_Limit(overdraft_Limit);
	}
public int getOverdraft_Limit() {
	return overdraft_Limit;
}
public void setOverdraft_Limit(int overdraft_Limit) {
	this.overdraft_Limit = overdraft_Limit;
}
@Override
public String toString() {//return object AS the string
	return "CurrentAccount  Detail:Account id"+this.getAccountId()+"Customer Id"+this.getCustomerId()+"bank"+this.getBank()+"accountType"+this.getAccountType()+"balance"+this.getBalance()+"overdraftLimit"+this.overdraft_Limit;
}
@Override
public String getAccountDetails() {
	return "CurrentAccount class with overDraft_date"+this.overdraft_Limit;
}



}
