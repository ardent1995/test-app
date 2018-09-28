package com.example.tousif.testapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tousif.testapp.R
import com.example.tousif.testapp.StudentDetailsActivity
import com.example.tousif.testapp.model.Student
import com.example.tousif.testapp.util.DataManager

class StudentListAdapter(val studentList:List<Student>): RecyclerView.Adapter<StudentListAdapter.StudentListViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudentListViewHolder {
        context = p0.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_student,p0,false)
        return StudentListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(p0: StudentListViewHolder, p1: Int) {
        val student = studentList[p1]
        p0.studentRollNo.text = student.rollNo.toString()
        p0.studentName.text = student.studentName
    }

    inner class StudentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val studentRollNo by lazy { itemView.findViewById<TextView>(R.id.tv_roll_no) }
        val studentName by lazy { itemView.findViewById<TextView>(R.id.tv_name) }

        init {
            itemView.setOnClickListener {
                val intent = Intent(context,StudentDetailsActivity::class.java)
                intent.putExtra(DataManager.CURRENT_STUDENT_KEY,studentList[adapterPosition])
                intent.putExtra(DataManager.CURRENT_STUDENTS_INDEX,adapterPosition)
                context?.startActivity(intent)
            }

            itemView.findViewById<ImageView>(R.id.iv_delete_item).setOnClickListener {
                DataManager.deleteStudentInfo(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }
}