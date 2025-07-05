public class Movie {
    private String description;
    private int duration;
    private String genre;
    private String language;
    private int movieId;
    private String title;
    private String imagePath; // ✅ مضافة

    // ✅ Constructor الجديد مع imagePath
    public Movie(String description, int duration, String genre, String language, int movieId, String title, String imagePath) {
        this.description = description;
        this.duration = duration;
        this.genre = genre;
        this.language = language;
        this.movieId = movieId;
        this.title = title;
        this.imagePath = imagePath; // مضافة هنا
    }

    public String getDetails() {
        return "Title: " + title + ", Genre: " + genre + ", Duration: " + duration + " mins, Language: " + language;
    }

    public boolean isAvailable() {
        // مثال بسيط: متاح دائمًا
        return true;
    }

    // ✅ Getters كاملة
    public String getTitle() { return title; }
    public int getMovieId() { return movieId; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }
    public String getDescription() { return description; }
    public String getLanguage() { return language; }
    public String getImagePath() { return imagePath; } // ✅ Getter جديد
}
