import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Contains a method to read in the ziplocs file and Stores zipcode, town name,
 * state name, latitude, and longitude into Location 
 * Extends PlaceInfo.java
 * Parent class for Population.java
 */
public class Location extends PlaceInfo {

    protected String[] locationData;
    protected String longitude;
    protected String latitude;

    /**
     * Constructor for Location.java Initializing zipcode, town name, state name,
     * latitude, and longitude
     * 
     * @param zipCode   the zip code from PlaceInfo
     * @param townName  the town name from PlaceInfo
     * @param stateName the state name from PlaceInfo
     * @param lat       the latitude associated with the zipcode
     * @param longt     the longitude associated with the zipcode
     */
    public Location(String zipCode, String townName, String stateName, String lat, String longt) {
        super(zipCode, townName, stateName);
        longitude = longt;
        latitude = lat;
    }

    /**
     * Sets the longitude
     * 
     * @param longitude the longitude from constructor
     */
    public void setLong(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets the latitude
     * 
     * @param latitude the latitude from constructor
     */
    public void setLat(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude
     * 
     * @return longitude the longitude from setter method
     */
    public String getLong() {
        return longitude;
    }

    /**
     * Gets the latitude
     * 
     * @return latitude the latitude from setter method
     */
    public String getLat() {
        return latitude;
    }

    /**
     * Method to read in the ziplocs file 
     * Calls matchedZip method with parameters zipcode, lattude, and longitude if the 
     * line in the file contains latitude and longitude
     * Calls matchedZip method with parameters zipcode, null, and null if the 
     * line in the file does not contain latitude and longitude
     * 
     * @param file the zip code file
     * @throws FileNotFoundException if the file does not exist or cannot be opened
     * @throws IOException if the file cannot be read
     */
    public void readFile2(String file) throws FileNotFoundException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
            while ((line != null)) {
                locationData = line.split(",");
                if ((locationData[5] != null) && (locationData[6] != null)) {
                    matchZip(locationData[0].replace("\"", ""), locationData[5], locationData[6]);
                } else {
                    matchZip(locationData[0].replace("\"", ""), null, null);
                }
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error in opening the file: " + file);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
            System.exit(1);
        }
    }

    /**
     * Method to match the zipcodes and associated information from ziplocs with 
     * the zipcodes and associated information from uszipcodes
     * Puts arraylist into a population object if the current index of zipList is 
     * an instance of Population
     * If not, puts arraylist into a Location object
     * 
     * @param z
     * @param longt
     * @param lat
     */
    public void matchZip(String z, String longt, String lat) {
        for (int i = 0; i < zipList.size(); i++) {
            if (zipList.get(i).getZipCode().equals(z)) {
                if (zipList.get(i) instanceof Population) {
                    Population pop = (Population) zipList.get(i);
                    pop.setLat(lat);
                    pop.setLong(longt);

                } else {
                    Location location = new Location(z, zipList.get(i).getTown(), zipList.get(i).getState(), lat,
                            longt);
                    zipList.set(i, location);
                }
            }
        }
    }

}
