package lab.pkg07;

public class Parts {
    
    private int partIDNumber;
    private String partDescription;
    private double partCost;
    private double partPrice;
    private double partWeight;
    private String partLocation;
    private int partQuantity;
    
    public void SetPartIDNumber(int somePartIDNumber) {
        
        partIDNumber = somePartIDNumber;
    
    }

    public void SetPartDescription(String somePartDescription) {
        
       partDescription = somePartDescription;
    
    }

    public void SetPartCost(double somePartCost) {
        
       partCost = somePartCost;
    
    }
    
    public void SetPartPrice(double somePartPrice) {
        
        partPrice = somePartPrice;
    
    }

    public void SetPartWeight(double somePartWeight) {
        
        partWeight = somePartWeight;
    
    }

    public void SetPartLocation(String somePartLocation) {
        
        partLocation = somePartLocation;
    
    }

    public void SetPartQuantity(int somePartQuantity) {
        
        partQuantity = somePartQuantity;
    
    }
    
    public int GetPartIDNumber( ) {
        
        return partIDNumber;
        
    }
    
    public String GetPartDescription( ) {
        
        return partDescription;
                
    }
    
    public double GetPartCost( ) {
        
        return partCost;
        
    }
    
    public double GetPartPrice( ) {
        
        return partPrice;
        
    }
    
    public double GetPartWeight( ) {
        
        return partWeight;
                
    }
    
    public String GetPartLocation( ) {
        
        return partLocation;
        
    }
    
    public int GetPartQuantity( ) {
        
        return partQuantity;
                
    }

}
