package com.antgroup.exam;

import com.antgroup.exam.common.DatatypeException;
import com.antgroup.exam.condition.*;
import com.antgroup.exam.data.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author huwei
 * @date 2020/8/27
 */
public class WhereTest {

    private List<Student> students = Student.getTestData(10000);

    @Test
    public void testQueryDataWithoutCondition() {
        System.out.println("-------------------testQueryDataWithoutCondition:");
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,null,null);
        Assert.assertEquals(10000,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithEqualCondition() {
        System.out.println("-------------------testQueryDataWithEqualCondition:");
        Where<Student> where = new Where<Student>().and(new EqualCondition<Student>().add("age",10));
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,where,null,null,null);
        queryResult.forEach(s -> {
            System.out.println(s);
            Assert.assertEquals(10,(int) s.getAge());
        });
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithNotEqualCondition() {
        System.out.println("-------------------testQueryDataWithNotEqualCondition:");
        Where<Student> where = new Where<Student>().and(new NotEqualCondition<Student>().add("age",10));
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,where,null,null,null);
        queryResult.forEach(s -> {
            System.out.println(s);
            Assert.assertNotEquals(10,(int) s.getAge());
        });
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithLikeCondition() {
        System.out.println("-------------------testQueryDataWithLikeCondition:");
        Where<Student> where = new Where<Student>()
                .and(new LikeCondition<Student>().add("className","Class"));
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,where,null,null,null);
        Assert.assertEquals(0,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithGreaterThanCondition() {
        System.out.println("-------------------testQueryDataWithGreaterThanCondition:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("age",10));
        } catch (NoSuchFieldException | DatatypeException e) {
            e.printStackTrace();
        }
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,where,null,null,null);
        queryResult.forEach(s -> {
            System.out.println(s);
            Assert.assertTrue((int) s.getAge() > 10);
        });
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithGreaterThanConditionAndThrowDatatypeException() {
        System.out.println("-------------------testQueryDataWithGreaterThanConditionAndThrowDatatypeException:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("name","student1"));
        } catch (NoSuchFieldException | DatatypeException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(e instanceof DatatypeException);
        }
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithGreaterThanConditionAndThrowNoSuchFieldException() {
        System.out.println("-------------------testQueryDataWithGreaterThanConditionAndThrowNoSuchFieldException:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("weight",100));
        } catch (NoSuchFieldException | DatatypeException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(e instanceof NoSuchFieldException);
        }
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithLessThanCondition() {
        System.out.println("-------------------testQueryDataWithLessThanCondition:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new LessThanCondition<Student>(Student.class).add("age",12));
        } catch (NoSuchFieldException | DatatypeException e) {
            e.printStackTrace();
        }
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,where,null,null,null);
        queryResult.forEach(s -> {
            System.out.println(s);
            Assert.assertTrue((int) s.getAge() < 12);
        });
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithLessThanConditionAndThrowDatatypeException() {
        System.out.println("-------------------testQueryDataWithLessThanConditionAndThrowDatatypeException:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new LessThanCondition<Student>(Student.class).add("name","student1"));
        } catch (NoSuchFieldException | DatatypeException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(e instanceof DatatypeException);
        }
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithLessThanConditionAndThrowNoSuchFieldException() {
        System.out.println("-------------------testQueryDataWithLessThanConditionAndThrowNoSuchFieldException:");
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new LessThanCondition<Student>(Student.class).add("weight",100));
        } catch (NoSuchFieldException | DatatypeException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(e instanceof NoSuchFieldException);
        }
        System.out.println("---------------------------------------------");
    }
}
