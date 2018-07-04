import java.util.ArrayList;
/**
 * Representation of a Player moving around in an adventure game.
 * The player "knows" where s/he is and can carry things in a pack.
 */
public class Player {
  private ArrayList<Thing> pack = new ArrayList<Thing>();
  private Room location;
  private static final int MAX_THINGS = 5; //bag size increased from 3 to 5
  
  public String toString() {
    StringBuffer result = new StringBuffer();
    for (Thing thing : pack) {
      result.append(thing.toString() + "\n");
    }
    result.append("Available capacity = " + (MAX_THINGS - pack.size()));
    return result.toString();
  }
  
  /**
   * Move this player to the specified room as long as the room is not null.
   * @param room to move to
   * @return true if the move was successful, false otherwise
   */
  boolean moveTo(Room room) {
    if (room != null) {
      location = room;
      return true;
    }
    else {
      return false;
    }
  }
  /**
   * @return the room the player is currently in.
   */
  Room getLocation() {
    return location;
  }
  
  /**
   * @param thing the player would like to add to the pack
   * @return true if the player can carry thing, false otherwise
   */
  boolean canCarry(Thing thing) {
    return (pack.size() < MAX_THINGS);
  }
  
  /**
   * Try and drop an thing.
   * @param name of thing to drop
   * @return true if the player successfully dropped the thing, false otherwise
   */
  boolean drop(String name) {
    for (int i = 0; i < pack.size(); i++) {
      if (pack.get(i).name().equals(name)) {
        Thing thing = pack.remove(i);
        location.add(thing);
        return true;
      }
    }
    return false;
  }
  
  /**
   * Try and pickup an thing.
   * @param name of the thing to pickup.
   * @return the Thing if it was successfully picked up, otherwise null
   */
  Thing pickup(String name) {
    if (location.contains(name)) {
      Thing thing = location.remove(name);
      if (canCarry(thing) && !location.contains("Charlie McDowell") && !name.equals("Charlie McDowell")) {
        pack.add(thing); // got it if the thing is not McDowell, and if McDowell isn't in the room
        return thing;
      }
      else if(location.contains("Charlie McDowell") || name.equals("Charlie McDowell")){
        location.add(thing); //puts back the thing if it was McDowell, or if he's in the room
        return null;
      } else {
        location.add(thing); // put it back. That was too much.
        return null;
      }
    }
    else {
      return null; // it wasn't there to be picked up
    }
  }
  Thing destroy(String name) { //destroy the thing if it's in the room
    if (location.contains(name)) {
      Thing thing = location.remove(name); 
    }
    return null;
  }
  Thing retrieve(String name){ //used to get thing from the player's bag
    for(Thing thing: pack){
      if(thing.name().equals(name)){
        return thing;
      }
    }
    return null;
  } 
}
