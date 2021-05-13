package Assignment4.OJ;

import java.util.ArrayList;
import java.util.Comparator;

@SuppressWarnings("unused")
public class RoomManager {
    private final static ArrayList<Room> roomInfoLynn = new ArrayList<>();
    private final static ArrayList<Room> roomInfoYidan = new ArrayList<>();
    private final static ArrayList<Room> roomInfoLearningNexus = new ArrayList<>();

    /**
     * Create a room with rid, location and capacity specified and add it to the room list of given library.
     * Other attributes of the room should be assigned by default values as stated in 2.1.
     * @param rid room identity.
     * @param location the location of the room.
     * @param capacity the capacity of the room.
     * @return true if it's created.
     */
    public static boolean addRoom (String rid, Library location, int capacity){
        return addRoom(rid, location, capacity, true, true);
    }

    /**
     * Create a room with rid, location, capacity, hasDisplay and hasWhiteboard specified and add it
     * to the room list of given library which is specified by library.
     * Other attributes of the room should be assigned by default values as stated in 2.1.
     * @param rid room identity number.
     * @param library the library in the room.
     * @param capacity the capacity of the room.
     * @param hasDisplay the display?
     * @param hasWhiteboard whether the whiteboard in the room.
     * @return true if the room can be added.
     */
    public static boolean addRoom (String rid, Library library, int capacity, boolean hasDisplay, boolean hasWhiteboard){
        if (rid == null || library == null){
            return false;
        }
        if (!rid.matches("R[1-9][0-9]{2}")){
            return false;
        }
        if (capacity <= 0){
            return false;
        }
        Room room = new Room(rid, library, capacity, hasDisplay, hasWhiteboard);
        ArrayList<Room> rooms = getRoomsFromLibrary(library);
        if (rooms == null){
            return false;
        }
        if (rooms.contains(room)){
            return false;
        }
        rooms.add(room);
        return true;
    }

    /**
     * Get a room from the specific library with the certain room ID.
     * @param library the library we want to search.
     * @param rid the certain room id we determine to search for.
     * @return the room we ask.
     */
    private static Room getRoomFromLibrary(Library library, String rid){
        ArrayList<Room> rooms = getRoomsFromLibrary(library);
        if (rooms == null){
            return null;
        }
        for (Room room : rooms) {
            if (room.getRid().equals(rid)){
                return room;
            }
        }
        return null;
    }

    /**
     * Someone SID orders a room rid in a library from start time to end time with numberOfTeammates teammates together.
     * Each user can only order one room in each library (but could order up to 3 rooms in 3 different libraries.
     * These orders may even happen at the same time, such as an online meeting using rooms in different libraries).
     * But one user CANNOT order two rooms in one library as stated in 2.4.
     * Return false if no room has room id equaling to rid.
     * Other rules are stated in 2.4;
     * We ensure that library and SID are valid;
     * If all rules are followed, the order is successful and the method returns true.
     * Make sure the room with rid update its attributes according to this order.
     * @param library the library of the room.
     * @param rid the room id.
     * @param SID the student id.
     * @param start the start time of the book.
     * @param end the end time of the book.
     * @param numberOfTeammates the number of the teammates.
     * @return true if we book it successfully, false if else.
     */
    public static boolean orderRoom (Library library, String rid, String SID, int start, int end, int numberOfTeammates){
        Room room = getRoomFromLibrary(library, rid);
        if (room == null){
            return false;
        }
        return room.setApplicant(start, end, SID, numberOfTeammates);
    }

    /**
     * This method is used to cancel all room book from a specific student.
     * @param SID the student identity.
     * @return true if we can cancel some orders from it.
     */
    public static boolean cancelOrder (String SID){
        return cancelOrder(SID, Library.Lynn) | cancelOrder(SID, Library.Yidan) | cancelOrder(SID, Library.LearningNexus);
    }

    /**
     * If no order is to be canceled, return false.
     * Otherwise cancel all orders in the library specified by location for applicant with student id equaling SID.
     * TIPS： the related attributes of rooms MUST be updated.
     * @param SID the id of the student.
     * @param location the library we need to kill.
     * @return True if we did it, false if otherwise.
     */
    public static boolean cancelOrder (String SID, Library location){
        ArrayList<Room> list = getRoomsFromLibrary(location);
        if (list == null){
            return false;
        }
        if (SID == null){
            return false;
        }
        boolean out = false;
        for (Room room : list) {
            out = out | room.removeApplicant(SID);
        }
        return out;
    }

