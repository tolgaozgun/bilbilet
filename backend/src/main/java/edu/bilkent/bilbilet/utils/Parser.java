package edu.bilkent.bilbilet.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("in.txt"));
        
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] strArr = line.split(" ");
            
            if (strArr.length > 2 && strArr[0].equalsIgnoreCase("CREATE") && strArr[1].equalsIgnoreCase("TABLE")) {
                String curModel = strArr[2].replaceAll("\\(", "");
                PrintWriter write = new PrintWriter(new File(curModel + ".java"));
                
                write.println(
                    "package edu.bilkent.bilbilet.model;"
                    + "\n"
                    + "\nimport lombok.AllArgsConstructor;"
                    + "\nimport java.math.BigDecimal;"
                    + "\nimport java.sql.Timestamp;"
                    + "\nimport jakarta.persistence.Id;"
                    + "\nimport jakarta.validation.constraints.NotBlank;"
                    + "\nimport jakarta.validation.constraints.NotNull;"
                    + "\nimport lombok.Data;"
                    + "\nimport lombok.NoArgsConstructor;"
                    + "\n"
                    + "\n@Data"
                    + "\n@NoArgsConstructor"
                    + "\n@AllArgsConstructor"
                    + "\npublic class " + curModel + " {"
                    + "\n\t@Id"
                );

                String curLine = scan.nextLine();
                String[] curStrArr = curLine.split(" ");

                while (curStrArr.length > 0 && !curStrArr[0].equals(");")) {
                    if (curStrArr.length < 2 || curStrArr[4].contains("PRIMARY") || curStrArr[4].contains("FOREIGN") || curStrArr[4].contains("CONSTRAINT")) {
                        curLine = scan.nextLine();
                        curStrArr = curLine.split(" ");
                        continue;
                    }
                    
                    // List<String> debug = Arrays.asList(curStrArr);
                    // System.out.println(debug);

                    String curPropertyName = curStrArr[4];
                    String curPropertyType = curStrArr[5];
                    String _property = "NOT_RECOGNIZED";
                    String _assertion = "";

                    if (curPropertyType.contains("VARCHAR")) {
                        _property = "String";
                        _assertion = (curStrArr.length > 2) ? "@NotBlank" : "";
                    }
                    else if (curPropertyType.contains("TEXT")) {
                        _property = "String";
                        _assertion = (curStrArr.length > 2) ? "@NotBlank" : "";
                    }
                    else if (curPropertyType.contains("INT")) {
                        _property = "int";
                        _assertion = (curStrArr.length > 2) ? "@NotNull" : "";
                    }
                    else if (curPropertyType.contains("NUMERIC")) {
                        _property = "BigDecimal";
                        _assertion = (curStrArr.length > 2) ? "@NotNull" : "";
                    }
                    else if (curPropertyType.contains("BOOLEAN")) {
                        _property = "boolean";
                        _assertion = (curStrArr.length > 2) ? "@NotNull" : "";
                    }
                    else if (curPropertyType.contains("DATETIME")) {
                        _property = "Timestamp";
                        _assertion = (curStrArr.length > 2) ? "@NotNull" : "";
                    }

                    if (_assertion.length() > 0) {
                        write.println(
                            "\t" + _assertion
                            + "\n\tprivate " + _property + " " + curPropertyName + ";\n"
                        );
                    }
                    else {
                        write.println("\n\tprivate " + _property + " " + curPropertyName + ";\n");
                        System.out.println(_property);
                    }

                    if (scan.hasNextLine()) {
                        curLine = scan.nextLine();
                        curStrArr = curLine.split(" ");
                    }
                    else {
                        break;
                    }
                }

                write.println("}");
                write.close();
            }
        }
        
        scan.close();
        System.out.println("Done!");
    }
}