package gmail.vezhur2003.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ManyVacanciesContext {
    private boolean success;
    private List<gmail.vezhur2003.blps.DTO.VacancyData> vacancies;
    private String error;

    public ManyVacanciesContext() {
        this.setSuccess(false);
        this.setError("empty many answers context");
        this.setVacancies(new ArrayList<>());
    }

    public ManyVacanciesContext(List<gmail.vezhur2003.blps.DTO.VacancyData> vacancyDataList) {
        this.setSuccess(true);
        this.setError("");
        this.setVacancies(vacancyDataList);
    }

    public ManyVacanciesContext(String error) {
        this.setSuccess(false);
        this.setError(error);
        this.setVacancies(new ArrayList<>());
    }


}
