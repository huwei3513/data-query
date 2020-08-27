package com.antgroup.exam;

import com.antgroup.exam.common.OrderDirection;
import com.antgroup.exam.data.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class OrderByTest {

    List<Student> students = Student.getTestData(100);

    @Test
    public void testQueryDataOrderByOneField() {
        OrderBy<Student> orderBy = new OrderBy<Student>()
                .add("gender", OrderDirection.ASC);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, null, orderBy, null, null);
        int preGender = queryResult.get(0).getGender();
        for (Student student : queryResult) {
            Assert.assertTrue(student.getGender() >= preGender);
            preGender = student.getGender();
        }
    }
    //                .add("age", OrderDirection.ASC);
}
