package co.simplon.plantproject.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(name = "role_code", updatable = false)
    private String code;


    public Role() {
    }

    public String getCode() {
	return code;
    }

    @SuppressWarnings("unused")
	private void setCode(String code) {
    	// Not updatable
	this.code = code;
    }


    @Override
	public String toString() {
		return "Role [code=" + code + "]";
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
	Role other = (Role) obj;
	return Objects.equals(code, other.code);
    }

}
