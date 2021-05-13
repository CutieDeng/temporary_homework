import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class Time extends CutieIdentity{
    /**
     * 0~6 means the time in Monday.
     * <p>
     * and so on.
     */
    protected final Course[] coursesSchedule = new Course[35];

    private static Field dayField;
    private static Field timeField;

    static {
        try {
            dayField = CourseTime.class.getDeclaredField("day");
            timeField = CourseTime.class.getDeclaredField("time");
        } catch (NoSuchFieldException e) {
            dayField = null;
            timeField = null;
        }
    }

    /**
     * Use reflect to get the value of the course time.
     *
     * @param time the object which is CourseTime.
     * @return the value which can map in the Course array.
     */
    protected static int getTimeNumber(CourseTime time) {
        try {
            dayField.setAccessible(true);
            int dayNumber = ((Day) dayField.get(time)).value;
            timeField.setAccessible(true);
            int timeNumber = (Integer) timeField.get(time);
            if (dayNumber < 0 || dayNumber >= 7) {
                throw new RuntimeException("weekday = " + (dayNumber + 1));
            }
            if (timeNumber < 1 || timeNumber > 5) {
                throw new RuntimeException("time in day = " + timeNumber);
            }
            return timeNumber - 1 + 5 * dayNumber;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * This method would add a course in the schedule.
     * <p>
     *
     * @param course the course you want to add in the schedule.
     */
    protected void simpleAddCourse(Course course) {
        coursesSchedule[getTimeNumber(course.getTime())] = course;
    }

    /**
     * This method would add a course in the time of schedule.
     * @param time the time of the schedule.
     * @param course the course we set in the schedule.
     */
    protected void simpleAddCourse(CourseTime time, Course course) {
        coursesSchedule[getTimeNumber(time)] = course;
    }

    /**
     * This method is used to check the corresponding time in the timetable.
     * <p>
     * If there exists some courses or activities to do, it would return false, means
     * you can't add the course in it.
     * <p>
     * Otherwise, it would return true.
     *
     * @param course the course you want to examine whether to add in the time table or not.
     * @return True if you are permitted to add in the time table.
     */
    protected boolean checkCorrespondingTime(Course course) {
        if (course == null) {
            return false;
        }
        int index = getTimeNumber(course.getTime());
        return coursesSchedule[index] == null;
    }

    /**
     * This method would find the course occurrence time in the time schedule.
     * <p>
     * If you set the parameter course "null", you can find the free time in the schedule.
     *
     * @param course the course you want to ensure whether in the course table, and how many times it shows up.
     * @return the time it shows up.
     */
    protected int findCorrespondingCourse(Course course) {
        int cnt = 0;
        for (Course course1 : coursesSchedule) {
            if (course1 == course) {
                cnt++;
            }
        }
        return cnt;
    }

    protected void deleteCorrespondingCourse(Course course) {
        for (int i = 0; i < coursesSchedule.length; i++) {
            if (coursesSchedule[i] == course) {
                coursesSchedule[i] = null;
            }
        }
    }

    protected Course getCorrespondingCourse(CourseTime time) {
        return coursesSchedule[getTimeNumber(time)];
    }

    public static void main(String[] args) {
        System.out.println(getTimeNumber(new CourseTime(Day.Sunday, 5)));
    }
}
