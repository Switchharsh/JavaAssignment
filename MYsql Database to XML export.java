package com.learningjava;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.sql.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, TransformerConfigurationException {

	// write your code here
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

        try {
            // building document
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // setting root node of the xml
            Element rootElement = doc.createElement("EmployeeRecords");
            doc.appendChild(rootElement);

            while (rs.next()) {
                // ensuring the whole database is gone through
                // setting element Employee
                Element Employee = doc.createElement("Employee");
                rootElement.appendChild(Employee);
                // setting attribute id for employee
                Attr attr = doc.createAttribute("id");
                // taking value of id from the database
                attr.setValue(String.valueOf(rs.getInt("EmpID")));
                Employee.setAttributeNode(attr);
                // setting element Empname and taking value from the database
                Element EmpName = doc.createElement("EmpName");
                EmpName.appendChild(doc.createTextNode(rs.getString("EmpName")));
                // appending element as a child of Employee element
                Employee.appendChild(EmpName);
                // setting an element Salary and taking its value from db
                Element Salary = doc.createElement("EmpSalary");
                Salary.appendChild(doc.createTextNode(String.valueOf(rs.getLong("EmpSalary"))));
                // appending the data to the Employee node as a child
                Employee.appendChild(Salary);
                // same as above
                Element EmpCity = doc.createElement("EmpCity");
                EmpCity.appendChild(doc.createTextNode(rs.getString("EmpName")));
                Employee.appendChild(EmpCity);
            }
            /* putting data to a xml file, for windows try to push
               data in a file outside of C or the windows drive
               because of the permissions
             */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // prints with proper newlines
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            // exporting to emp.xml in H drive
            StreamResult result = new StreamResult(new File("H:\\emp.xml"));
            transformer.transform(source, result);
            // also streaming output to console
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
