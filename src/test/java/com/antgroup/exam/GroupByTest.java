package com.antgroup.exam;

import com.antgroup.exam.data.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Description:
 *
 * @author: huwei
 * @date: 2020-08-27
 */
public class GroupByTest {

    private List<Student> students = Student.getTestData(100);

    @Test
    public void testQueryDataWithGroupBy() {
        System.out.println("-------------------testQueryDataWithGroupBy:");
        GroupBy<Student, String> groupBy = new GroupBy<>("className");
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,groupBy,null);
        String preClassName = queryResult.get(0).getClassName();
        System.out.println(queryResult.get(0));
        for (int i = 1; i < queryResult.size(); i++) {
            System.out.println(queryResult.get(i));
            Assert.assertTrue(queryResult.get(i).getClassName().compareTo(preClassName) <= 0);
            preClassName = queryResult.get(i).getClassName();
        }
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithGroupByUseNumericalField() {
        System.out.println("-------------------testQueryDataWithGroupByUseNumericalField:");
        GroupBy<Student, String> groupBy = new GroupBy<>("gender");
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,groupBy,null);
        int preGender = queryResult.get(0).getGender();
        System.out.println(queryResult.get(0));
        for (int i = 1; i < queryResult.size(); i++) {
            System.out.println(queryResult.get(i));
            Assert.assertTrue(queryResult.get(i).getGender().compareTo(preGender) >= 0);
            preGender = queryResult.get(i).getGender();
        }
        System.out.println("---------------------------------------------");
    }
}
