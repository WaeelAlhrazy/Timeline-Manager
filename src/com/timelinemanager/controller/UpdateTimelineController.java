package com.timelinemanager.controller;

import java.util.Optional;

import com.timelinemanager.Entity.Timeline;
import com.timelinemanager.model.TimelineModel;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * 
 * 
 * @author
 * @version 0.00.00
 * @name UpdateTimelineController.java
 */
public class UpdateTimelineController {

	/*
	 * Elements and user input variables from the "Update timeline" window
	 */
	@FXML
	private AnchorPane updateNewTimeline_anchorPane;
	@FXML
	private DatePicker updateDatePicker_startDate;
	@FXML
	private DatePicker updateDatePicker_endDate;
	@FXML
	private Button updateEditTimelineButton;
	@FXML
	private Button updateCancelEditTimeline;
	@FXML
	private TextArea updateTimelineDescription;
	@FXML
	private TextField updateTimelineTitle;

	private Timeline updatedTimeline;
	private TimelineModel timelineModel;

	@FXML
	public void initialize() {
		
		//Action event for the Update Timeline button.
		//Opens a new window with the data from the current timeline which can be modified and saved.
		updateEditTimelineButton.setOnAction(ev -> {
			this.timelineModel.setTimeline(updatedTimeline);
			((Node) (ev.getSource())).getScene().getWindow().hide();
		});
		
		//ActionEvent for the cancel button inside the Update current timeline window
		//Opens a pop-up window asking for exit confirmation
		updateCancelEditTimeline.setOnAction(cancelEvent -> {
			
			Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
	                			"Are you sure you want to cancel editing the timeline?");
			updateCancelEditTimeline = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
	        	closeConfirmation.setHeaderText("Confirm Exit");
	        	closeConfirmation.initModality(Modality.APPLICATION_MODAL);
	        	Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
	        	if (!ButtonType.OK.equals(closeResponse.get())) {
	        		  cancelEvent.consume();
	        	}
	        	else
	        		((Node)(cancelEvent.getSource())).getScene().getWindow().hide();
			});
	}

	/**
	 * Initializes the TimelineModel which this class will get data from when a
	 * new timeline is created or updated.
	 * 
	 * @param timelineModel
	 *            - timelineModel object to send and receive data from.
	 */
	public void initTimelineModel(TimelineModel timelineModel) {
		if (this.timelineModel != null) {
			throw new IllegalStateException("Model can only be initiated once");
		}

		this.timelineModel = timelineModel;
		this.updatedTimeline = timelineModel.getTimeline().getValue();
		updateTimelineTitle.setText(updatedTimeline.getTitle());
		updateTimelineDescription.setText(updatedTimeline.getDescription());			
		updateDatePicker_startDate.setValue(updatedTimeline.getStartDate());
		updateDatePicker_endDate.setValue(updatedTimeline.getEndDate());
		
		// Code for when timeline was changed.
		timelineModel.getTimeline().addListener((ChangeListener<Timeline>) (observable, oldValue, newValue) -> {
			
		});
	}
}