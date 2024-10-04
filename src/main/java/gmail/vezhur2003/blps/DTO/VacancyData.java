package gmail.vezhur2003.blps.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import gmail.vezhur2003.blps.secondary.VacancyEntity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacancyData {

    private Long id;
    private String name;
    private String topic;
    private Long userId;
    private Boolean confirmation;
    private Long salary;
    private String location;
    private String company;
    private String contact;
    private String shortDescription;
    private String longDescription;

    public VacancyData(VacancyEntity vacancy) {
        this.setId(vacancy.getId());
        this.setName(vacancy.getName());
        this.setTopic((vacancy.getTopic()));
        this.setUserId(vacancy.getUserId());
        this.setConfirmation(vacancy.getConfirmation());
        this.setSalary(vacancy.getSalary());
        this.setLocation(vacancy.getLocation());
        this.setCompany(vacancy.getCompany());
        this.setContact(vacancy.getContact());
        this.setShortDescription(vacancy.getShortDescription());
        this.setLongDescription(vacancy.getLongDescription());
    }

    public VacancyData(Long id, String name, String topic, Long userId, Boolean confirmation,
                       Long salary, String location, String company, String contact,
                       String shortDescription, String longDescription) {
        this.setId(id);
        this.setName(name);
        this.setTopic(topic);
        this.setUserId(userId);
        this.setConfirmation(confirmation);
        this.setSalary(salary);
        this.setLocation(location);
        this.setCompany(company);
        this.setContact(contact);
        this.setShortDescription(shortDescription);
        this.setLongDescription(longDescription);
    }
}
