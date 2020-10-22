package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.findAllInstitution();
    }

    @ModelAttribute("allBags")
    public Integer showAllBags() {
        return donationService.getAllBags();
    }

    @ModelAttribute("allDonations")
    public long showNumberOfDonations() {
        return donationService.findAllDonations();
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }



}
