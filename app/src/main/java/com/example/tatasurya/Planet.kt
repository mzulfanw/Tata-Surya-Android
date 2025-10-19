package com.example.tatasurya

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    val name: String,
    val description: String,
    val photo: String
) : Parcelable