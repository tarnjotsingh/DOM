package cs2fd16.tarnjot.assignment;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;


public class Crime {

    String title, date, time, area, address, priority, description;
    ArrayList<Person> victims, witnesses;
    ArrayList<Item> evidence, crimeSpecific;

    public Crime()
    {
        title = "title";
        date = "date";
        time = "00:00:00";
        area = "area";
        address = "address";
        priority = "priority";
        description = "description";
        victims = new ArrayList<>();
        witnesses = new ArrayList<>();
        evidence = new ArrayList<>();
        crimeSpecific = new ArrayList<>();
    }

    public Crime(String title, String date, String time, String area, String address, String priority, String description)
    {
        this.title = title;
        this.date = date;
        this.time = time;
        this.area = area;
        this.address = address;
        this.priority = priority;
        this.description = description;
        victims = new ArrayList<>();
        witnesses = new ArrayList<>();
        evidence = new ArrayList<>();
        crimeSpecific = new ArrayList<>();
    }

    //Second constructor takes in a node as an argument which is used when reading an XML file.
    public Crime(Node cr) throws Exception
    {
        if(cr.getNodeName() != "crime")
            throw new Exception("not a crime node");
        else
        {
            NodeList list = cr.getChildNodes();

            for(int i = 0; i < list.getLength(); i++)
                System.out.println(list.item(i).getNodeName());

            title = list.item(0).getTextContent();
            date = list.item(1).getTextContent();
            time = list.item(2).getTextContent();
            area = list.item(3).getTextContent();
            address = list.item(4).getTextContent();
            priority = list.item(5).getTextContent();
            description = list.item(6).getTextContent();

            //Populate the victims arraylist
            victims = new ArrayList<>();
            NodeList vicList = list.item(7).getChildNodes();
            for(int i = 0; i < vicList.getLength(); i++)
                victims.add(new Victim(vicList.item(i)));

            //populate the witnesses arraylist
            witnesses = new ArrayList<>();
            NodeList witList = list.item(8).getChildNodes();
            for(int i = 0; i < witList.getLength(); i++)
                witnesses.add(new Witness(witList.item(i)));

            //populate the evidence arraylist
            evidence = new ArrayList<>();
            NodeList eviList = list.item(9).getChildNodes();
            for(int i = 0; i < eviList.getLength(); i++)
                evidence.add(new Item(eviList.item(i)));

            //populate the crime specific arraylist
            crimeSpecific = new ArrayList<>();
            NodeList csList = list.item(10).getChildNodes();
            for(int i = 0; i < csList.getLength(); i++)
                crimeSpecific.add(new Item(csList.item(i)));
        }
    }

    public void setTitle(String t) { title = t; }
    public void setDate(String d) { date = d; }
    public void setTime(String t) { time = t; }
    public void setArea(String a) { area = a; }
    public void setAddress(String a) { address = a; }
    public void setPriority(String p) { priority = p; }
    public void setDescription(String d) { description = d; }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getArea() { return area; }
    public String getAddress() { return address; }
    public String getPriority() { return priority; }
    public String getDescription() { return description; }
    public ArrayList<Person> getVictims() { return victims; }
    public ArrayList<Person> getWitnesses() { return witnesses; }
    public ArrayList<Item> getEvidence() { return evidence; }
    public ArrayList<Item> getCrimeSpecific() { return crimeSpecific; }
    public int getVictimNum() { return victims.size(); }
    public int getWitnissNum() { return witnesses.size(); }
    public int getEvidenceNum() { return evidence.size(); }
    public int getCrimeSpecificNum() { return crimeSpecific.size(); }


    public void addVictims(Victim... v) { for(Victim victim : v) victims.add(victim); }
    public void addWitnesses(Witness... w) { for(Witness witness : w) witnesses.add(witness); }
    public void addEvidence(Item... evid) { for(Item item : evid) evidence.add(item); }
    public void addCrimeSpecific(Item... items) { for(Item i : items) crimeSpecific.add(i); }

    public Element getElement(Document doc)
    {
        Element crime = doc.createElement("crime");

        //title
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(this.title));
        crime.appendChild(title);

        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(this.date));
        crime.appendChild(date);

        //time
        Element time = doc.createElement("time");
        time.appendChild(doc.createTextNode(this.time));
        crime.appendChild(time);

        //area
        Element area = doc.createElement("area");
        area.appendChild(doc.createTextNode(this.area));
        crime.appendChild(area);

        //address
        Element address = doc.createElement("address");
        address.appendChild(doc.createTextNode(this.address));
        crime.appendChild(address);

        //priority
        Element priority = doc.createElement("priority");
        priority.appendChild(doc.createTextNode(this.priority));
        crime.appendChild(priority);

        //description
        Element desc = doc.createElement("description");
        desc.appendChild(doc.createTextNode(this.description));
        crime.appendChild(desc);

        //victims
        Element vict = doc.createElement("victims");
        for(Person v : victims)
            vict.appendChild(v.getElement(doc));
        crime.appendChild(vict);

        //witnesses
        Element witn = doc.createElement("witnesses");
        for(Person w : witnesses)
            witn.appendChild(w.getElement(doc));
        crime.appendChild(witn);

        //evidence
        Element ev = doc.createElement("evidence");
        for(Item e : evidence)
            ev.appendChild(e.getElement(doc));
        crime.appendChild(ev);

        //crime specific
        Element sp = doc.createElement("crime_specific");
        for(Item i : crimeSpecific)
            sp.appendChild(i.getElement(doc));
        crime.appendChild(sp);

        return crime;
    }


    public String genReport()
    {
        String report = title + " : Priority " + priority + "\n" +
                "At " + time + " on " + date + " at " + address + ", " + area+ " , a crime took place.\n" +
                "Description of the crime:\n" + description + " .\n\n" +
                "Victims Involved:\n";

        for(Person v : victims)
            report += v.toText() + "\n";

        report += "Witnisses Involved:\n";

        for(Person w : witnesses)
            report += w.toText() + "\n";

        report += "Evidence:\n";

        for(Item e : evidence)
            report += e.toText() + "\n";

        report += "Crime Specific:\n";

        for(Item i : crimeSpecific)
            report += i.toText() + "\n";

        return report;
    }

    public String toString() { return title + " " + date + " " + time + " " + area + " " + address + " " + priority + " " + description + " " + victims + " " + witnesses + " " + evidence + " " + crimeSpecific; }
}
