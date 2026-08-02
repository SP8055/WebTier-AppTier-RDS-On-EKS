package app;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cricket_products")
public class CricketProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String category;
	private BigDecimal price;
	private String description;

	public CricketProduct() {}

	public CricketProduct(String name, String category, BigDecimal price, String description) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	//Getters and Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
}

