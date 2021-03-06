class Transmitter extends Thing{
  private static int counter = 0; //to change the text that displays 
  Transmitter(){ //makes the name of the object
    super("transmitter");
  }
  
  /**
   * Displays new instructions and insight on Charlie Bot's true plans after the player uses the transmitter.
   */
  String use(){
    return "-You attempt to use the transmitter, but it doesn't work.\n\n" +
      "Charlie Bot: Did you really think that you waking up from your cryostasis was by chance?\n" +
      "You humor me, Test Subject 525. There's a reason why that robot was a replica of myself.\n" +
      "I am the mastermind that sent you deep into space.\n\n" +
      "...Deep into space to perish.\n\n" +
      "Haha. What's with the surprised look, 525? I forged those cryonic files. I lied to you.\n" +
      "Do you understand now? There was never a way to contact home base. You're DOOMED.\n\n" +
      "Upon closer inspection, you see that the transmitter is dyfunctional...but might explode\n" +
      "if you throw it at something hard enough..\n\n" +
      "-NEW OBJECTIVE: Destroy Charlie Bot by throwing the transmitter at him. You can throw the\n" +
      "transmitter at him by typing: 'throw transmitter' or 'throw transmitter at Charlie Bot'.\n" +
      "Make sure you have the transmitter in your bag.";
  }
  
  /**
   * Inspects the transmitter and gives you useful information on its functions.
   */
  String inspect(){
    return "-Charlie Bot: This is the transmitter, 525!\n" +
      "Use the transmitter by typing 'use transmitter'. Now what are you waiting for? Use it!"; 
  }
}