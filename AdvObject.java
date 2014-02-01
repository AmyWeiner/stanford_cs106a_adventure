import java.io.BufferedReader;
import java.io.IOException;
import acm.util.ErrorException;
 
 
/*
 * File: AdvObject.java
 * --------------------
 * Alena Balgobin & Amy Weiner
 * CS106A
 * Section Leader: Molly Mackinlay
 * Assignment 7
 * --------------------------------
 * This file defines a class that models an object in the
 * Adventure game.
 */
 
/**
 * This class defines an object in the Adventure game.  An object is
 * characterized by the following properties:
 *
 * <ul>
 * <li>Its name, which is the noun used to refer to the object
 * <li>Its description, which is a string giving a short description
 * <li>The room number in which the object initially lives
 * </li>
 *
 * The external format of the objects file is described in the assignment.
 * The comments on the methods exported by this class show how to
 * use the initialized data structure.
 */
 
public class AdvObject{ 
 
/**
 * Returns the object name, which is the word used to refer to it.
 *
 * @return The name of the object
 */
    public String getName() {
        return objectName;
    }
 
/**
 * Returns the one-line description of the object.  This description should
 * start with an article, as in "a set of keys" or "an emerald the size of
 * a plover's egg."
 *
 * @return The description of the object
 */
    public String getDescription() {
        return objectDescription;
    }
 
/**
 * Returns the initial location of the object.  If this method returns 0,
 * that is taken to mean that the player is holding it.
 *
 * @return The room number in which the object initially resides
 */
    public int getInitialLocation() {
        return objectRmNumber;
    }
 
/**
 * Creates a new object by reading its data from the specified reader.
 * If no data is left in the reader, this method returns <code>null</code>
 * instead of an <code>AdvObject</code> value. 
 */
    public static AdvObject readObject(BufferedReader rd) {
        try {
            AdvObject object = new AdvObject();
            String line = rd.readLine();
            if (line == null) return null;
            object.objectName = line;
            line = rd.readLine();
            if(line == null) return null;
            object.objectDescription = line;
            line = rd.readLine();
            if(line == null) return null;
            object.objectRmNumber = Integer.parseInt(line); 
            rd.readLine(); // read blank space b/w objects
            return object;
        } catch (IOException ex) {
            throw new ErrorException(ex);
        } catch (NumberFormatException ex) {
            throw new ErrorException("Illegal object");
        }
    }
 
/* Private instance variables */   
    private String objectName;
    private String objectDescription;
    private int objectRmNumber;
}
 