package Model.People;

public class Adult extends Person{
    public Adult(String nameInput, int age) {
        super(nameInput,age);

    }

    /**

     * Calculate the Netto earnings from Brutto multiplied by a constant, that roughly represents the ratio of netto to brutto
     * in Slovakia.
     * @param incomeBrutto total earnings of this person
     */
    protected void calculateIncomeNetto(double incomeBrutto){
        this.portfolio.get(0).setMonthlyDeposit(incomeBrutto*0.732);
    }


    /**
     * for each child up to three an adult in slovakia gets 829.86 euros deposited to their bank account
     * for every child from then on, the person will get only 152 euros deposited into their bank account
     */
    private void moneyAtBirth(){
        if (numChildren>0 && numChildren<=3){
            this.portfolio.get(0).depositMoney(numChildren*829.86);
        }
        else if (numChildren > 3){
            this.portfolio.get(0).depositMoney(3*829.86+ (numChildren-3)*151.37);
        }
    }

    /**
     *
     *sets up this person. calculates how much money will he get for children and sets the expenses based on their education
     */
    public void setUp() {
        calculateIncomeNetto(income);
        moneyAtBirth();
        //zratam si pridavky na deti
        this.portfolio.get(0).addIncome(this.numChildren*24.95);

        //podla europskych prieskumov clovek ktory, ma vysoku skolu zaraba v priemere od 30 percent viacej ako clovek s maturitou
        //chcel som toto brat do uvahy ale nemohol som dospelemu cloveku len tak zvysit prijem, tak som mu znizil vydavky o 20 percent
        if(this.hasEducation){
            this.portfolio.get(0).setExpenses(this.portfolio.get(0).getIncome()*0.5);
        }
        else{
        this.portfolio.get(0).setExpenses(this.portfolio.get(0).getIncome()*0.7);}

    }

    /**
     * sets the number of children this person has
     */
    public void setNumChildren(int numChildren){this.numChildren=numChildren;}


}
