package songlib.view;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import songlib.io.*;



public class SongLibController {
	@FXML
	ListView<Song> listView;
	SongLibIO IO = new SongLibIO("../data/data.csv");//change to generic path variable
	//Song test = new Song("huh","mg");
		
	private ObservableList<Song> SongList;
	
	public void start(){
		//initial read from file
		SongList = FXCollections.observableArrayList(IO.read());
		//insert items into listview
		listView.setItems(SongList);
		SongList.add(new Song("test","an"));
	}
}
