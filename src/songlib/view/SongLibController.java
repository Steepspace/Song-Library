package songlib.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import songlib.io.*;



public class SongLibController {
	@FXML ListView<Song> listView;
	@FXML Text songText;
	@FXML Text artText;
	@FXML Text albText;
	@FXML Text yText;
	
	SongLibIO IO = new SongLibIO("../data/data.csv");//change to generic path variable
	//Song test = new Song("huh","mg");
		
	private ObservableList<Song> SongList;
	
	public void start(){
		//initial read from file
		SongList = FXCollections.observableArrayList(IO.read());
		//insert items into listview
		listView.setItems(SongList);
		SongList.add(new Song("test","an"));
		//selection handling
		//listView.getSelectionModel().select(0);
		listView.getSelectionModel().selectedIndexProperty().addListener( (obs, oldVal, newVal) -> songDetail());
	}

	private void songDetail() {
		Song selected = listView.getSelectionModel().getSelectedItem();
		songText.setText(selected.getName());
		artText.setText(selected.getArtist());
		albText.setText(selected.getAlbum());
		yText.setText(selected.getYear().toString());
	}
}
