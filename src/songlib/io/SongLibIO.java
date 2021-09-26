package songlib.io;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class SongLibIO {

    private ObservableList<Song> songList;
    private String path;

    public SongLibIO(final String path){
        this.path = path;
        read();
    }

    // 0 if successfully added
    // 1 if either name or artist is null or year is nonpositive
    // 2 if song already exists
    public boolean add(final Song song){
        // if (song == null ||
        //     song.getName() == null ||
        //     song.getArtist() == null ||
        //     (song.getYear() != null && song.getYear() < 1)) return 1;

        // // Remove any trailing or leading white spaces
        // song.setName(song.getName().strip());
        // song.setArtist(song.getArtist().strip());

        // if (song.getAlbum() != null) song.setAlbum(song.getAlbum().strip());

        if(songList.contains(song)) return false;

        songList.add(song);

        write();
        return true;
    }

    // 0 if successfully updated
    // 1 if either name or artist is null or year is nonpositive
    // 2 if newSong already exists in the data
    // 3 if oldSong does not exist in the data
    public boolean update(final Song oldSong, final Song newSong){
        // if (newSong == null ||
        //     newSong.getName() == null ||
        //     newSong.getArtist() == null ||
        //     (newSong.getYear() != null && newSong.getYear() < 1)) return 1;

        // // Remove any trailing or leading white spaces
        // newSong.setName(newSong.getName().strip());
        // newSong.setArtist(newSong.getArtist().strip());

        // if (newSong.getAlbum() != null) newSong.setAlbum(newSong.getAlbum().strip());

        if (!oldSong.equals(newSong) && songList.contains(newSong)) return false;

        final int index = songList.indexOf(oldSong);

        // if (index == -1) return 3;

        songList.set(index, newSong);

        write();
        return true;
    }

    // Remove song if exists
    public void delete(final Song song){
        songList.remove(song);

        write();
    }

    public ObservableList<Song> getList(){
        return songList;
    }

    private void read(){
        try {
            final BufferedReader csvReader = new BufferedReader(new FileReader(this.path));

            songList = FXCollections.observableArrayList(new ArrayList<>());

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
            songList = null;
        }
    }

    public void clear(){
        try {
            final File file = new File(path);
            file.delete();
            file.createNewFile();
        } catch (final Exception e) {}
    }

    private void write(){
       if(songList == null) return;
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
