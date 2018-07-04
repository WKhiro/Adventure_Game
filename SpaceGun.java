class SpaceGun extends Thing{
  private static int counter = 0; //to change the text that displays 
  SpaceGun(){ //makes the name of the object
    super("space gun");
  }
  
  /**
   * Player attempts to shoot with space gun when you're not supposed to, resulting in text from Charlie Bot.
   */
  String shoot(){
    String response = "";
    if(counter == 0){
      response = "-Charlie Bot: There is nothing of interest to shoot here, 525.";
    }
    counter++;
    if(counter == 2){
      response = "-Charlie Bot: Could you stop trying to shoot that thing?";
    }
    if(counter == 3){
      response = "-Charlie Bot: ...At this rate, I'm going to get vaporized by one of your stray bullets.";
      counter = counter - 3;
    }
    return response;
  }
  
  /**
   * Inspects the space gun and gives you useful information on its functions.
   */
  String inspect(){
    return "-Charlie Bot: This is a space gun, 525. It feels like something out of a sci-fi movie.\n" +
      "Fueled by some sort of alien technology, it utilizes pure energy rather than ammunition.\n" +
      "It's capable of doing serious damage to whatever is at the other end of it, and has no recoil.\n\n" +
      "You can shoot the space gun by typing: 'shoot space gun'."; 
  }
}