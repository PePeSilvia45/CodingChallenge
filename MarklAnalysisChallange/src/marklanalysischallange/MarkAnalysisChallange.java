package marklanalysischallange;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author James Park
 *
 * This program is for entering students marks for further analysis
 *
 */
public class MarkAnalysisChallange {

    //Global ArrayLost for storing marks
    static ArrayList<String> marks = new ArrayList<String>();
    //Scanner for use in any method
    static Scanner userIn = new Scanner(System.in);

    //Main body
    public static void main(String[] args) throws InterruptedException {
        //Create variables
        boolean userActive = true;

        //Starts the program loop
        while (userActive) {
            //Calls the menu method and uses the users choice to decide what to run next
            String choice = menu();
            switch (choice)//Takes users choice and runs the appropriate code
            {
                //Runs the enterMarks method used to populate the marks ArrayList
                case "1" ->
                    enterMarks();
                //Runs the showSectionStatistics method to break down the correct rates
                case "2" ->
                    showSectionStatistics();
                case "3" -> 
                    saveSectionStatistics();
                //Shows the pass/fail rates of the class
                case "4" ->
                    showClassBreakdown();
                //Loads the marks into memory
                case "5" ->
                    loadMarks();
                //Saves the marks currently stored
                case "6" ->
                    saveMarks();
                //Quits the program
                case "7" ->
                    clearMarks();
                //Quits the program
                case "8" ->
                    quit();
                //If an item from the menu was not chosen ask the user to choose again
                default -> {
                    System.err.println("Please select an item from the menu");
                    Thread.sleep(250);
                }
            }
        }
    }   //Main Ends

