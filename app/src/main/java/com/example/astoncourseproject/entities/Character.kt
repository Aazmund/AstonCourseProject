package com.example.astoncourseproject.entities

import android.graphics.Bitmap

class Character {
    val characterImg: Bitmap = Bitmap.createBitmap(30,30, Bitmap.Config.ARGB_8888)
    var characterName: String = ""
    var characterSpecies: String = ""
    var characterGender: String = ""
    var characterStatus: String = ""
}