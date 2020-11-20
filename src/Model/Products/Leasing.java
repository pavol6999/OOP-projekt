package Model.Products;

import Model.People.Person;

/**
 * Type of Loan, when a person wants to buy a car and he doesnt have enough money
 */
public class Leasing extends Account {
    public Leasing(){

        super();

    }
    private boolean added=false;
    private double carPrice;
    private double initialDepositPercent;
    private double initialDeposit;
    private boolean purchaseAvailable;
    private int timePeriod;
    private double coefficientIncrease;
    private boolean productAdded=false;
    private double tempExpenses;


    /**
     * Sets Up the leasing.
     * Calculates the initial deposit, total money borrowed in leasing and the monthly payment
     */
    public void setUp(){
        this.initialDeposit=this.carPrice*(this.initialDepositPercent/100);
        this.balance=this.carPrice*this.coefficientIncrease - this.initialDeposit;
        this.expenses=this.balance/(12*timePeriod);
    }



    /**
     * leasing works differently than a simple loan, because we need certain amount of money in order to pay off the
     * initial deposit. With this method, the leasing will be added to owner's portfolio of products but the owner will
     * start to pay for leasing once he pays off the initial deposit.
     * @param person owner of the leasing
     * @return true if the product was added, false otherwise
     */
    private boolean checkAvailability(Person person){
        if(!purchaseAvailable && person.portfolio.get(0).getBalance()>=this.initialDeposit){
            this.purchaseAvailable=true;
            person.portfolio.get(0).transferMoney(initialDeposit);
            this.productAdded=true;
            return true;
        }
        return false;
    }

    //na zaciatku sa vypocita kolko treba zaplatit za leasing, kazdy rok bude tato suma zmensovana o mesacnu splatku*12
    //ak bude splatena, deletable bude pravda a vypise sa ze auto bolo splatene

    /**
     * this product's balance represents the total money borrowed in the form of leasing, each year the owner pays of
     * a part of the leasing thus decreasing this product's balance. If this balance reaches zero, then reduce te owners
     * expenses that represent owners monthly payment for this product and delete this product.
     * @param person person that owns this product
     * @return true if this product can be deleted, false otherwise
     */
    public boolean isDeletable(Person person){
        if(this.balance<1)
        {
            this.balance=0;
            this.deletable=true;
            person.portfolio.get(0).setExpenses(this.tempExpenses);
            return true;
        }
        return false;
    }



    /**
     * each year check if the owner can start paying for this product
     * if the product is added, each year we deduct 12 times the monthly payment to this product from the total money
     * reserved for the leasing
     * @param person owner of this product
     */
    public void newYear(Person person){
        if(!productAdded && checkAvailability(person)){
            this.tempExpenses=person.portfolio.get(0).getExpenses();
            person.portfolio.get(0).addExpenses(this.expenses);
        }

        this.balance=(this.balance-12*this.expenses);

    }

    /**
     * Return string with current status of this product, how much money is left to pay and if this product is deletable,
     * adds special message
     * @param acc this product
     * @param person person that owns this product
     * @return message that holds the current state of this product
     */
    public String printInfo(Account acc,Person person){
        ProductOutput.ProductOperation oStream = new ProductOutput.ProductOperation();
        String string = "";

        if(this.productAdded) {
            if(!this.added){
                string += oStream.productAdded(person);
                this.added=true;
            }
            ProductOutput o = new ProductOutput();
            string +=  o.printInfo(this,person);
        }
        if(isDeletable(person)){
            string += oStream.deleted(person);
        }
        return string;
    }

    /** sets the price of the car with Tax */
    public void setCarPrice(double carPrice){this.carPrice=carPrice;}

    /** sets the initial deposit in percent */
    public void setInitialDepositPercent(double percent){this.initialDepositPercent=percent;}

    /** sets the coefficient increase */
    public void setCoefficientIncrease(double coefficient){this.coefficientIncrease=coefficient;}

    /** sets the time in years that this product will be valid */
    public void setTimePeriod(int timePeriod){this.timePeriod=timePeriod;}
}
