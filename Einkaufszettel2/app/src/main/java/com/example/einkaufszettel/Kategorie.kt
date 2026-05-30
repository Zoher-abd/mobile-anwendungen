package com.example.einkaufszettel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kategorie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
