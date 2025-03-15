package com.example.dominio.Notificação;

import com.example.dominio.Usuario;

public class NotificacaoEmail implements Notificacao{
    @Override
    public void enviarNotificacao(String texto, Usuario user){
        System.out.println("Enviando notificação por email para: " + user.getEmail());
    }
}
