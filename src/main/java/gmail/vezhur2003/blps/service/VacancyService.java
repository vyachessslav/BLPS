package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import gmail.vezhur2003.blps.DTO.VacancyData;
import gmail.vezhur2003.blps.entity.VacancyEntity;
import gmail.vezhur2003.blps.repository.VacancyRepository;
import gmail.vezhur2003.blps.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private UserRepository userRepository;

    public VacancyData createVacancy(VacancyData vacancy) {

        if (!userRepository.getById(vacancy.getUserId()).getRole().equals(Role.EMPLOYER)) {
            throw new IllegalArgumentException("User must be employer");
        }
        if (vacancy.getName() == null || vacancy.getName().isEmpty()) {
            throw new IllegalArgumentException("Vacancy name cannot be empty");
        }
        if (vacancy.getName().length() > 255) {
            throw new IllegalArgumentException("Vacancy name cannot be longer than 255 symbols");
        }

        VacancyEntity vacancyEntity = new VacancyEntity();
        vacancyEntity.setName(vacancy.getName());
        vacancyEntity.setConfirmation(vacancy.getConfirmation());
        vacancyEntity.setSalary(vacancy.getSalary());
        vacancyEntity.setCompany(vacancy.getCompany());
        vacancyEntity.setLocation(vacancy.getLocation());
        vacancyEntity.setContact(vacancy.getContact());
        vacancyEntity.setShortDescription(vacancy.getShortDescription());
        vacancyEntity.setLongDescription(vacancy.getLongDescription());
        vacancyEntity.setUserId(vacancy.getUserId());

        return new VacancyData(vacancyRepository.save(vacancyEntity));
    }

    public VacancyData getVacancyById(Long vacancyId) {
        try {
            VacancyEntity vacancy = vacancyRepository.findById(vacancyId).orElse(null);
            if (vacancy == null) {
                return new VacancyData();
            }
            return new VacancyData(vacancy);
        } catch (Exception e) {
            throw new IllegalArgumentException("Internal error while finding vacancy");
        }
    }

    public void deleteVacancyById(Long vacancyId, Long userId) {
        try {
            VacancyEntity vacancy = vacancyRepository.findById(vacancyId).orElse(null);
            if (vacancy == null) {
                throw new IllegalArgumentException("There is no vacancy with this id");
            }
            if (!Objects.equals(userId, vacancy.getUserId())) {
                throw new IllegalArgumentException("Vacancy does not belong to this user");
            }
            vacancyRepository.deleteById(vacancyId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Internal error while deleting vacancy");
        }
    }

    public List<VacancyData> searchVacancies(Integer offset, Integer limit) {
        List<VacancyEntity> vacancyEntities = vacancyRepository.findVacancyEntitiesByConfirmationTrue
                (PageRequest.of(offset, limit));
        List<VacancyData> vacancyDataList = new ArrayList<>();
        for (VacancyEntity ie : vacancyEntities) {
            vacancyDataList.add(new VacancyData(ie));
        }
        return vacancyDataList;
    }

    public List<VacancyData> unconfirmedVacancies(Long userId, Integer offset, Integer limit) {
        if (!userRepository.getById(userId).getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("User must be admin");
        }
        List<VacancyEntity> vacancyEntities = vacancyRepository.findVacancyEntitiesByConfirmationFalse(
                PageRequest.of(offset, limit));
        List<VacancyData> vacancyDataList = new ArrayList<>();
        for (VacancyEntity ie : vacancyEntities) {
            vacancyDataList.add(new VacancyData(ie));
        }
        return vacancyDataList;
    }

    public VacancyData confirmVacancy(Long vacancyId, Long userId) {
        if (!userRepository.getById(userId).getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("User must be admin");
        }
        VacancyEntity vacancy;
        try {
            vacancy = vacancyRepository.findById(vacancyId).orElse(null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Internal error while finding vacancy");
        }

        if (vacancy == null) {
            throw new IllegalArgumentException("No vacancy with this id");
        }

        if (vacancy.getConfirmation()) {
            throw new IllegalArgumentException("Vacancy is already confirmed");
        }
        vacancy.setConfirmation(true);

        return new VacancyData(vacancyRepository.save(vacancy));
    }
}
