package com.example.tousif.testapp

import android.content.Intent
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.tousif.testapp.adapter.StudentListAdapter
import com.example.tousif.testapp.util.DataManager
import com.example.tousif.testapp.util.PreferenceManager

class StudentListActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val fabAdd by lazy { findViewById<FloatingActionButton>(R.id.fab_add) }
    private val studentList by lazy { findViewById<RecyclerView>(R.id.rv_student_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        setSupportActionBar(toolbar)

        fabAdd.setOnClickListener {
            startActivity(Intent(this,StudentDetailsActivity::class.java))
        }

        studentList.layoutManager = LinearLayoutManager(this)
        studentList.adapter = StudentListAdapter(DataManager.getStudentList())

        val dividerItemDecoration = VerticalSpaceItemDecoration(20)
        studentList.addItemDecoration(dividerItemDecoration)
    }

    override fun onResume() {
        studentList.adapter?.notifyDataSetChanged()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.log_out){
            PreferenceManager.writetoSharedPreference(this@StudentListActivity, false)
            startActivity(Intent(this,LogInActivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    inner class VerticalSpaceItemDecoration(val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.top = verticalSpaceHeight
            outRect.bottom = verticalSpaceHeight
        }
    }

}
