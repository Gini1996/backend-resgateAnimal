package br.com.jhowsoftware.resgateanimal.messaging;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.utils.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer
{
    @RabbitListener(queues = RabbitMQConstants.FILA_TIPO_DENUNCIA, containerFactory = "rabbitListenerContainerFactory")
    public void consumirTipoDenuncia(TipoDenunciaDTO tipoDenuncia)
    {
       System.out.println("Mensagem recebida do RabbitMQ: A requisicao do Tipo Denuncia " + tipoDenuncia.getTipoDenuncia() + " ID: "+ tipoDenuncia.getIdTipoDenuncia() + " foi consumido com sucesso!");
    }
}