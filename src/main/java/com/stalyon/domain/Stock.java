package com.stalyon.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Stock.
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 18)
    @Column(name = "item_number", length = 18, nullable = false)
    private String itemNumber;

    @NotNull
    @Size(max = 20)
    @Column(name = "magasin", length = 20, nullable = false)
    private String magasin;

    @NotNull
    @Size(max = 20)
    @Column(name = "emplacement", length = 20, nullable = false)
    private String emplacement;

    @NotNull
    @Column(name = "qte_stk", nullable = false)
    private Float qteStk;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public Stock itemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getMagasin() {
        return magasin;
    }

    public Stock magasin(String magasin) {
        this.magasin = magasin;
        return this;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public Stock emplacement(String emplacement) {
        this.emplacement = emplacement;
        return this;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Float getQteStk() {
        return qteStk;
    }

    public Stock qteStk(Float qteStk) {
        this.qteStk = qteStk;
        return this;
    }

    public void setQteStk(Float qteStk) {
        this.qteStk = qteStk;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id=" + getId() +
            ", itemNumber='" + getItemNumber() + "'" +
            ", magasin='" + getMagasin() + "'" +
            ", emplacement='" + getEmplacement() + "'" +
            ", qteStk=" + getQteStk() +
            "}";
    }
}
