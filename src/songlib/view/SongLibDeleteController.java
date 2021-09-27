/*
 * @author Apurva Narde
 * @author Max Geiger
 */

package songlib.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import songlib.io.SongLibIO;
import songlib.io.Song;

public class SongLibDeleteController {

	@FXML Text deleteName, deleteArtist, deleteAlbum, deleteYear;

	private Song deleteSong;

	public void switchToDefault(final ActionEvent e) throws IOException {
		final Parent root = FXMLLoader.load(getClass().getResource("/songlib/view/SongLib.fxml"));
		final Scene scene = new Scene(root);
		final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void setField(final Song song){
		deleteName.setText(song.getName());
		deleteArtist.setText(song.getArtist());
		if(song.getAlbum() != null) deleteAlbum.setText(song.getAlbum());
		if(song.getYear() != null) deleteYear.setText(song.getYear().toString());
		deleteSong = song;
	}

	public void deleteSong(final ActionEvent e) throws IOException{
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songlib/view/SongLib.fxml"));
		final Parent root = loader.load();

		final SongLibController controller = loader.getController();

		controller.delete(deleteSong);

		final Scene scene = new Scene(root);
		final Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
