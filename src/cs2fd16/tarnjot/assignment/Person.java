package cs2fd16.tarnjot.assignment;


import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Person {

    protected int nID, age;
    protected String firstName, lastName;

    public  Person() {}

    public Person(int nID, int age, String firstName, String lastName)
    {
        this.nID = nID;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(Node p)
    {
        NodeList list = p.getChildNodes();

        nID = Integer.parseInt(list.item(0).getTextContent());
        age = Integer.parseInt(list.item(1).getTextContent());
        firstName = list.item(2).getTextContent();
        lastName = list.item(3).getTextContent();
    }

    public void setnID(int nID) { this.nID = nID; }
    public void setAge(int age) { this.age = age; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getnID() { return nID; }
    public int getAge() { return age; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    //Abstract getElement method because this method is going to be overridden
    public abstract Element getElement(Document doc);

    public String toText()
    {
        return
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Age: " + age + "\n" +
                "National ID: " + nID + "\n";
    }

    public String toString() { return nID + " " + age + " " + firstName + " " + lastName; }
}
