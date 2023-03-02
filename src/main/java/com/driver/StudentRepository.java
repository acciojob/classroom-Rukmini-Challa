package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentHashMap=new HashMap<>();
    HashMap<String,Teacher> teacherHashMap= new HashMap<>();
    HashMap<String, List<String>> studentteacherHashMap=new HashMap<>();

    public void addStudent(Student student){
        if(!studentHashMap.containsKey(student.getName()));
            studentHashMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        if(!teacherHashMap.containsKey(teacher.getName()));
        teacherHashMap.put(teacher.getName(), teacher);
    }


    public void addStudentTeacherPair(String student,String teacher){
        if((!studentHashMap.containsKey(student)) || (!teacherHashMap.containsKey(student))) {
            return;
        }
        if(!studentteacherHashMap.containsKey(teacher)){
            studentteacherHashMap.get(teacher).add(student);
        }
        else{
            List<String> l=new ArrayList<>();
            l.add(student);
            studentteacherHashMap.put(teacher, l);
        }
    }
     public Student  getStudentByName(String name){
        return  studentHashMap.get(name);
     }

     public Teacher getTeacherByName(String name){
        return teacherHashMap.get(name);
     }

     public List<String> getStudentsByTeacherName(String name){
            return studentteacherHashMap.get(name);
        }

        public List<String> getAllStudents(){
        List<String> ans= new ArrayList<>();
        for(String name: studentHashMap.keySet()){
            ans.add(name);
        }
        return ans;
     }
     public void deleteTeacherByName(String teacher){
        List<String> l= studentteacherHashMap.get(teacher);
        for(String name : l){
            if(studentHashMap.containsKey(name)){
                studentHashMap.remove(name);
            }
        }
        teacherHashMap.remove(teacher);
     }

     public void deleteAllTeachers(){
        for(String name : studentteacherHashMap.keySet()){
            List<String> list= studentteacherHashMap.get(name);
            for(int i=0;i<list.size();i++){
                if(studentHashMap.containsKey(list.get(i))){
                    studentHashMap.remove(list.get(i));
                }
            }
            teacherHashMap.remove(name);
        }
        for(String name :teacherHashMap.keySet()){
            teacherHashMap.remove(name);
        }
     }
}
