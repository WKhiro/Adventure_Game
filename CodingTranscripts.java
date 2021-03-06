class CodingTranscripts extends Thing{
  CodingTranscripts(){ //makes the name of the object
    super("coding transcripts");
  }
  
  /**
   * Returns player's attempt to use the coding transcripts when you're not supposed to, 
   * resulting in text from Charlie Bot.
   */
  String use(){
    return "-Charlie Bot: There's no reason to use these transcripts right now.";
  }
  
  /**
   * Inspects the coding transcripts and returns useful information on its functions.
   */
  String inspect(){
    return "-Charlie Bot: These are coding transcripts, 525.\n" +
      "They're very, very poorly written, and would make any programmer feel extremely agitated.\n\n" +
      "Maybe they'll have some 'use' for you later on. Use them by typing: 'use coding transcripts'."; 
  }
}