package co.simplon.plantproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(name = "role_code", updatable = false)
    private String code;

    @Column(name = "role_name", updatable = false)
    private String name;

    public Role() {
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "{code=" + code + ", name=" + name + "}";
    }

}
