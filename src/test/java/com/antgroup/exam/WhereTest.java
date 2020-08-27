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

    List<Student> students = Student.getTestData(100);

    @Test
    public void testQueryDataWithoutCondition() {
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, null, null, null, null);
        Assert.assertEquals(100, queryResult.size());
    }

    @Test
    public void testQueryDataWithEqualCondition() {
        Where<Student> where = new Where<Student>().and(new EqualCondition<Student>().add("age", 10));
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, where, null, null, null);
        queryResult.forEach(s -> {
            Assert.assertEquals(10, (int) s.getAge());
        });
    }

    @Test
    public void testQueryDataWithNotEqualCondition() {
        Where<Student> where = new Where<Student>().and(new NotEqualCondition<Student>().add("age", 10));
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, where, null, null, null);
        queryResult.forEach(s -> {
            Assert.assertNotEquals(10, (int) s.getAge());
        });
    }

    @Test
    public void testQueryDataWithLikeCondition() {
        Where<Student> where = new Where<Student>()
                .and(new LikeCondition<Student>().add("className", "Class"));
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, where, null, null, null);
        Assert.assertEquals(0, queryResult.size());
    }

    @Test
    public void testQueryDataWithGreaterThanCondition() {
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("age", 10));
        } catch (NoSuchFieldException | DatatypeException e) {
            e.printStackTrace();
        }
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, where, null, null, null);
        queryResult.forEach(s -> {
            Assert.assertTrue((int) s.getAge() > 10);
        });
    }

    @Test
    public void testQueryDataWithGreaterThanConditionAndThrowDatatypeException() {
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("name", "student1"));
        } catch (NoSuchFieldException | DatatypeException e) {
            Assert.assertTrue(e instanceof DatatypeException);
        }
    }

    @Test
    public void testQueryDataWithGreaterThanConditionAndThrowNoSuchFieldException() {
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new GreaterThanCondition<Student>(Student.class).add("weight", 100));
        } catch (NoSuchFieldException | DatatypeException e) {
            Assert.assertTrue(e instanceof NoSuchFieldException);
        }
    }

    @Test
    public void testQueryDataWithLessThanCondition() {
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new LessThanCondition<Student>(Student.class).add("age", 12));
        } catch (NoSuchFieldException | DatatypeException e) {
            e.printStackTrace();
        }
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students, where, null, null, null);
        queryResult.forEach(s -> {
            Assert.assertTrue((int) s.getAge() < 12);
        });
    }

    @Test
    public void testQueryDataWithLessThanConditionAndThrowDatatypeException() {
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new LessThanCondition<Student>(Student.class).add("name", "student1"));
        } catch (NoSuchFieldException | DatatypeException e) {
            Assert.assertTrue(e instanceof DatatypeException);
        }
    }

    @Test
    public void testQueryDataWithLessThanConditionAndThrowNoSuchFieldException() {
        Where<Student> where = null;
        try {
            where = new Where<Student>()
                    .and(new LessThanCondition<Student>(Student.class).add("weight", 100));
        } catch (NoSuchFieldException | DatatypeException e) {
            Assert.assertTrue(e instanceof NoSuchFieldException);
        }
    }
}
