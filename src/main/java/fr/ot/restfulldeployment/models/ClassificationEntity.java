package fr.ot.restfulldeployment.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NamedQuery(name = "classification.getAll",
        query = "SELECT c FROM ClassificationEntity c ORDER BY c.idClassification")
@Table(name = "Classification", schema = "dbo", catalog = "CRKF")
public class ClassificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_classification")
    private int idClassification;
    @Basic
    @Column(name = "Classification")
    private String classification;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="classification")
    private List<FamilleEntity> familles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassificationEntity that = (ClassificationEntity) o;

        if (idClassification != that.idClassification) return false;
        if (classification != null ? !classification.equals(that.classification) : that.classification != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClassification;
        result = 31 * result + (classification != null ? classification.hashCode() : 0);
        return result;
    }
}
