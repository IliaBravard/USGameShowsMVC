package shows.controller; // The package where this controller class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Nov 22, 2022
 */

// Including the needed imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import shows.beans.Game;
import shows.repository.GameRepository;

@Controller // Indicates this class as the controller class
public class WebController {

	@Autowired // The autowiring needed for the JPA repository
	GameRepository repository;

	/**
	 * This method returns the default page (i.e. the index) when the user runs
	 * application.
	 * 
	 * @return the index page
	 */
	@GetMapping("")
	public String viewIndex() {
		return "index";
	}

	/**
	 * This method returns all show records from the database and presents them to
	 * the user.
	 * 
	 * @param model - the model interface
	 * @return the results page
	 */
	@GetMapping("/viewAll")
	public String viewAllRecords(Model model) {
		model.addAttribute("shows", repository.findAll());
		return "results";
	}

	/**
	 * This method provides us with a new default, no argument constructor to pass
	 * in the user data. In other words, the HTTP GET request.
	 * 
	 * @param model - the model interface
	 * @return the user input page
	 */
	@GetMapping("/addGameShow")
	public String addNewContact(Model model) {
		Game show = new Game();
		model.addAttribute("newGameShow", show);
		return "input";
	}

	/**
	 * This method processes the new record after the form has been submitted by the
	 * user. In other words, the HTTP POST request.
	 * 
	 * @param show  - the model's attribute
	 * @param model - the model interface
	 * @return the view all records page
	 */
	@PostMapping("/addGameShow")
	public String addNewContact(@ModelAttribute Game show, Model model) {
		repository.save(show);
		return viewAllRecords(model);
	}

	/**
	 * This method actually saves the new game show object to the database.
	 * 
	 * @param model - the model interface
	 * @param toAdd - the new game show record to be added
	 * @return the results page
	 */
	@PostMapping("/saveGameShow")
	public String saveBook(Model model, @ModelAttribute("toAdd") Game toAdd) {
		repository.save(toAdd);
		return "redirect:/viewAll";
	}

	/**
	 * This method handles the edit operation for when the user wants to update a
	 * given game show record.
	 * 
	 * @param id - the id of the record to be edited
	 * @return the updated model and view page
	 */
	@GetMapping("/edit/{id}")
	public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("editShow");
		Game toEdit = repository.findById(id).get();
		mav.addObject("show", toEdit);
		return mav;
	}

	/**
	 * This method handles the delete operation for when the user wants to remove a
	 * given game show record from the local database.
	 * 
	 * @param id - the id of the record to be deleted
	 * @return the results page
	 */
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
		return "redirect:/viewAll";
	}
}