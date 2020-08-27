import com.antgroup.exam.condition.EqualCondition;
import com.antgroup.exam.DataQuery;
import com.antgroup.exam.Student;
import com.antgroup.exam.Where;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huwei
 * @date 2020/8/26
 */
public class ApplicationTest {

    @Test
    public void test() {
        System.out.println("hello world!");
        Where where = new Where();
    }

    @Test
    public void testQuery() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("student1", "class1", 10, 1));
        students.add(new Student("student2", "class1", 11, 0));
        students.add(new Student("student3", "class1", 10, 0));
        Where<Student> where = new Where<Student>()
                .and(new EqualCondition<Student>().add("gender", 0))
                .and(new EqualCondition<Student>().add("name", "student1"))
                .or(new EqualCondition<Student>().add("age", 11));
        List<Student> queryResult = DataQuery.query(students, where, "", "", "");
        queryResult.forEach(System.out::println);
    }
}
