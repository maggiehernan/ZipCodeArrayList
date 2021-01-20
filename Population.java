/**
 * Contains a toString method to print out variables
 * Extends Location.java
 */
public class Population extends Location {

    private String population;

    /**
     * Constructor for Population.java Initializing zipcode, town name, state name,
     * latitude, longitude, and Population
     * 
     * @param zipCode the zipcode from Location
     * @param townName the town from Location
     * @param stateName the stat from Location
     * @param latitude the latitude from Location
     * @param longitude the longitude from Location
     * @param pop the population associated with zipcode
     */
    public Population(String zipCode, String townName, String stateName, String latitude, String longitude, String pop) {
        super(zipCode, townName, stateName, latitude, longitude);
        this.population = pop;
    }

    /**
     * toString method to use when printing our the information associated with the user input
     * 
     * @return town name, state name, zipcode, population, latitude, and longitude
     */
    public String toString() {
        return "    " + townName + "," + stateName + " " + zipCode + "       Pop:"+ population + " Lat:" + latitude + 
        " Lon:" + longitude;
    }


}
