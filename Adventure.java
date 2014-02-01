import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import acm.program.ConsoleProgram;
import acm.util.ErrorException;
 
/*
 * File: Adventure.java
 * --------------------
 * Alena Balgobin & Amy Weiner
 * CS106A
 * Section Leader: Molly Mackinlay
 * Assignment 7
 * Outside Help: After waiting in the lair for over 2 hours with no 
 * assistance, we talked to a friend of mine who helped us with debugging
 * the program.
 * --------------------------------
 * This program plays the CS106A Adventure game.
 */
 
/**
 * This class is the main program class for the Adventure game.
 */
 
public class Adventure extends ConsoleProgram{ 
 
    public void run() {
        getAdventureName();
        getFiles();
        inventoryList = new ArrayList<String>();
        room = RoomMap.get(1);
        playGame();
    }
 
/* 
 * Prompts the user to give the name of the game they want to play 
 */
    private void getAdventureName() {
        println("Welcome to Adventure!");
        filename = readLine("Enter the name of the Adventure: ");
        filename = filename.toLowerCase();
        String first = filename.substring(0, 1).toUpperCase();
        String last = filename.substring(1);
        filename = first + last;
    }
 
/*
 * Takes the name of the game and uses it to make the names of the
 * files needed to play the game. Then reads the files and stores the
 * information so that it can be accessed throughout the game.  
 */
    private void getFiles() {
        String roomfile = filename + "Rooms.txt";
        String objectfile = filename + "Objects.txt";
        String synonymsfile = filename + "Synonyms.txt";
        RoomMap = new HashMap<Integer, AdvRoom>();
        try {
            BufferedReader rdRoom = new BufferedReader(new FileReader(roomfile));
            while (true) {
                AdvRoom room = AdvRoom.readRoom(rdRoom);
                if(room == null) break;
                RoomMap.put(room.getRoomNumber(), room);
            }
            rdRoom.close();
        } catch (IOException ex) {
            throw new ErrorException(ex);
        } catch (NumberFormatException ex) {
            throw new ErrorException("Could not read room file");
        }
        ObjMap = new HashMap<String, AdvObject>();
        try {
            BufferedReader rdObject = new BufferedReader(new FileReader(objectfile));
            while (true) {
                AdvObject object = AdvObject.readObject(rdObject);
                if(object == null) break;
                ObjMap.put(object.getName(), object);
                AdvRoom home = RoomMap.get(object.getInitialLocation());
                home.addObject(object);
            }
            rdObject.close();
        } catch (IOException ex) {
            //does nothing 
        } catch (NumberFormatException ex) {
            throw new ErrorException("Could not read object file");
        }
        SynMap = new HashMap<String, String>();
        try {
            BufferedReader rdSyn = new BufferedReader(new FileReader(synonymsfile));
            while (true) {
                String line = rdSyn.readLine();
                if (line == null || line.length() == 0) break;
                parseSynLine(line);
            }
            rdSyn.close();
        } catch (IOException ex) {
            //does nothing 
        } catch (NumberFormatException ex) {
            throw new ErrorException("Could not read synonyms file");
        }
    }
     
/*
 *  Parses the data in the synonyms file line by line
 *  and stores it in a hash map so it can be used later.
 */
    private void parseSynLine(String line) {
        int equal = line.indexOf("=");
        String abbreviated = line.substring(0, equal);
        String original = line.substring(equal + 1);
        SynMap.put(abbreviated, original);
    }
 
/* Plays the game. */  
    private void playGame() {
        printDescription();
        room.setVisited(true);
        while(room != null) {
            String command = readLine("> ");
            command = command.toUpperCase();
            executeCmd(command);
        }
        println("Good game!");
    }
/* 
 * Takes command entered by user and moves the player 
 * based on the command entered.
 */
    private void executeCmd(String command) {
        int space = command.indexOf(" ");
        String verbCmd;
        if(space == -1) {
            verbCmd = command; 
        } else {
            verbCmd = command.substring(0, space);
        }
        verbCmd = checkForSyn(verbCmd);
        String objectCmd;
        if(space == -1) {
            objectCmd = "";     
        } else {
            objectCmd = command.substring(space + 1);
        }
        objectCmd = checkForSyn(objectCmd);
        checkCmd(verbCmd, objectCmd);
    }
     
/* Checks to make sure the command is in its canonical form */ 
    private String checkForSyn(String cmd) {
        if(SynMap.containsKey(cmd)) {
            cmd = SynMap.get(cmd);
        }
        return cmd;
    }
     
/* Prints the long description of the current room */
    private void printDescription() {
        String [] lookDescrip = room.getDescription();
        for(int i = 0; i < lookDescrip.length; i++) {
            println(lookDescrip[i]);
        }
    }
 
/*
 * The user puts in a command, this method checks to see if it is one of the 6 
 * built in action verbs, or a motion verb, otherwise it prints an error message
 */
    private void checkCmd(String verb, String obj) {
        if (verb.equals("QUIT")) {
            room = null;
        } else if (verb.equals("HELP")) {
            printDirections();
        } else if(verb.equals("INVENTORY")) { 
            if(inventoryList.isEmpty())
                println("You are empty-handed.");
            else {
                for(int i = 0; i < inventoryList.size(); i++) {
                    println(inventoryList.get(i));
                }
            }
        } else if (verb.equals("LOOK")) {
            printDescription();
            AdvObject[] objects = room.getObjects();
            if(objects.length > 0) {
                println("The objects in the room are:");
                for(int i = 0; i < objects.length; i++) {
                    println(objects[i].getName());
                }           
            }
        } else if (verb.equals("TAKE")) {
            takeObj(obj);
        } else if (verb.equals("DROP")) {
            dropObj(obj);
        } else {
            if(!executeMotion(verb))
                println("Command not understood");
        }
    }
 
/* Prints the game directions for the user */  
    private void printDirections() {
        print("Welcome to Adventure!\n" +
        "Somewhere nearby is Colossal Cave, where others have found fortunes in\n" +
        "treasure and gold, though it is rumored that some who enter are never\n" +
        "seen again.  Magic is said to work in the cave.  I will be your eyes\n" +
        "and hands.  Direct me with natural English commands; I don't understand\n" +
        "all of the English language, but I do a pretty good job.\n\n" +
 
        "It's important to remember that cave passages turn a lot, and that\n" +
        "leaving a room to the north does not guarantee entering the next from\n" +
        "the south, although it often works out that way.  You'd best make yourself\n" +
        "a map as you go along.\n\n" +
 
        "Much of my vocabulary describes places and is used to move you there.\n" +
        "To move, try words like IN, OUT, EAST, WEST, NORTH, SOUTH, UP, or DOWN.\n" +
        "I also know about a number of objects hidden within the cave which you\n" +
        "can TAKE or DROP.  To see what objects you're carrying, say INVENTORY.\n" +
        "To reprint the detailed description of where you are, say LOOK.  If you\n" +
        "want to end your adventure, say QUIT.\n");
    }
 
/* Moves the user to a new room based on the motion commands they give */  
    private boolean executeMotion(String cmd) {
        AdvMotionTableEntry[] tableOfMotions = room.getMotionTable();
        for(int i = 0; i < tableOfMotions.length; i++) {
            AdvMotionTableEntry curr = tableOfMotions[i];
            if(curr.getDirection().equals(cmd)) {
                if(!curr.getKeyName().isEmpty() && !inventoryList.contains(curr.getKeyName())) {
                    continue;
                } 
                if(curr.getDestinationRoom() == 0) {
                    room = null;
                    return true; 
                }
                room = RoomMap.get(curr.getDestinationRoom());
                AdvMotionTableEntry[] motions = room.getMotionTable();
                int f = containsForced(motions);
                if(f != -1) {
                    printDescription();
                    room.setVisited(true);
                    room = RoomMap.get(motions[f].getDestinationRoom());
                } else if(!room.hasBeenVisited()) {
                    printDescription();
                    room.setVisited(true);
                } else {
                    println(room.getName());
                }
                return true;
            }
        }
        return false;
    }
     
/* Checks to see if the motion is FORCED */
    private int containsForced(AdvMotionTableEntry[] motions) {
        for(int i = 0; i < motions.length; i++) {
            AdvMotionTableEntry curr = motions[i];
            if(curr.getDirection().equals("FORCED")) {
                return i;
            }
        }
        return -1;
    }
 
/* Method for taking an object */  
    private void takeObj(String obj) {
        AdvObject object = ObjMap.get(obj);
        if(room.containsObject(object)) {
            inventoryList.add(object.getName());
            room.removeObject(object);
            println("Taken");
        } else {
            println("Object not available");
        }
    }
 
/* Method for dropping an object */
    private void dropObj(String obj) {
        AdvObject object = ObjMap.get(obj);
        if(inventoryList.contains(obj)) {
            inventoryList.remove(obj);
            room.addObject(object);
            println("Dropped"); 
        } else {
            println("You don't have that object");
        }
    }
 
/* Private instance variables */   
    private String filename;
    private HashMap<String, String> SynMap;
    private HashMap<Integer, AdvRoom> RoomMap;
    private HashMap<String, AdvObject> ObjMap;
    private ArrayList<String> inventoryList;
    private AdvRoom room;
}
 