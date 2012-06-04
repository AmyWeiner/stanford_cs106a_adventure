/*
 * File: AdvRoom.java
 * ------------------
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

public class AdvRoom extends AdvRoomMagicSuperclass {

	////////////////////////////////////////////////////////////////////
	// To complete the assignment, you need to remove the superclass  //
	// designation and implement the methods that are commented out   //
	// in this file.                                                  //
	////////////////////////////////////////////////////////////////////

/**
 * Returns the room number.
 *
 * @return The room number
 */
//	public int getRoomNumber() {
//		// You write this part
//	}

/**
 * Returns the room name, which is its one-line description.
 *
 * @return The room name
 */
//	public String getName() {
//		// You write this part
//	}

/**
 * Returns an array of strings that correspond to the long description
 * of the room.
 *
 * @return An array of strings giving the long description of the room
 */
//	public String[] getDescription() {
//		// You write this part
//	}

/**
 * Adds an object to the list of objects in the room.
 *
 * @param The AdvObject to be added
 */
//	public void addObject(AdvObject obj) {
//		// You write this part
//	}

/**
 * Removes an object from the list of objects in the room.
 *
 * @param The AdvObject to be removed
 */
//	public void removeObject(AdvObject obj) {
//		// You write this part
//	}

/**
 * Checks whether the specified object is in the room.
 *
 * @param The AdvObject being tested
 * @return true if the object is in the room, and false otherwise
 */
//	public boolean containsObject(AdvObject obj) {
//		// You write this part
//	}

/**
 * Returns an array of all the objects in the room.
 *
 * @return An array containing the objects in the room
 */
//	public AdvObject[] getObjects() {
//		// You write this part
//	}

/**
 * Sets the flag indicating that this room has been visited as specified
 * by the value of the parameter flag.  Calling setVisited(true) means
 * that the room has been visited; calling setVisited(false) restores
 * its initial state.
 *
 * @param flag The new state of the "visited" flag
 */
//	public void setVisited(boolean flag) {
//		// You write this part
//	}

/**
 * Returns true if the room has previously been visited.
 *
 * @return true if the room has previously been visited; false otherwise
 */
//	public boolean hasBeenVisited() {
//		// You write this part
//	}

/**
 * Returns the motion table associated with this room, which is an
 * array of directions, room numbers, and enabling objects stored
 * in a AdvMotionTableEntry.
 *
 * @return The array of motion table entries associated with this room
 */
//	public AdvMotionTableEntry[] getMotionTable() {
//		// You write this part
//	}

/**
 * Creates a new room by reading its data from the specified reader.
 * If no data is left in the reader, this method returns <code>null</code>
 * instead of an <code>AdvRoom</code> value.  Note that this is a
 * static method, which means that you need to call
 *
 *<pre><code>
 *     AdvRoom.readRoom(rd)
 *</code></pre>
 *
 * @param rd The reader from which the room data is read
 */
//	public static AdvRoom readRoom(BufferedReader rd) {
//		// You write this part
//	}

}
