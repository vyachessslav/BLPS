package gmail.vezhur2003.blps.camunda;

import gmail.vezhur2003.blps.DTO.VacancyData;
import gmail.vezhur2003.blps.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CamundaConfiguration {

    private final VacancyService vacancyService;

    private final TransactionTemplate transactionTemplate;

    @Bean
    @ExternalTaskSubscription(value = "searchVacancies")
    public ExternalTaskHandler searchVacanciesHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            List<VacancyData> vacancies = vacancyService.searchVacancies(0, 10);
            StringBuilder vacancyNames = new StringBuilder("");
            for (VacancyData vacancy : vacancies) {
                vacancyNames.append(vacancy.getName()).append(", ");
            }
            helper.completeWithVar("vacancy_list", vacancyNames.toString());
        }, Optional.of(transactionTemplate));
    }

    @Bean
    @ExternalTaskSubscription(value = "getVacancyById", variableNames = {"vacancy_id"})
    public ExternalTaskHandler getVacancyByIdHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            Long vacancyId = helper.getVarOrThrow("vacancy_id");
            VacancyData vacancyData = vacancyService.getVacancyById(vacancyId);
            helper.completeWithVar("vacancy_name", vacancyData.getName());
        }, Optional.of(transactionTemplate));
    }

    @Bean
    @ExternalTaskSubscription(value = "deleteVacancy", variableNames = {"vacancy_id", "user_id"})
    public ExternalTaskHandler deleteVacancyHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            Long vacancyId = helper.getVarOrThrow("vacancy_id");
            Long userId = helper.getVarOrThrow("user_id");
            vacancyService.deleteVacancyById(vacancyId, userId);
            externalTaskService.complete(externalTask);
        }, Optional.of(transactionTemplate));
    }

    @Bean
    @ExternalTaskSubscription(value = "createVacancy", variableNames = {"name", "topic", "user_id"})
    public ExternalTaskHandler createVacancyHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            String name = helper.getVarOrThrow("name");
            String topic = helper.getVarOrThrow("topic");
            Long userId = helper.getVarOrThrow("user_id");
            VacancyData vacancyData = new VacancyData(1L, name, topic, userId, false,
                    1000L, "SPB", "Gasprom", "gasprom@mail.ru",
                    "test", "testtest");
            vacancyService.createVacancy(vacancyData);
            externalTaskService.complete(externalTask);
        }, Optional.of(transactionTemplate));
    }

    @Bean
    @ExternalTaskSubscription(value = "confirmVacancy", variableNames = {"vacancy_id"})
    public ExternalTaskHandler confirmVacancyHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            Long vacancyId = helper.getVarOrThrow("vacancy_id");
            vacancyService.confirmVacancy(vacancyId);
            externalTaskService.complete(externalTask);
        }, Optional.of(transactionTemplate));
    }

    @Bean
    @ExternalTaskSubscription(value = "getUnconfirmedVacancies")
    public ExternalTaskHandler getUnconfirmedVacanciesHandler() {
        return new MyExternalTaskHandler((externalTask, externalTaskService, helper) -> {
            List<VacancyData> vacancies = vacancyService.unconfirmedVacancies(0, 10);
            StringBuilder vacancyNames = new StringBuilder("");
            for (VacancyData vacancy : vacancies) {
                vacancyNames.append(vacancy.getName()).append(", ");
            }
            helper.completeWithVar("vacancy_list", vacancyNames.toString());
        }, Optional.of(transactionTemplate));
    }
}
