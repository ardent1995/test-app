package com.example.tousif.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.tousif.testapp.model.Student
import com.example.tousif.testapp.util.DataManager

class StudentDetailsActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.student_details_activity_toolbar) }
    private val tietStudentName by lazy { findViewById<TextInputEditText>(R.id.tiet_student_name) }
    private val tietRollNo by lazy { findViewById<TextInputEditText>(R.id.tiet_roll_no) }
    private val btnSubmit by lazy { findViewById<Button>(R.id.btn_submit) }
    private val btnUpdate by lazy { findViewById<Button>(R.id.btn_update) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if(intent.hasExtra(DataManager.CURRENT_STUDENT_KEY) && intent.hasExtra(DataManager.CURRENT_STUDENTS_INDEX)) {
            val student = intent.extras?.getParcelable<Student>(DataManager.CURRENT_STUDENT_KEY)
            val indexOfStudent = intent.extras?.getInt(DataManager.CURRENT_STUDENTS_INDEX)
            tietStudentName.setText(student?.studentName)
            tietRollNo.setText(student?.rollNo.toString())
            btnSubmit.visibility = View.GONE
            btnUpdate.visibility = View.VISIBLE
            btnUpdate.setOnClickListener {
                if (tietStudentName.text?.trim()!!.isNotEmpty() && tietRollNo.text?.trim()!!.isNotEmpty()) {
                    DataManager.updateStudentInfo(indexOfStudent!!,tietStudentName.text.toString(), Integer.parseInt(tietRollNo.text.toString()))
                    finish()
                } else {
                    Toast.makeText(this, "Enter valid info", Toast.LENGTH_LONG).show()
                }
            }
        }else {
            btnSubmit.visibility = View.VISIBLE
            btnUpdate.visibility = View.GONE
            btnSubmit.setOnClickListener {
                if (tietStudentName.text?.trim()!!.isNotEmpty() && tietRollNo.text?.trim()!!.isNotEmpty()) {
                    DataManager.addtoStudentList(tietStudentName.text.toString(), Integer.parseInt(tietRollNo.text.toString()))
                    tietStudentName.setText("")
                    tietRollNo.setText("")
                    tietStudentName.requestFocus()
                    tietRollNo.clearFocus()
                    Toast.makeText(this, "Successfull", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Enter valid info", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
