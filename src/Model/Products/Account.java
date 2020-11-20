package Model.Products;
import Model.People.Old;
import Model.People.Person;

/**
 * The most basic product. Product that represents our money/bank account in real life
 */
public class Account implements ProductI {

    /**
     * Sets the name of this product automatically
     */
    public Account()
    {
        this.productName=getClass().getSimpleName();
    }
    protected double balance=0;
    public String productName;
    protected double income=0;
    protected double expenses=0;
    protected double monthlyDeposit;
    protected boolean deletable=false;

    /**
     * Adds to this balance the sum of income deducted by the sum of expenses during the period of one year
     * @param person owner of this product
     */
    public void newYear(Person person){this.balance+=(12*this.income -12*expenses);}


    /** Deposits money to this product's balance */
    public void depositMoney(double amountDeposited){this.balance+=amountDeposited;}


    /** Transfers money out of this product's balance */
    public void transferMoney(double amountTransfered){this.balance-=amountTransfered;}


    /** Sets the expenses of this account */
    public void setExpenses(double expenses){this.expenses=expenses;}


    /**
     * Deducts expenses from the sum of all expenses bound to this account
     * @param amount expenses to be decreased
     */
    public void decreaseExpenses(double amount){this.expenses-=amount;}

    /**
     * Adds another income on top of current one ( product was fully paid )
     * @param addedIncome income to be added
     */
    public void addIncome(double addedIncome){this.income+=addedIncome;}

    /**
     * Add expenses to this account
     * @param addedExpenses expenses to be added
     */
    public void addExpenses(double addedExpenses){this.expenses+=addedExpenses;}


    /** @return Expenses of this product */
    public double getExpenses(){return this.expenses;}


    /** @return Total balance of this account*/
    public double getBalance(){return Math.round(this.balance);}


    /** @return Name of the product */
    public String getProductName(){return this.getClass().getSimpleName();}


    /** @return Monthly payment to this product*/
    public double getMonthlyDeposit(){return this.monthlyDeposit;}


    /** Sets the income as a monthly deposit to this account */
    public void setMonthlyDeposit(double income){this.income=income;}


    /** @return Income of the person */
    public double getIncome(){return this.income;}
}
