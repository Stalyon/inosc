package com.stalyon.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A MvtStk.
 */
@Entity
@Table(name = "mvt_stk")
public class MvtStk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_mvt", nullable = false)
    private LocalDate dateMvt;

    @Size(max = 3)
    @Column(name = "code_mvt", length = 3)
    private String codeMvt;

    @NotNull
    @Size(max = 18)
    @Column(name = "item_number", length = 18, nullable = false)
    private String itemNumber;

    @Size(max = 20)
    @Column(name = "magasin", length = 20)
    private String magasin;

    @Size(max = 20)
    @Column(name = "emplacement", length = 20)
    private String emplacement;

    @NotNull
    @Column(name = "qte", nullable = false)
    private Float qte;

    @Size(max = 12)
    @Column(name = "num_ordre", length = 12)
    private String numOrdre;

    @Size(max = 4)
    @Column(name = "num_ligne_ordre", length = 4)
    private String numLigneOrdre;

    @Size(max = 12)
    @Column(name = "lot_number", length = 12)
    private String lotNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateMvt() {
        return dateMvt;
    }

    public MvtStk dateMvt(LocalDate dateMvt) {
        this.dateMvt = dateMvt;
        return this;
    }

    public void setDateMvt(LocalDate dateMvt) {
        this.dateMvt = dateMvt;
    }

    public String getCodeMvt() {
        return codeMvt;
    }

    public MvtStk codeMvt(String codeMvt) {
        this.codeMvt = codeMvt;
        return this;
    }

    public void setCodeMvt(String codeMvt) {
        this.codeMvt = codeMvt;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public MvtStk itemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getMagasin() {
        return magasin;
    }

    public MvtStk magasin(String magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public MvtStk emplacement(String emplacement) {
        this.emplacement = emplacement;
        return this;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Float getQte() {
        return qte;
    }

    public MvtStk qte(Float qte) {
        this.qte = qte;
        return this;
    }

    public void setQte(Float qte) {
        this.qte = qte;
    }

    public String getNumOrdre() {
        return numOrdre;
    }

    public MvtStk numOrdre(String numOrdre) {
        this.numOrdre = numOrdre;
        return this;
    }

    public void setNumOrdre(String numOrdre) {
        this.numOrdre = numOrdre;
    }

    public String getNumLigneOrdre() {
        return numLigneOrdre;
    }

    public MvtStk numLigneOrdre(String numLigneOrdre) {
        this.numLigneOrdre = numLigneOrdre;
        return this;
    }

    public void setNumLigneOrdre(String numLigneOrdre) {
        this.numLigneOrdre = numLigneOrdre;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public MvtStk lotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
        return this;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MvtStk)) {
            return false;
        }
        return id != null && id.equals(((MvtStk) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MvtStk{" +
            "id=" + getId() +
            ", dateMvt='" + getDateMvt() + "'" +
            ", codeMvt='" + getCodeMvt() + "'" +
            ", itemNumber='" + getItemNumber() + "'" +
            ", magasin='" + getMagasin() + "'" +
            ", emplacement='" + getEmplacement() + "'" +
            ", qte=" + getQte() +
            ", numOrdre='" + getNumOrdre() + "'" +
            ", numLigneOrdre='" + getNumLigneOrdre() + "'" +
            ", lotNumber='" + getLotNumber() + "'" +
            "}";
    }
}
