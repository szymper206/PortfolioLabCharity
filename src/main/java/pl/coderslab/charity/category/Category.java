package pl.coderslab.charity.category;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Donation donation;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
