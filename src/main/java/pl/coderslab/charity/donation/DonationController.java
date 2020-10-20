package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/donation")
public class DonationController {
    private final DonationService donationService;
    private final CategoryService categoryService;


    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/add")
    public String addDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/donation")
    public String addDonation(@Valid Donation donation, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        donationService.saveDonation(donation);
        return "form-confirmation";
    }
}
