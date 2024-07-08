package br.com.jhowsoftware.resgateanimal.services;

public interface RabbitMQService
{
    void enviarMensagem(String nomeFila, Object mensagem);
}
