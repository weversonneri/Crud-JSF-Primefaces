package br.com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectarBD {

    private static final String URL = "jdbc:mysql://localhost:3306/CadastroJogos?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection con;

    public static Connection abrirConexao() {
        try {
            
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conectado");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO");
            ex.printStackTrace();
        }
        return con;
    }
}