package be.groups.glanguage.glanguage.api.entities.formula;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Formula description
 * 
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_DESCRIPTION")
@SuppressWarnings("unused")
public class FormulaDescription {

	/**
	 * Technical unique id
	 */
	private Integer id;

	/**
	 * Name
	 */
	private String name;

	/**
	 * Name
	 */
	private String description;
	
	
	/**
	 * Type
	 */
	private FormulaType type;

	/**
	 * Priority
	 */
	private FormulaPriority priority;
	
	/**
	 * ReturnType
	 */
	private FormulaReturnType returnType;
	
	public FormulaDescription() {
		super();
	}

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @return the priority
	 */
	@Column(name = "FORMULA_PRIORITY_ID")
	@Convert(converter = FormulaPriorityConverter.class)
	public FormulaPriority getPriority() {
		return priority;
	}

	/**
	 * @return the returnType
	 */
	@Column(name = "FORMULA_RETURN_TYPE_ID")
	@Convert(converter = FormulaReturnTypeConverter.class)
	public FormulaReturnType getReturnType() {
		return returnType;
	}

	/**
	 * @return the type
	 */
	@Transient
	public FormulaType getType() {
		if (type == null) {
			type = FormulaType.translate(id);
		}
		return type;
	}
	
	/**
	 * @param id the id to set
	 */
	private void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param priority the priority to set
	 */
	private void setPriority(FormulaPriority priority) {
		this.priority = priority;
	}

	/**
	 * @param returnType the returnType to set
	 */
	private void setReturnType(FormulaReturnType returnType) {
		this.returnType = returnType;
	}

}