    /**
     * This method is used to get the rooms array list from the specific library as the
     * parameter.
     * @param library the library of the rooms you ask.
     * @return the room array list.
     */
    private static ArrayList<Room> getRoomsFromLibrary(Library library){
        switch (library){
            case LearningNexus:
                return roomInfoLearningNexus;
            case Lynn:
                return roomInfoLynn;
            case Yidan:
                return roomInfoYidan;
        }
        return null;
    }

    /**
     * Search a list of rooms with the need of display(identified by hasDisplay) and whiteboard
     * (identified by hasWhiteboard) which are available for order from start time to end time in library location.
     * You can still get rooms with display even though you set needDisplay as false.
     * However, you cannot get rooms without display if you set needDisplay as true.
     * For needWhiteboard, it is the same.
     * @param location library we need to examine.
     * @param start the start time we need to use.
     * @param end the end time we need to use.
     * @param needDisplay need Display.
     * @param needWhiteboard need Whiteboard.
     * @return the room list.
     */
    public static ArrayList<Room> searchRoom (Library location, int start, int end, boolean needDisplay, boolean needWhiteboard){
        ArrayList<Room> rooms = getRoomsFromLibrary(location);
        ArrayList<Room> ans = new ArrayList<>();
        if (rooms == null){
            return ans;
        }
        for (Room room : rooms) {
            if (room.checkCannotApplicant(start, end, null, 0)){
                continue;
            }
            if (needDisplay && (!room.isHasDisplay())){
                continue;
            }
            if (needWhiteboard && (!room.isHasWhiteboard())){
                continue;
            }
            ans.add(room);
        }
        return ans;
    }

    /**
     * The default comparator is to compare different room identity to sort them.
     */
    private static final Comparator<Room> comparator = (o1, o2) -> {
        int v1 = Integer.parseInt(o1.getRid().substring(1));
        int v2 = Integer.parseInt(o2.getRid().substring(1));
        return v1 - v2;
    };
    /**
     * The returned rooms should be in order.
     * First sort rooms in order of libraries
     * (For landmark specified, follow its priority: priority[0], priority[1], and so forth.
     * If the landmark is not specified, use default order: Yidan, Lynn, LearningNexus).
     * @param start the start time
     * @param end the end time.
     * @return the room list.
     */
    public static ArrayList<Room> searchRoom (int start, int end){
        ArrayList<Room> test = searchRoom(Library.Yidan, start, end, false, false);
        test.sort(comparator);
        ArrayList<Room> ans = test;
        test = searchRoom(Library.Lynn, start, end, false, false);
        test.sort(comparator);
        ans.addAll(test);
        test = searchRoom(Library.LearningNexus, start, end, false, false);
        test.sort(comparator);
        ans.addAll(test);
        return ans;
    }

    /**
     * This method is used to search the room to use, sorting by the landmark.
     * @param start the start time we want to ask.
     * @param end the end time we want to ask.
     * @param landmark the specific landmark to sort the rooms.
     * @return the room list.
     */
    public static ArrayList<Room> searchRoom (int start, int end, Landmark landmark){
        ArrayList<Room> rooms = searchRoom(start, end);
        rooms.sort((o1, o2) -> {
            Library[] priority = landmark.getPriority();
            Library o1Location = o1.getLocation();
            Library o2Location = o2.getLocation();
            int valueO1 = -1;
            int valueO2 = -1;
            for (int i = 0; i < priority.length; i++) {
                if (o1Location == priority[i]){
                    valueO1 = i;
                }
                if (o2Location == priority[i]){
                    valueO2 = i;
                }
            }
            if (valueO1 < 0 || valueO2 < 0){
                throw new IndexOutOfBoundsException();
            }
            if (valueO1 == valueO2){
                return comparator.compare(o1, o2);
            }
            return valueO1 - valueO2;
        });
        return rooms;
    }

    /**
     * Used to get the string of the search result from 3.5,
     * including the info and order info of all the rooms in this list.
     * If list is empty which means it’s size is 0, return "No room to show.".
     * @param list the list of the rooms.
     * @return the info string.
     */
    public static String showOrder (ArrayList<Room> list){
         StringBuilder builder = new StringBuilder();
         if (list == null || list.size() == 0){
             return "No room to show.";
         }
        for (Room room : list) {
            builder.append(room.toString());
        }
        return builder.toString();
    }

    /**
     * Just get the total rooms from all libraries.
     * @return the total room list.
     */
    public static ArrayList<Room> getList() {
        ArrayList<Room> out = new ArrayList<>();
        out.addAll(roomInfoLynn);
        out.addAll(roomInfoYidan);
        out.addAll(roomInfoLearningNexus);
        return out;
    }

}
