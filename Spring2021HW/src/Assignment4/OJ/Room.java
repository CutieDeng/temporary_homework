package Assignment4.OJ;

import java.util.Arrays;

@SuppressWarnings("unused")
public class Room {

    /**
     * Represents room id such as ‘R101’.
     */
    private final String rid;

    /**
     * Represents which library this room is located in. Default: Library.Lynn.
     */
    private Library location = Library.Lynn;

    /**
     * A POSITIVE integer representing the capacity of the room. Default: 3.
     */
    private int capacity = 3;

    /**
     * Represents whether this room has a display. Default: true.
     */
    private boolean hasDisplay = true;

    /**
     * Represents whether this room has a whiteboard. Default: true.
     */
    private boolean hasWhiteboard = true;

    /**
     * Contains SIDs of applicants of each valid single hour.
     * You can use the indices to represent the start time of each order.
     */
    private final String[] applicants = new String[14];

    /**
     * The room constructor.
     * <p>
     *
     * Default value:<p>
     * location : Lynn library. <p>
     * capacity : 3<p>
     * hasDisplay : true<p>
     * hasWhiteboard : true
     * @param rid the room identity.
     */
    public Room(String rid) {
        this.rid = rid;
    }

    /**
     * The room constructor.
     * <p>
     *
     * The room would default has a display screen and a white board.
     * @param rid the room identity.
     * @param location the library of the room.
     * @param capacity the capacity of the room.
     */
    public Room(String rid, Library location, int capacity) {
        this.rid = rid;
        this.location = location;
        this.capacity = capacity;
    }

    /**
     * The room constructor.
     * @param rid the room identity.
     * @param location the library of the room.
     * @param capacity the capacity of the room.
     * @param hasDisplay true if it has a display screen.
     * @param hasWhiteboard true if it has a white board.
     */
    public Room(String rid, Library location, int capacity, boolean hasDisplay, boolean hasWhiteboard) {
        this.rid = rid;
        this.location = location;
        this.capacity = capacity;
        this.hasDisplay = hasDisplay;
        this.hasWhiteboard = hasWhiteboard;
    }

    /**
     * Only return room info and order info from start time to end time
     * (8 \le≤ start \le≤ 21, 9 \le≤ end \le≤ 22). For example,
     * toString(9, 12) returns order info from 09:00 to 12:00.
     * If end is no greater than start or either of them is not in their range, return null.
     * @param start start time.
     * @param end end time.
     * @return the room info and order info from start time to end time.
     */
    public String toString(int start, int end) {
        if (end <= start){
            return null;
        }
        if (end > 22){
            return null;
        }
        if (start < 8){
            return null;
        }
        StringBuilder builder = new StringBuilder(String.format("%s %s, capacity=%d, hasDisplay=%s, hasWhiteboard=%s\n",
                this.location,
                this.rid,
                this.capacity,
                this.hasDisplay,
                this.hasWhiteboard));
        builder.append("|");
        for (int hour = start; hour < end; hour++) {
            builder.append(String.format("%-8s|", String.format("%02d:00", hour)));
        }
        builder.append("\n");
        boolean shown = false;
        for (int hour = start; hour < end; hour++){
            if (applicants[hour-8] == null){
                builder.append("|EMPTY   ");
            }
            else{
                if (shown && applicants[hour - 8].equals(applicants[hour-9])){
                    builder.append("         ");
                }
                else{
                    builder.append(String.format("|%8s", applicants[hour-8]));
                }
            }
            shown = true;
        }
        builder.append("|\n");
        return builder.toString();
    }

    /**
     * Returns the info and all orders in a whole day (from 08:00 to 22:00) of this room object as a string.
     *
     * The returned string includes 3 lines which should strictly follows the rules stated below:
     *
     * The first line contains basic attributes of the room;
     * The second line contains the timetable of one day available for order. Between every two '|'s there are space for 8 characters. Each time interval is represented as xx:00 where xx is the beginning time of this time interval;
     * The third line also follows the same format as the second line. In each interval, put a string "EMPTY" if there is no order. Otherwise, put the SID of the applicant of this reservation. When one applicant made an order of 2 hours, omit the second ‘|’ as the following example.
     * For example, the following string is returned when calling toString():
     * @return the info and all orders in a whole day (from 08:00 to 22:00) of this room object as a string.
     */
    @Override
    public String toString() {
        return toString(8, 22);
    }

