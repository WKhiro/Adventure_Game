import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*; 
import java.awt.Font;

/**
 * Program #4 Adventure Game GUI
 * A space adventure game where a player moves from room to room interacting with things in the rooms.
 * The initial world is created from a text file named world.txt or an alternative input file can be specified
 * on the command line. See the specification for the format of the input text file.
 * The game has been programmed to be fully functional using GUI, and does not utilize the console. Seeds can
 * still be provided for testing.
 */

public class Adventure {
  Player player = new Player(); //needs to be accessed inside of listener files, so not private
  private Room entryWay;
  
  /**
   * Main method. Creates the GUI of the game, and initializes the player's starting room.
   * Also creates the seed generation.
   */
  public static void main(String[] args) throws IOException {
    //create the game as an object for listener access, and create the frame/pane
    Adventure theGame = new Adventure(); 
    JFrame frame = new JFrame("Test Subject 525's Deep Space Adventure");
    Container pane = frame.getContentPane();
    
    //custom fonts for GUI
    Font gameFont = new Font("Georgia", Font.ITALIC, 16);
    Font directionalFont = new Font("Papyrus", Font.BOLD, 30);
    Font utilitiesFont = new Font("Georgia", Font.BOLD, 30);
    
    //create the buttons, input field, output text area, and a label
    JTextField inputArea = new JTextField();
    JTextArea outputArea = new JTextArea();
    JButton north = new JButton("NORTH");
    JButton east = new JButton("EAST");
    JButton south = new JButton("SOUTH");
    JButton west = new JButton("WEST");
    JButton instruct = new JButton("INSTRUCTIONS");
    JButton quit = new JButton("QUIT");
    JButton current = new JButton("CURRENT ROOM");
    JLabel title = new JLabel("CONTROL PAD", SwingConstants.CENTER);
      
    //apply the custom fonts accordingly to components
    inputArea.setFont(gameFont);
    outputArea.setFont(gameFont);
    north.setFont(directionalFont);
    east.setFont(directionalFont);
    west.setFont(directionalFont);
    south.setFont(directionalFont);
    quit.setFont(utilitiesFont);
    instruct.setFont(utilitiesFont);
    current.setFont(utilitiesFont);
    title.setFont(utilitiesFont);
    
    //set initial pane's layout to 0 rows, 1 column, and place the output area in it
    pane.setLayout(new GridLayout(0,1));
    outputArea.setText(theGame.printInstructions());
    outputArea.setEditable(false); //don't make it editable
    pane.add(outputArea);
    
    //make a new panel for buttons below the initial pane
    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(3,3)); //3 rows, 3 columns
    buttons.add(current);
    buttons.add(north);
    buttons.add(title);
    buttons.add(west);
    buttons.add(inputArea);
    buttons.add(east);
    buttons.add(instruct);
    buttons.add(south);
    buttons.add(quit);
    pane.add(buttons);
    
    //add listener to input area
    InputListener inputFunctionality = new InputListener(theGame, outputArea, inputArea, frame);
    inputArea.addActionListener(inputFunctionality);
    
    //add listener to buttons
    ButtonListener buttonFunctionality = new ButtonListener(theGame, outputArea, frame);
    north.addActionListener(buttonFunctionality);
    south.addActionListener(buttonFunctionality);
    west.addActionListener(buttonFunctionality);
    east.addActionListener(buttonFunctionality);
    quit.addActionListener(buttonFunctionality);
    instruct.addActionListener(buttonFunctionality);
    current.addActionListener(buttonFunctionality);
    frame.setSize(1050,850); //set the initial frame size
    frame.setVisible(true);
    
