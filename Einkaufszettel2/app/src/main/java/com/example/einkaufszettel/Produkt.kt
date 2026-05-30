package com.example.einkaufszettel.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Kategorie::class,
            parentColumns = ["id"],
            childColumns = ["kategorieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Produkt(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val kategorieId: Int
)
