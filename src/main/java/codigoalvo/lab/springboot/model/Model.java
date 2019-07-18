package codigoalvo.lab.springboot.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface Model {

	Long getId();
	void setId(Long id);

}
