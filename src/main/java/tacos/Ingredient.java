package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//import java.util.Objects;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor// switched the places
@Entity
public class Ingredient {
	
	@Id
	private final String id;
	private final String name;
	private Type type;
	
	public static enum Type {
		WRAP, PROTEIN,VEGGIES, CHEESE, SAUCE
	}
//
	public Type getType() {
		return this.type;
	}

	

}
