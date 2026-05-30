package com.example.einkaufszettel.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProduktDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(produkt: Produkt)

    @Query("SELECT * FROM Produkt WHERE kategorieId = :katId ORDER BY name ASC")
    fun getProdukteZuKategorie(katId: Int): Flow<List<Produkt>>

    @Delete
    suspend fun delete(produkt: Produkt)
}
