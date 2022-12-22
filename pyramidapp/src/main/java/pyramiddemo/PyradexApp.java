package pyramiddemo;

import java.util.Scanner;

import org.json.simple.*;

public class PyradexApp
{

  
  protected Pharaoh[] pharaohArray;
  protected Pyramid[] pyramidArray;

    public static void main( String[] args )
    {
      // create and start the app
    App app = new App();
    app.start();
    }

    // main loop for app
  public void start() {
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // loop until user quits
    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    }
  }

  // constructor
  public PyradexApp() {
    
    // read egyptian array of pharaohs
    String pharaohFile =
      "C:/Users/louie/OneDrive/Documents/GitHub/Pyradex/pyramidapp/src/main/java/pyramiddemo/pharaoh.json";
    JSONArray pharaohJSONArray = JSONFile.readArray(pharaohFile);

    // create and intialize the array of pharaohs
    initializePharaoh(pharaohJSONArray);

    // read array of pyramids
    String pyramidFile =
      "C:/Users/louie/OneDrive/Documents/GitHub/Pyradex/pyramidapp/src/main/java/pyramiddemo/pyramid.json";
    JSONArray pyramidJSONArray = JSONFile.readArray(pyramidFile);

    // create and initialize the array of pyramids
    initializePyramid(pyramidJSONArray);

  }

  // initialize the pharaoh array
  private void initializePharaoh(JSONArray pharaohJSONArray) {
    // create array and hash map
    pharaohArray = new Pharaoh[pharaohJSONArray.size()];

    // initalize the array
    for (int i = 0; i < pharaohJSONArray.size(); i++) {
      // get the object
      JSONObject o = (JSONObject) pharaohJSONArray.get(i);

      // parse the json
      Integer id = toInteger(o, "id");
      String name = o.get("name").toString();
      Integer begin = toInteger(o, "begin");
      Integer end = toInteger(o, "end");
      Integer contribution = toInteger(o, "contribution");
      String hieroglyphic = o.get("hieroglyphic").toString();

      // add a new pharoah to array
      Pharaoh p = new Pharaoh(id, name, begin, end, contribution, hieroglyphic);
      pharaohArray[i] = p;
    }
  }

  // initialize the pyramid array
  private void initializePyramid(JSONArray pyramidJSONArray) {
    // create array and hash map
    pyramidArray = new Pyramid[pyramidJSONArray.size()];

    // initalize the array
    for (int i = 0; i < pyramidJSONArray.size(); i++) {
      // get the object
      JSONObject o = (JSONObject) pyramidJSONArray.get(i);

      // parse the json object
      Integer id = toInteger(o, "id");
      String name = o.get("name").toString();
      JSONArray contributorsJSONArray = (JSONArray) o.get("contributors");
      String[] contributors = new String[contributorsJSONArray.size()];
      for (int j = 0; j < contributorsJSONArray.size(); j++) {
        String c = contributorsJSONArray.get(j).toString();
        contributors[j] = c;
      }

      // add a new pyramid to array
      Pyramid p = new Pyramid(id, name, contributors);
      pyramidArray[i] = p;
    }
  }

  // get a integer from a json object, and parse it
  private Integer toInteger(JSONObject o, String key) {
    String s = o.get(key).toString();
    Integer result = Integer.parseInt(s);
    return result;
  }

  // get first character from input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';

    String rawInput = scan.nextLine();

    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      command = rawInput.charAt(0);
    }

    return command;
  }

  // print all pharaohs
  private void printAllPharaoh() {
    for (int i = 0; i < pharaohArray.length; i++) {
      printMenuLine();
      pharaohArray[i].print();
      printMenuLine();
    }
  }

  // print all pyramids
  private void printAllPyramids() {
    for (int i = 0; i < pyramidArray.length; i++) {
      printMenuLine();
      pyramidArray[i].print();
      printMenuLine();
    }
  }

  // Block feature Message 1
  private void printMsg1()
  {
    System.out.println("Get 70% off your first month of Pyradex!");
    System.out.println("Gain unlimited translations of heiroglyphic records for only $4.99 a month!");
    printMenuLine();
  }

  // Block feature Message 2
  private void printMsg2(){
    System.out.println("Subscribe to Pyradex Plus to access this feature!");
    System.out.println("Gain access to unlimited translations of heiroglyphic records for only $4.99 a month!");
    printMenuLine();
  }

  // Block feature Message 3
  private void printMsg3(){
    System.out.println("To access this feature, subscribe to Pyradex!");
    System.out.println("Gain access to unlimited translations of heiroglyphic records for only $4.99 a month!");
    printMenuLine();
  }

  private Boolean executeCommand(Scanner scan, Character command) {
    Boolean success = true;

    switch (command) {
      case '1':
        printAllPharaoh();
        break;
      
      case '2':
      printMsg1();

      case '3':
        printAllPyramids();
        break;

      case '4':
        printMsg2();

      case '5':
      printMsg3();

      case 'q':
        System.out.println("Thank you for using Pyradex!");
        break;

      default:
        System.out.println("ERROR: Unknown commmand");
        success = false;
    }

    return success;
  }

  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t\t%s\n", command, desc);
  }

  private static void printMenuLine() {
    System.out.println(
      "-------------------------------------------------------"
    );
  }

  // prints the menu
  public static void printMenu() {
    System.out.println("Welcome to Pyradex");
    printMenuLine();
    System.out.printf("Command\t\tDescription\n");
    System.out.printf("-------\t\t---------------------------------------\n");
    printMenuCommand('1', "List All the Pharoahs");
    printMenuCommand('2', "Display A Specific Egyptian Pharaoh");
    printMenuCommand('3', "List All Pyramids");
    printMenuCommand('4', "Display A Specific Pyramid");
    printMenuCommand('5', "Display A List of Requested Pyramids");
    printMenuCommand('q', "Quit");
    printMenuLine();
  }
}
