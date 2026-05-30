package com.example.einkaufszettel.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Kategorie::class, Produkt::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kategorieDao(): KategorieDao
    abstract fun produktDao(): ProduktDao
}
