package gmail.vezhur2003.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gmail.vezhur2003.blps.entity.VacancyEntity;

@Getter
@Setter
@ToString
public class VacancyContext {
    private boolean success;
    private gmail.vezhur2003.blps.DTO.VacancyData vacancy;
    private String error;

    public VacancyContext() {
        this.setSuccess(false);
        this.setVacancy(null);
        this.setError("empty vacancy context");
    }

    public VacancyContext(String error) {
        this.setSuccess(false);
        this.setVacancy(null);
        this.setError(error);
    }

    public VacancyContext(VacancyEntity vacancy) {
        this.setSuccess(true);
        this.setError("");
        this.setVacancy(new gmail.vezhur2003.blps.DTO.VacancyData(vacancy));
    }

    public VacancyContext(gmail.vezhur2003.blps.DTO.VacancyData vacancy) {
        this.setSuccess(true);
        this.setError("");
        this.setVacancy(vacancy);
    }
}
