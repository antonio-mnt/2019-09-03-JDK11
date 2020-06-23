/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	/*txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...");*/
    	
    	int n = 1;
    	
    	try {
    		n = Integer.parseInt(this.txtPassi.getText());
    	}catch(NumberFormatException ne) {
    		this.txtResult.setText("Inserire una lunghezza per il cammino.");
    		return;
    	}
    	
    	if(this.boxPorzioni.getValue()==null) {
    		this.txtResult.setText("Selezionare una porzione ed accertarsi di aver creato il grafo.");
    		return;
    	}
    	
    	
    	this.model.trovaPercoso(this.boxPorzioni.getValue(), n);
    	
    	this.txtResult.clear();
    	
    	if(this.model.getBest()==null) {
    		this.txtResult.setText("Cammino non trovato.");
    		return;
    	}
    	
    	this.txtResult.appendText("Peso del cammino: "+this.model.getPesoTotale()+"\n");
    	for(String s: this.model.getBest()) {	
    		this.txtResult.appendText(s+"\n");
    	}
    	
    	
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	/*txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...");*/
    	
    	if(this.boxPorzioni.getValue()==null) {
    		this.txtResult.setText("Selezionare una porzione.");
    		return;
    	}
    	
    	this.txtResult.setText(this.model.getElencoConnessi(this.boxPorzioni.getValue()));
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	/*txtResult.clear();
    	txtResult.appendText("Creazione grafo...");*/
    	
    	int n = 0;
    	
    	try {
    		n = Integer.parseInt(this.txtCalorie.getText());
    	}catch(NumberFormatException ne) {
    		this.txtResult.setText("Inserire un numero di calorie.");
    		return;
    	}
    	
    	this.model.creaGrafo(n);
    	
    	this.boxPorzioni.getItems().addAll(this.model.getPorzioni());
    	
    	System.out.println(this.model.getNUmeroVertici()+"  "+this.model.getNumeroArchi());
    	
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
