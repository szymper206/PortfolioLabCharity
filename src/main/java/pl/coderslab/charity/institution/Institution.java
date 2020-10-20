package pl.coderslab.charity.institution;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "institution")
    private List<Donation> donations = new ArrayList<>();

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
