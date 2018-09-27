package com.example.tousif.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(val studentName: String, val rollNo: Int) : Parcelable