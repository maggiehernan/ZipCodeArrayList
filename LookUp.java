import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A user-interactive program that reads in a file
 * searches for zipcode to match user input
 * gets town name state name, population, 
 * latitude, and longitude associated with the zipcode
 * 
 * @author mhernan
 * Created: Sep 28 2020
 */
public class LookUp {

    /**
	 * Run to begin program
     * Population objects to read in files and store the information that 
     * mathes the zipcode from the user input
	 * 
	 * @param args ignored
     * @throws FileNotFoundException if index out of range
	 */
    public static void main(String[] args) throws FileNotFoundException {
        Population place = new Population(null, null, null, null, null, null);
        String input = "";
        String zips = "/home/gtowell/Public/206/a3/zipLocs.csv";
        String usZips = "/home/gtowell/Public/206/a3/uszipcodes.zip";
        Scanner scnr = new Scanner(System.in);

        place.readFile(usZips);
        place.readFile2(zips);

        while (input != "00000") {
            System.out.print("Enter a zipcode to lookup (00000 to quit): ");
            input = scnr.nextLine();

            Population placeData = new Population(null, null, null, null, null, null);
            placeData = (Population) place.searchForInput(input); 
            if (input.equals("00000")) {
                System.out.println("Goodbye");
                break;
            }
            if (placeData == null) {
                System.out.println("No such zipcode");
            }
            else {
                System.out.println(placeData.toString());
            }
        }
        scnr.close();
    }
}

