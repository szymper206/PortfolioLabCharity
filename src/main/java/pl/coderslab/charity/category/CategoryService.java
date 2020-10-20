package pl.coderslab.charity.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
