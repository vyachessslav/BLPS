package gmail.vezhur2003.blps.config;

import gmail.vezhur2003.blps.DTO.UserData;
import gmail.vezhur2003.blps.DTO.VacancyData;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value(value = "${spring.kafka.bootstrap-server}")
    private String kafkaServer;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return props;
    }

    @Bean
    public Map<String, Object> vacancyProducerConfigs() {
        Map<String, Object> props = producerConfigs();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public Map<String, Object> userProducerConfigs() {
        Map<String, Object> props = producerConfigs();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Long, VacancyData> vacancyProducerFactory() {
        return new DefaultKafkaProducerFactory<>(vacancyProducerConfigs());
    }

    @Bean
    public ProducerFactory<String, UserData> userProducerFactory() {
        return new DefaultKafkaProducerFactory<>(userProducerConfigs());
    }

    @Bean(name = "vacancy-kafka-template")
    public KafkaTemplate<Long, VacancyData> issueKafkaTemplate() {
        return new KafkaTemplate<>(vacancyProducerFactory());
    }

    @Bean(name = "user-kafka-template")
    public KafkaTemplate<String, UserData> userKafkaTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }
}
