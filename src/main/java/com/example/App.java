
package com.example;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.DAO.Conexao;
import com.example.dominio.Menu;

public class App {
    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        Menu menu = new Menu(conexao.conectar());
        menu.iniciarMenu();
    }
}
