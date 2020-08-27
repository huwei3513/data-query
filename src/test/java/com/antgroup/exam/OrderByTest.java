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

    private List<Student> students = Student.getTestData(100);

    @Test
    public void testQueryDataOrderByOneField() {
        System.out.println("-------------------testQueryDataOrderByOneField:");
        OrderBy<Student> orderBy = new OrderBy<Student>()
                .add("gender",OrderDirection.ASC);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,orderBy,null,null);
        int preGender = queryResult.get(0).getGender();
        System.out.println(queryResult.get(0));
        for (int i = 1; i < queryResult.size(); i++) {
            System.out.println(queryResult.get(i));
            Assert.assertTrue(queryResult.get(i).getGender() >= preGender);
            preGender = queryResult.get(i).getGender();
        }
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataOrderByMultiField() {
        System.out.println("-------------------testQueryDataOrderByMultiField:");
        OrderBy<Student> orderBy = new OrderBy<Student>()
                .add("gender",OrderDirection.ASC)
                .add("age",OrderDirection.ASC);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,orderBy,null,null);
        int preGender = queryResult.get(0).getGender();
        int preAge = queryResult.get(0).getAge();
        System.out.println(queryResult.get(0));
        for (int i = 1; i < queryResult.size(); i++) {
            System.out.println(queryResult.get(i));
            Assert.assertTrue(queryResult.get(i).getGender() >= preGender);
            if (queryResult.get(i).getGender() == preGender) {
                Assert.assertTrue(queryResult.get(i).getAge() >= preAge);
            }
            preGender = queryResult.get(i).getGender();
            preAge = queryResult.get(i).getAge();
        }
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataOrderByStringFieldDesc() {
        System.out.println("-------------------testQueryDataOrderByStringFieldDesc:");
        OrderBy<Student> orderBy = new OrderBy<Student>()
                .add("name",OrderDirection.DESC);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,orderBy,null,null);
        String preName = queryResult.get(0).getName();
        System.out.println(queryResult.get(0));
        for (int i = 1; i < queryResult.size(); i++) {
            System.out.println(queryResult.get(i));
            Assert.assertTrue(queryResult.get(i).getName().compareTo(preName) < 0);
            preName = queryResult.get(i).getName();
        }
        System.out.println("---------------------------------------------");
    }
}
