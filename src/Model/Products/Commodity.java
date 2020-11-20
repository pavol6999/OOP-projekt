package Model.Products;

import Model.People.Person;

/**
 * Type of investment where the profit is not guaranteed
 */
public class Commodity extends Investment {
    private String commodity_name;
    private double min_range=-30;
    private double max_range=+35;
    public Commodity(){
        super();
    }


    /**
     * {@inheritDoc}
     * each year calculate new Yield percent from the range
     * @param person owner of the product
     */
    public void newYear(Person person){
        super.newYear(person);
        this.annualYieldPercentage=random_price_range();
    }

    /** sets the name of this product */
    public void setCommodity_name(String name){this.commodity_name=name;}

    /** @return the name of this product */
    public String getCommodity_name(){return this.commodity_name;}

    /** calculates the new yield for each year */
    private double random_price_range(){
        return (Math.random() * ((this.max_range + this.min_range) + 1)) + this.min_range;
    }

}
