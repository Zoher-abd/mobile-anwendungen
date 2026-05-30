@Entity(foreignKeys = @ForeignKey(
        entity = Kategorie.class,
        parentColumns = "id",
        childColumns = "kategorieId",
        onDelete = ForeignKey.CASCADE
))
public class Produkt {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public int kategorieId;
}
