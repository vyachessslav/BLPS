package gmail.vezhur2003.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gmail.vezhur2003.blps.DTO.VacancyContext;
import gmail.vezhur2003.blps.DTO.VacancyData;
import gmail.vezhur2003.blps.DTO.ManyVacanciesContext;
import gmail.vezhur2003.blps.service.VacancyService;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
@CrossOrigin
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/{id}")
    public ResponseEntity<VacancyContext> getVacancyById(@PathVariable Long id) {
        try {
            VacancyData vacancy = vacancyService.getVacancyById(id);
            return ResponseEntity.ok(new VacancyContext(vacancy));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new VacancyContext(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new VacancyContext("Internal error while finding vacancy"));
        }
    }

    @GetMapping("/allVacancies")
    public ResponseEntity<ManyVacanciesContext> searchVacancies() {
        try {
            List<VacancyData> vacancies = vacancyService.searchVacancies();
            return ResponseEntity.ok(new ManyVacanciesContext(vacancies));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ManyVacanciesContext(e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<VacancyContext> createVacancy(@RequestBody VacancyData vacancy)   {
        try {
            VacancyData createdVacancy = vacancyService.createVacancy(vacancy);
            return ResponseEntity.ok(new VacancyContext(createdVacancy));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new VacancyContext(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new VacancyContext("Internal error while creating vacancy"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVacancyById(@PathVariable Long id, @RequestParam Long userId) {
        try {
            vacancyService.deleteVacancyById(id, userId);
            return ResponseEntity.ok("Vacancy deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Internal error while deleting vacancy");
        }
    }
}
