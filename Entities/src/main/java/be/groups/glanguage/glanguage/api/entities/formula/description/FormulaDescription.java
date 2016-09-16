package be.groups.glanguage.glanguage.api.entities.formula.description;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FORMULA_DESCRIPTION")
public class FormulaDescription {
	
	/*
	 * Fields
	 */
	private Integer id;
	
	private FormulaType type;
	
	private String name;
	
	private String descriptionFr;
	
	private String descriptionNl;
	
	private String descriptionDe;
	
	private String descriptionX;
	
	private FormulaPriority priority;
	
	private List<FormulaParametersCombination> parametersCombinations;
	
	private Map<List<FormulaReturnType>, FormulaReturnType> parametersCombinationsReturnTypes;
	
	/*
	 * Constructors
	 */
	public FormulaDescription() {
		super();
	}
	
	/*
	 * Getters
	 */
	@Id
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "ID", nullable = false, insertable = false, updatable = false)
	@Convert(converter = FormulaTypeConverter.class)
	public FormulaType getType() {
		return type;
	}
	
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}
	
	@Column(name = "DESCRIPTION_FR", nullable = true)
	public String getDescriptionFr() {
		return descriptionFr;
	}
	
	@Column(name = "DESCRIPTION_NL", nullable = true)
	public String getDescriptionNl() {
		return descriptionNl;
	}
	
	@Column(name = "DESCRIPTION_DE", nullable = true)
	public String getDescriptionDe() {
		return descriptionDe;
	}
	
	@Column(name = "DESCRIPTION_X", nullable = true)
	public String getDescriptionX() {
		return descriptionX;
	}
	
	@Column(name = "PRIORITY_ID")
	@Convert(converter = FormulaPriorityConverter.class)
	public FormulaPriority getPriority() {
		return priority;
	}
	
	@OneToMany(mappedBy = "description")
	public List<FormulaParametersCombination> getParametersCombinations() {
		return parametersCombinations;
	}
	
	/*
	 * Setters
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setType(FormulaType type) {
		this.type = type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}
	
	public void setDescriptionNl(String descriptionNl) {
		this.descriptionNl = descriptionNl;
	}
	
	public void setDescriptionDe(String descriptionDe) {
		this.descriptionDe = descriptionDe;
	}
	
	public void setDescriptionX(String descriptionX) {
		this.descriptionX = descriptionX;
	}
	
	public void setPriority(FormulaPriority priority) {
		this.priority = priority;
	}
	
	public void setParametersCombinations(List<FormulaParametersCombination> parametersCombinations) {
		this.parametersCombinations = parametersCombinations;
	}
	
	/*
	 * Methods
	 */
	@Transient
	public boolean isValid(List<FormulaReturnType> parametersTypes) {
		if (parametersCombinationsReturnTypes == null) {
			initParametersCombinationsReturnTypes();
		}
		
		return parametersCombinationsReturnTypes.containsKey(parametersTypes);
	}
	
	@Transient
	public FormulaReturnType getReturnType(List<FormulaReturnType> parametersTypes) {
		if (parametersCombinationsReturnTypes == null) {
			initParametersCombinationsReturnTypes();
		}
		
		return parametersCombinationsReturnTypes.get(parametersTypes);
	}
	
	private void initParametersCombinationsReturnTypes() {
		parametersCombinationsReturnTypes = parametersCombinations.stream()
				.collect(Collectors.toMap(
						c -> c.getParametersDescriptions().stream().map(d -> d.getReturnType()).collect(Collectors.toList()),
						c -> c.getReturnType()));
	}
	
}
