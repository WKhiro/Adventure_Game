class Bananas extends Thing{
  private static int counter = 0; //to change the text that displays 
  Bananas(){ //makes the name of the object
    super("bananas");
  } 
  
  /**
   * Returns player's attempts to toss the bananas when you're not supposed to, resulting in text from Charlie Bot.
   * Uses a String variable to allow counter to reset. Return statements would make the reset unreachable.
   */
  String toss(){
    String response = "";
    if(counter == 0){
      response = "-Charlie Bot: I'd rather you not toss these bananas at me, 525.";
    }
    counter++;
    if(counter == 2){
      response = "-Charlie Bot: Stop that before I start calling you 'Monkey' instead.";
    }
    if(counter == 3){
      response = "-Charlie Bot: You monkey.";
      counter = counter - 3;
    }
    return response;
  }
  
  /**
   * Returns player's attempt to eat the bananas, resulting in text from Charlie Bot.
   */
  String eat(){
    return "-Charlie Bot: Yeah...I don't think this is the time to be eating bananas, 525.";
  }
  
  /**
   * Inspects the bananas and returns useful information on its functions.
   */
  String inspect(){
    return "-Charlie Bot: These are bananas, 525. They're fruits.\n" +
      "There's nothing really special about them.\n\n" +
      "You could 'toss' these bananas, and maybe 'eat' them, but that's about it."; 
  }
}