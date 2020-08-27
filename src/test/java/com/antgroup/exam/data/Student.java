package com.antgroup.exam.data;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author huwei
 * @date 2020/8/26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private String className;
    public Integer age;
    private Integer gender;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static List<Student> getTestData(int count) {
        String[] classes = {"class1", "class2", "class3", "class4"};
        int[] genders = {0, 1};
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            students.add(new Student("student" + (i + 1),
                    classes[new Random().nextInt(4)],
                    10 + new Random().nextInt(4),
                    genders[new Random().nextInt(2)]));
        }
        return students;
    }
}
