package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.services.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(String nomeFila, Object mensagem)
    {
        this.rabbitTemplate.convertAndSend(nomeFila,mensagem);
    }
}
