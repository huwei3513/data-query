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
public class LimitTest {
    private List<Student> students = Student.getTestData(100);

    @Test
    public void testQueryDataAtFirstTen() {
        System.out.println("-------------------testQueryDataAtFirstTen:");
        Limit limit = new Limit(0,10);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,null,limit);
        Assert.assertEquals("student1",queryResult.get(0).getName());
        Assert.assertEquals(10,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithNegativeOffset() {
        System.out.println("-------------------testQueryDataWithNegativeOffset:");
        Limit limit = new Limit(-1,10);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,null,limit);
        Assert.assertEquals("student1",queryResult.get(0).getName());
        Assert.assertEquals(10,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithNegativeRows() {
        System.out.println("-------------------testQueryDataWithNegativeRows:");
        Limit limit = new Limit(0,-1);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,null,limit);
        Assert.assertEquals(0,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithTooBigRows() {
        System.out.println("-------------------testQueryDataWithTooBigRows:");
        Limit limit = new Limit(0,200);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,null,limit);
        Assert.assertEquals(100,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void testQueryDataWithTooBigOffset() {
        System.out.println("-------------------testQueryDataWithTooBigOffset:");
        Limit limit = new Limit(200,10);
        List<Student> queryResult = new DataQuery<Student, String>()
                .query(students,null,null,null,limit);
        Assert.assertEquals(0,queryResult.size());
        queryResult.forEach(System.out::println);
        System.out.println("---------------------------------------------");
    }
}
