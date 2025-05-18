package br.com.jhowsoftware.resgateanimal.messaging;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.utils.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer
{
    private static final Logger LOG_TECNICO = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = RabbitMQConstants.FILA_TIPO_DENUNCIA, containerFactory = "rabbitListenerContainerFactory")
    public void consumirTipoDenuncia(TipoDenunciaDTO tipoDenuncia)
    {
        LOG_TECNICO.info("Mensagem recebida do RabbitMQ: A requisicao do Tipo Denuncia {} ID:{} foi consumido com sucesso!", tipoDenuncia.getTipoDenuncia(), tipoDenuncia.getIdTipoDenuncia());
    }
}