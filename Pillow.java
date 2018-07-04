class Pillow extends Thing{
  private static int counter = 0; //to change the text that displays 
  Pillow(){ //makes the name of the object
    super("pillow");
  }
  
  /**
   * Player attempts to whack with pillow when you're not supposed to, resulting in text from Charlie Bot.
   */
  String whack(){
    String response = "";
    if(counter == 0){
      response = "-Charlie Bot: Ouch. What was that for, 525?";
    }
    counter++;
    if(counter == 2){
      response = "-Charlie Bot: This pillow seems to just be taking up space in your bag.";
    }
    if(counter == 3){
      response = "-Charlie Bot: Yes, helper bots can sense pain, 525.";
      counter = counter - 3;
    }
    return response;
  }
  
  /**
   * Inspects the pillow and gives you useful information on its functions.
   */
  String inspect(){
    return "-Charlie Bot: This is a pillow, 525.\n" +
      "It's soft and fluffy, but has no other special qualities those two traits.\n\n" +
      "The best you could do with this pillow in combat is 'whack' things with it."; 
  }
}