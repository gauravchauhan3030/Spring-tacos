////package tacos.web;
////
////import java.util.ArrayList;
////import java.util.List;
////import java.util.stream.Collectors;
////
////import javax.validation.Valid;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.Errors;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.SessionAttributes;
////
////import lombok.extern.slf4j.Slf4j;
////import tacos.Ingredient;
////import tacos.Taco;
////import tacos.data.IngredientRepository;
////import tacos.data.TacoRepository;
////import tacos.Ingredient.Type;
////import tacos.Order;;
////
////
////@Slf4j
////@Controller
////@RequestMapping("/design")
////@SessionAttributes("order") //need order objects in multiple requests so created session
////public class DesignTacoController {
////	
////	private final IngredientRepository ingredientRepo;
////	
////	private final TacoRepository designRepo;
////	
////	@ModelAttribute(name="order") //ensures that an Order object will be created in the model.
////	public Order order() {
////		return new Order();
////	}
////	
////	@ModelAttribute(name="taco")
////	public Taco taco() {
////		return new Taco();
////	}
////	
////	@Autowired
////	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
////		this.ingredientRepo=ingredientRepo;
////		this.designRepo = designRepo;
////	}
////
////	@GetMapping
////	public String showDesignForm(Model model) {
////		List<Ingredient> ingredients = new ArrayList<>();
////		
////		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
////		
////		Type[] types = Ingredient.Type.values();
////		
////		for(Type type: types) {
////			model.addAttribute(type.toString().toLowerCase(),
////					 filterByType(ingredients, type));
////		}
////		
////		return "design";
////	}
////	
//////	@GetMapping
//////	public String showDesignForm(Model model) {
//////		List<Ingredient> ingredients = Arrays.asList(
//////				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//////				 new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//////				 new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//////				 new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//////				 new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//////				 new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//////				 new Ingredient("CHED", "Cheddar", Type.CHEESE),
//////				 new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//////				 new Ingredient("SLSA", "Salsa", Type.SAUCE),
//////				 new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//////				);
//////		
//////		Type[] types = Ingredient.Type.values();
//////		
//////		for(Type type: types) {
//////			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//////		}
//////		
//////		model.addAttribute("design", new Taco());
//////		
//////		System.out.println(model+ "model");
//////		
//////		return "design";
//////	}
////	
////	@PostMapping
////	public String processDesign(@Valid Taco design, Errors errors, 
////			@ModelAttribute Order order) { // @ModelAttribute to indicate that its
////										   //value should come from the model and that Spring MVC shouldn’t attempt to bind
////										   //request parameters to it
////		
////		if(errors.hasErrors()) {
////			return "design";
////		}
////		
////		log.info("Processing design: " + design);
////		
////		Taco saved = designRepo.save(design);
////		
////		order.AddDesign(saved);
////		
////		return "redirect:/orders/current";
////	}
////	private List<Ingredient> filterByType(
////		      List<Ingredient> ingredients, Type type) {
////		    return ingredients
////		              .stream()
////		              .filter(x -> x.getType().equals(type))
////		              .collect(Collectors.toList());
////		  }
////}
//
//package tacos.web;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import tacos.Taco;
//import tacos.Ingredient;
//import tacos.Ingredient.Type;
//import tacos.Order;
//import tacos.data.TacoRepository;
//import tacos.data.IngredientRepository;
//
//// tag::classShell[]
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("order")
//public class DesignTacoController {
//  
////end::classShell[]
//
////tag::bothRepoProperties[]
////tag::ingredientRepoProperty[]
//  private final IngredientRepository ingredientRepo;
//  
////end::ingredientRepoProperty[]
//  private TacoRepository designRepo;
//
////end::bothRepoProperties[]
//  
//  /*
//// tag::ingredientRepoOnlyCtor[]
//  @Autowired
//  public DesignTacoController(IngredientRepository ingredientRepo) {
//    this.ingredientRepo = ingredientRepo;
//  }
//// end::ingredientRepoOnlyCtor[]
//   */
//
//  //tag::bothRepoCtor[]
//  @Autowired
//  public DesignTacoController(
//        IngredientRepository ingredientRepo, 
//        TacoRepository designRepo) {
//    this.ingredientRepo = ingredientRepo;
//    this.designRepo = designRepo;
//  }
//
//  //end::bothRepoCtor[]
//  
//  // tag::modelAttributes[]
//  @ModelAttribute(name = "order")
//  public Order order() {
//    return new Order();
//  }
//  
//  @ModelAttribute(name = "taco")
//  public Taco taco() {
//    return new Taco();
//  }
//
//  // end::modelAttributes[]
//  // tag::showDesignForm[]
//  
//  @GetMapping
//  public String showDesignForm(Model model) {
//    List<Ingredient> ingredients = new ArrayList<>();
//    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
//    
//    Type[] types = Ingredient.Type.values();
//    for (Type type : types) {
//      model.addAttribute(type.toString().toLowerCase(), 
//          filterByType(ingredients, type));      
//    }
//
//    return "design";
//  }
////end::showDesignForm[]
//
//  //tag::processDesign[]
//  @PostMapping
//  public String processDesign(
//      @Valid Taco design, Errors errors, 
//      @ModelAttribute Order order) {
//	  
//	 System.out.println("design--"+design);
//	 System.out.println("errors--"+errors);
//
//    if (errors.hasErrors()) {
//      return "design";
//    }
//
//    Taco saved = designRepo.save(design);
//    order.addDesign(saved);
//
//    return "redirect:/orders/current";
//  }
//  //end::processDesign[]
//  
//  private List<Ingredient> filterByType(
//      List<Ingredient> ingredients, Type type) {
//    return ingredients
//              .stream()
//              .filter(x -> x.getType().equals(type))
//              .collect(Collectors.toList());
//  }
//
//  /*
////tag::classShell[]
//  ...
////end::classShell[]
//   */
////tag::classShell[]
//
//}
////end::classShell[]


package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

//tag::injectingDesignRepository[]
//tag::injectingIngredientRepository[]
@Controller
@RequestMapping("/design")
//end::injectingIngredientRepository[]
@SessionAttributes("order")
//tag::injectingIngredientRepository[]
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  //end::injectingIngredientRepository[]
  private TacoRepository tacoRepo;

  //end::injectingDesignRepository[]
  /*
  //tag::injectingIngredientRepository[]
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  //end::injectingIngredientRepository[]
   */
  //tag::injectingDesignRepository[]

  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  //end::injectingDesignRepository[]

  //tag::injectingIngredientRepository[]

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
System.out.println("ingredients--"+ ingredients);
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }

    return "design";
  }
  //end::injectingIngredientRepository[]

//tag::injectingDesignRepository[]
  @PostMapping
  public String processDesign(
      @Valid Taco taco, Errors errors,
      @ModelAttribute Order order) {

    if (errors.hasErrors()) {
      return "design";
    }

    Taco saved = tacoRepo.save(taco);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

//end::injectingDesignRepository[]

  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }
  
}