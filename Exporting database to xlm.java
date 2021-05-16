package com.learningjava;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        // connecting to mysql database using the jdbc mysql connector
        Class.forName("com.mysql.cj.jdbc.Driver");
        // connecting to local hosted database and putting it into connect
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employee" ,
                "root" ,
                // password hidden artificially for safety purposes
                "****"
        );
        // giving MYSQL query to the connected database
        Statement statement = connect.createStatement();
        // storing received data in rs
        ResultSet rs = statement.executeQuery("select * from EmployeeRecords");
        // building the file
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // add elements to Document
            Element rootElement = doc.createElement("EmployeeRecords");
            // append root element to document
            doc.appendChild(rootElement);
            while (rs.next()) {
            // append first child element to root element
            rootElement.appendChild(createUserElement(doc,
                    String.valueOf(rs.getInt("EmpID")),
                    rs.getString("EmpName"),
                    String.valueOf(rs.getLong("EmpSalary")),
                    rs.getString("EmpCity")));
                System.out.println(rootElement);
            }
            // for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("xmldb.xml"));

            // write data
            transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node createUserElement(Document doc, String EmpID,
                                          String EmpName, String EmpSalary, String EmpCity) {
        Element user = doc.createElement("employee");

        // set EmpID attribute
        user.setAttribute("EmpID", EmpID);

        // create EmpName element
        user.appendChild(createUserElements(doc, user, "EmpName", EmpName));

        // create EmpSalary element
        user.appendChild(createUserElements(doc, user, "EmpSalary", EmpSalary));

        // create EmpCity element
        user.appendChild(createUserElements(doc, user, "EmpCity", EmpCity));

        return user;
    }

    // utility method to create text node
    private static Node createUserElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}