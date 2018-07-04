import java.awt.event.*;
import javax.swing.*;

class ButtonListener implements ActionListener{
  private Adventure theGame;
  private JTextArea outputArea;
  private JFrame frame;
  ButtonListener(Adventure theGame, JTextArea outputArea, JFrame frame){
    this.theGame = theGame;
    this.outputArea = outputArea;
    this.frame = frame;
  }
  
  /**
   * Listens to the button presses according to their labels.
   * Sets the output to different texts depending on button pressed, and returns that output.
   * @param e listens to the button press.
   */
  public void actionPerformed(ActionEvent e){
    String response = "";
    if(e.getActionCommand().equals("INSTRUCTIONS")){
      response = theGame.printInstructions();
    } else if(e.getActionCommand().equals("CURRENT ROOM")){
      response = "You are in " + theGame.player.getLocation() + "\n\n" + theGame.look(theGame.player);
    } else if(e.getActionCommand().equals("NORTH")){
      response = theGame.enter(theGame.player, theGame.player.getLocation().north());
    } else if(e.getActionCommand().equals("SOUTH")){
      response = theGame.enter(theGame.player, theGame.player.getLocation().south());
    } else if(e.getActionCommand().equals("WEST")){
      response = theGame.enter(theGame.player, theGame.player.getLocation().west());
    } else if(e.getActionCommand().equals("EAST")){
      response = theGame.enter(theGame.player, theGame.player.getLocation().east());
    } else if(e.getActionCommand().equals("QUIT")){
      frame.dispose(); //closes the GUI
      return; //ends the program in the command prompt
    }
    outputArea.setText(response);
  }
}