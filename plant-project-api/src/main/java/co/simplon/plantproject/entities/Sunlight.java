package co.simplon.plantproject.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sunlight_levels")
public class Sunlight extends AbstractEntity {

    @Column(name = "sunlight_code", updatable = false)
    private String code;

    @Column(name = "sunlight_name", updatable = false)
    private String name;

    @Column(name = "logical_order", updatable = false)
    private int logicalOrder;

    public Sunlight() {

    }

    public String getCode() {
	return code;
    }

    @SuppressWarnings("unused")
	private void setCode(String code) {
    	// Not updatable
	this.code = code;
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
	private void setName(String name) {
    	// Not updatable
	this.name = name;
    }

    public int getLogicalOrder() {
	return logicalOrder;
    }

    @SuppressWarnings("unused")
	private void setLogicalOrder(int logicalOrder) {
    	// Not updatable
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
	Sunlight other = (Sunlight) obj;
	return Objects.equals(code, other.code);
    }

}