package songlib.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.text.Text;
import javafx.scene.Scene;
/*
 * @author Apurva Narde
 * @author Max Geiger
 */

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import songlib.io.SongLibIO;
import songlib.io.Song;

public class SongLibController{

	@FXML ListView<Song> listView;
	@FXML Text nameText, artistText, albumText, yearText;
	@FXML Button addB, delB, editB;

	SongLibIO IO = new SongLibIO("../data/data.csv");//change to generic path variable
	private SelectionModel<Song> model;

	public void switchToAdd(final ActionEvent e) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/songlib/view/add.fxml"));
		final Scene scene = new Scene(root);
		final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToEdit(final ActionEvent e) throws IOException {
		final Song song = model.getSelectedItem();
		if(song == null) return;

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/edit.fxml"));
		final Parent root = loader.load();

		final SongLibEditController controller = loader.getController();

		controller.setField(song);

		final Scene scene = new Scene(root);
		final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToDelete(final ActionEvent e) throws IOException {
		final Song song = model.getSelectedItem();
		if(song == null) return;

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/delete.fxml"));
		final Parent root = loader.load();

		final SongLibDeleteController controller = loader.getController();

		controller.setField(song);

		final Scene scene = new Scene(root);
		final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void initialize(){
		//insert items into listview
		listView.setItems(IO.getList());

		model = listView.getSelectionModel();
		//selection handling
		model.selectedIndexProperty().addListener( (obs, oldVal, newVal) -> songDetail());
		model.selectFirst();
	}

	private void songDetail(){
		final Song selected = model.getSelectedItem();
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

	public boolean add(final Song song){
		if (IO.add(song)){
			model.select(song);
			return true;
		}
		return false;
	}

	public void delete(final Song song){
		model.select(song);
		final int songIndex = model.getSelectedIndex();

		if(songIndex == IO.getList().size()-1) model.selectPrevious();
		else model.selectNext();

		IO.delete(song);
		songDetail();
	}

	public boolean edit(final Song old, final Song song){
		if (IO.update(old, song)){
			model.select(song);
			return true;
		}
		return false;
	}
}
