package co.simplon.plantproject.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity

@Table(name = "hydration_levels")

public class Hydration extends AbstractEntity {

    @Column(name = "hydration_code", updatable = false)
    private String code;

    @Column(name = "hydration_name", updatable = false)
    private String name;

    @Column(name = "logical_order", updatable = false)
    private int logicalOrder;

    public Hydration() {

// TODO Auto-generated constructor stub

    }

    public String getCode() {

	return code;

    }

    @SuppressWarnings("unused")
    public void setCode(String code) {

	this.code = code;

    }

    public String getName() {

	return name;

    }

    @SuppressWarnings("unused")
    public void setName(String name) {

	this.name = name;

    }

    public int getLogicalOrder() {

	return logicalOrder;

    }

    @SuppressWarnings("unused")
    public void setLogicalOrder(int logicalOrder) {

	this.logicalOrder = logicalOrder;

    }

    @Override
    public String toString() {
	return "{code=" + code + ", name=" + name
		+ ", logicalOrder=" + logicalOrder + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Hydration other = (Hydration) obj;
	return Objects.equals(code, other.code);
    }

}