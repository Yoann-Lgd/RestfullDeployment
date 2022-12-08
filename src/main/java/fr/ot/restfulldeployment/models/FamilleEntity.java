package fr.ot.restfulldeployment.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedQuery(name = "famille.getAll",
        query = "SELECT f FROM FamilleEntity f ORDER BY f.idFamille")
@Table(name = "Famille", schema = "dbo", catalog = "CRKF")
public class FamilleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_famille")
    private int idFamille;
    @Basic
    @Column(name = "Famille")
    private String famille;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classification", referencedColumnName = "id_classification")
    private ClassificationEntity classification;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamilleEntity that = (FamilleEntity) o;

        if (idFamille != that.idFamille) return false;
        if (classification != that.classification) return false;
        if (famille != null ? !famille.equals(that.famille) : that.famille != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFamille;
        result = 31 * result + (famille != null ? famille.hashCode() : 0);
        result = 31 * result + (classification != null ? classification.hashCode() : 0);
        return result;
    }
}
