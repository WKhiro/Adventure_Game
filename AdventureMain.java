import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;  

class AdventureMain{
  public static void main(String[] args) throws IOException {
    Adventure theGame = new Adventure();
    
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
    
    theGame.player.moveTo(theGame.entryWay);
    
    printInstructions(theGame.player); //added a parameter, player, so the player also looks around while in the starting room
    
    play(theGame.player); 
  }
  /**
   * Prints the instructions and explains the different actions you can input.
   * @param player - the player of the game. In this method, it's called to utilize the look() method to look around
   * at the start.
   */
  static void printInstructions(Player player) {
    System.out.println("\nHello, Test Subject 525. I am Charlie, your personal helper bot.\n" +
                       "I suggest you pick up the cryonic files in the room and inspect them before proceeding.\n" +
                       "You may drop them afterwards if you wish.\n\n" +
                       "You can traverse the ship by typing north, south, east, or west.\n" +
                       "You can see what is in the room you are in by typing 'look'.\n" +
                       "You can pick things up by typing 'pickup thing' where thing is what you see in the room.\n"+
                       "You can drop things you are carrying by typing 'drop thing' where thing names something you have.\n"+
                       "You can see your status by typing status and quit by typing quit.\n\n" +
                       "You can also inspect things you are carrying by typing 'inspect thing' where thing names\n" +
                       "something you have.\n" +
                       "Inspected things may possess different functions, so inspect them thoroughly.\n" +
                       "If you use things in your bag when you're not supposed to, I'll inform you.\n" +
                       "If you continually try to use things when you're not supposed to...\n" +
                       "you can see for yourself what'll happen.\n\n" +
                       "OBJECTIVE: My sensors have detected a dangerous invader among us...quietly lurking around the ship.\n" +
                       "Search the ship for weapons to arm yourself with, and find a transmitter to contact home base.\n" +
                       "I suggest you act quickly before this creature finds you first, 525.\n\n" +
                       "You have awoken the Cryostasis Containment Chamber.");
    look(player); //looks around the starting room
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
  static void play(Player player) {
    Scanner in = new Scanner(System.in);
    
    while (in.hasNextLine()) {
      String cmd = in.nextLine();
      if (cmd.equals("quit")) {
        System.out.println("\n-Too scared to proceed?\n" +
                           "Good bye. Until next time, Test Subject 525.");
        return;
      }
      else if (cmd.contains("north")) {
        enter(player, player.getLocation().north());      
      }
      else if (cmd.contains("south")) {
        enter(player, player.getLocation().south()); 
      }
      else if (cmd.contains("east")) {
        enter(player, player.getLocation().east()); 
      }
      else if (cmd.contains("west")) {
        enter(player, player.getLocation().west()); 
      }
      else if (cmd.contains("look")) {
        look(player);
      }
      else if (cmd.startsWith("pickup") ) {
        pickup(player, cmd.substring(7)); // MAGIC NUMBER length of pickup plus a space
      }
      else if (cmd.startsWith("drop")) {
        drop(player, cmd.substring(5)); // MAGIC NUMBER length of drop plus a space    
      }
      else if (cmd.contains("status")) {
        System.out.println(player);
      }
      else if(cmd.contains("inspect cryonics files")){ 
        Thing files = player.retrieve("cryonics files");
        if(files == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          CryoFiles cryo = (CryoFiles)files;
          cryo.inspect(); //gives information on cryonics files
        }
      }
      else if(cmd.contains("inspect coding transcripts")){
        Thing ct = player.retrieve("coding transcripts");
        if(ct == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          CodingTranscripts transcripts = (CodingTranscripts)ct;
          transcripts.inspect(); //gives information on coding transcripts
        }
      }
      else if(cmd.contains("use coding transcripts")){
        Thing ct = player.retrieve("coding transcripts");
        if(ct == null){
          System.out.println("\n-Charlie Bot: You can't use anything, 525.");
        } else if(player.getLocation().contains("Charlie McDowell")){ //if McDowell is in the room
          System.out.println("\n-You shove the horribly written coding transcripts into McDowell's face!\n" +
                             "He makes a disgusted face, and begins to short circuit due to agitation from looking at\n" +
                             "the transcripts. He starts to verbally recite random bits and pieces of code like a\n" +
                             "robotic rapper rapping in code.\n\n" +
                             "The robotic Charlie McDowell explodes into pieces!\n" +
                             "-the transmitter slides across the floor-\n\n" +
                             "Charlie Bot: You did it 525! Now hurry, grab the transmitter and use it by typing in\n" +
                             "'use transmitter'.");
          player.destroy("Charlie McDowell"); //removes McDowell from the room
          look(player); //looks to show you what is still in the room
        } else {
          CodingTranscripts transcripts = (CodingTranscripts)ct;
          transcripts.use(); //you can't use them
        }
      }
      else if(cmd.contains("inspect transmitter")){
        Thing trans = player.retrieve("transmitter");
        if(trans == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          Transmitter transmitter = (Transmitter)trans;
          transmitter.inspect(); //gives info on transmitter
        }
      }
      else if(cmd.contains("use transmitter")){
        Thing trans = player.retrieve("transmitter");
        if(trans == null){
          System.out.println("\n-Charlie Bot: If you could just magically use the transmitter, we wouldn't need to go\n" +
                             "retrieve it, 525.");
        } else {
          Transmitter transmitter = (Transmitter)trans;
          transmitter.use(); //uses the transmitter
        }
      }
      else if(cmd.contains("throw transmitter at Charlie Bot") || cmd.contains("throw transmitter")){
        Thing trans = player.retrieve("transmitter");
        if(trans!=null){
          System.out.println("\n-You threw the transmitter at Charlie Bot and destroyed him! Congratulations!\n" +
                             "YOU WIN!...Or do you? You're stuck on a ship in deep space after all...\n" +
                             "To be continued. Thanks for playing!");
          return; //ends the game in victory
        } else {
          System.out.println("-You can't do that.");
        }
      }
      else if (cmd.contains("shoot spacegun") || cmd.contains("shoot space gun")) {
        Thing sgun = player.retrieve("space gun");
        if(sgun == null){
          System.out.println("\n-Charlie Bot: You possess nothing you can shoot with, 525.");
        } else if(player.getLocation().contains("Charlie McDowell")){ 
          if(Math.random()>0.5){ //flips a coin and changes the scenario randomly
            System.out.println("\n-McDowell uses his laptop as a shield and deflects the energized\n" +
                               "bullets back at you! He takes out his candy bag, and begins chucking candy at you.\n" +
                               "The barrage of candy overwhelms you.\n" +
                               "You faint from overconsumption of sugar. YOU LOSE.");
            return; //end the game in defeat
          } else {
            System.out.println("\n-McDowell dodges the bullet.\n" +
                               "Charlie Bot: Fire again, 525?");
          }
        } else {
          SpaceGun spacegun = (SpaceGun)sgun;
          spacegun.shoot(); //tells you not to shoot it 
        }
      }
      else if(cmd.contains("inspect space gun") || cmd.contains("inspect spacegun")){
        Thing sgun = player.retrieve("space gun");
        if(sgun == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          SpaceGun spacegun = (SpaceGun)sgun;
          spacegun.inspect(); //gives info on space gun
        }
      }
      else if (cmd.startsWith("slash")) {
        Thing lsaber = player.retrieve("lightsaber");
        if(lsaber == null){
          System.out.println("\n-Charlie Bot: You possess nothing you can slash with, 525.");
        } else if(player.getLocation().contains("Charlie McDowell")){
          if(Math.random()>0.5){ //flips a coin and changes the scenario randomly
            System.out.println("\n-Charlie Bot: The lightsaber plinks off McDowell's suit as if he were made of adamantium!\n" +
                               "McDowell is enraged! McDowell used laptop shuriken! He throws his laptop at you \n" +
                               "horizontally as if it were a shuriken.\n" +
                               "You have fainted from a fatal blow to the head. YOU LOSE.");
            return; //ends the game in defeat
          } else {
            System.out.println("\n-Charlie Bot: McDowell is completely unaffected by the lightsaber! He shoots you an\n" + 
                               "intimidating look. I suggest you approach with a different method of attack if you\n" + 
                               "wish to release the transmitter from his grasp.");
          }
        } else {
          LightSaber lightsaber = (LightSaber)lsaber;
          lightsaber.slash(); //tells you not to slash with the lightsaber
        }
      }
      else if(cmd.contains("inspect lightsaber") || cmd.contains("inspect light saber")){
        Thing lsaber = player.retrieve("lightsaber");
        if(lsaber == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          LightSaber lightsaber = (LightSaber)lsaber;
          lightsaber.inspect(); //gives info on lightsaber
        }
      }
      else if(cmd.contains("inspect pillow")){
        Thing pillows = player.retrieve("pillow");
        if(pillows == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          Pillow pillow = (Pillow)pillows;
          pillow.inspect(); //gives info on pillow
        }
      }
      else if(cmd.startsWith("whack")){
        Thing pillows = player.retrieve("pillow");
        if(pillows == null){
          System.out.println("\n-Charlie Bot: You have nothing you can use to whack other things.");
        } else if(player.getLocation().contains("Charlie McDowell")){ //if used when McDowell is in the room
          System.out.println("\n-You start whacking McDowell with the pillow.\n" +
                             "He completely ignores you, and continues to code on his laptop.\n\n" +
                             "Charlie Bot: The pillow is NOT the ideal weapon in this case.");
        } else {
          Pillow pillow = (Pillow)pillows;
          pillow.whack(); //tells you not to use it
        }
      }
      else if(cmd.contains("inspect blankets")){
        Thing blankys = player.retrieve("blankets");
        if(blankys == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          Blankets blankets = (Blankets)blankys;
          blankets.inspect();
        }
      }
      else if(cmd.startsWith("snuggle blankets")){
        Thing blankys = player.retrieve("blankets");
        if(blankys == null){
          System.out.println("\n-Charlie Bot: What are you trying to snuggle, 525?");
        } else {
          Blankets blankets = (Blankets)blankys;
          blankets.snuggle(); //gives you flavor text and tells you not to use it
        }
      }
      else if(cmd.contains("inspect bananas")){
        Thing nanas = player.retrieve("bananas");
        if(nanas == null){
          System.out.println("\n-Charlie Bot: You can't inspect what you don't have, 525.");
        } else {
          Bananas bananas = (Bananas)nanas;
          bananas.inspect(); //tells you info about the bananas
        }
      }
      else if(cmd.startsWith("toss bananas")){
        Thing nanas = player.retrieve("bananas");
        if(nanas == null){
          System.out.println("\n-Charlie Bot: You don't have any bananas to toss, 525.");
        } else if(player.getLocation().contains("Charlie McDowell")){ //if tossed at McDowell
          System.out.println("\n-McDowell catches the bananas you toss!\n" +
                             "He flings them back at your head as a warning.\n" +
                             "Charlie Bot: I really don't think bananas are an effective weapon, 525...\n");
        } else {
          Bananas bananas = (Bananas)nanas;
          bananas.toss(); //tells you to stop tossing them
        }
      }
      else if(cmd.contains("eat bananas")){
        Thing nanas = player.retrieve("bananas");
        if(nanas == null){
          System.out.println("\n-Charlie Bot: What are you trying to eat, 525? Invisible bananas?");
        } else {
          Bananas bananas = (Bananas)nanas;
          bananas.eat(); //flavor text and tells you not to eat them
        }
      }
      else {
        System.out.println("\n-Charlie Bot: I believe you have inputted some gibbish, 525."); //bad input from user
      }
    }
  }
  
  /**
   * Player attempts to move into the specificed room.
   * This could be teleporting or to a connected room. There is no check for passageway.
   * If the room is null, the move will fail.
   * @param Player - the player trying to move.
   * @param room - the room to move to - could be null
   */
  static void enter(Player player, Room room) {
    if (player.moveTo(room)) {
      System.out.println("\n-Charlie Bot: You have entered " + player.getLocation());
    }
    else {
      System.out.println("\nThat way appears to be blocked.");
    }
  }
}