package com.stalyon.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Bom.
 */
@Entity
@Table(name = "bom")
public class Bom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 18)
    @Column(name = "item_number", length = 18, nullable = false)
    private String itemNumber;

    @Column(name = "bom_operation_number")
    private Integer bomOperationNumber;

    @Column(name = "bom_sequence_number")
    private Integer bomSequenceNumber;

    @NotNull
    @Size(max = 18)
    @Column(name = "component_number", length = 18, nullable = false)
    private String componentNumber;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @NotNull
    @Column(name = "bom_yield", nullable = false)
    private Float bomYield;

    @Column(name = "eff_date")
    private LocalDate effDate;

    @Column(name = "dis_date")
    private LocalDate disDate;

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

    public Bom itemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getBomOperationNumber() {
        return bomOperationNumber;
    }

    public Bom bomOperationNumber(Integer bomOperationNumber) {
        this.bomOperationNumber = bomOperationNumber;
        return this;
    }

    public void setBomOperationNumber(Integer bomOperationNumber) {
        this.bomOperationNumber = bomOperationNumber;
    }

    public Integer getBomSequenceNumber() {
        return bomSequenceNumber;
    }

    public Bom bomSequenceNumber(Integer bomSequenceNumber) {
        this.bomSequenceNumber = bomSequenceNumber;
        return this;
    }

    public void setBomSequenceNumber(Integer bomSequenceNumber) {
        this.bomSequenceNumber = bomSequenceNumber;
    }

    public String getComponentNumber() {
        return componentNumber;
    }

    public Bom componentNumber(String componentNumber) {
        this.componentNumber = componentNumber;
        return this;
    }

    public void setComponentNumber(String componentNumber) {
        this.componentNumber = componentNumber;
    }

    public Float getQuantity() {
        return quantity;
    }

    public Bom quantity(Float quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getBomYield() {
        return bomYield;
    }

    public Bom bomYield(Float bomYield) {
        this.bomYield = bomYield;
        return this;
    }

    public void setBomYield(Float bomYield) {
        this.bomYield = bomYield;
    }

    public LocalDate getEffDate() {
        return effDate;
    }

    public Bom effDate(LocalDate effDate) {
        this.effDate = effDate;
        return this;
    }

    public void setEffDate(LocalDate effDate) {
        this.effDate = effDate;
    }

    public LocalDate getDisDate() {
        return disDate;
    }

    public Bom disDate(LocalDate disDate) {
        this.disDate = disDate;
        return this;
    }

    public void setDisDate(LocalDate disDate) {
        this.disDate = disDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bom)) {
            return false;
        }
        return id != null && id.equals(((Bom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bom{" +
            "id=" + getId() +
            ", itemNumber='" + getItemNumber() + "'" +
            ", bomOperationNumber=" + getBomOperationNumber() +
            ", bomSequenceNumber=" + getBomSequenceNumber() +
            ", componentNumber='" + getComponentNumber() + "'" +
            ", quantity=" + getQuantity() +
            ", bomYield=" + getBomYield() +
            ", effDate='" + getEffDate() + "'" +
            ", disDate='" + getDisDate() + "'" +
            "}";
    }
}
