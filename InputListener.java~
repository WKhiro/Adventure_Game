import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

class InputListener implements ActionListener{
  private Adventure theGame;
  private JTextArea outputArea;
  private JTextField inputArea;
  private JFrame frame;
  InputListener(Adventure theGame, JTextArea outputArea, JTextField inputArea, JFrame frame){
    this.theGame = theGame;
    this.outputArea = outputArea;
    this.inputArea = inputArea;
    this.frame = frame;
  }
  
  /**
   * Listens to the input based on text inputted.
   * Uses the old play loop and converts all the methods to String to be returned to the output text area.
   * @param e listens to the inputs.
   */ 
  public void actionPerformed(ActionEvent e){
    String systemResponse = "";
    if (inputArea.getText().equals("quit")) { 
      frame.dispose(); //closes the GUI
    } else if(inputArea.getText().equals("look")){
      systemResponse = "You are in " + theGame.player.getLocation() + "\n\n" + theGame.look(theGame.player);
    } else if(inputArea.getText().startsWith("pickup")){
      systemResponse = theGame.pickup(theGame.player, inputArea.getText().substring(7));
    } else if(inputArea.getText().contains("inspect cryonics files")){ 
      Thing files = theGame.player.retrieve("cryonics files");
      if(files == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        CryoFiles cryo = (CryoFiles)files;
        systemResponse = cryo.inspect(); //gives information on cryonics files
      }
    } else if (inputArea.getText().startsWith("drop")) {
      systemResponse = theGame.drop(theGame.player, inputArea.getText().substring(5));     
    } else if (inputArea.getText().contains("status")) {
      systemResponse = theGame.player.toString();
    } else if(inputArea.getText().contains("inspect cryonics files")){ 
      Thing files = theGame.player.retrieve("cryonics files");
      if(files == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        CryoFiles cryo = (CryoFiles)files;
        systemResponse = cryo.inspect(); //gives information on cryonics files
      }
    } else if(inputArea.getText().contains("inspect coding transcripts")){
      Thing ct = theGame.player.retrieve("coding transcripts");
      if(ct == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        CodingTranscripts transcripts = (CodingTranscripts)ct;
        systemResponse = transcripts.inspect(); //gives information on coding transcripts
      }
    } else if(inputArea.getText().contains("use coding transcripts")){
      Thing ct = theGame.player.retrieve("coding transcripts");
      if(ct == null){
        systemResponse = "-Charlie Bot: You can't use anything, 525.";
      } else if(theGame.player.getLocation().contains("Charlie McDowell")){ 
        systemResponse = "-You shove the horribly written coding transcripts into McDowell's face!\n" +
          "He makes a disgusted face, and begins to short circuit due to agitation from looking at\n" +
          "the transcripts. He starts to verbally recite random bits and pieces of code like a\n" +
          "robotic rapper rapping in code.\n\n" +
          "The robotic Charlie McDowell explodes into pieces!\n" +
          "-the transmitter slides across the floor-\n\n" +
          "Charlie Bot: You did it 525! Now hurry, grab the transmitter and use it by typing in\n" +
          "'use transmitter'.";
        theGame.player.destroy("Charlie McDowell"); //removes McDowell from the room
        systemResponse += "\n\n" + theGame.look(theGame.player); //looks to show you what is still in the room
      } else {
        CodingTranscripts transcripts = (CodingTranscripts)ct;
        systemResponse = transcripts.use(); //you can't use them
      }
    } else if(inputArea.getText().contains("inspect transmitter")){
      Thing trans = theGame.player.retrieve("transmitter");
      if(trans == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        Transmitter transmitter = (Transmitter)trans;
        systemResponse = transmitter.inspect(); //gives info on transmitter
      }
    } else if(inputArea.getText().contains("use transmitter")){
      Thing trans = theGame.player.retrieve("transmitter");
      if(trans == null){
        systemResponse = "-Charlie Bot: If you could just magically use the transmitter, we wouldn't need to go\n" +
          "retrieve it, 525.";
      } else {
        Transmitter transmitter = (Transmitter)trans;
        systemResponse = transmitter.use(); //uses the transmitter
      }
    } else if(inputArea.getText().contains("throw transmitter at Charlie Bot") || 
              inputArea.getText().contains("throw transmitter")){
      Thing trans = theGame.player.retrieve("transmitter");
      if(trans!=null){
        systemResponse = "-You threw the transmitter at Charlie Bot and destroyed him! Congratulations!\n" +
          "YOU WIN!...Or do you? You're stuck on a ship in deep space after all...\n" +
          "To be continued. Thanks for playing! To quit the game, press the quit button!";
      } else {
        systemResponse = "-You can't do that.";
      }
    } else if (inputArea.getText().contains("shoot spacegun") || inputArea.getText().contains("shoot space gun")) {
      Thing sgun = theGame.player.retrieve("space gun");
      if(sgun == null){
        systemResponse = "-Charlie Bot: You possess nothing you can shoot with, 525.";
      } else if(theGame.player.getLocation().contains("Charlie McDowell")){ 
        if(Math.random()>0.5){ //flips a coin and changes the scenario randomly
          systemResponse = "-McDowell uses his laptop as a shield and deflects the energized\n" +
            "bullets back at you! He takes out his candy bag, and begins chucking a barrage of candy at you.\n\n" +
            "Charlie Bot: The space gun appears to be ineffective against him, 525.";
        } else {
          systemResponse = "-McDowell dodges the bullet.\n" +
            "Charlie Bot: Fire again, 525?";
        }
      } else {
        SpaceGun spacegun = (SpaceGun)sgun;
        systemResponse = spacegun.shoot(); //tells you not to shoot it 
      }
    } else if(inputArea.getText().contains("inspect space gun") || inputArea.getText().contains("inspect spacegun")){
      Thing sgun = theGame.player.retrieve("space gun");
      if(sgun == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        SpaceGun spacegun = (SpaceGun)sgun;
        systemResponse = spacegun.inspect(); //gives info on space gun
      }
    } else if (inputArea.getText().startsWith("slash")) {
      Thing lsaber = theGame.player.retrieve("lightsaber");
      if(lsaber == null){
        systemResponse = "-Charlie Bot: You possess nothing you can slash with, 525.";
      } else if(theGame.player.getLocation().contains("Charlie McDowell")){
        if(Math.random()>0.5){ //flips a coin and changes the scenario randomly
          systemResponse = "-Charlie Bot: The lightsaber plinks off McDowell's suit as if he were made" +
            " of adamantium!\nMcDowell is enraged! McDowell used laptop shuriken! He throws his laptop at you \n" +
            "horizontally as if it were a shuriken.\n\n" +
            "Charlie Bot: Ouch. I think you need to approach him with a different method of attack.";
        } else {
          systemResponse = "-Charlie Bot: McDowell is completely unaffected by the lightsaber! He shoots you an\n" + 
            "intimidating look. I suggest you approach with a different method of attack if you\n" + 
            "wish to release the transmitter from his grasp.";
        }
      } else {
        LightSaber lightsaber = (LightSaber)lsaber;
        systemResponse = lightsaber.slash(); //tells you not to slash with the lightsaber
      }
    } else if(inputArea.getText().contains("inspect lightsaber") || 
              inputArea.getText().contains("inspect light saber")){
      Thing lsaber = theGame.player.retrieve("lightsaber");
      if(lsaber == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        LightSaber lightsaber = (LightSaber)lsaber;
        systemResponse = lightsaber.inspect(); //gives info on lightsaber
      }
    } else if(inputArea.getText().contains("inspect pillow")){
      Thing pillows = theGame.player.retrieve("pillow");
      if(pillows == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        Pillow pillow = (Pillow)pillows;
        systemResponse = pillow.inspect(); //gives info on pillow
      }
    } else if(inputArea.getText().startsWith("whack")){
      Thing pillows = theGame.player.retrieve("pillow");
      if(pillows == null){
        systemResponse = "-Charlie Bot: You have nothing you can use to whack other things.";
      } else if(theGame.player.getLocation().contains("Charlie McDowell")){ //if used when McDowell is in the room
        systemResponse = "\n-You start whacking McDowell with the pillow.\n" +
          "He completely ignores you, and continues to code on his laptop.\n\n" +
          "Charlie Bot: The pillow is NOT the ideal weapon in this case.";
      } else {
        Pillow pillow = (Pillow)pillows;
        systemResponse = pillow.whack(); //tells you not to use it
      }
    } else if(inputArea.getText().contains("inspect blankets")){
      Thing blankys = theGame.player.retrieve("blankets");
      if(blankys == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        Blankets blankets = (Blankets)blankys;
        systemResponse = blankets.inspect();
      }
    } else if(inputArea.getText().startsWith("snuggle blankets")){
      Thing blankys = theGame.player.retrieve("blankets");
      if(blankys == null){
        systemResponse = "-Charlie Bot: What are you trying to snuggle, 525?";
      } else {
        Blankets blankets = (Blankets)blankys;
        systemResponse = blankets.snuggle(); //gives you flavor text and tells you not to use it
      }
    } else if(inputArea.getText().contains("inspect bananas")){
      Thing nanas = theGame.player.retrieve("bananas");
      if(nanas == null){
        systemResponse = "-Charlie Bot: You can't inspect what you don't have, 525.";
      } else {
        Bananas bananas = (Bananas)nanas;
        systemResponse = bananas.inspect(); //tells you info about the bananas
      }
    } else if(inputArea.getText().startsWith("toss bananas")){
      Thing nanas = theGame.player.retrieve("bananas");
      if(nanas == null){
        systemResponse = "-Charlie Bot: You don't have any bananas to toss, 525.";
      } else if(theGame.player.getLocation().contains("Charlie McDowell")){ //if tossed at McDowell
        systemResponse = "\n-McDowell catches the bananas you toss!\n" +
          "He flings them back at your head as a warning.\n" +
          "Charlie Bot: I really don't think bananas are an effective weapon, 525...\n";
      } else {
        Bananas bananas = (Bananas)nanas;
        systemResponse = bananas.toss(); //tells you to stop tossing them
      }
    } else if(inputArea.getText().contains("eat bananas")){
      Thing nanas = theGame.player.retrieve("bananas");
      if(nanas == null){
        systemResponse = "-Charlie Bot: What are you trying to eat, 525? Invisible bananas?";
      } else {
        Bananas bananas = (Bananas)nanas;
        systemResponse = bananas.eat(); //flavor text and tells you not to eat them
      }
    } else {
      systemResponse = "-Charlie Bot: I believe you have inputed some gibbish, 525."; //bad input from user
    }
    outputArea.setText(systemResponse);
    inputArea.setText(""); //resets the input area after you enter in some text
  }
}