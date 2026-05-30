@Entity
public class Kategorie {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;
}
