package br.com.jhowsoftware.resgateanimal.config;

import br.com.jhowsoftware.resgateanimal.utils.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Component
@Configuration
@EnableRabbit
public class RabbitMQConnection
{
    private static final String NOME_EXCHANGE = "amq.direct";
    private final AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin)
    {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila)
    {
        return new Queue(nomeFila, true,false,false);
    }

    private DirectExchange trocaDireta()
    {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca)
    {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(),fila.getName(),null);
    }

    @PostConstruct
    private void adiciona()
    {
        Queue filaColoracao = this.fila(RabbitMQConstants.FILA_COLORACAO);
        Queue filaCondicao = this.fila(RabbitMQConstants.FILA_CONDICAO);
        Queue filaDenuncia = this.fila(RabbitMQConstants.FILA_DENUNCIA);
        Queue filaPelo = this.fila(RabbitMQConstants.FILA_PELO);
        Queue filaPorte = this.fila(RabbitMQConstants.FILA_PORTE);
        Queue filaResgate = this.fila(RabbitMQConstants.FILA_RESGATE);
        Queue filaTipoAnimal = this.fila(RabbitMQConstants.FILA_TIPO_ANIMAL);
        Queue filaTipoDenuncia = this.fila(RabbitMQConstants.FILA_TIPO_DENUNCIA);
        Queue filaTipoUsuario = this.fila(RabbitMQConstants.FILA_TIPO_USUARIO);
        Queue filaUsuario = this.fila(RabbitMQConstants.FILA_USUARIO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoColoracao = this.relacionamento(filaColoracao,troca);
        Binding ligacaoCondicao = this.relacionamento(filaCondicao,troca);
        Binding ligacaoDenuncia = this.relacionamento(filaDenuncia,troca);
        Binding ligacaoPelo = this.relacionamento(filaPelo,troca);
        Binding ligacaoPorte = this.relacionamento(filaPorte,troca);
        Binding ligacaoResgate = this.relacionamento(filaResgate,troca);
        Binding ligacaoTipoAnimal = this.relacionamento(filaTipoAnimal,troca);
        Binding ligacaoTipoDenuncia = this.relacionamento(filaTipoDenuncia,troca);
        Binding ligacaoTipoUsuario = this.relacionamento(filaTipoUsuario,troca);
        Binding ligacaoUsuario = this.relacionamento(filaUsuario,troca);

        this.amqpAdmin.declareQueue(filaColoracao);
        this.amqpAdmin.declareQueue(filaCondicao);
        this.amqpAdmin.declareQueue(filaDenuncia);
        this.amqpAdmin.declareQueue(filaPelo);
        this.amqpAdmin.declareQueue(filaPorte);
        this.amqpAdmin.declareQueue(filaResgate);
        this.amqpAdmin.declareQueue(filaTipoAnimal);
        this.amqpAdmin.declareQueue(filaTipoDenuncia);
        this.amqpAdmin.declareQueue(filaTipoUsuario);
        this.amqpAdmin.declareQueue(filaUsuario);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoColoracao);
        this.amqpAdmin.declareBinding(ligacaoCondicao);
        this.amqpAdmin.declareBinding(ligacaoDenuncia);
        this.amqpAdmin.declareBinding(ligacaoPelo);
        this.amqpAdmin.declareBinding(ligacaoPorte);
        this.amqpAdmin.declareBinding(ligacaoResgate);
        this.amqpAdmin.declareBinding(ligacaoTipoAnimal);
        this.amqpAdmin.declareBinding(ligacaoTipoDenuncia);
        this.amqpAdmin.declareBinding(ligacaoTipoUsuario);
        this.amqpAdmin.declareBinding(ligacaoUsuario);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
