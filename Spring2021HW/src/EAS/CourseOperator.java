public interface CourseOperator {
// some common operators of coursees for both teachers and students.
// which may be implemented differently for teachers and students

    boolean dropCourse(Course course);
    // to drop the course and delete it from schedule.
    // There is not necessary to check whether some students have had the ccourse for teachers in this method.

    boolean changeCourse(Course oldCourse1, Course newCourse2);
    // to change oldCourse1 to newCourse2 and update the schedules.
    // A student could change a new course with different time or teacher but with the same course code and type. Both with the same or different teachers are OK. Both with the same or different classrooms are OK. If it returns true, the old course would be deleted from the student's schedule. And the new course would be added to the schedule. If there is not an available course for the student to choose at the time, it returns false and dose not change the schedule.
    // A teacher could change his course time and classroom. There must be the same course code and type and teacher. If the classroom is available at the new time and the teacher is free, it returns true. Otherwise it returns false. If it returns true, the old course would be deleted from the teacher's schedule and classroom's schedule, and the new one would be added in.
}
