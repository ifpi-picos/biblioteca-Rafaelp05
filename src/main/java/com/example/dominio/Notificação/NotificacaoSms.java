package com.example.dominio.Notificação;

import com.example.dominio.Usuario;

public class NotificacaoSms implements Notificacao{
    @Override
    public void enviarNotificacao(String texto, Usuario user){
        System.out.println("Enviando notificação por sms para: " + user.getNome());
    }
}