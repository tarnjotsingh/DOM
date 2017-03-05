package cs2fd16.tarnjot.assignment;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Item {

    private String attribute; //type of item
    private String title, details;

    public Item(String attr, String t, String d)
    {
        attribute = attr;
        title = t;
        details = d;
    }

    public Item(Node it)
    {
        NodeList list = it.getChildNodes();
        attribute = ((Element)it).getAttribute("type");

        title = list.item(0).getTextContent();
        details = list.item(1).getTextContent();
    }

    public void setAttribute(String attribute) { this.attribute = attribute; }
    public void setDetails(String details) { this.details = details; }
    public void setTitle(String title) { this.title = title; }

    public String getAttribute() { return attribute; }
    public String getDetails() { return details; }
    public String getTitle() { return title; }

    public Element getElement(Document doc)
    {
        Element item = doc.createElement("item");
        item.setAttribute("type", attribute);

        Element t = doc.createElement("title");
        t.appendChild(doc.createTextNode(title));
        item.appendChild(t);

        Element d = doc.createElement("details");
        d.appendChild(doc.createTextNode(details));
        item.appendChild(d);

        return item;
    }

    public String toText()
    {
        return
                attribute + "\n" +
                title + "\n" +
                "Details: " + details + "\n";
    }

    public String toString() { return attribute + " " + title + " " + details; }
}
