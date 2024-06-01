package gmail.vezhur2003.blps.secondary;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    List<VacancyEntity> findVacancyEntitiesByConfirmationFalse(PageRequest pageRequest);

    List<VacancyEntity> findVacancyEntitiesByConfirmationTrue(PageRequest pageRequest);
}
