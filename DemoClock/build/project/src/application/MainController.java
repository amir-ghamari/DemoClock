package application;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainController implements Initializable {

	@FXML
	private Label hourLabel;

	@FXML
	private Label minLabel;

	@FXML
	private Label secLabel;

	@FXML
	private Label amPm;
	
	@FXML
    private ImageView btnExit;

	String time[];
	int intHour;

	Thread dateThread;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dateThread = new Thread(this::handleThread);
		dateThread.start();

	}

	private void handleThread() {

		Date date;
		while (true) {
			date = new Date();
			time = date.toString().split(" ");
			String s[] = time[3].split(":");

			Platform.runLater(() -> {

				minLabel.setText(s[1]);
				secLabel.setText(s[2]);
				intHour = Integer.parseInt(s[0]);
				if (intHour < 12 & intHour != 0) {
					
					intHour = intHour;
					hourLabel.setText(String.valueOf(intHour));
					amPm.setText("AM");
				} 
				else if (intHour ==0) {
					intHour = intHour + 12;
					hourLabel.setText(String.valueOf(intHour));
					amPm.setText("AM");
				}
				else {
					intHour = intHour - 12;
					hourLabel.setText(String.valueOf(intHour));
					amPm.setText("PM");
				}
			});

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	public void btnExit(ActionEvent ae){
		System.exit(1);
	}

}
