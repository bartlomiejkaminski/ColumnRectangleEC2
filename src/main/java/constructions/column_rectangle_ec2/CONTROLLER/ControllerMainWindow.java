package constructions.column_rectangle_ec2.CONTROLLER;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.ResourceBundle;

import constructions.column_rectangle_ec2.MODEL.Container;
import constructions.column_rectangle_ec2.MODEL.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerMainWindow implements Initializable {

	// TextFields
	@FXML
	private TextField textFieldB;

	@FXML
	private TextField textFieldH;

	@FXML
	private TextField textFieldA;

	@FXML
	private TextField textFieldFCK;

	@FXML
	private TextField textFieldFYK;

	@FXML
	private TextField textFieldFI;

	@FXML
	private TextField textFieldPelzanie;

	@FXML
	private TextField textFieldYC;

	@FXML
	private TextField textFieldYS;

	@FXML
	private TextField textFieldNED;

	@FXML
	private TextField textFieldMED;

	@FXML
	private TextField textFieldEc;

	@FXML
	private TextField textFieldEs;

	@FXML
	private TextField textFieldDHd;

	@FXML
	private TextField textFieldU;

	@FXML
	private CheckBox checkBoxIfSymetric;

	// Labels Dane
	@FXML
	private Label label_b;

	@FXML
	private Label label_h;

	@FXML
	private Label label_a;

	@FXML
	private Label label_fck;

	@FXML
	private Label label_fyk;

	@FXML
	private Label label_fi;

	@FXML
	private Label label_eff;

	@FXML
	private Label label_yc;

	@FXML
	private Label label_ys;

	@FXML
	private Label label_NEd;

	@FXML
	private Label label_MEd;

	@FXML
	private Label label_Ec;

	@FXML
	private Label label_Es;

	@FXML
	private Label label_Hd;

	@FXML
	private Label label_u;

	Data data;
	Container container = Container.getInstance();
	
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		
		data = container.getData(); // Get instance Data

		// Binding b
		textFieldB.textProperty().bindBidirectional(data.b_Property());
		label_b.textProperty().bind(data.b_Property());

		// Binding h
		textFieldH.textProperty().bindBidirectional(data.h_Property());
		label_h.textProperty().bind(data.h_Property());

		// Binding a
		textFieldA.textProperty().bindBidirectional(data.a_Property());
		label_a.textProperty().bind(data.a_Property());

		// Binding fck
		textFieldFCK.textProperty().bindBidirectional(data.fck_Property());
		label_fck.textProperty().bind(data.fck_Property());

		// Binding fyk
		textFieldFYK.textProperty().bindBidirectional(data.fyk_Property());
		label_fyk.textProperty().bind(data.fyk_Property());

		// Binding fi
		textFieldFI.textProperty().bindBidirectional(data.fi_Property());
		label_fi.textProperty().bind(data.fi_Property());

		// Binding eff
		textFieldPelzanie.textProperty().bindBidirectional(data.eff_Property());
		label_eff.textProperty().bind(data.eff_Property());

		// Binding yc
		textFieldYC.textProperty().bindBidirectional(data.yc_Property());
		label_yc.textProperty().bind(data.yc_Property());

		// Binding ys
		textFieldYS.textProperty().bindBidirectional(data.ys_Property());
		label_ys.textProperty().bind(data.ys_Property());

		// Binding NEd
		textFieldNED.textProperty().bindBidirectional(data.Ned_Property());
		label_NEd.textProperty().bind(data.Ned_Property());

		// Binding MEd
		textFieldMED.textProperty().bindBidirectional(data.Med_Property());
		label_MEd.textProperty().bind(data.Med_Property());

		// Binding Ec
		textFieldEc.textProperty().bindBidirectional(data.Ec_Property());
		// label_MEd.textProperty().bind(data.Ec_Property());

		// Binding Es
		textFieldEs.textProperty().bindBidirectional(data.Es_Property());
		// label_MEd.textProperty().bind(data.Es_Property());

		// Binding Hd
		textFieldDHd.textProperty().bindBidirectional(data.Hd_Property());
		label_Hd.textProperty().bind(data.Hd_Property());

		// Binding u
		textFieldU.textProperty().bindBidirectional(data.u_Property());
		label_u.textProperty().bind(data.u_Property());

		// Binding u
		textFieldU.textProperty().bindBidirectional(data.u_Property());
		label_u.textProperty().bind(data.u_Property());

		// Binding ifSymetric
		checkBoxIfSymetric.selectedProperty().bindBidirectional(data.ifSymetric_Property());
		// label_u.textProperty().bind(data.u_Property());

	}

}
