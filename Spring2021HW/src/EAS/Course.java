import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class Course extends CutieIdentity {
    private static int idCnt = 0;// number of courses created
    private final int id = ++idCnt;//generated automatically from 1

    public final String code; // CS102A
    public final String name; // Introduction to Computer Programming A
    private String abbrevName;// JavaA
    private Teacher teacher;
    private int capacity;// maxium number of students
    public CourseType type;//Lecture, Lab
    private CourseTime time;
    private Classroom room;
    private List<Student> students = new ArrayList<>();// who selected this course

    @Necessary
    public Course(String code, String name, String abbrevName, Teacher teacher, int capacity, CourseType type) {
        this.code = code;
        this.name = name;
        this.abbrevName = abbrevName;
        this.teacher = teacher;
        this.capacity = capacity;
        this.type = type;
        if (code == null || name == null || teacher == null || type == null) {
            exceptions.add(String.format("When construct the Course, you set some properties with null pointers" +
                            "{cid=%d code=%s name=%s teacher_cid=%s type=%s}.",
                    this.cutieID, this.code, this.name, (this.teacher == null ? "null" : this.teacher.cutieID),
                    this.type));
        }
        if (capacity <= 0) {
            exceptions.add(String.format("When construct the Course{cid=%d}, you set the capacity = %d!",
                    this.cutieID, this.capacity));
        }
    }

    @Necessary
    public Course(String code, String name, String abbrevName, Teacher teacher, int capacity, CourseType type, CourseTime time, Classroom room) {
        this(code, name, abbrevName, teacher, capacity, type);
        this.time = time;
        this.room = room;
        if (time == null || room == null) {
            exceptions.add(String.format("When construct the Course, you set some properties with null pointers" +
                            "{cid=%d time=%s room_cid=%s}.",
                    this.cutieID, this.time, (this.room == null ? "null" : this.room.cutieID)));
        }
    }

    /**
     * This method would get the abbreviated name of the course.
     * <p>
     * If you haven't set it, it would be generated from the complete name.
     *
     * @return the abbrev name.
     */
    @Necessary
    public String getAbbrevName() {
        if (this.abbrevName == null || this.abbrevName.equals("")) {
            StringBuilder builder = new StringBuilder();
            for (char c : this.name.toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    builder.append(c);
                }
            }
            // wait to protect the information in the complete name.
            this.abbrevName = builder.toString();
        }
        return abbrevName;
    }

    @Necessary
    public void setAbbrevName(String abbrevName) {
        if (abbrevName == null) {
            exceptions.add(String.format("you set the abbrevName of Course{cid=%d} as null.", this.cutieID));
        } else if (abbrevName.equals("")) {
            exceptions.add(String.format("you set the abbrevName of Course{cid=%d} as empty string.", this.cutieID));
        }
        this.abbrevName = abbrevName;
    }

    /**
     * This method would make the abbrev name null.
     * <p>
     * I don't know it's usage. So I would record it.
     */
    @Necessary
    public void setAbbrevName() {
        this.abbrevName = null;
        exceptions.add(String.format("You use the setAbbrevName null method for Course{cid=%d}.", this.cutieID));
    }

    /**
     * This method would get the time of the course.
     * <p>
     * If you haven't set the time but get it at once, you would be recorded.
     *
     * @return the course time you get from the course.
     */
    @Necessary
    public CourseTime getTime() {
        if (this.time == null) {
            exceptions.add(String.format("You get the time (not initialized) from Course{cid=%d}.", this.cutieID));
        }
        return this.time;
    }

    /**
     * This method would get the teacher of the course.
     * <p>
     * If you haven't set the teacher but get it at once, you would be recorded.
     *
     * @return the teacher you get from the course.
     */
    @Necessary
    public Teacher getTeacher() {
        if (this.teacher == null) {
            exceptions.add(String.format("You get the teacher (not initialized) of Course{cid=%d}.", this.cutieID));
        }
        return teacher;
    }

    /**
     * I suggest you to set the property, teacher of the course to a not-null instance.
     * <p>
     * If you set the teacher in an unexpected situation, you would be recorded.
     *
     * @param teacher the teacher you set as the course.
     */
    @Necessary
    public void setTeacher(Teacher teacher) {
        Teacher originalTeacher = this.teacher;
        this.teacher = teacher;
        if (this.teacher != null && originalTeacher == null) {
            return;
        }
        exceptions.add(String.format("You set teacher{cid=%s} for the course{cid=%d teacher_cid=%s}.",
                (this.teacher == null ? "null" : this.teacher.cutieID), this.cutieID,
                (originalTeacher == null ? "null" : originalTeacher.cutieID)));
    }

    /**
     * You can use this method to get the classroom of the course.
     * <p>
     * If you get the null value from it, I would record it.
     *
     * @return The classroom of the course.
     */
    @Necessary
    public Classroom getRoom() {
        if (this.room == null) {
            exceptions.add(String.format("You get the classroom(null) of the course{cid=%d}.", this.cutieID));
        }
        return room;
    }

    /**
     * To set the time and classroom for the course.
     * If the course is created by constructor without classroom and time, this method is needed.
     *
     * @param room the room of the course.
     * @param time the time of the course.
     */
    @Necessary
    public void setRoomTime(Classroom room, CourseTime time) {
        // todo: fix the problem.
        if (this.room != null || this.time != null || room == null || time == null) {
            exceptions.add(String.format("You set properties{room_id=%s time=%s} for the course{cid=%d room_id=%s time=%s}.",
                    (room == null ? "null" : room.cutieID), time, this.cutieID, (this.room == null ? "null" : this.room.cutieID), this.time));
        }
        this.room = room;
        this.time = time;
    }

    /**
     * This method would like to get the capacity of the course.
     * <p>
     * I don't suggest you to get it, if the capacity is negative, you would be recorded.
     *
     * @return the capacity of the course.
     */
    @Necessary
    public int getCapacity() {
        if (this.capacity <= 0) {
            exceptions.add(String.format("You get the capacity = %d of the course{cid=%d}.", capacity, this.cutieID));
        }
        return capacity;
    }

    /**
     * This method can set the capacity of the course.
     * <p>
     * Don't use it readily, or I would record it when you set it confused.
     *
     * @param capacity the capacity you want to set.
     */
    @Necessary
    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            exceptions.add(String.format("You set the capacity = %d for the course{cid=%d}.", capacity, this.cutieID));
        }
        if (this.students != null && capacity < this.students.size()) {
            exceptions.add(String.format("You set the capacity = %d for the course{cid=%d student_count=%d}.", capacity, this.cutieID, this.students.size()));
        }
        this.capacity = capacity;
    }

    /**
     * To add a student to the list of those students who choose the course.
     * <p>
     * If the student is already in the list, it returns false to add into the list.
     * <p>
     * Otherwise, the student would be added to the list and it returns true.
     *
     * @param student the student would be added in the class.
     * @return True if he would be added to class.
     */
    @Necessary
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        if (this.students.contains(student)) {
            return false;
        }
        if (this.students.size() >= this.capacity) {
            exceptions.add(
                    String.format("You add the student{cid=%d} in the course{cid=%d} list, but the course list is overflowed.",
                    student.cutieID, this.cutieID));
        }
        this.students.add(student);
        return true;
    }

    /**
     * To delete a student from the list of those students who choose the course.
     * If the student is already in the list, it returns true to delete from the list.
     * Otherwise, it returns false.
     *
     * @param student the student who would be deleted from the class.
     * @return True if the student has been deleted.
     */
    @Necessary
    public boolean deleteStudent(Student student) {
        return this.students.remove(student);
    }

    public String toStringForTeacher() {
        return String.format("%s, %s, %s", this.code, this.getAbbrevName(), this.room.toString());
    }

    public String toStringForClassroom() {
        return String.format("%s, %s, %s", this.code, this.getAbbrevName(), this.teacher.name);
    }

    public String toStringForStudent() {
        return String.format("%s, %s, %s, %s", this.code, this.getAbbrevName(), this.teacher.name, this.room.toString());
    }

    public void setType(CourseType type) {
        if (type == null || this.type != null) {
            exceptions.add(String.format("You set a course{cid=%d type=%s}'s type as %s.    ",
                    this.cutieID, this.type, type));
        }
        this.type = type;
    }

    public void setTime(CourseTime time) {
        if (time == null || this.time != null) {
            exceptions.add(String.format("You set a course{cid=%d time=%s}'s time as %s.",
                    this.cutieID, this.time, time));
        }
        this.time = time;
    }

    public CourseType getType() {
        return type;
    }

    public List<Student> getStudents() {
        return students;
    }
}
