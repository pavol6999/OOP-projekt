package Model.Products;

import Model.People.Person;

/**
 * Class represents Loan with simple Interest
 */
public class Loan extends Account {
    private double interest;
    private double totalLoan;

    private int totalYears;
    private double borrowedSum;
    private double moneyPaid=0;
    private boolean deletable=false;
    private boolean productAdded=false;
    public Loan(){
        super();
    }

    // po kazdom roku sa vieme stretnut s dvoma scenarmi aby sme ju mohli vymazat

    //1. bud je pozicka splatena cela takymi istymi splatkami kazdy mesiac

    /**
     * If the sum of total money Paid exceeds or equals to money borrowed, set this class as deletable
     * @param person person that owns this product
     * @return true, if the product can be deleted. Otherwise false
     */
    public boolean isDeletable(Person person){
        if(this.moneyPaid>=totalLoan || deletable){
            this.deletable=true;
            person.portfolio.get(0).decreaseExpenses(this.monthlyDeposit);

            return true;
        }
        return false;
    }
    //2. osobna bilancia cloveka bola vacsia ako potrebne peniaze na splatenie pozicky, tak ju splatil predcasne

    /**
     * If the owner of this product has deposit large enough to pay the whole Loan at once, then this product will be
     * paid and deletable set to true
     * @param person person that owns this product
     */
    private void payInAdvance(Person person){
        if(!deletable && person.portfolio.get(0).getBalance()>=(this.totalLoan-this.moneyPaid)){
            this.deletable=true;
        }
    }

    /**
     * Every year the total money paid equals to previous's year + 12 times the monthly deposit
     * @param person owner of this product
     */
    public void newYear(Person person){
        if(!productAdded){
            this.productAdded=true;
            person.portfolio.get(0).addExpenses(this.monthlyDeposit);
        }
        payInAdvance(person);
        this.moneyPaid+=12*this.monthlyDeposit;

    }

    /**
     * Calculate the total Loan and monthly deposit after adding interest rates
     */
    public void setUp(){
        calculateLoan();
        setMonthlyDeposit();
    }

    /**
     * With simple interest the total Loan does not rise
     */
    private void calculateLoan(){
        this.totalLoan=this.borrowedSum*(this.interest/100)*this.totalYears + this.borrowedSum;
    }

    /**
     * Calculate the monthly deposit to this product
     */
    private void setMonthlyDeposit(){
        this.monthlyDeposit=totalLoan/totalYears/12;
    }


    /**
     * Return string with current status of this product, how much is money is paid and if this product is deletable,
     * adds special message
     * @param acc this product
     * @param person person that owns this product
     * @return message that holds the current state of this product
     */
    public String printInfo(Account acc,Person person){
        String out_string="";
        ProductOutput output = new ProductOutput();
        out_string = output.printInfo(this,person);
        if(isDeletable(person)){
            ProductOutput.ProductOperation out = new ProductOutput.ProductOperation();
            out_string += out.deleted(this,person);
        }
        return out_string;
    }

  /** @return if this product can be deleted*/
  public boolean getDeletable(){return this.deletable;}

  /** Sets the borrowed sum */
  public void setBorrowedSum(double amount){this.borrowedSum=amount;}

  /** Sets the Interest */
  public void setInterest(double interest){this.interest=interest;}

  /** Sets the total time in years of this Loan */
  public void setTotalYears(int years){this.totalYears=years;}

  /** @return the total loan */
  public double getTotalLoan(){return this.totalLoan;}

  /** @return the sum of money paid */
  public double getMoneyPaid(){return this.moneyPaid;}
}
