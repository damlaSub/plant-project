package co.simplon.plantproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "water_levels")
public class Water extends AbstractEntity {

    public Water() {
	// TODO Auto-generated constructor stub
    }

    @Column(name = "name", updatable = false)
    private String name;

    @Column(name = "logical_order", updatable = false)
    private int logicalOrder;

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
	return "{name=" + name + ", logicalOrder="
		+ logicalOrder + "}";
    }

}
