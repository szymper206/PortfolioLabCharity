package pl.coderslab.charity.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public void saveInstitution(Institution institution) {
        institutionRepository.save(institution);
    }

    public Optional<Institution> findInstitutionById(long id) {
        return institutionRepository.findById(id);
    }

    public void deleteCategory(Institution institution) {
        institutionRepository.delete(institution);
    }

    public List<Institution> findAllInstitution() {
        return institutionRepository.findAll();
    }
}
