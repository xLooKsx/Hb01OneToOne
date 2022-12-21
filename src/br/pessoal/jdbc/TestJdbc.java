package br.pessoal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        connectionTest("hb-01-one-to-one-uni");
        connectionTest("hb-03-one-to-many");
    }

    private static void connectionTest(final String dataBaseName) {
        final String jdbcUrl = "jdbc:mysql://localhost:3306/"+dataBaseName+"?useSSL=false";
        final String user= "hbstudent";
        final String pass= "hbstudent";

        try {
            System.out.println("Connecting to database "+jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful !!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
