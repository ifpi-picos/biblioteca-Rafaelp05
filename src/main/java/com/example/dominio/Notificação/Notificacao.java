package com.example.dominio.Notificação;

import com.example.dominio.Usuario;

public interface Notificacao {
    void enviarNotificacao(String texto, Usuario user);
}