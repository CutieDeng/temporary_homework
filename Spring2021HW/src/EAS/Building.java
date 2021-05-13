import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Building extends CutieIdentity {
    private List<Classroom> rooms = null;
    private Location location = null;
    private int id = 0;
    private boolean isIdSet = false;

    /**
     * The creating order of the Building instance.
     */

    public final static ArrayList<Building> buildings = new ArrayList<>();

    /**
     * the constructor of two arguments, location and id.
     * <p>
     * If you make null the incoming parameter as location, you would be recorded.
     *
     * @param location the location of the building.
     * @param id       the id of the building.
     */
    public Building(Location location, int id) {
        this();
        this.location = location;
        this.id = id;
        isIdSet = true;
        if (location == null) {
            exceptions.add(String.format("When construct Building{cid=%d id=%d}, you give a null location.",
                    this.cutieID, this.id));
        }
    }

    public Building() {
        buildings.add(this);
    }

    /**
     * This method would get the location of the building.
     * <p>
     * It may get null if you haven't initialize it well.
     * <p>
     * And I would record this situation in the exception list.
     *
     * @return the location of the building.
     */
    public Location getLocation() {
        if (location == null) {
            exceptions.add(String.format("Building{cid=%d id=%d room capacity=%d}'s location(null) has been got.",
                    this.cutieID, this.id, (rooms == null ? 0 : rooms.size())));
        }
        return location;
    }

    /**
     * This method can set a location of the building.
     * <p>
     * If you move a building in an unexpected situation, I would record it.
     *
     * @param location you want to move the building to someplace.
     */
    public void setLocation(Location location) {
        Location originLocation = this.location;
        this.location = location;
        if (location != null && originLocation == null) {
            return;
        }
        exceptions.add(String.format("Building{cid=%d id=%d room capacity=%d location=%s} has been changed to the location %s.",
                this.cutieID, this.id, (rooms == null ? 0 : rooms.size()), originLocation, this.location));
    }

    /**
     * This method would get the identity of the building.
     * <p>
     * You would be recorded, if you haven't set it but just get it.
     *
     * @return the id of building.
     */
    public int getId() {
        if (!isIdSet) {
            exceptions.add(String.format("You attempt to get the property id from an uninitialized Building{cid=%d room capacity=%d location=%s}.",
                    this.cutieID, (rooms == null ? 0 : rooms.size()), this.location));
        }
        return id;
    }

    /**
     * You can use this method to set the id of the building.
     * <p>
     * If you have set it, you would be recorded.
     *
     * @param id the id you set as the identity of building.
     */
    public void setId(int id) {
        if (isIdSet) {
            exceptions.add(String.format("You set id = %d to the initialized Building{cid=%d id=%d room capacity=%d location=%s}.",
                    id, this.cutieID, this.id, (rooms == null ? 0 : rooms.size()), this.location));
        }
        isIdSet = true;
        this.id = id;
    }

    /**
     * Add a classroom to the building.
     * <p>
     * If there is a reference attribute of building of classroom, it would be added.
     *
     * @param room the room you believe it should be in the building.
     * @return true if you add it in the building successfully.
     */
    public boolean addRoom(Classroom room) {
        if (room == null) {
            exceptions.add(String.format("You attempt to add a Classroom{null} to the Building{cid=%d id=%d room capacity=%d location=%s}.",
                    this.cutieID, this.id, (rooms == null ? 0 : rooms.size()), this.location));
            return false;
        }
        if (room.getBuilding() != this) {
            return false;
        }
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        if (rooms.contains(room)) {
            return false;
        }
        return rooms.add(room);
    }

    /**
     * Delete a classroom from the building.
     * <p>
     * If you delete a null room in the building, you would get false.
     *
     * @param room the specific room you want to delete.
     * @return true if you delete it successfully.
     */
    public boolean deleteRoom(Classroom room) {
        if (room == null) {
            return false;
        }
        if (room.getBuilding() != this) {
            return false;
        }
        return rooms.remove(room);
    }

    public List<Classroom> getRooms() {
        return rooms;
    }

    /**
     * This method is used to get the simple name of the building.
     * <p>
     * It's composed by the simple name of location and identity.
     *
     * @return the simple name of building.
     */
    @Override
    public String toString() {
        try {
            return String.format("%s#%d", this.location.simpleName, this.id);
        } catch (NullPointerException e) {
            exceptions.add(String.format("You try to get a name of Building{cid=%d id=%d room capacity=%d} whose location is null.",
                    this.cutieID, this.id, (rooms == null ? 0 : rooms.size())));
            return "#" + this.id;
        }
    }

    public void setRooms(List<Classroom> classrooms){
        this.rooms = classrooms;
    }
}
