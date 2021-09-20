package songlib.io;

public class Song implements Comparable<Song> {
    private String name;
    private String artist;
    private String album;
    private Integer year;

    public Song(final String name, final String artist, final String album, final Integer year){
        this(name, artist);
        this.album = album;
        this.year = year;
    }

    public Song(final String name, final String artist){
        this.name = name;
        this.artist = artist;
    }

    public int compareTo(final Song other) {
        final int c = this.name.compareToIgnoreCase(other.name);

        return c == 0 ? this.artist.compareToIgnoreCase(other.artist) : c;
    }

    public boolean equals(final Object obj){
        if(obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        final Song other = (Song)obj;

        return this.name.equalsIgnoreCase(other.name) && this.artist.equalsIgnoreCase(other.artist);
    }

    public String toString(){
        return this.name + "|" + this.artist + "|" + this.album + "|" + this.year;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setArtist(final String artist) {
        this.artist = artist;
    }

    public void setAlbum(final String album) {
        this.album = album;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public Integer getYear() {
        return this.year;
    }
}
