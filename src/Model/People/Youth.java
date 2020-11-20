package Model.People;

/**
 * class that represents students/young people
 */
public class Youth extends Person{

    public Youth(String nameInput, int age) {
        super(nameInput,age);
        this.schoolYear=this.age-15;

    }
    private int schoolYear;
    private double dormitoryPrice;
    private double foodPrice;

    //v nextYear musim vypocitat aj v ktorom rocniku je, aby som mu potom vedel setnut jeho vydavky
    //schoolYear>12 == (4 roky stredna skola, 8 rokov vysoka skola )

    /** in nextYear the program has to determine in which year in shcool this person is, in order to determine their expenses
     * if the schoolYear is greater than 12, this person changes expenses
     * @return message that holds all the info of this person's products
     */
    public String nextYear(){

        super.nextYear();
        this.schoolYear++;
        if(this.schoolYear>12){
            this.portfolio.get(0).setExpenses(this.portfolio.get(0).getIncome()*0.7);
        }
       return message;
    }


    /**
     * if this youth earns up to 400 euros, he does not have to pay taxes. if he earns more than 400 eures, then the taxes
     * are calculated and deducted from the salary
     * @param incomeBrutto full income of this person
     */
    protected void calculateIncomeNetto(double incomeBrutto){
       if(incomeBrutto <= 400){
        this.portfolio.get(0).setMonthlyDeposit(incomeBrutto);
       }
       if(incomeBrutto > 400){
           this.portfolio.get(0).setMonthlyDeposit(400+(incomeBrutto-400)*0.8);
       }
    }

    /**
     * sets up this class, first it calculates the Netto Income for this person and based on their education calculates
     * theirs expenses
     */
    public void setUp(){

        calculateIncomeNetto(income);

        if(hasEducation){
            //ked je clovek na strednej skole, nemusi si platit za jedlo ani za internat, kedze byva doma
            // predpokladam ze clovek ktory chodi na vysoku skolu (schoolYear>4) navstevuje internat a plati si za jedlo
            if(schoolYear>4) {
                this.portfolio.get(0).setExpenses(this.portfolio.get(0).getIncome() / 2 + this.dormitoryPrice + this.foodPrice);
            }
        }
        //ak nestuduje, tak sa mu rovno vypocitaju obycajne vydavky
        else{
            this.portfolio.get(0).setExpenses(this.portfolio.get(0).getIncome()*0.7);
        }
    }
    /** sets the price of this's dormitory if education is true */
    public void setDormitoryPrice(double price){this.dormitoryPrice=price;}

    /** sets the price of this's food on dormitory if education is true */
    public void setFoodPrice(double price){this.foodPrice=price;}



}
