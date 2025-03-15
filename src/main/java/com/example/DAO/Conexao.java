package com.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final String url = "jdbc:postgresql://localhost:5432/biblioteca";
    private final String usuario = "postgres";
    private final String senha = "Senhapost";

    public Connection conectar(){
        try {
            Connection conectar = DriverManager.getConnection(url, usuario, senha);

            if (conectar != null){
                return conectar;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar banco de dados: " + e.getMessage());
        }

        return null;
    }
}