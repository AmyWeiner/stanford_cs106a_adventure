import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import acm.util.ErrorException;
 
 
/*
 * File: AdvRoom.java
 * ------------------
 * Alena Balgobin & Amy Weiner
 * CS106A
 * Section Leader: Molly Mackinlay
 * Assignment 7
 * --------------------------------
 * This file defines a class that models a single room in the
 * Adventure game.
 */
 
/**
 * This class defines a single room in the Adventure game.  A room is
 * is characterized by the following properties:
 *
 * <ul>
 * <li>A room number, which must be greater than zero
 * <li>Its name, which is a one-line string identifying the room
 * <li>Its description, which is a line array describing the room
 * <li>A list of objects contained in the room
 * <li>A flag indicating whether the room has been visited
 * <li>A motion table specifying the exits and where they lead
 * </li>
 *
 * The external format of the room file is described in the assignment
 * handout.
 */
 
public class AdvRoom {
     
/**
 * Creates a new room by reading its data from the specified reader.
 * If no data is left in the reader, this method returns <code>null</code>
 * instead of an <code>AdvRoom</code> value.  
 */
    public static AdvRoom readRoom(BufferedReader rd) {
        try {
            String line = rd.readLine();
            if (line == null) return null;
            AdvRoom room = new AdvRoom();
            room.roomNumber = Integer.parseInt(line);
            line = rd.readLine();
            if(line == null) return null;
            room.roomName = line;
            ArrayList<String> roomDescription = new ArrayList<String>();
            while (true) {
                line = rd.readLine();
                if (line.equals(MARKER)) break;
                roomDescription.add(line);
            }
            room.strArray = roomDescription.toArray(new String[roomDescription.size()]);
            ArrayList<AdvMotionTableEntry> motionList = new ArrayList<AdvMotionTableEntry>();
            while (true) {
                line = rd.readLine();
                if (line == null || line.length() == 0) break;
                parseMotionLine(motionList, line);
            }
            room.motionTable = motionList.toArray(new AdvMotionTableEntry[motionList.size()]);
            motionMap.put(room.roomName, room.motionTable);
            return room;
        } catch (IOException ex) {
            throw new ErrorException(ex);
        } catch (NumberFormatException ex) {
            throw new ErrorException("Illegal room number");
        }
    }
     
/* 
 * This method parses the motion lines and saves them as motion table entries  
 */
    private static void parseMotionLine(ArrayList<AdvMotionTableEntry> motionList, String line) {
        int space = line.indexOf(" ");
        String dir = line.substring(0, space);
        String spaceless = line.substring(space).trim();
        int slash = spaceless.indexOf("/");
        int Room;
        String key;
        if (slash == -1) {
            Room = Integer.parseInt(spaceless);
            key = "";
        }
        else {
            Room = Integer.parseInt(spaceless.substring(0,slash));
            key = spaceless.substring(slash + 1);
        }
        AdvMotionTableEntry entry = new AdvMotionTableEntry(dir,Room,key);
        motionList.add(entry);
    }
 
/**
 * Returns the room number.
 *
 * @return The room number
 */
    public int getRoomNumber() {
        return roomNumber;
    }
 
/**
 * Returns the room name, which is its one-line description.
 *
 * @return The room name
 */
    public String getName() {
        return roomName;
    }
 
/**
 * Returns an array of strings that correspond to the long description
 * of the room.
 *
 * @return An array of strings giving the long description of the room
 */
    public String[] getDescription() {
        return strArray;
    }
 
/**
 * Adds an object to the list of objects in the room.
 *
 * @param The AdvObject to be added
 */
    public void addObject(AdvObject obj) {
        objectList.add(obj);
    }
 
/**
 * Removes an object from the list of objects in the room.
 *
 * @param The AdvObject to be removed
 */
    public void removeObject(AdvObject obj) {
        objectList.remove(obj);
    }
 
/**
 * Checks whether the specified object is in the room.
 *
 * @param The AdvObject being tested
 * @return true if the object is in the room, and false otherwise
 */
    public boolean containsObject(AdvObject obj) {
        return objectList.contains(obj);
    }
 
/**
 * Returns an array of all the objects in the room.
 *
 * @return An array containing the objects in the room
 */
    public AdvObject[] getObjects() {
        return objectList.toArray(new AdvObject[objectList.size()]);
    }
 
/**
 * Sets the flag indicating that this room has been visited as specified
 * by the value of the parameter flag.  Calling setVisited(true) means
 * that the room has been visited; calling setVisited(false) restores
 * its initial state.
 *
 * @param flag The new state of the "visited" flag
 */
    public void setVisited(boolean flag) {
        visitedFlag = flag;
    }
 
/**
 * Returns true if the room has previously been visited.
 *
 * @return true if the room has previously been visited; false otherwise
 */
    public boolean hasBeenVisited() {
        return visitedFlag;
    }
 
/**
 * Returns the motion table associated with this room, which is an
 * array of directions, room numbers, and enabling objects stored
 * in a AdvMotionTableEntry.
 *
 * @return The array of motion table entries associated with this room
 */
    public AdvMotionTableEntry[] getMotionTable() {
        return motionTable;
    }
 
/* Private constants and instance variables */ 
    private static String MARKER = "-----";
    private int roomNumber;
    private String roomName;
    private AdvMotionTableEntry[] motionTable;
    private String[] strArray;
    private ArrayList<AdvObject> objectList = new ArrayList<AdvObject>();
    private boolean visitedFlag;
    private static Map<String, AdvMotionTableEntry[]> motionMap = new HashMap<String, AdvMotionTableEntry[]>();
}
 