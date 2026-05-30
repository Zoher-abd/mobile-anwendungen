package com.example.einkaufszettel.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KategorieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kategorie: Kategorie)

    @Query("SELECT * FROM Kategorie ORDER BY name ASC")
    fun getAll(): Flow<List<Kategorie>>

    @Delete
    suspend fun delete(kategorie: Kategorie)
}
