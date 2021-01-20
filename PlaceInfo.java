import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
* Contains a method to read in the uszipcodes file and 
* Stores zipcode, town name, and state name into PlaceInfo
* Parent class for Location.java
*/
public class PlaceInfo {

    protected ArrayList<PlaceInfo> zipList = new ArrayList<PlaceInfo>();
    protected String[] zipData;
    protected String townName;
    protected String stateName;
    protected String zipCode;
    protected Population storePopulation;

    /**
     * Constructor for PlaceInfo.java
     * Initializing zipcode, town name, and state name
     * 
     * @param zip the zip code from file
     * @param town town name from file associated with zipcode
     * @param state state name from file associated with zipcode
     */
    public PlaceInfo(String zip, String town, String state) {
        this.zipCode = zip;
        this.townName = town;
        this.stateName = state;
    }

    /**
     * Gets zipcode from arraylist
     * 
     * @return zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets town name from arraylist
     * 
     * @return townName
     */
    public String getTown() {
        return townName;
    }

    /**
     * Gets state name from arraylist
     * 
     * @return stateName
     */
    public String getState (){
        return stateName;
    }

    /**
     * Method to read in the uszipcodes file
     * Creates a zipList arraylist from Population objects if the currents index of the 
     * zipData array contains all of the parameters (zipcode, town, state, latitude,
     * longitude, and population)
     * Creates a zipList arraylist from PlaceInfo objects if there is no latitude, longitude,
     * or population
     * 
     * @param file the zip code file
     * @throws FileNotFoundException if the file does not exist or
     * cannot be opened
     * @throws IOException if the file cannot be read
     */
    public void readFile(String file) throws FileNotFoundException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
            while ((line != null)) {
                zipData = line.split(",");
                if (zipData.length > 3 && zipData[3] != null) {
                    storePopulation = new Population(zipData[0], zipData[1], zipData[2], null, null, zipData[3]);
                    zipList.add(storePopulation);
                }
                else {
                    PlaceInfo storeNames = new PlaceInfo(zipData[0], zipData[1], zipData[2]);
                    zipList.add(storeNames);
                
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
     * Method to search through zipList for the matching zipcode in the file to the user input
     * Stores the matching information in zipList to a Population object (place)
     * Had to cast zipList to Population
     * 
     * @param userInput
     * @return place if the user input matches a zipcode 
     * @return null if the user input does not match any zipcodes
     */
    public Population searchForInput(String userInput) {
        for (int i = 0; i < zipList.size(); i++) {
            if (zipList.get(i).getZipCode().equals(userInput)) {
                Population place = (Population) zipList.get(i);
                return place;
            }
        }
        return null;
    }
}
         
    
