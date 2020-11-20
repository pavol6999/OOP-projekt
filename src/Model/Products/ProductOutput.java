package Model.Products;

import Model.People.Person;
import Model.Products.*;
/** Class that handles the output string of printInfo in product  */
public class ProductOutput {
    /**
     * Outputs the total balance, if the expenses are greater than the income of the person, output warning message
     * @param acc the product that we want information from
     * @param person the owner of the product
     * @return message that holds current state of the product owned by the  person
     */
     public String printInfo(Account acc, Person person){
        String message = "";
        message = message + "(" + person.getName()+") Bilancia osobneho uctu: "+acc.getBalance()+'\n';
        if(acc.getExpenses()>acc.getIncome()){
            message = message + "POZOR! Mesacne vydavky osoby "+person.getName()+" prekrocili mesacne prijmy."+"\n";
        }
        return message;
    }

    /**
     * Outputs the total balance of the product + the amount of owners money that he invested into the investment/commodity
     * @param acc product
     * @param person owner of the product
     * @return message that holds current state of the product owned by the  person
     */
    public String printInfo(Investment acc, Person person)
    {
         String message = "";
         //commodity
         if(acc instanceof Commodity)
         {
             message = "("+person.getName()+") nasporene peniaze v "+acc.getProductName()+" "+((Commodity) acc).getCommodity_name()+": "+acc.getBalance()+" ("+acc.getTotalAssets()+" z vlastnych zdrojov)"+"\n";
         }
         //investment
         else
         {
             message = "("+person.getName()+") nasporene peniaze v "+acc.getProductName()+": "+acc.getBalance()+" ("+acc.getTotalAssets()+" z vlastnych zdrojov)"+"\n";
         }
         return message;
    }

    /**
     * Outputs the total money left to pay
     * @param acc product
     * @param person owner of the product
     * @return message that holds current state of the product owned by the  person
     */
    public String printInfo(Leasing acc, Person person){
        return "("+person.getName()+")"+" zostava splatit z Leasingu: "+Math.round(acc.getBalance())+"\n";
    }

    /**
     * Outputs the total money left to pay
     * @param acc product
     * @param person owner of the product
     * @return message that holds current state of the product owned by the  person
     */
    public String printInfo(Loan acc, Person person )
    {
         return "("+person.getName()+") zostava zaplatit "+acc.getProductName()+": "+(acc.getTotalLoan()-acc.getMoneyPaid())+"\n";
    }

    /**
     * Nested class with method that handles the withdrawal message
     */
    public static class InvestmentOutput {
        /**
         * After the Investment product/Commodity product has reached theirs final year, the product will be deleted
         * with the return message of how much money got deposited into their bank accounts
         * @param acc product
         * @param person owner of the product
         * @return message that describes how much money got deposited into the owners bank account
         */
        public String withdrawal(Investment acc, Person person) {
            return "(" + person.getName() + ") Withdrawing " + Math.round(acc.getBalance()) + " Euros to the bank account of " + person.getName()+"\n";
        }
    }

    /**
     * Nested class with method that handles the deletion of the product
     */
    public static class ProductOperation{
        /**
         *
         * @param acc product
         * @param person owner of the product
         * @return String that describes whose product got paid completely or if the product got paid off in advance
         */
        public String deleted(Loan acc, Person person)
        {
            String message = "";
            message = "Pozicka pre "+person.getName()+" je splatena" + "\n";
            if(acc.getDeletable())
                message  = message + "\nPozicka bola pre "+person.getName()+" splatena predcasne\n";

            return message;
        }

         /**
         *
         * @param person owner of the product
         * @return message that says whose car was paid off
         */
         public String deleted(Person person)
         {
            return "("+person.getName()+") Auto bolo splatene"+"\n";
         }

        /**
         *
         * @param person owner of the product
         * @return message that says who added new Leasing to their repository
         */
         public String productAdded(Person person)
         {
             return "Novy leasing pre "+person.getName()+"\n";
         }
    }
}
