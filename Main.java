package sk.itsovy.javaSql;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Date;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("jozo");

        File readFile = new File("C:\\Users\\jpavu\\Desktop\\JAVA\\projectRodneCislo\\TextIn.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\jpavu\\Desktop\\JAVA\\projectRodneCislo\\TextOut.txt");

        try {


            Scanner sc = new Scanner(readFile);
            Scanner sc2 = null;

            try {
                sc2 = new Scanner(new File("C:\\Users\\jpavu\\Desktop\\JAVA\\projectRodneCislo\\TextIn.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc2.hasNextLine()) {
                //premenne pre string
                String Str = sc2.nextLine();
                System.out.println(Str);
                String[] arr = Str.split(" ");
                System.out.println(Str);
                String bnumRaw = arr[2].replace("/", "");

                long bnumLong = Long.parseLong(bnumRaw);
                int bnumCompare = Integer.parseInt(bnumRaw.substring(3, 4));
                int bnumCompare2 = Integer.parseInt(bnumRaw.substring(4, 6));
                int bnumCompare3 = Integer.parseInt(bnumRaw.substring(0, 2));
                String bdate = Str.split(" ")[3];

                String bdateRaw = bdate.replace(".", "");

                int bmonth = Integer.parseInt(bdateRaw.substring(3, 4));
                int bday = Integer.parseInt(bdateRaw.substring(0, 2));
                int byear = Integer.parseInt(bdateRaw.substring(6, 8));
                String fname = arr[0];
                String lname = arr[1];
                String rc = arr[2];
                String date = arr[3].replace(".", "-");

                    if (rc.matches("^[0-9]{2}[0,1,5,6][0-9]{3}\\/?[0-9]{3,4}$") && bnumLong % 11 == 0 &&
                            bnumCompare == bmonth && bnumCompare2 == bday && bnumCompare3 == byear) {
                    }
                    System.out.println(date);

                    SimpleDateFormat dateformat1 = new SimpleDateFormat("dd-MM-yyyy");
                    Date date1 = null;
                    try {
                        date1 = dateformat1.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(date1);

                        Person person1 = new Person(fname, lname, date1, rc);
                        Database db1 = new Database();
                        db1.insertNewPerson(person1);
                        List<Person> persons = db1.selectAll();

                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                        Document doc = docBuilder.newDocument();

                        Element rootPersons = doc.createElement("Persons");
                        doc.appendChild(rootPersons);

                        for (int i = 1; i < persons.size(); i++) {

                            System.out.println(persons.get(i).getName() + " " + persons.get(i).getSurname());

                            Element person = doc.createElement("Person");
                            rootPersons.appendChild(person);

                            Element name = doc.createElement("FirstName");
                            name.appendChild(doc.createTextNode(persons.get(i).getName()));// tu meno
                            person.appendChild(name);

                            Element lastname = doc.createElement("LastName");
                            lastname.appendChild(doc.createTextNode(persons.get(i).getSurname()));// tu priezvisko
                            person.appendChild(lastname);

                            Element dnar = doc.createElement("Date");
                            dnar.appendChild(doc.createTextNode(persons.get(i).getDob().toString()));// tu date
                            person.appendChild(dnar);

                            Element birthnum = doc.createElement("BirthNumber");
                            birthnum.appendChild(doc.createTextNode(persons.get(i).getBnum()));// tu bnum
                            person.appendChild(birthnum);

                        }

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File("file.xml"));

                        transformer.transform(source, result);

                        System.out.println("File saved!");


                        sc.close();
                        fileWriter.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (TransformerConfigurationException e) {
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }

                }




        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}