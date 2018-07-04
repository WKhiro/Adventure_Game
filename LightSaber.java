class LightSaber extends Thing{
  private static int counter = 0; //to change the text that displays 
  LightSaber(){ //makes the name of the object
    super("lightsaber");
  }
  
  /**
   * Returns player's attempts to slash when you're not supposed to, resulting in text from Charlie Bot.
   * Uses a String variable to allow counter to reset. Return statements would make the reset unreachable.
   */
  String slash(){
    String response = "";
    if(counter == 0){
      response = "-Charlie Bot: There is nothing of interest to slash here, 525.";
    }
    counter++;
    if(counter == 2){
      response = "-Charlie Bot: Are you trying to hack and slash your personal helper bot up?";
    }
    if(counter == 3){
      response = "-Charlie Bot: This is not the time to be role-playing as a Jedi, 525.";
      counter = counter - 3;
    }
    return response;
  }
  
  /**
   * Inspects the lightsaber and returns useful information on its functions.
   */
  String inspect(){
    return "-Charlie Bot: This is a lightsaber, 525. It gives off an aura of familarity.\n" +
      "It is a plasma blade that requires a kyber crystal to power its saber from a metal hilt.\n" +
      "It has the power to cut through virtually anything it touches.\n\n" +
      "You can slash with the lightsaber by typing: 'slash'."; 
  }
}