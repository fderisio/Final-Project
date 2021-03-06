package saveup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import saveup.domain.Category;
import saveup.domain.Expense;
import saveup.domain.Income;
import saveup.domain.JsonViews;
import saveup.domain.PayMethod;
import saveup.domain.User;
import saveup.service.CategoryService;
import saveup.service.ExpenseService;
import saveup.service.IncomeService;
import saveup.service.PayMethodService;
import saveup.service.UserService;

@RestController
@RequestMapping("/user")
public class RestUserController {

	private final UserService userService;
	private final CategoryService categoryService;
	private final PayMethodService paymethodService;
	private final IncomeService incomeService;
	private final ExpenseService expenseService;
	
	@Autowired
	public RestUserController(UserService userService, CategoryService categoryService,
			PayMethodService paymethodService, IncomeService incomeService,
			ExpenseService expenseService) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.paymethodService = paymethodService;
		this.incomeService = incomeService;
		this.expenseService = expenseService;
		
	}
	
	// works 09/07/2016
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}")
	public User retrieveUserById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/categories")
	public List<Category> retrieveCategoriesByUserId(@PathVariable Long userId) {
		return categoryService.findAllByUserId(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/paymethods")
	public List<PayMethod> retrievePayMethodByUserId(@PathVariable Long userId) {
		return paymethodService.findAllByUserId(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/incomes")
	public List<Income> retrieveIncomeByUserId(@PathVariable Long userId) {
		return incomeService.findAllByUserId(userId);
	}
	
	// works 09/07/2017
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/expenses")
	public List<Expense> retrieveAllExpenses(@PathVariable Long userId) {
		return expenseService.retrieveAllExpensesForUser(userId);
	}
	
	// works 09/07/2017
	@JsonView(JsonViews.Public.class)
	@PostMapping("/login")
	public User postUserByEmailAndPassword(@RequestBody User postedUser) {
		String email = postedUser.getEmail();
		String password = postedUser.getPassword();
		User user = userService.findByEmailAndPassword(email, password);
		return user;
	}

	@JsonView(JsonViews.Public.class)
	@PostMapping("/signup")
	public void registerNewUser(@RequestBody User postedUser) {
		userService.registerNewUser(postedUser);
	}
	
//	@JsonView(JsonViews.Public.class)
//	@PostMapping("/signup")
//	public HttpEntity<Void> registerNewUser(@RequestBody User postedUser) {
//		User savedUser = userService.registerNewUser(postedUser);
//		
//		UriComponents uriComponents = fromMethodCall(
//			on(getClass()).retrieveUserById(savedUser.getId())).build();
//
//		return ResponseEntity.created(uriComponents.encode().toUri()).build();
//	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/{categoryId}/{paymentId}/expenses/add")
	public void createExpense(@RequestBody Expense postedExpense, 
			@PathVariable Long categoryId, Long paymentId) 
	{
		Long value3 = postedExpense.getPayMethod().getId();
		expenseService.registerNewExpense(postedExpense, categoryId, value3);
	}
	
	// works 09/07/2016
	@JsonView(JsonViews.Public.class)
	@PostMapping("/{userId}/categories/add")
	public void createCategory(@RequestBody Category postedCategory, @PathVariable Long userId) {
		categoryService.saveCategoryForUser(postedCategory, userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/{userId}/incomes/add") // in the future: @PostMapping("/{userId}/categories/add")
	public void createIncome(@RequestBody Income postedIncome, @PathVariable Long userId) {
		incomeService.saveIncomeForUser(postedIncome, userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/{userId}/paymethods/add")
	public void createPayMethod(@RequestBody PayMethod postedPayMethod, @PathVariable Long userId) {
		paymethodService.savePayMethodForUser(postedPayMethod, userId);
	}

}
