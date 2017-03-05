package cs2fd16.tarnjot.assignment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Victim extends Person {

    public Victim(int nID, int age, String firstName, String lastName)
    {
        super(nID, age, firstName, lastName);
    }

    public Victim(Node n)
    {
        super(n);
    }

    @Override
    public Element getElement(Document doc) {

        Element victim = doc.createElement("victim");

        //national id
        Element id = doc.createElement("national_id");
        id.appendChild(doc.createTextNode(""+nID));
        victim.appendChild(id);

        //age
        Element a = doc.createElement("age");
        a.appendChild(doc.createTextNode(""+ age));
        victim.appendChild(a);

        //firstname
        Element fName = doc.createElement("first_name");
        fName.appendChild(doc.createTextNode(firstName));
        victim.appendChild(fName);

        //lastname
        Element lName = doc.createElement("last_name");
        lName.appendChild(doc.createTextNode(lastName));
        victim.appendChild(lName);


        return victim;
    }
}
