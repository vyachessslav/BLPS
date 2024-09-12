package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.UserData;
import gmail.vezhur2003.blps.DTO.VacancyData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<Long, VacancyData> vacancyKafkaTemplate;
    private final KafkaTemplate<String, UserData> userKafkaTemplate;
    @Value(value = "${app.kafka.topic-names.vacancy}")
    private String vacancyTopicName;
    @Value(value = "${app.kafka.topic-names.user}")
    private String userTopicName;

    public void sendVacancy(VacancyData issueDto) {
        vacancyKafkaTemplate.send(vacancyTopicName, issueDto.getId(), issueDto);
        vacancyKafkaTemplate.flush();
    }

    public void sendUser(String login, UserData user) {
        userKafkaTemplate.send(userTopicName, login, user);
        userKafkaTemplate.flush();
    }
}

