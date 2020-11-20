package Model.People;

/**
 * class that represents old person
 */
public class Old extends Person{
    private double pension;
    private int yearsWorked;

    public Old(String nameInput,int age) {
        super(nameInput,age);
        this.yearsWorked=age-18;


    }

    /** the pension is calculated based on years worked for the country. every year that this person has studied is minus
     * one year worked for this person
     * @param incomeBrutto zero
     */
    protected void calculateIncomeNetto(double incomeBrutto){
        if(hasEducation){
            this.yearsWorked-=8;
            this.pension=calculateMinimalPension()*1.37+40*numChildren;
        }
        else{
            this.pension= calculateMinimalPension() + 40*numChildren;
        }
        this.portfolio.get(0).setMonthlyDeposit(this.pension);
    }


    /**
     * sets up the expenses for this person and calculates the pension. for every child that this person has
     * add plus 20 euros to their expenses, it represents gifts for this children
     */
    public void setUp() {

        calculateIncomeNetto(0);
        //vydavky maju zatial 20 eur na kazde dieta (vianocoe 10 Eur, velka noc 10 EUR) + polovica ich prijmov
        this.portfolio.get(0).setExpenses(20*numChildren+this.portfolio.get(0).getIncome()/2);

    }

    /** calculates the total pension based on how much years this person has worked. the formula is derived from Socialna
     * poistovna
     * @return pension
     */
    private double calculateMinimalPension(){

        if(yearsWorked<40){
            return 334.30 + 210.20*(0.02*(yearsWorked-30));
            }
        else{
            return 334.30 + 210.20*0.18 + 210.20*((yearsWorked-39)*0.03);
        }

    }

    /**
     * sets the number of children this person has
     */
    public void setNumChildren(int numChildren){this.numChildren=numChildren;}

}
