import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class Person extends Time implements CourseOperator{
    protected String id;
    protected String name;
    protected Map<CourseTime, Course> schedule = new HashMap<>();

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
