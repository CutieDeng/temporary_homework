import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unused")
public class Student extends Person implements CourseOperator {
    @Necessary
    public Student() {
        exceptions.add(String.format("You set the student{cid=%d} without id and name!", this.cutieID));
    }

    @Necessary
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * This method would check the course
     * @param code
     * @param name
     * @param type
     * @return
     */
    @Necessary
    public boolean courseExist(String code, String name, CourseType type) {
        return Arrays.stream(coursesSchedule).anyMatch(
                someCourse -> someCourse != null &&
                        someCourse.type == type &&
                        Objects.equals(someCourse.name, name) &&
                        Objects.equals(someCourse.code, code)
        );
    }

    public boolean courseExist(Course course) {
        if (course == null) {
            return false;
        }
        return super.getCorrespondingCourse(course.getTime()) == course;
    }

    public boolean chooseCourse(Course course) {
        if (courseExist(course.code, course.name, course.type)) {
            return false;
        }
        if (this.schedule.get(course.getTime()) != null) {
            return false;
        }
        if (!course.addStudent(this)) {
            return false;
        }
        this.schedule.put(course.getTime(), course);
        return true;
    }

    @Override
    public boolean dropCourse(Course course) {
        if (!course.deleteStudent(this)) {
            return false;
        }
        this.schedule.remove(course.getTime());
        // dangerous used.
        return true;
    }

    @Override
    public boolean changeCourse(Course oldCourse1, Course newCourse2) {
        if (!Objects.equals(oldCourse1.code, newCourse2.code) ||
                !Objects.equals(oldCourse1.type, newCourse2.type)) {
            return false;
        }
        if (!dropCourse(oldCourse1)) {
            return false;
        }
        if (!chooseCourse(newCourse2)) {
            chooseCourse(oldCourse1);
            return false;
        }
        return true;
    }

    public String printSchedule() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append("'s Schedule\n");
        for (Day value : Day.values()) {
            builder.append(value).append("\n");
            for (int i = 1; i < 6; i++) {
                Course course = this.schedule.get(new CourseTime(value, i));
                builder.append(i);
                if (course != null) {
                    builder.append(" ").append(course.toStringForStudent());
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public int getScheduleCourseNum() {
        return this.schedule.size();
    }
}
