@Dao
public interface EinkaufsDao {
    @Insert void insertKategorie(Kategorie k);
    @Insert void insertProdukt(Produkt p);
    @Query("SELECT * FROM Kategorie") List<Kategorie> getAlleKategorien();
    @Query("SELECT * FROM Produkt WHERE kategorieId = :katId") List<Produkt> getProdukteFuerKategorie(int katId);
}
