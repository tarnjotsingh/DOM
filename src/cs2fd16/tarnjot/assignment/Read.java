package cs2fd16.tarnjot.assignment;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class Read extends Application {

    private PoliceRecords policeRecords;
    private Stage stage;
    private File file;
    private String fileS;

    private void loadXML()
    {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML File", "*.xml")
        );
        file = chooser.showOpenDialog(stage);
        if(file == null)
            return;

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(file);
            policeRecords = new PoliceRecords(xmlDoc.getFirstChild());

            fileS = file.getPath();
        }
        catch(Exception ex) { System.out.println(ex); }

    }

    //Provides a JavaFX window to display the report to the user.
    private void report()
    {
        Stage dBox = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 450, 600);

        TextArea textArea = new TextArea();
        textArea.setText(policeRecords.report());
        textArea.setEditable(false);

        root.getChildren().add(textArea);

        dBox.setResizable(false);
        dBox.initModality(Modality.APPLICATION_MODAL);
        dBox.initOwner(stage);
        dBox.setTitle("Police Records report");
        dBox.setScene(scene);
        dBox.showAndWait();
    }

    @Override
    public void start(Stage primaryStage)
    {
        policeRecords = new PoliceRecords();
        stage = primaryStage;

        VBox root = new VBox(10);
        HBox buttons = new HBox(10);

        Button loadXml = new Button("Load Records");
        Button genRepr = new Button("Generate Report");
        Label label = new Label("Load a Police Records xml file and generate report");
        Label filePath = new Label("No file selected");

        buttons.getChildren().addAll(loadXml, genRepr);
        buttons.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, buttons, filePath);
        root.setPadding(new Insets(10,10, 10, 10));

        loadXml.setOnAction(e ->{
            loadXML();
            if(file != null)
                filePath.setText(fileS);
        });

        genRepr.setOnAction(e -> report());

        primaryStage.setTitle("Police Records");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
