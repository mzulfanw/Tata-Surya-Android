package com.example.tatasurya

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person (
    val name: String,
    val email: String,
    val photo: Int
) : Parcelable