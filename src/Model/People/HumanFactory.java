package Model.People;

import Model.People.Adult;
import Model.People.Old;
import Model.People.Person;
import Model.People.Youth;

public class HumanFactory {

    /**
     * factory design pattern that creates classes based on their age
     */
    public static Person createBeing(String name, int age){


        if(age >= 15 && age <= 26){
            return new Youth(name,age);
        }
        if(age>26 && age<62){
            return new Adult(name,age);
        }
        if(62 <= age){
            return new Old(name,age);
        }

        return null;

    }
}
