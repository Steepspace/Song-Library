/*
 * @author Apurva Narde
 * @author Max Geiger
 */

package songlib.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import songlib.io.SongLibIO;
import songlib.io.Song;

public class SongLibEditController {

	@FXML TextField editName, editArtist, editAlbum, editYear;

	private Stage stage;
	private Song old;

	public void switchToDefault(final ActionEvent e) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/songlib/view/SongLib.fxml"));
		final Scene scene = new Scene(root);
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void setField(final Song song){
		editName.setText(song.getName());
		editArtist.setText(song.getArtist());
		if(song.getAlbum() != null) editAlbum.setText(song.getAlbum());
		if(song.getYear() != null) editYear.setText(song.getYear().toString());
		old = song;
	}

	public void editSong(final ActionEvent e) throws IOException{
		final String name = editName.getText().strip();
		final String artist = editArtist.getText().strip();

		if(name.isEmpty() || name.contains("|")){
			getAlert("Error", "Name cannot be empty or contain |", "Please enter valid name");
			return;
		}
		if(artist.isEmpty() || artist.contains("|")){
			getAlert("Error", "Artist cannot be empty or contain |", "Please enter valid artist");
			return;
		}
		if(editAlbum.getText().contains("|")){
			getAlert("Error", "Artist cannot contain |", "Please enter valid album or leave this field empty");
			return;
		}
		if(!editYear.getText().strip().isEmpty() && (!editYear.getText().strip().matches("[0-9]+") || Integer.parseInt(editYear.getText().strip()) < 1)){
			getAlert("Error", "Year must be positive integer", "Please enter valid year or leave this field empty");
			return;
		}

		final String album = !editAlbum.getText().strip().isEmpty() ? editAlbum.getText().strip() : null;
		final Integer year = !editYear.getText().strip().isEmpty() ? Integer.valueOf(editYear.getText().strip()) : null;

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/SongLib.fxml"));
		final Parent root = loader.load();

		final SongLibController controller = loader.getController();

		if(!controller.edit(old, new Song(name, artist, album, year))){
			getAlert("Error", "Name and Artist are the same as an existing song", "Please enter a unique song");
			return;
		}

		final Scene scene = new Scene(root);
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	private void getAlert(final String title, final String header, final String content){
			final Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(stage);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			alert.showAndWait();
	}
}