    //make it so that the GUI frame appears at the center of the screen instead of the top left
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth()-frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight()-frame.getHeight()) / 2);
    frame.setLocation(x, y);
    
    //seed generation for testing
    Random rand;
    if (args.length == 2) {
      // use a seed if provided for testing
      rand = new Random(Integer.parseInt(args[1]));
    }
    else {
      rand = new Random(); // let it be really random
    }
    if (args.length >= 1) {
      theGame.entryWay = randomWorld(args[0], rand);
    }
    else {
      theGame.entryWay = randomWorld("world.txt", rand);
    }
    //always moves the player to the entry way at the start 
    theGame.player.moveTo(theGame.entryWay); 
  }
  
  /**
   * Read the input file for the list of rooms and their content, then connect them randomly.
   * @param fileName - the name of the world specification file.
   */
  static Room randomWorld(String fileName, Random rand) throws IOException {
    Scanner fileIn = new Scanner(new File(fileName));
    ArrayList<Room> rooms = new ArrayList<Room>();
    
    // first create the rooms and their content - first room is the entrance room
    Room entrance = new Room(fileIn.nextLine());
    addStuff(entrance, fileIn);
    rooms.add(entrance);
    
    // add more rooms
    while (fileIn.hasNextLine()) {
      String name = fileIn.nextLine();
      if (name.equals("*****")) break; // YUK!
      else {
        Room room = new Room(name);
        addStuff(room, fileIn);
        rooms.add(room);   
      }
    }
    
    // now connect the rooms randomly
    for (Room room : rooms) {
      room.connectNorth(rooms.get(rand.nextInt(rooms.size())));
      room.connectEast(rooms.get(rand.nextInt(rooms.size())));
      room.connectSouth(rooms.get(rand.nextInt(rooms.size())));
      room.connectWest(rooms.get(rand.nextInt(rooms.size())));
    }   
    return entrance;    
  }
  
  /**
   * Assumes there is always a blank line after the last line of stuff being added.
   * Adds all the objects into the world.
   * @param room - the room to fill.
   * @param in - a Scanner reading from the world specification file, ready to read the next room.
   */
  static void addStuff(Room room, Scanner in) {
    String name = in.nextLine().trim();
    while (name.length() > 0) {
      if(name.equals("space gun")){
        room.add(new SpaceGun());
      } else if(name.equals("lightsaber")){
        room.add(new LightSaber());
      } else if(name.equals("pillow")){
        room.add(new Pillow());
      } else if(name.equals("blankets")){
        room.add(new Blankets());
      } else if(name.equals("Charlie McDowell")){
        room.add(new HorrificBeast());
      } else if(name.equals("cryonics files")){
        room.add(new CryoFiles());
      } else if(name.equals("coding transcripts")){
        room.add(new CodingTranscripts());
      } else if(name.equals("bananas")){
        room.add(new Bananas());
      } else if(name.equals("transmitter")){
        room.add(new Transmitter());
      } else {
        room.add(new Thing(name));
      }
      name = in.nextLine().trim();   
    }
  }
  
  /**
   * Returns the instructions and explains the different actions you can input.
   */
  static String printInstructions() {
    return "Hello, Test Subject 525. I am Charlie, your personal helper bot.\n\n" +
      "I suggest you pick up the cryonic files in the starting area, and inspect them before proceeding." +
      " You may drop them afterwards if you wish.\n\n" +
      "You awaken in the Cryostasis Containment Chamber, which only contains 'cryonics files'.\n" +
      "You can traverse the ship by using the north, south, east, or west buttons.\n" +
      "You can see what is in the room you are in by typing 'look', or you can use the current room button.\n" +
      "You can pick things up by typing 'pickup thing' where thing is what you see in the room.\n"+
      "You can drop things you are carrying by typing 'drop thing' where thing names something you have.\n"+
      "You can see your status by typing status, and quit by using the quit button.\n" +
      "If you wish to view the instructions again, press the instructions button.\n\n" +
      "You can also inspect things you are carrying by typing 'inspect thing' where thing names" +
      " something you have.\n" +
      "Inspected things may possess different functions, so inspect them thoroughly.\n" +
      "If you use things in your bag when you're not supposed to, I'll inform you." +
      " If you continually try to use things when you're not supposed to...\n" +
      "you can see for yourself what'll happen.\n\n" +
      "OBJECTIVE: My sensors have detected a dangerous invader among us...quietly lurking around the ship.\n" +
      "Search the ship for weapons to arm yourself with, and find a transmitter to contact home base.\n" +
      "I suggest you act quickly before this creature finds you first, 525.\n\n";
  }
  
  /**
   * Returns player's attempt to move into the specified room.
   * This could be teleporting or to a connected room. There is no check for passageway.
   * If the room is null, the move will fail.
   * @param Player - the player trying to move.
   * @param room - the room to move to - could be null
   */
  static String enter(Player player, Room room) {
    if (player.moveTo(room)) {
      return "-Charlie Bot: You have entered " + player.getLocation();
    } else {
      return "That way appears to be blocked.";
    }
  }
  
  /**
   * Return and display the contents of what the player sees in the room s/he is currently in.
   * @param player - the player doing the looking
   */
  static String look(Player player) {
    String stuff = player.getLocation().whatStuff();
    if (!stuff.equals("")) {
      if(player.getLocation().contains("Charlie McDowell")){
        return "-You hear the sound of typing echoing throughout room...\n" +
          "Charlie Bot: Oh my word is that...is that ME? It seems that the mysterious intruder was\n" +
          "simply a robotic copy of myself. On the other hand, I see the transmitter, 525!\n" +
          "However, it appears that it has fallen into the intruder's clutches.\n" +
          "More specifically, it seems to be in his back pocket.\n" +
          "Great. He looks like an ordinary human, but I would brace yourself for battle, 525.\n\n" +
          "He hasn't noticed you...arm yourself and attempt to take the transmitter from him.\n\n" +
          "-You see:\n" + stuff;
      } else {
        return "-You see:\n" + stuff;
      }
    } else {
      return "The room is empty.";
    }
  }
  
  /**
   * Player attempts to pickup the specified object.
   * @param player - player doing the picking up
   * @param - what to pickup
   */
  static String pickup(Player player, String what) {
    if (player.getLocation().contains(what)) {
      Thing thing = player.pickup(what);
      
      //doesn't let you pick up things where McDowell is, and doesn't let you pickup McDowell
      if (thing != null && !player.getLocation().contains("Charlie McDowell") && !what.equals("Charlie McDowell")) {
        return "-You have picked up the " + thing;
      } else if(player.getLocation().contains("Charlie McDowell") && !what.equals("Charlie McDowell")){
        return "-Charlie Bot: The transmitter is currently in McDowell's possession. You can't safely\n" +
          "retrieve it without him noticing, 525. You'll have to use something to take it from him\n" +
          "by force.";
      } else if(what.equals("Charlie McDowell") || what.equals("charlie mcdowell")){ //you can't pickup McDowell
        return "-Charlie Bot: You can't just shove the professor into your bag and call it a day, 525.\n" +
          "Nice try though.";
      } else {
        return "-Your bag is full, 525. You need to drop something else first.";
      }
    } else {
      return "-Charlie Bot: There doesn't seem to be any " + what + " in the room, 525.";
    }
  }
  
  /**
   * Returns player's attempt to drop the specified object.
   * @param player - player doing the dropping
   * @param - what to drop
   */
  static String drop(Player player, String what) {
    if (player.drop(what)) {
      return "-Charlie Bot: You have dropped the " + what;
    } else {
      return "-Charlie Bot: You aren't carrying " + what + ", 525.\n" +
        "Has the cryostasis affected your sense of weight?";
    }
  }
}