package model;

public class SavingsAccount extends Account {

	private double interestRate;

	public SavingsAccount(int accountId, int customerId, Bank bankId, String accountType, double balance,double interestRate) {
		super(accountId, customerId, bankId, accountType, balance);//call the super class constructor i.e the Account class so that all the values are initialized in super class
	    this.setInterestRate(interestRate);
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String getAccountDetails() {
		// TODO Auto-generated method stub
		return "Saving class with interest rate"+this.interestRate;
	}
	public String toString() {//return object AS the string
		return "SavingsAccount Detail:Account id"+this.getAccountId()+"Customer Id"+this.getCustomerId()+"bank"+this.getBank()+"accountType"+this.getAccountType()+"balance"+this.getBalance()+"interestRate"+this.getInterestRate();
	}

	

}
