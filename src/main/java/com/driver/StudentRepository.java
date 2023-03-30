package com.driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentMap;
    HashMap<String, Teacher> teacherMap;
    HashMap<String, List<String>> teacherStudentsMap;

    public StudentRepository(){
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.teacherStudentsMap = new HashMap<>();
    }

    public void addStudent(Student student){
        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            if (teacherStudentsMap.containsKey(teacher)){
                List<String> studentsList = teacherStudentsMap.get(teacher);
                studentsList.add(student);
                teacherStudentsMap.put(teacher, studentsList);
            }
            else{
                List<String> studentsList = new ArrayList<>();
                studentsList.add(student);
                teacherStudentsMap.put(teacher, studentsList);
            }
            teacherMap.get(teacher).setNumberOfStudents(teacherStudentsMap.get(teacher).size());
        }

    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }
    public List<String> getStudentsByTeacherName(String teacher){
        List<String> students = teacherStudentsMap.get(teacher);
        return students;
    }
    public List<String> getAllStudents(){
        List<String> students = new ArrayList<>();
        for (String student : studentMap.keySet()){
            students.add(student);
        }
        return students;
    }

    public void deleteTeacherByName(String teacher) {
        List<String> students = teacherStudentsMap.get(teacher);
        for (String student : students){
            studentMap.remove(student);
        }
        teacherStudentsMap.remove(teacher);
        teacherMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String teacher : teacherStudentsMap.keySet()){
            List<String> students = teacherStudentsMap.get(teacher);
            for (String student : students){
                studentMap.remove(student);
            }
        }
        teacherStudentsMap.clear();
        teacherMap.clear();
    }
}