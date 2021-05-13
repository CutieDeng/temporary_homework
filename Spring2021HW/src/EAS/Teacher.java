import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Teacher extends Person implements CourseOperator {

    @Necessary
    private Location preferLocation;

    @Necessary
    public Teacher() {
    }

    @Necessary
    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * To the get available classrooms from the classroom list in <strong>Db.buildings</strong>.
     * <p>
     * According to the teacher’s prefer location and it returns only the classrooms in the prefer location.
     * <p>
     * If there is no available classrooms in prefer location. It returns classrooms in other locations.
     * <p>
     * It returns an empty list if there isn’t any available classroom for the course.
     *
     * @param time     the time we want to find free classrooms.
     * @param capacity the capacity we acquire for the classrooms.
     * @param type     the type of the classroom we need.
     * @return the list about the classrooms available.
     */
    @Necessary
    public List<Classroom> getFreeClassroom(CourseTime time, int capacity, CourseType type) {
        List<Classroom> preferredFreeClassrooms = new ArrayList<>();
        List<Classroom> notPreferredFreeClassrooms = new ArrayList<>();
        for (Building building : Db.buildings) {
            List<Classroom> now;
            if (this.preferLocation != null && this.preferLocation.equals(building.getLocation())) {
                now = preferredFreeClassrooms;
            } else {
                now = notPreferredFreeClassrooms;
            }
            if (building.getRooms() != null) {
                List<Classroom> collect = building.getRooms().stream().filter(room -> room != null && room.seatNum >= capacity &&
                        room.getCourse(time) == null && room.type.equals(type)).collect(Collectors.toList());
                now.addAll(collect);
            }
        }
        if (preferredFreeClassrooms.size() > 0) {
            return preferredFreeClassrooms;
        } else {
            return notPreferredFreeClassrooms;
        }
    }

    /**
     * A course with course time and classroom could be created successfully only the classroom
     * is empty at the course time, and the course type is the same as the classroom’s courseType,
     * and the capacity of course is not more than the seat numbers.
     * Then this course will be added to the teacher’s schedule and to the classroom’s schedule.
     * Otherwise the course can not be in the classroom, return false and do not add the the schedule.
     *
     * @param course the course waited to be added.
     * @return true if it will be added to the teacher's schedule and to the classrom's schedule.
     */
    @Necessary
    public boolean createCourse(Course course) {
        if (course == null) {
            return false;
        }
        Classroom room = course.getRoom();
        if (room == null) {
            return false;
        }
        String s = room.addCourse(course);
        if (!s.equals("OK: Adding course to classroom success.")) {
            return false;
        }
        if (super.getCorrespondingCourse(course.getTime()) != null) {
            if (!room.deleteCourse(course)) {
                exceptions.add(String.format("Exception: we succeed to add the course{cid=%d} in the classroom{cid=%d}, " +
                        "but delete it from the classroom failing.", course.cutieID, room.cutieID));
            }
            return false;
        } else {
            if (super.findCorrespondingCourse(course) > 0) {
                exceptions.add(String.format("You add the same course{cid=%d} in the teacher{cid=%d}," +
                        " but at different points of time.", course.cutieID, this.cutieID));
            }
        }
        if (course.getTeacher() != this) {
            exceptions.add(String.format("You add the course{cid=%s teacher_cid=%s} for the teacher{cid=%d}.",
                    course.cutieID, (course.getTeacher() == null ? "null" : course.getTeacher().cutieID),
                    this.cutieID));
        }
        this.simpleAddCourse(course);
        return true;
    }

    @Necessary
    public boolean createCourse(String code, String name, String abbrevName,
                                CourseTime time, Classroom room, int capacity, CourseType type) {
        Course course = new Course(code, name, abbrevName, this, capacity, type, time, room);
        exceptions.add(String.format("[USAGE]We invoke the method create course with the new instance" +
                "{cid=%d}", course.cutieID));
        return createCourse(course);
    }


    /**
     * If the course is not already on the schedule of the teacher, return false.
     * <p>
     * Else remove the selected course in teacher’s schedule and classroom’s schedule, return true.
     * <p>
     * If you set the course null, you would get false, too.
     *
     * @param course the course we want to drop from the teacher and the classroom.
     * @return true if we drop it, false if we can't find it in the schedule of teacher.
     */
    @Necessary
    public boolean dropCourse(Course course) {
        if (course == null) {
            return false;
        }
        Classroom room = course.getRoom();
        if (room.getCourse(course.getTime()) != course) {
            return false;
        }
        room.deleteCourse(course);
        super.simpleAddCourse(course.getTime(), null);
        if (super.findCorrespondingCourse(course) > 0) {
            exceptions.add(String.format("Teacher{cid=%d} drop the course{cid=%d}, but hasn't dropped all in his" +
                            " course schedule.",
                    this.cutieID, course.cutieID));
        }
        return true;
    }

    @Necessary
    public boolean changeCourse(Course oldCourse1, Course newCourse2) {
        if (!Objects.equals(oldCourse1.code, newCourse2.code) ||
                !Objects.equals(oldCourse1.type, newCourse2.type)) {
                    return false;
                }
        if (!this.dropCourse(oldCourse1)) {
            return false;
        }
        if (!this.createCourse(newCourse2)) {
            if (!this.createCourse(oldCourse1)) {
                exceptions.add(String.format("[EXCEPTION]: Drop the original course{cid=%s} but cannot restore it well.",
                        (oldCourse1 == null ? "null" : oldCourse1.cutieID)));
            }
            return false;
        }
        return true;
    }

    @Necessary
    public String printSchedule() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append("'s Schedule\n");
        for (Day value : Day.values()) {
            builder.append(value).append("\n");
            for (int i = 1; i < 6; i++) {
                Course course = this.schedule.get(new CourseTime(value, i));
                builder.append(i);
                if (course != null) {
                    builder.append(" ").append(course.toStringForTeacher());
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public int getScheduleCourseNum() {
        return 35 - super.findCorrespondingCourse(null);
    }

    /**
     * This method would get the prefer location of the teacher.
     * <p>
     * You would be recorded, when you get a null pointer.
     *
     * @return the prefer location of the teacher.
     */
    @Necessary
    public Location getPreferLocation() {
        if (this.preferLocation == null) {
            exceptions.add(String.format("You get the preferLocation of the teacher{cid=%d}, which is null.", this.cutieID));
        }
        return this.preferLocation;
    }

    /**
     * @param preferLocation the prefer location you want to set for the teacher.
     */
    @Necessary
    public void setPreferLocation(Location preferLocation) {
        if (this.preferLocation != null || preferLocation == null) {
            exceptions.add(String.format("You set the preferLocation{%s} of the teacher{cid=%d prefer location=%s}.",
                    preferLocation, this.cutieID, this.preferLocation));
        }
        this.preferLocation = preferLocation;
    }

}
