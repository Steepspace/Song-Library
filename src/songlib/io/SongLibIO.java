package songlib.io;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class SongLibIO {

    private ArrayList<Song> songList;
    private String path;

    public SongLibIO(){
        songList = new ArrayList<>();
    }

    public SongLibIO(final String path){
        this();
        this.path = path;
    }

    // 0 if successfully added
    // 1 if either name or artist is null or year is nonpositive
    // 2 if song already exists
    public int add(final Song song){
        if (song == null ||
            song.getName() == null ||
            song.getArtist() == null ||
            (song.getYear() != null && song.getYear() < 1)) return 1;

        songList = read();

        // Remove any trailing or leading white spaces
        song.setName(song.getName().strip());
        song.setArtist(song.getArtist().strip());

        if (song.getAlbum() != null) song.setAlbum(song.getAlbum().strip());

        if(songList.contains(song)) return 2;

        songList.add(song);

        write();
        return 0;
    }

    // 0 if successfully updated
    // 1 if either name or artist is null or year is nonpositive
    // 2 if newSong already exists in the data
    // 3 if oldSong does not exist in the data
    public int update(final Song oldSong, final Song newSong){
        if (newSong == null ||
            newSong.getName() == null ||
            newSong.getArtist() == null ||
            (newSong.getYear() != null && newSong.getYear() < 1)) return 1;

        songList = read();

        // Remove any trailing or leading white spaces
        newSong.setName(newSong.getName().strip());
        newSong.setArtist(newSong.getArtist().strip());

        if (newSong.getAlbum() != null) newSong.setAlbum(newSong.getAlbum().strip());

        if (!oldSong.equals(newSong) && songList.contains(newSong)) return 2;

        final int index = songList.indexOf(oldSong);

        if (index == -1) return 3;

        songList.set(index, newSong);

        write();
        return 0;
    }

    // Remove song if exists
    public void delete(final Song song){
        songList = read();

        songList.remove(song);

        write();
    }

    public ArrayList<Song> read(){
        try {
            final BufferedReader csvReader = new BufferedReader(new FileReader(this.path));
            songList.clear();

            String row;
            while ((row = csvReader.readLine()) != null) {
                final String[] data = row.split("\\|");

                final Song song = new Song(data[0], data[1]);

                if (!data[2].equals(" "))
                    song.setAlbum(data[2]);
                if (!data[3].equals(" "))
                    song.setYear(Integer.parseInt(data[3]));

                songList.add(song);
            }
            csvReader.close();
        } catch (final Exception e) {
            return null;
        }

        return songList;
    }

    public void clear(){
        try {
        final File file = new File(path);
        file.delete();
        file.createNewFile();
        } catch (final Exception e) {}
    }

    private void write(){
       if(songList == null || songList.isEmpty()) return;
       songList.sort(null);

        try {
            final FileWriter csvWriter = new FileWriter(this.path);

            for(final Song song : songList){
                csvWriter.append(song.getName()+"|"+
                                 song.getArtist()+"|");

                if(song.getAlbum() != null) csvWriter.append(song.getAlbum()+"|");
                else csvWriter.append(" |");

                if(song.getYear() != null) csvWriter.append(song.getYear()+"|");
                else csvWriter.append(" |");

                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (final Exception e) {}
    }
}
