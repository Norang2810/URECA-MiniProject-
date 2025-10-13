package game.dto;

public class Genre {
    private int genreId;       // PK
    private String genreName;  // 장르명

    public Genre() {}

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName; // comboBox에서 장르명만 보이도록
    }
}
