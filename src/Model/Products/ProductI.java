package Model.Products;

import Model.People.Person;

/**
 * In the future, there may be added more products to this program. In order to handle them properly, each
 * product must include this interface.
 */
public interface ProductI {
    /**
     * Method to get necessary information about the product such as the APR, monthly deposit, period of time
     * the product will be active. It must be called in the constructor for every class except Account class.
     */
    default void setUp(){};

    /**
     * The calculator is set to iterate through each year from the start to the end year that we provided,
     * and calculate how the product has changed over the year (if the product has reached it's usefulness)
     * @param person person that owns this product
     */
    void newYear(Person person);

    /**
     * Default function that prints the information about the product, should be called each year.
     * @param acc this product
     * @param person person that owns this product
     * @return String to be printed
     */
    default String printInfo(Account acc,Person person)
    {
        ProductOutput oStream = new ProductOutput();
        return oStream.printInfo(acc,person);
    };

    /** Checks if the product can be deleted
     * @param person person that owns this product
     */
    default boolean isDeletable(Person person){return false;};
}