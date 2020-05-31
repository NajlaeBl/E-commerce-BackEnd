package org.sid.lightecomv1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity // une entité jpa est une instance d'une classe que l'on peut suavegarder dans
		// ou charger depuis une bd relationnelle
@Data // de lombok , pour getters et setters

@ToString
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String photo;
	private String description;

	@OneToMany(mappedBy = "category")
	private Collection<Product> products;

	public Category() {

	}

	public Category(Long id, String name, String photo, String description, Collection<Product> products) {

		this.id = id;
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.products = products;
	}

}
