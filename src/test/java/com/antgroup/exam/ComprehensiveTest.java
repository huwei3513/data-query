package com.antgroup.exam;

import com.antgroup.exam.common.DatatypeException;
import com.antgroup.exam.common.OrderDirection;
import com.antgroup.exam.condition.GreaterThanCondition;
import com.antgroup.exam.condition.LikeCondition;
import com.antgroup.exam.condition.NotEqualCondition;
import com.antgroup.exam.data.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author huwei
 * @date 2020/8/26
 */
public class ComprehensiveTest {

    private List<Student> students = Student.getTestData(100);

    @Test
    public void testQueryDataWithAllParams() {
        System.out.println("-------------------testQueryDataWithAllParams:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("age",10))
                    .and(new NotEqualCondition<Student>().add("gender",0))
                    .or(new LikeCondition<Student>().add("className","class3"));
        } catch (NoSuchFieldException | DatatypeException e) {
            e.printStackTrace();
        }
        OrderBy<Student> orderBy = new OrderBy<Student>()
                .add("gender",OrderDirection.ASC)
                .add("age",OrderDirection.DESC);
        GroupBy<Student, String> groupBy = new GroupBy<>("className");
        Limit limit = new Limit(0,10);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,where,orderBy,groupBy,limit);
        queryResult.forEach(s -> {
            System.out.println(s);
            Assert.assertTrue(s.getAge() > 10);
            if (!"class3".equals(s.getClassName())) {
                Assert.assertTrue(s.getGender() != 0);
            }
        });
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }
}
