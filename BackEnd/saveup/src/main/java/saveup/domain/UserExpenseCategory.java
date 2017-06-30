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
@Table(name = "category")
public class UserExpenseCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@ManyToOne(optional = false)
	private User user;
	
	@Column(nullable = false)
	private boolean fixed = false;
	
	public UserExpenseCategory() {
		/* required by JPA */
	}
	
	public UserExpenseCategory(String name, User user, boolean fixed) {
		this.name = name;
		this.user = user;
		this.fixed = fixed;
	}
	
}
