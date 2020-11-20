package Model.Products;
public class ProductFactory {
    /**
     * Factory design pattern to return Classes that corresponds to the Input String
     * @param productName Name of the class to be created
     * @return new Class that corresponds to the input String, if no Class with the String productName exists, returns
     * null
     */
    public static Account newProduct(String productName){
        if(productName == null)
            return null;
        if(productName.equalsIgnoreCase("Investment"))
            return new Investment();
        if(productName.equalsIgnoreCase("Commodity"))
            return new Commodity();
        if(productName.equalsIgnoreCase("Leasing"))
            return new Leasing();
        if(productName.equalsIgnoreCase("Loan"))
            return new Loan();


        return null;
    }
}
