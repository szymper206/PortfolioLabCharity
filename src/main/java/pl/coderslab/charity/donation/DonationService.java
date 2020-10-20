package pl.coderslab.charity.donation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public Optional<Donation> findDonationById(long id) {
        return donationRepository.findById(id);
    }

    public void deleteDonation(Donation donation) {
        donationRepository.delete(donation);
    }

    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }

    public Integer getAllBags() {
        int allBags = 0;
        List<Donation> donations = findAllDonations();
        for (Donation donation: donations) {
            allBags += donation.getQuantity();
        }
        return allBags;
    }
}
