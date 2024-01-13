package com.learning.customcollection;

import java.time.LocalDate;

public class Student {
    private String name;
    private int age;
    private LocalDate joinDate;

    public Student(String name, int age, LocalDate joinDate) {
        this.name = name;
        this.age = age;
        this.joinDate = joinDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student otherStudentObj = (Student) obj;
        return name.equalsIgnoreCase(otherStudentObj.name)
                && age == otherStudentObj.age
                && this.joinDate.isEqual(otherStudentObj.joinDate);
    }
}
