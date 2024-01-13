package com.learning.customcollection;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Student mihirStud = new Student("Mihir", 23, LocalDate.of(2002, 12, 2));
        Student mihirStud1 = new Student("MIHIR", 23, LocalDate.of(2002, 12, 2));
        Student jayStud = new Student("Jay", 23, LocalDate.of(2002, 12, 2));
        Student hirenStud = new Student("Hiren", 23, LocalDate.of(2002, 12, 2));
        UniqueObjectList<Student> uniqueObjectListStudents = new UniqueObjectList<>();
        uniqueObjectListStudents.add(mihirStud);
        uniqueObjectListStudents.add(mihirStud1);
        uniqueObjectListStudents.add(jayStud);
        uniqueObjectListStudents.add(hirenStud);
        System.out.println("Array Size : %d".formatted(uniqueObjectListStudents.size()));
    }
}
