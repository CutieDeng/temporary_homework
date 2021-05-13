import java.util.Map;

@SuppressWarnings("unused")
public class Classroom extends Time {

    @Necessary
    public final int id;//eg:101
    @Necessary
    public final int seatNum;//eg:50
    @Necessary
    private Building building;
    @Necessary
    CourseType type;// Lecture or Lab

    /**
     * schedule is the unused thing.
     */
    @SuppressWarnings("unused")
    @Necessary
    private Map<CourseTime, Course> schedule;

    @Necessary
    public Classroom(int id, int seatNum, Building building, CourseType type) {
        this.id = id;
        this.seatNum = seatNum;
        this.building = building;
        this.type = type;
    }

    /**
     * OK: Adding course to classroom success.
     * ERROR: Another course already exists at the time.
     * ERROR: Course type not same as classroom.
     * ERROR: Not enough seats in the classroom for this course.
     *
     * @param course the course you want to add in the time schedule.
     * @return the String above.
     */
    @Necessary
    public String addCourse(Course course) {
        try {
            if (!super.checkCorrespondingTime(course)) {
                return "ERROR: Another course already exists at the time.";
            }
        } catch (RuntimeException ignored) {
        }
        if (course.type != this.type) {
            return "ERROR: Course type not same as classroom.";
        }
        if (course.getCapacity() > this.seatNum) {
            return "ERROR: Not enough seats in the classroom for this course.";
        }
        if (course.getRoom() != this) {
            exceptions.add(String.format("You add a course{cid=%d Classroom_cid=%s} in the classroom{cid=%d}.",
                    course.cutieID, (course.getRoom() == null ? "null" : course.getRoom().cutieID), this.cutieID));
        }
        super.simpleAddCourse(course);
        return "OK: Adding course to classroom success.";
    }

    /**
     * If you set a null parameter, or the course wouldn't happen in the classroom, you would get false.
     * <p>
     * Otherwise, you would get true.
     *
     * @param course the course you want to delete from the classroom.
     * @return It will be true, if the course is in the schedule of the classroom and delete it from the schedule.
     */
    @Necessary
    public boolean deleteCourse(Course course) {
        if (course == null) {
            return false;
        }
        if (super.getCorrespondingCourse(course.getTime()) == course) {
            super.simpleAddCourse(course.getTime(), null);
        }
        else {
            return false;
        }
        if (super.findCorrespondingCourse(course) > 0) {
            exceptions.add(String.format("You delete the course{cid=%d} from the classroom{cid=%d}, but you haven't deleted " +
                    "all corresponding courses.", course.cutieID, this.cutieID));
        }
        return true;
    }

    /**
     * You would directly get the corresponding course from the course time.
     *
     * @param courseTime The time you ask.
     * @return The course you would get, may be null.
     */
    public Course getCourse(CourseTime courseTime) {
        return super.getCorrespondingCourse(courseTime);
    }

    /**
     * To print the schedule of a classroom as follow to the returned string:
     * <p>
     * Classroom.toString() Schedule
     * Monday
     * 1 Course.code, Course.abbrevName, [Teacher.name]
     * 2
     * 3 Course.code, Course.abbrevName, [Teacher.name]
     * 4 Course.code, Course.abbrevName, [Teacher.name]
     * 5
     * Tuesday
     * …
     * Sunday
     * …
     * <p>
     * Notice:
     * <p>
     * If there is not a course at the time slot, it may print just the
     * number and blank in a line in every weekdays.
     *
     * @return the format of string such as above.
     */
    public String printSchedule() {
        StringBuilder builder = new StringBuilder();
        builder.append(this).append(" Schedule\n");
        for (Day value : Day.values()) {
            builder.append(value).append("\n");
            for (int i = 1; i < 6; i++) {
                Course course = getCourse(new CourseTime(value, i));
                builder.append(i).append(" ");
                if (course != null) {
                    builder.append(course.toStringForClassroom());
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public int getScheduleCourseNum() {
        return 35 - findCorrespondingCourse(null);
    }

    public Building getBuilding() {
        if (this.building == null) {
            exceptions.add(String.format("You attempt to get the building of classroom{cid=%d building=null}",
                    this.cutieID));
        }
        return this.building;
    }

    @Override
    public String toString() {
        if (this.type == null || this.building == null) {
            exceptions.add(
                    String.format("You attempt to get the String from Classroom{cid=%d type=%s id=%d seatNum=%d building=%s}",
                            this.cutieID, this.type, this.id, this.seatNum, this.building));
        }
        return String.format("%sR%d(%d)%s", this.type,
                this.id,
                this.seatNum,
                this.building);
    }

    public void setType(CourseType type) {
        if (type == null || this.type != null) {
            exceptions.add(String.format("You set a classroom{cid=%d type=%s}'s type as %s.",
                    this.cutieID, this.type, type));
        }
        this.type = type;
    }
}
