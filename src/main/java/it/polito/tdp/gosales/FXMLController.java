package it.polito.tdp.gosales;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.Model;
import it.polito.tdp.gosales.model.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {

    private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbBrand;

    @FXML
    private ComboBox<?> cmbProdotto;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextArea txtResGrafo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaPercorso(ActionEvent event) {

    }
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String brand = this.cmbBrand.getValue();
    	if (brand==null) {
    		this.txtResGrafo.setText("Please select a brand.");
    		return;
    	}
    	
    	Integer anno = this.cmbAnno.getValue();
    	if (anno == null) {
    		this.txtResGrafo.setText("Please select a year.");
    		return;
    	}
    	
    
    	
    	//creazione grafp.
    	this.model.buildGraph(brand, anno);
    	
    	List<Products> vertici = this.model.getProductsByBrand(brand);
    	this.txtResGrafo.setText("Grafo creato.\n"+ "Ci sono " + this.model.getNumNodi() + " vertici.\n" + "Ci sono " + this.model.getNumArchi()+ " archi.\n");
    	
    	txtArchi.setText("Top 3 archi: \n");
        List<Arco> topArchi = this.model.getTopArchi();

        for (Arco a : topArchi)
            txtArchi.appendText(a+"\n");

        txtArchi.appendText("Nodi che compaiono almeno due volte:\n");
        List<Integer> repeatedProdsID = this.model.repeatedProducts();

        for (Integer i : repeatedProdsID)
            txtArchi.appendText("Product ID : " + i + "\n");
    
    
    }

    @FXML
    void initialize() {
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBrand != null : "fx:id=\"cmbBrand\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProdotto != null : "fx:id=\"cmbProdotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArchi != null : "fx:id=\"txtArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResGrafo != null : "fx:id=\"txtResGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	this.cmbAnno.getItems().setAll(2015, 2016, 2017, 2018);
    	this.cmbBrand.getItems().setAll(model.getBrand());
    }

}
