package saveup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "expense")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private UserExpenseCategory userExpenseCategory;
	
	@Column(nullable = false, length = 20)
	private String description;
	
	@Column(nullable = true, length = 30)
	private String store;
	
	@Column(name = "exp_date", nullable = false)
	private String expDate;
	
	@Column(nullable = false, length = 15)
	private Double total;
	
	@ManyToOne(optional = false)
	private UserPayMethod userPayMethod;
	
	public Expense() {
		/* required by JPA */
	}
	
	/* constructor without store */
	public Expense(UserExpenseCategory userExpenseCategory, String description,
		String expDate,Double total, UserPayMethod userPayMethod) {
		this.userExpenseCategory = userExpenseCategory;
		this.description = description;
		this.expDate = expDate;
		this.total = total;
		this.userPayMethod = userPayMethod;
	}
	
	/* full constructor */
	public Expense(UserExpenseCategory userExpenseCategory, String description,
			String store, String expDate,Double total, UserPayMethod userPayMethod) {
		this.userExpenseCategory = userExpenseCategory;
		this.description = description;
		this.store = store;
		this.expDate = expDate;
		this.total = total;
		this.userPayMethod = userPayMethod;
	}
		
}
