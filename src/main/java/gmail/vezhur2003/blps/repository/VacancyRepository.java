package gmail.vezhur2003.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gmail.vezhur2003.blps.entity.VacancyEntity;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    List<VacancyEntity> findAll();
}
