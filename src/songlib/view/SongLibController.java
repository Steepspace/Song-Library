package songlib.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class SongLibController {
	@FXML
	ListView<String> listView;
	
	private ObservableList<String> obsList;
	
	public void start(){
		obsList = FXCollections.observableArrayList("1","2","3");
		listView.setItems(obsList);
	}
}
