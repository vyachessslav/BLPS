package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.primary.UserEntity;
import gmail.vezhur2003.blps.secondary.VacancyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<Long, VacancyEntity> vacancyKafkaTemplate;
    private final KafkaTemplate<String, UserEntity> userKafkaTemplate;
    @Value(value = "${app.kafka.topic-names.vacancy}")
    private String vacancyTopicName;
    @Value(value = "${app.kafka.topic-names.user}")
    private String userTopicName;

    public void sendVacancy(VacancyEntity vacancyEntity) {
        vacancyKafkaTemplate.send(vacancyTopicName, vacancyEntity.getId(), vacancyEntity);
        vacancyKafkaTemplate.flush();
    }

    public void sendUser(String login, UserEntity user) {
        userKafkaTemplate.send(userTopicName, login, user);
        userKafkaTemplate.flush();
    }
}

