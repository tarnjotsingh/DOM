package cs2fd16.tarnjot.assignment;

//To parse and save into memory
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//To output a DOM document object to System.out
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.geometry.HPos;
import org.w3c.dom.Document;
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * The majority of the code that has been used in the Write class has to do with JavaFX to set up the interface for the user.
 */
public class Write extends Application{

    private PoliceRecords policeRecords;
    private Stage stage;

    /*
     *addPerson, addItems, reportCrime do not use anything of the XML DOM packages, they simply provide a JavaFX
     *interface for the user.
     */

    private void addPerson(Stage owner, Crime crime, char type)
    {
        Stage dBox = new Stage();
        VBox root = new VBox(10);
        GridPane pane = new GridPane();

        Button add = new Button("Add");

        Label
                idL = new Label("National ID: "),
                ageL = new Label("Age: "),
                fNameL = new Label("First Name: "),
                lNameL = new Label("Last Name: ");
        TextField
                idF = new TextField("0"),
                ageF = new TextField("0"),
                fNameF = new TextField(),
                lNameF = new TextField();

        pane.add(idL, 0 , 0);
        pane.add(ageL, 0, 1);
        pane.add(fNameL, 0, 2);
        pane.add(lNameL,0, 3);

        pane.add(idF, 1 , 0);
        pane.add(ageF, 1, 1);
        pane.add(fNameF, 1, 2);
        pane.add(lNameF,1, 3);
        pane.add(add, 1, 4);
        pane.setHalignment(add, HPos.RIGHT);

        add.setOnAction(e -> {
            if(type == 'V') {
                crime.addVictims(new Victim(Integer.parseInt(idF.getText()), Integer.parseInt(ageF.getText()), fNameF.getText(), lNameF.getText()));
            }
            else if(type == 'W') {
                crime.addWitnesses(new Witness(Integer.parseInt(idF.getText()), Integer.parseInt(ageF.getText()), fNameF.getText(), lNameF.getText()));
            }

            dBox.close();
        });

        add.setAlignment(Pos.TOP_RIGHT);

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(2); pane.setVgap(5);
        root.getChildren().addAll(pane);

        dBox.initModality(Modality.APPLICATION_MODAL);
        dBox.initOwner(owner);
        dBox.setTitle((type == 'V' ? "Add Victim" : "Add Witness"));
        dBox.setResizable(false);
        dBox.setScene(new Scene(root));
        dBox.showAndWait();
    }

    private void addItems(Stage owner, Crime crime, char type)
    {
        Stage dBox = new Stage();
        VBox root = new VBox(10);
        GridPane pane = new GridPane();

        Button add = new Button("Add");

        Label
                typeL = new Label("Type: "),
                titleL = new Label("Title: "),
                detailsL = new Label("Details");
        TextField
                typeF =  new TextField(),
                titleF = new TextField(),
                detailsF = new TextField();

        pane.add(typeL, 0, 0);
        pane.add(titleL, 0, 1);
        pane.add(detailsL, 0, 2);

        pane.add(typeF, 1, 0);
        pane.add(titleF, 1, 1);
        pane.add(detailsF, 1, 2);
        pane.add(add, 1, 3);
        pane.setHalignment(add, HPos.RIGHT);

        add.setOnAction(e -> {
            if(type == 'E')
                crime.addEvidence(new Item(typeF.getText(), titleF.getText(), detailsF.getText()));
            else if(type == 'S')
                crime.addCrimeSpecific(new Item(typeF.getText(), titleF.getText(), detailsF.getText()));

            dBox.close();
        });

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(2); pane.setVgap(5);
        root.getChildren().addAll(pane);

        dBox.initModality(Modality.APPLICATION_MODAL);
        dBox.initOwner(owner);
        dBox.setTitle((type == 'E' ? "Add Evidence" : "Add Crime Specific"));
        dBox.setResizable(false);
        dBox.setScene(new Scene(root));
        dBox.showAndWait();
    }

