package com.learningjava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

        public class Main {
            public static void main(String[] args) throws Exception {
                // connecting to mysql database using the jdbc musql connector
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
                // Apache POI XSLX excel workbook
                XSSFWorkbook workbook = new XSSFWorkbook();
                // creating new excel sheet
                XSSFSheet spreadsheet = workbook.createSheet("employe db");
                // creating new row
                XSSFRow row = spreadsheet.createRow(0);
                // creating cell
                XSSFCell cell;

                cell = row.createCell(0);
                // Setting value to cells
                cell.setCellValue("EMP ID");
                cell = row.createCell(1);
                cell.setCellValue("EMP NAME");
                cell = row.createCell(2);
                cell.setCellValue("SALARY");
                cell = row.createCell(3);
                cell.setCellValue("CITY");
                /* initializing an integer i to go through the
                   whole database and put it into relevant excel cell */
                int i = 1;

                while(rs.next()) {
                    row = spreadsheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(rs.getInt("EmpId"));
                    cell = row.createCell(1);
                    cell.setCellValue(rs.getString("EmpName"));
                    cell = row.createCell(2);
                    cell.setCellValue(rs.getInt("EmpSalary"));
                    cell = row.createCell(3);
                    cell.setCellValue(rs.getString("EmpCity"));
                    i++;
                }
                // outputting to the file
                FileOutputStream out = new FileOutputStream(new File("exceldatabase.xlsx"));
                // writing
                workbook.write(out);
                // flushing to the file
                out.close();
                System.out.println("exceldatabase.xlsx written successfully");
            }
        }
