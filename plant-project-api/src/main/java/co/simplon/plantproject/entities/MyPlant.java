package co.simplon.plantproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_plants")
public class MyPlant extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", updatable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plant_id", updatable = false)
    private Plant plant;

    public MyPlant() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(Account account) {
	this.account = account;
    }

    public Plant getPlant() {
	return plant;
    }

    public void setPlant(Plant plant) {
	this.plant = plant;
    }

    @Override
    public String toString() {
	return "{account=" + account + ", plant=" + plant
		+ "}";
    }

}