    private void reportCrime()
    {
        //New crime object to store all the data to.
        Crime crime = new Crime();

        Stage dBox = new Stage();
        VBox root = new VBox(10);
        GridPane pane = new GridPane();

        Button
                report = new Button("Report"),
                addVict = new Button("Add"),
                addWitn = new Button("Add"),
                addEvid = new Button("Add"),
                addCS = new Button("Add");
        Label
                titleL = new Label("Title: "),
                dateL = new Label("Date: "),
                timeL = new Label("Time: "),
                areaL = new Label("Area: "),
                addressL = new Label("Address: "),
                priorityL = new Label("Priority: "),
                descL = new Label("Description: "),
                victL = new Label("Victims: "), victN = new Label("0"),
                witnL = new Label("Witnesses: "), witnN = new Label("0"),
                evidL = new Label("Evidence: "), evidN = new Label("0"),
                crimL = new Label("Crime Specific: "), crimN = new Label("0");
        TextField
                titleF = new TextField(),
                dateF = new TextField(),
                timeF = new TextField() ,
                areaF = new TextField(),
                addressF = new TextField(),
                priorityF = new TextField(),
                descF = new TextField();

        pane.add(titleL, 0, 0);
        pane.add(dateL, 0, 1);
        pane.add(timeL, 0, 2);
        pane.add(areaL, 0, 3);
        pane.add(addressL, 0, 4);
        pane.add(priorityL, 0, 5);
        pane.add(descL, 0, 6);
        pane.add(victL, 0, 7);
        pane.add(witnL, 0, 8);
        pane.add(evidL, 0, 9);
        pane.add(crimL, 0, 10);

        pane.add(titleF, 1, 0);
        pane.add(dateF, 1, 1);
        pane.add(timeF, 1, 2);
        pane.add(areaF, 1, 3);
        pane.add(addressF, 1, 4);
        pane.add(priorityF, 1, 5);
        pane.add(descF, 1, 6);

        pane.add(victN, 1, 7);
        pane.add(addVict, 1, 7);    pane.setHalignment(addVict, HPos.RIGHT);
        pane.add(witnN, 1, 8);
        pane.add(addWitn, 1, 8);    pane.setHalignment(addWitn, HPos.RIGHT);
        pane.add(evidN, 1, 9);
        pane.add(addEvid, 1, 9);    pane.setHalignment(addEvid, HPos.RIGHT);
        pane.add(crimN, 1, 10);
        pane.add(addCS, 1, 10);     pane.setHalignment(addCS, HPos.RIGHT);
        pane.add(report, 1, 11);    pane.setHalignment(report, HPos.RIGHT);

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(2); pane.setVgap(5);
        root.getChildren().addAll(pane);

        /*The setOnAction handlers here simply open up another window and update the counter to show how many
         *victims/witnesses/evidence/crime specifics have been added.*/
        addVict.setOnAction(e -> {
            addPerson(dBox, crime, 'V');
            victN.setText("" + crime.getVictimNum());
        });

        addWitn.setOnAction(e -> {
            addPerson(dBox, crime, 'W');
            witnN.setText("" + crime.getWitnissNum());
        });

        addEvid.setOnAction(e -> {
            addItems(dBox, crime, 'E');
            evidN.setText("" + crime.getEvidenceNum());
        });

        addCS.setOnAction(e -> {
            addItems(dBox, crime, 'S');
            crimN.setText("" + crime.getCrimeSpecificNum());
        });

        //Store all data that is stored as text nodes into the crime object that was created at the start of the method.
        report.setOnAction(e -> {
            crime.setTitle(titleF.getText());
            crime.setDate(dateF.getText());
            crime.setTime(timeF.getText());
            crime.setArea(areaF.getText());
            crime.setAddress(addressF.getText());
            crime.setPriority(priorityF.getText());
            crime.setDescription(descF.getText());

            policeRecords.addCrime(crime);
            dBox.close();
        });

        dBox.initModality(Modality.APPLICATION_MODAL);
        dBox.initOwner(stage);
        dBox.setTitle("Report Crime");
        dBox.setScene(new Scene(root));

            dBox.showAndWait();
    }

    private void saveXml()
    {
        FileChooser chooser = new FileChooser();

        chooser.setTitle("Save XML");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML File", "*.xml")
        );

        File saveTo = chooser.showSaveDialog(stage);

        if(saveTo == null)
            return;

        try
        {
            //Create a new DOM Document object.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.newDocument();

            //Have the data stored in the xmlDoc
            xmlDoc.appendChild(policeRecords.getElement(xmlDoc));

            //Now to write and save to the xml doc
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(saveTo);

            transformer.transform(source, result);
        }
        catch(Exception ex) {System.out.println(ex);}
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        policeRecords = new PoliceRecords();
        stage = primaryStage;

        VBox root = new VBox(10);
        HBox buttons = new HBox(10);

        Button report = new Button("Report Crime");
        Button save = new Button("Save XML");

        Label label = new Label("Report crimes and export them to an XML file");

        buttons.getChildren().addAll(report, save);
        buttons.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, buttons);
        root.setPadding(new Insets(10,10, 10, 10));

        //Setting the event handlers for the two buttons
        report.setOnAction(e -> reportCrime());
        save.setOnAction(e -> saveXml());


        primaryStage.setTitle("Police Records");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String args[]) { launch(args); }
}