    /*----Menu Methods----*/
    //Print a menu for the user to choose from
    public static String menu() {
        //Create variables 
        String choice;
        String menu = """
                           ---------------------------------
                             Welcome to CDF marks analysis 
                           ---------------------------------                   
                             1) Enter marks                
                             2) Show section statistics  
                             3) Save section statistics
                             4) Show class breakdown       
                             5) Load marks
                             6) Save marks
                             7) Clear marks
                             8) Quit                       
                           ---------------------------------""";
        System.out.println(menu);//Print the menu variable
        System.out.print(">>");
        choice = userIn.next();//Store users input into the choice var
        return choice;  //Return the users choice to main
    }   //menu() method ends here
    //Let the user enter marks and add them to the ArrayList
    public static void enterMarks() throws InterruptedException {
        //Create variables
        boolean exit = false;// Used to exit loop

        //Ask the user to enter the marks
        System.out.print("Please enter marks\n");
        while (!exit) {
            System.out.print(">");
            String inMark = userIn.next();
            String wantsToSave;

            if ("#".equals(inMark)) {
                //Ask the user if they wish to save the marks they have entered 
                System.out.print("Do you want to save the marks entered <y or n>? >>");
                wantsToSave = userIn.next();
                if ("y".equalsIgnoreCase(wantsToSave)) {
                    //If they say yes call saveMarks() method 
                    saveMarks();
                    exit = true;
                } else if ("n".equalsIgnoreCase(wantsToSave)) {
                    System.out.println("Exiting without saving...");
                    exit = true;

                }
            } else if (inMark.length() != 3) {
                //Make sure the user has entered a mark for each section
                System.err.println("Please enter a mark for each section.");
            } else {
                //Make sure that the numbers entered are all between 0 and 5
                if (((Character.getNumericValue(inMark.charAt(0)) <= 5)
                        && (Character.getNumericValue(inMark.charAt(0)) >= 0))
                        && ((Character.getNumericValue(inMark.charAt(1)) <= 5)
                        && (Character.getNumericValue(inMark.charAt(1)) >= 0))
                        && ((Character.getNumericValue(inMark.charAt(2)) <= 5)
                        && (Character.getNumericValue(inMark.charAt(2)) >= 0))) {
                                            // Add the valid mark to the ArrayList
                    marks.add(inMark);
                    System.out.println("Mark entered.");
                } else {
                    //Ask the user to enter a mark between 0-5
                    System.err.println("Please Enter a mark out of 5.");
                }
            }
        }
    }   //enterMarks() method ends here
    //This will display how many students got each possible mark in each section
    public static void showSectionStatistics() {
        //Create arrays to split the each set of marks into each section
        int[] section1 = {};
        int[] section2 = {};
        int[] section3 = {};

        //This section uses for loops to to count the frequency of results in each section
        for (int i = 0; i < 3; i++) {
            int zero = 0;
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;
            int five = 0;

            for (int j = 0; j < marks.size(); j++) {
                switch (marks.get(j).charAt(i)) {
                    case '0' ->
                        zero++;
                    case '1' ->
                        one++;
                    case '2' ->
                        two++;
                    case '3' ->
                        three++;
                    case '4' ->
                        four++;
                    case '5' ->
                        five++;
                }
            }
            switch (i)//Use switch statement to populate each section array with the correct results
            {
                case 0 ->
                    section1 = new int[]{zero, one, two, three, four, five};
                case 1 ->
                    section2 = new int[]{zero, one, two, three, four, five};
                case 2 ->
                    section3 = new int[]{zero, one, two, three, four, five};
            }
        }
        //Print the percentage of which each result shows up in Section 1
        System.out.println("\nSection 1:");
        for (int i = 0; i < section1.length; i++) {
            double percentage = (Double.valueOf(section1[i])
                    / Double.valueOf(marks.size()))
                    * Double.valueOf(100);
            System.out.println("Number correct " + i + ": " + (int) percentage + "%");
        }

        //Print the percentage of which each result shows up in Section 2
        System.out.println("\nSection 2:");
        for (int i = 0; i < section2.length; i++) {
            double percentage = (Double.valueOf(section2[i])
                    / Double.valueOf(marks.size()))
                    * Double.valueOf(100);
            System.out.println("Number correct " + i + ": " + (int) percentage + "%");
        }

        //Print the percentage of which each result shows up in Section 3
        System.out.println("\nSection 3:");
        for (int i = 0; i < section3.length; i++) {
            double percentage = (Double.valueOf(section3[i])
                    / Double.valueOf(marks.size()))
                    * Double.valueOf(100);
            System.out.println("Number correct " + i + ": " + (int) percentage + "%");
        }
    }   //showSectionStatistics() method ends
    //This method will save the statistics to a txt doc
    public static void saveSectionStatistics() {
        
        
        
    } //saveSectionStatistics() method ends    
    //This method will show a breakdown of the pass rates 
    public static void showClassBreakdown() {
        //Create variables to store the persentages of pass rates in a class
        int fullMarks = 0;
        int borderLine = 0;
        int fullPaper = 0;
        int failSec1 = 0;
        int failSec2 = 0;
        int failSec3 = 0;

        /*Use for loop to loop through the results to Find how many got full
          marks and how many landed on the pass line*/
        for (int i = 0; i < marks.size(); i++) {
            if ("555".equals(marks.get(i))) {
                fullMarks++;
            } else if ("333".equals(marks.get(i))) {
                borderLine++;
            }
        }

        //This loop checks if the value in each section is 3 or more for each
        for (int i = 0; i < marks.size(); i++) {
            if ((3 <= Character.getNumericValue(marks.get(i).charAt(0)))
                    && (3 <= Character.getNumericValue(marks.get(i).charAt(1)))
                    && (3 <= Character.getNumericValue(marks.get(i).charAt(2)))) {
                fullPaper++;
            }
        }

        //This loop checks to see how many times each section was failed
        for (int i = 0; i < marks.size(); i++) {
            for (int j = 0; j < marks.get(i).length(); j++) {
                switch (i) {
                    case 0 -> {
                        if (Character.getNumericValue(marks.get(i).charAt(j)) < 3) {
                            failSec1++;
                        }
                    }
                    case 1 -> {
                        if (Character.getNumericValue(marks.get(i).charAt(j)) < 3) {
                            failSec2++;
                        }
                    }
                    case 2 -> {
                        if (Character.getNumericValue(marks.get(i).charAt(j)) < 3) {
                            failSec3++;
                        }
                    }
                }
            }
        }
        //Prints each value to the console
        System.out.println("Full Marks 555: " + (int) ((Double.valueOf(fullMarks)
                / Double.valueOf(marks.size()))
                * Double.valueOf(100)) + "%");

        System.out.println("Borderline 333: " + (int) ((Double.valueOf(borderLine)
                / Double.valueOf(marks.size()))
                * Double.valueOf(100)) + "%");

        System.out.println("Full paper pass all sections >=3: "
                + (int) ((Double.valueOf(fullPaper)
                / Double.valueOf(marks.size()))
                * Double.valueOf(100)) + "%");

        System.out.println("failed Section 1 less than 3: "
                + (int) ((Double.valueOf(failSec1)
                / Double.valueOf(marks.size()))
                * Double.valueOf(100)) + "%");

        System.out.println("failed Section 2 less than 3: "
                + (int) ((Double.valueOf(failSec2)
                / Double.valueOf(marks.size()))
                * Double.valueOf(100)) + "%");

        System.out.println("failed Section 3 less than 3: "
                + (int) ((Double.valueOf(failSec3)
                / Double.valueOf(marks.size()))
                * Double.valueOf(100)) + "%");
    }//showClassBreakdown() method ends
    //This method loads the marks from a text file into array
    public static void loadMarks() throws InterruptedException {
        //Ask the user for the file name of the file they would like to open
        System.out.print("Please enter the file you would like to load \n>>");
        String fileName = userIn.next();
        String currentFileInMemory = "";
        //Create a substring to check for file extension 
        String txtCheck = fileName.substring(fileName.length() - 4);
        int lines = 0;
        if (!".txt".equals(txtCheck)) {
            //Add file extension if needed
            fileName += ".txt";
        }
        try {
            if (!fileName.equals(currentFileInMemory)) {
                //clear current marks in memory 
                clearMarks();
                //Get the file and add each line to the marks array
                File marksFile = new File(fileName);
                //Open file reader
                System.out.println(fileName + " Opened.");
                Scanner fileReader = new Scanner(marksFile);
                while (fileReader.hasNextLine()) {
                    marks.add(fileReader.nextLine());
                    lines++;
                }
                Thread.sleep(250);
                System.out.print("Loading");
                load();
                System.out.println(lines + " Student marks imported to memory.");
                fileReader.close();
                System.out.println(fileName + " closed.");
                //Set the name of the current file in memory
                currentFileInMemory = fileName;
            } else {
                System.out.println("File already in memory");
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error occured.\nFile not Found");
        }
    }//loadMarks() method ends
    //This method saves the marks currently stored in the marks arraylist 
    public static void saveMarks() throws InterruptedException {
        
        try {
            String fileName = createFile();
            //Create file with file name provided
            File marksFile = new File(fileName);

            //Check if the file allready exists and if not save the new file
            if (marksFile.createNewFile()) {
                try ( //Create the file writer
                        FileWriter fileWrite = new FileWriter(fileName)) {
                    for (int i = 0; i < marks.size(); i++) {
                        //Save each mark to a new line
                        fileWrite.write(marks.get(i) + "\n");
                    }
                    //Close file writer
                }
                //Tell the user that the file has saved (delay for effect)
                System.out.println("---------------------------------\n");
                System.out.println(marks.size() + " Student marks in memory.");
                Thread.sleep(250);
                System.out.print("Saving");
                load();
                System.out.println("Marks successfully saved to '" + fileName + "'.");
                Thread.sleep(250);
                System.out.println(marks.size() + " lines saved to file.\n");
                Thread.sleep(500);
            } else {
                //If the file exists tell the user
                System.out.println("File name already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error ocurred.");
        }
    }//saveMarks() method ends 
    //This will clear the marks from memory
    public static void clearMarks() {
        //Note the array size
        int size = marks.size();
        int removed = 0;

        for (int i = 0; i < size; i++) {
            //Remove each item from the array
            marks.remove(0);
            removed++;
        }
        System.out.println(removed + " marks removed from memory.");

    }//clearMarks() method ends
    //This will exit the program
    public static void quit() {
        //Prints a final message then ends the program
        System.out.println("Thanks for using CSF marks analysis\nGoodbye.");
        System.exit(0);
    }//quit() method ends
    
    /*----Extra Methods----*/
    //This method just prints three dots
    public static void load() throws InterruptedException{
       Thread.sleep(250);
        System.out.print(".");
        Thread.sleep(250);
        System.out.print(".");
        Thread.sleep(250);
        System.out.print(".");
        Thread.sleep(250);
   }
    //This method creates files
    public static String createFile(){
        //Create var for holding the file name
        String fileName;
        //Get the file name the user would like to use
        System.out.print("Please enter a file name >");
        fileName = userIn.next();
        //Get the last 4 characters of the string to check for the file extension
        String txtCheck = fileName.substring(fileName.length() - 4);

        if (!".txt".equals(txtCheck)) {
            //If the file name entered doesnt have a .txt file extension add one
            fileName += ".txt";
        }
        return fileName;
   }
}