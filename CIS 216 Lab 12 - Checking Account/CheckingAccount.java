package lab.pkg12;

public class CheckingAccount {
    
    private double balance;
    private String transactionDate;
    private String transactionDescription;
    private double transactionAmount;
    
    public void SetBalance(double userBalance) {
        
        balance = userBalance;
        
    }
    
    public void SetTransactionDate(String userTransactionDate) {
        
        transactionDate = userTransactionDate;
        
    }
    
    public void SetTransactionDescription(String userTransactionDescription) {
        
        transactionDescription = userTransactionDescription;
        
    }
    
    public void SetTransactionAmount(double userTransactionAmount) {
        
        transactionAmount = userTransactionAmount;
        
    }
    
    public void AddAmount(double amountToAdd) {
        
        transactionAmount = transactionAmount + amountToAdd;
        
    }
    
    public double GetBalance( ) {
        
        return balance;
        
    }
               
    public String GetTransactionDate( ) {
        
        return transactionDate;
        
    }
    
    public String GetTransactionDescription( ) {
        
        return transactionDescription;
        
    }
    
    public double GetTransactionAmount( ) {
        
        return transactionAmount;
        
    }

}
