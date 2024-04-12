package application.data_base;

import java.sql.Connection;
import java.sql.DriverManager;

public class Data_Base_Conn {

    private static Connection connection;
    public static String etudiant_connecte;
    public static int prof_connecte;
    public static int admin_connecte;
    public static String nom_connecte;

    public static Connection getConnection() {

       

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

			 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_ecole","root","");

			 return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }


}
