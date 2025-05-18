package br.com.jhowsoftware.resgateanimal.services.impl;

import br.com.jhowsoftware.resgateanimal.controllers.TipoDenunciaController;
import br.com.jhowsoftware.resgateanimal.services.RabbitMQService;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroInexistente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService
{
    private static final Logger LOG_TECNICO = LoggerFactory.getLogger(RabbitMQServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensagem(String nomeFila, Object mensagem)
    {
        try
        {
            LOG_TECNICO.info("Enviando mensagem para a fila: {}", nomeFila);
            this.rabbitTemplate.convertAndSend(nomeFila,mensagem);
            LOG_TECNICO.info("Mensagem enviada com sucesso para a fila: {}", nomeFila);
        }
        catch (RegistroInexistente e)
        {
            LOG_TECNICO.error("Erro ao enviar a mensagem para a fila: {}", nomeFila, e);
            throw new RegistroInexistente("Erro ao enviar a mensagem para a fila: " + nomeFila);
        }
    }
}
