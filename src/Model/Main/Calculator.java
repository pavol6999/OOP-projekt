package Model.Main;


import Model.People.Person;
import Model.Products.ProductOutput;

import java.util.ArrayList;

/** main class that initializes clients and calculates simulated year for every client and their products*/
public class Calculator {
    /** initialization of the clients */
    public void initializeCustomers(ArrayList<Person> clients){

        for(Person person: clients){
            person.setUp();
        }
    }

    /** simulation of one year
     *
     * @param clients database of clients got from mainController
     * @return message that holds all the information for one year worth of simulation
     */
    public String simulateYear(ArrayList<Person> clients){
        String info = "";
        for(Person person: clients) {
            info += person.nextYear();
        }
        return info;
    }

}
