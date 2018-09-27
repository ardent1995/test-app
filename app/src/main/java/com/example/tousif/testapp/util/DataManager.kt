package com.example.tousif.testapp.util

import com.example.tousif.testapp.model.Student

object DataManager {

    val CURRENT_STUDENT_KEY = "student"
    val CURRENT_STUDENTS_INDEX = "index"
    private val studentList = ArrayList<Student>()

    fun getStudentList() : ArrayList<Student>{
        return studentList
    }

    fun addtoStudentList(studentName: String, studentRollNo: Int){
        studentList.add(Student(studentName,studentRollNo))
    }

    fun updateStudentInfo(indexOfStudent: Int,studentName: String, studentRollNo: Int){
        studentList[indexOfStudent] = Student(studentName,studentRollNo)
    }
}