package gmail.vezhur2003.blps.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import gmail.vezhur2003.blps.entity.VacancyEntity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacancyData {

    private Long id;
    private String name;
    private Boolean confirmation;
    private Long salary;
    private String location;
    private String company;
    private String contact;
    private String shortDescription;
    private String longDescription;
    private Long userId;

    public VacancyData(VacancyEntity vacancy) {
        this.setId(vacancy.getId());
        this.setName(vacancy.getName());
        this.setConfirmation(vacancy.getConfirmation());
        this.setSalary(vacancy.getSalary());
        this.setLocation(vacancy.getLocation());
        this.setCompany(vacancy.getCompany());
        this.setContact(vacancy.getContact());
        this.setShortDescription(vacancy.getShortDescription());
        this.setLongDescription(vacancy.getLongDescription());
        this.setUserId(vacancy.getUserId());
    }
}
