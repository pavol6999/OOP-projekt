package Model.People;
import Model.Products.ProductFactory;
import Model.Products.*;

import java.util.ArrayList;
import java.util.Iterator;

/** main abstract class of a person */
public abstract class Person{
    protected String name;
    protected int age;
    protected String message = "";
    protected boolean hasEducation;
    protected int numChildren;
    protected double income;

    //agregacia, kazdy clovek ma pri vytvoreni svoje vlastne portfolio, kde sa mu budu ukladat jeho produkty z package PRODUCTS
    public ArrayList<Account> portfolio= new ArrayList<Account>();

    /**
     * when the human factory returns a new person class, the name and age is set to that person + adds portfolio
     * with their account
     * @param nameInput name of the person
     * @param age age of the person
     */
    public Person(String nameInput,int age){
        this.age=age;
        this.name=nameInput;

        // kazdy ma na zaciatku portfolia nejaky ucet, ktory funguje ako jeho penazenka, je to rozdelenie inicializovaneho objektu a jeho financii
        this.portfolio.add(new Account());
        this.portfolio.get(0).productName= this.portfolio.get(0).getClass().getSimpleName() + "\\" + this.name;
        //System.out.println("SetUp required for: " + this.name + "\n");

    }

    /**
     * If the product is not null, add the set up the product and add it to this person's portfolio, set the expenses
     * on their main account
     * @param product product to be added
     */
    public void addProduct(Account product){
       {

            if(product!=null) {
                product.setUp();
                this.portfolio.add(product);
                this.portfolio.get(0).addExpenses(product.getMonthlyDeposit());
            }
            else{
                System.out.println("Vam vybrany produkt nie je k dispozicii");
            }


        }
    }

    //kazdy rok v main->Calculator zavolame tuto funkciu pre kazdu osobu, v nej prejdem cele portfolio osoby cez iterator
    //updatnem bilanciu vsetkych produktov, zistim ci je jeden z nich vymazatelny (splateny Leasing, Dosporili sme v investment) a
    //ak je tak ho vymazem.
    //iterator som musel pouzit vtedy, lebo keby som z ArraList vymazal objekt a dalej iteroval, vyhodilo by mi to error

    /**
     * every year add one year to this person and iterate through every product this person has, if there is one that can
     * be deleted, delete it through iterator.
     * @return message that holds all the info of this person's products
     */
    public String nextYear(){
        this.age++;
        message="";
        Iterator<Account> itr = portfolio.iterator();
        while(itr.hasNext()){
            Account acc=itr.next();
            acc.newYear(this);
            message += acc.printInfo(acc,this);


            //mensi pokus o RTTI, aby mi to zbytocne nemuselo checkovat isDeletable objektu typu Account, kedze ten sa neda vymazat
            if(acc instanceof Investment||acc instanceof Leasing || acc instanceof  Loan){
                if(  acc.isDeletable(this))
                    itr.remove();
            }
        }
        return message;
    }


    /**
     * abstract method that is overriden in classes that extends this class
     * @param amount amount to be calculated
     */
    protected abstract void calculateIncomeNetto(double amount);

    /**
     * abstract method that is overriden in classes that extends this class
     */
    public abstract void setUp();

    /**
     *
     * @return this person name
     */
    public String getName(){return this.name;}

    /** set's this person's income */
    public void setIncome(double income){this.income=income;}
    /** sets this person's education */
    public void setEducation(boolean education){this.hasEducation=education; }
    /** @return true if this person has education, false otherwise */
    public boolean getEducation(){return this.hasEducation;}

}