    /**
     * All reservation cases only consider the time in one day, that is, you do not need to consider the real time;
     *
     * Reservations are limited to one hour or two hours at a time. Rooms are only available from 08:00 to 22:00;
     *
     * If the sum of numberOfTeammates and 1 is greater than the room’s capacity or the value of numberOfTeammates is negative, this order fails. Notice that teammates do not contain the applicant.
     *
     * If there has been an order for this room object in the selected time interval, that is, the corresponding applicants on this time interval are not null, the order fails;
     *
     * If the applicant has ordered the room before this order, which means the SID has been in the applicants, the order fails;
     *
     * If all rules are followed, the order is successful and the method returns true. If order fails, do nothing but return false.
     *
     * TIPS: the room’s applicantsMust be updated at the same time.
     * @param start the start duration to be booked.
     * @param end the end duration to be booked.
     * @param SID the student identity number.
     * @param numberOfTeammates the number of the teammates will use the room.
     * @return true if book the room well.
     */
    public boolean setApplicant(int start, int end, String SID, int numberOfTeammates){
        if (checkCannotApplicant(start, end, SID, numberOfTeammates)){
            return false;
        }
        if (end - start > 2){
            return false;
        }
        for (int hour = start; hour < end; hour++) {
            applicants[hour-8] = SID;
        }
        return true;
    }

    /**
     * This method will be called to cancel orders of applicant with SID:
     * search the SID from the array ‘applicants’,
     * if SID is found, then reset the related array item(s) as null and returns true,
     * do nothing but return false otherwise.
     * @param SID the student identity number.
     * @return true if remove it well, false if else.
     */
    public boolean removeApplicant (String SID){
        if (SID == null){
            return false;
        }
        boolean successful = false;
        for (String applicant : applicants) {
            if (SID.equals(applicant)) {
                successful = true;
                break;
            }
        }
        if (!successful){
            return false;
        }
        for (int i = 0; i < applicants.length; i++) {
            if (SID.equals(applicants[i])){
                applicants[i] = null;
            }
        }
        return true;
    }

    /**
     * Overrides the equals method, it's used in the contains method in the array list.
     * @param obj the object to compare.
     * @return True if they are both room and has the same room identity.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj.getClass() != Room.class){
            return false;
        }
        return (this.rid.equals(((Room) obj).rid));
    }

    /**
     * It's used to check whether we can apply an applicant for a room.
     * <p>
     *
     * But this method is an accessor method, but not a modifier method.
     * <p>
     *
     * We won't apply the applicant directly, or you can invoke the method
     * {@link Room#setApplicant(int, int, String, int)} to apply the applicant.
     *
     * @param start the start time we can use.
     * @param end the end time we can use.
     * @param SID the student ID.
     *            If the ID is null, we won't check it but just skip this problem.
     * @param numberOfTeammates the number of teammates shouldn't be less than 0.
     * @return True if we can apply an applicant for this room.
     */
    public boolean checkCannotApplicant(int start, int end, String SID, int numberOfTeammates){
        if (start >= end){
            return true;
        }
        if (start < 8){
            return true;
        }
        if (end > 22){
            return true;
        }
        if (numberOfTeammates < 0 || numberOfTeammates >= this.capacity){
            return true;
        }
        for (int hour = start; hour < end; hour++) {
            if (applicants[hour - 8] != null) {
                return true;
            }
        }
        return SID != null && Arrays.asList(applicants).contains(SID);
    }

    /**
     * @return True if the room has a display screen.
     */
    public boolean isHasDisplay() {
        return hasDisplay;
    }

    /**
     * @return True if the room has a white board.
     */
    public boolean isHasWhiteboard() {
        return hasWhiteboard;
    }

    /**
     * @return the room location library.
     */
    public Library getLocation() {
        return location;
    }

    /**
     * @return the room ID.
     */
    public String getRid() {
        return rid;
    }
}
