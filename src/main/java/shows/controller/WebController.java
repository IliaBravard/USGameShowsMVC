package shows.controller; // The package where this controller class is located at

// Including the needed imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Allows access to the specified entity
import shows.repository.GameRepository;

@Controller // Indicates this class as the controller class
public class WebController {
	@Autowired // The autowiring needed for the JPA repository
	GameRepository repository;

	@GetMapping("/viewAllRecords")
	public String viewAllContacts(Model model) {
		model.addAttribute("shows", repository.findAll());
		return "results";
	}
}
