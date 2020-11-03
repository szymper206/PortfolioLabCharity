package pl.coderslab.charity.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/institution")
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "institution/form";
    }

    @PostMapping("/add")
    public String addInstitution(@ModelAttribute @Valid Institution institution,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "institution/form";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/institution/all";
    }

    @GetMapping("/all")
    public String allInstitution(Model model) {
        model.addAttribute("institutions", institutionService.findAllInstitution());
        return "institution/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditInstitution(@PathVariable long id, Model model) {
        Optional<Institution> institution = institutionService.findInstitutionById(id);
        if (!institution.isPresent()) {
            model.addAttribute("errorMessage", "Nie ma instytucji o takim id");
            return "error";
        }
        model.addAttribute("institution", institution.get());
        return "institution/form";
    }

    @PostMapping("/edit/{id}")
    public String editInstitution(@Valid Institution institution,
                                       BindingResult result,
                                       @PathVariable long id,
                                       Model model) {
        if (result.hasErrors()) {
            return "institution/form";
        }
        if (institution.getId() != id) {
            model.addAttribute("errorMessage", "id sie nie zgadzają");
            return "error";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/institution/all";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteInstitution(@PathVariable long id, Model model) {
        Optional<Institution> institution = institutionService.findInstitutionById(id);
        if (!institution.isPresent()) {
            model.addAttribute("errorMessage", "Nie ma instytucji o takim id");
            return "error";
        }
        model.addAttribute("institution", institution.get());
        institutionService.deleteInstitution(institution.get());
        return "redirect:/institution/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteInstitution(@Valid Institution institution,
                                   BindingResult result,
                                   @PathVariable long id,
                                   Model model) {
        if (result.hasErrors()) {
            return "redirect:/institution/all";
        }
        if (institution.getId() != id) {
            model.addAttribute("errorMessage", "id sie nie zgadzają");
            return "error";
        }
        institutionService.deleteInstitution(institution);
        return "redirect:/institution/all";
    }
}
