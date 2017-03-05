package cs2fd16.tarnjot.assignment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class PoliceRecords {

    private ArrayList<Crime> crimes;

    /*----------------------------------------------------------------*/
    public PoliceRecords() { crimes = new ArrayList<>(); }

    public PoliceRecords(Crime... c)
    {
        crimes = new ArrayList<>();

        //Add all the crimes into the arraylist
        for(Crime crime : c)
            crimes.add(crime);
    }

    public PoliceRecords(Node pr) throws Exception
    {
        crimes = new ArrayList<>();
        if(pr.getNodeName() != "police_records")
            throw new Exception("xml file is not a police record");
        else
        {
            NodeList list = pr.getChildNodes();
            for(int i = 0; i < list.getLength(); i++)
                crimes.add(new Crime(list.item(i)));
        }
    }
    /*---------------------------------------------------------------*/
    public void setCrimes(ArrayList<Crime> crimes) { this.crimes = crimes; }

    public ArrayList<Crime> getCrimes() { return crimes; }
    public Crime getCrime(int index) { return crimes.get(index); }
    public void addCrime(Crime crime) { crimes.add(crime); }

    public Element getElement(Document doc)
    {
        Element root = doc.createElement("police_records");
        for(Crime crime : crimes)
            root.appendChild(crime.getElement(doc));

        return root;
    }

    public String report()
    {
        String s =
                "Police Report\n\n" +
                "There have been " + crimes.size() + " reported crime(s).\n\n";

        for(Crime c : crimes)
            s += "----------------------------------\n" + c.genReport();

        return s;
    }

}
