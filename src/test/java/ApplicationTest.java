import com.antgroup.exam.DataQuery;
import com.antgroup.exam.Where;
import com.antgroup.exam.common.DatatypeException;
import com.antgroup.exam.condition.GreaterThanCondition;
import com.antgroup.exam.condition.LikeCondition;
import com.antgroup.exam.condition.NotEqualCondition;
import com.antgroup.exam.data.Student;
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
        students.add(new Student("student1", "class1", (byte) 10, 1));
        students.add(new Student("student2", "class1", (byte) 11, 0));
        students.add(new Student("student3", "class1", (byte) 10, 0));
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("age", 10))
                    .and(new NotEqualCondition<Student>().add("gender", 0))
                    .or(new LikeCondition<Student>().add("name", "student1"));
        } catch (NoSuchFieldException | DatatypeException e) {
            e.printStackTrace();
        }
        List<Student> queryResult = new DataQuery<Student>().query(students, where, "", "", "");
        queryResult.forEach(System.out::println);
    }
}
