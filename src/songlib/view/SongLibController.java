package songlib.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.FocusModel;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import songlib.io.SongLibIO;
import songlib.io.Song;

public class SongLibController implements Initializable{

	@FXML ListView<Song> listView;
	@FXML Text nameText, artistText, albumText, yearText;
	@FXML Button addB, delB, editB;

	SongLibIO IO = new SongLibIO("../data/data.csv");//change to generic path variable
	private SelectionModel<Song> model;

	public void switchToAdd(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/songlib/view/add.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToEdit(ActionEvent e) throws IOException {
		Song song = model.getSelectedItem();
		if(song == null) return;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/edit.fxml"));
		Parent root = loader.load();

		SongLibEditController controller = loader.getController();

		controller.setField(song);

		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void initialize(URL url, ResourceBundle rb) {
		//insert items into listview
		listView.setItems(IO.getList());

		model = listView.getSelectionModel();
		//selection handling
		model.selectedIndexProperty().addListener( (obs, oldVal, newVal) -> songDetail());
		model.selectFirst();
	}

	private void songDetail(){
		Song selected = model.getSelectedItem();
		if(selected == null){
			nameText.setText(null);
			artistText.setText(null);
			albumText.setText(null);
			yearText.setText(null);
			return;
		}

		nameText.setText(selected.getName());
		artistText.setText(selected.getArtist());
		albumText.setText(selected.getAlbum());
		if(selected.getYear() != null) yearText.setText(selected.getYear().toString());
		else yearText.setText(null);
	}
	
	public boolean add(Song song){
		if (IO.add(song)){
			model.select(song);
			return true;
		}
		return false;
	}
	
	public void deleteSong(ActionEvent e){
		Song song = model.getSelectedItem();
		int songIndex = model.getSelectedIndex();

		if(songIndex == IO.getList().size()-1) model.selectPrevious();
		else model.selectNext();

		IO.delete(song);
		songDetail();
	}
	
	public boolean edit(Song old, Song song){
		if (IO.update(old, song)){
			model.select(song);
			return true;
		}
		return false;
	}
}
