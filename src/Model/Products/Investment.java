package Model.Products;

import Model.People.Person;

/** product that represents compound interest formula */
public class Investment extends Account
{
    public Investment(){
        super();


    }
    protected double initialInvestment=0;
    protected double annualYieldPercentage=10;
    protected int yearsInvesting;
    protected double totalAssets=0;
    protected double deposit;
    protected int totalYears=0;


    @Override
    public void setUp(){

        depositMoney(deposit);
        addTotalAssets(deposit);        // peniaze, ktore sme vlozili na ucet nase vlastne
    }



    /** each year this balance goes up by the amount of 12 times the income to this account + the profit from APY */
    public void newYear(Person person){this.balance=(this.initialInvestment+12*this.monthlyDeposit+this.balance)
            *(1+this.annualYieldPercentage/100);
            addTotalAssets(12*this.monthlyDeposit);
            totalYears++;

    }


    /**
     * {@inheritDoc}
     * if the product can be deleted, delete it and return output message
     * @param acc this product
     * @param person person that owns this product
     * @return
     */
    public String printInfo(Account acc, Person person){
        String message = "";
        ProductOutput oStream = new ProductOutput();
        if(isDeletable(person)){
            ProductOutput.InvestmentOutput output = new ProductOutput.InvestmentOutput();
            message += output.withdrawal(this,person);
        }
        return message + oStream.printInfo(this,person);
    }

    /**
     * {@inheritDoc}
     * @param person person that owns this product
     * @return true if can be deleted, otherwise false
     */
    public boolean isDeletable(Person person) {

        if(totalYears==yearsInvesting){
            person.portfolio.get(0).depositMoney(this.balance);

            //nemozeme platit za nejaky produkt ked ho uz nemame (aj ked sa to v skutocnosti stava) a tak zmensime vydavky osoby o sumu
            //ktoru sme vkladali na tento ucet
            person.portfolio.get(0).decreaseExpenses(this.monthlyDeposit);
            this.deletable=true;
            return true;
        }
        return false;
    }



    /** sets the initial deposit */
    public void setDeposit(double amount){this.deposit=amount;}

    /** sets the monthly deposit to this account */
    public void setMonthlyDeposit(double amount){ this.monthlyDeposit=amount; }


    /** @return the total sum of money invested in this product */
    public double getTotalAssets(){return this.totalAssets;}

    /** sets the total timem this product will be active */
    public void setYearsInvesting(int yearsInvesting) {
        this.yearsInvesting = yearsInvesting;
    }

    /** adds money to total money invested in the product */
    protected void addTotalAssets(double ownResources){
        this.totalAssets+=ownResources;
    }

    /** sets the Yield of this product */
    public void setYield(double Yield){this.annualYieldPercentage=Yield;}

}
