package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class CricketProductController {

	@Autowired
	private CricketProductRepository repository;

	@GetMapping
	public List<CricketProduct> getAllProducts() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public CricketProduct getProductById(@PathVariable Long id) {
		return repository.findById(id).orElse(null);
	}

	@PostMapping
	public CricketProduct addProduct(@RequestBody CricketProduct product) {
		return repository.save(product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

