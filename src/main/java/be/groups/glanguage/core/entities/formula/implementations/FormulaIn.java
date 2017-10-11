package be.groups.glanguage.core.entities.formula.implementations;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static be.groups.glanguage.core.entities.formula.description.FormulaReturnType.BOOLEAN;
import static be.groups.glanguage.core.entities.formula.description.FormulaReturnType.NUMERIC;

/**
 * Formula implementing the logical in operation - check if an element is part of a list of elements
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_IN)
public class FormulaIn extends AbstractNonTerminalFormula {

    protected FormulaIn() {
        super();
    }

    public FormulaIn(FormulaDescription description,
                     AbstractFormula element,
                     List<AbstractFormula> inList,
                     Evaluator evaluator) throws GLanguageException {
        super(description, getParametersAsList(element, inList), evaluator);

        this.parameters = new ArrayList<>();
        this.parameters.add(element);
        this.parameters.addAll(inList);
    }

    /**
     * Get the parameters as list<br>
     * Helper method for constructor to pass the parameters as a list to the super constructor of
     * {@link AbstractNonTerminalFormula}
     *
     * @param element the first parameter to be added to the list
     * @param inList the other parameters to be added to the list after {@code element}
     * @return a list with at least 2 items which are
     * <ol>
     *     <li>the value to be checked if it is present in the list</li>
     *     <li>the elements of the list (1 or more)</li>
     * </ol>
     */
    @Transient
    @JsonIgnore
    private static List<AbstractFormula> getParametersAsList(AbstractFormula element, List<AbstractFormula> inList) {
        List<AbstractFormula> list = new ArrayList<>();
        list.add(element);
        list.addAll(inList);
        return list;
    }

    /**
     * Get the value as {@link Boolean}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return true if the first parameter is part of the list of the second parameter, false otherwise
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        try {
            AbstractFormula element = getParameters().get(0);
            Iterator<AbstractFormula> itInList = getParameters().listIterator(1);
            boolean result = false;
            switch (getElementsType(evaluator)) {
                case INTEGER:
                    int i = element.getIntegerValue(evaluator);
                    while (!result && itInList.hasNext()) {
                        result = i == itInList.next().getIntegerValue(evaluator);
                    }
                    return result;
                case NUMERIC:
                    double d = element.getNumericValue(evaluator);
                    while (!result && itInList.hasNext()) {
                        result = d == itInList.next().getNumericValue(evaluator);
                    }
                    return result;
                case DATE:
                    LocalDate date = element.getDateValue(evaluator);
                    if (date != null) {
                        while (!result && itInList.hasNext()) {
                            result = date.equals(itInList.next().getDateValue(evaluator));
                        }
                    }
                    return result;
                case BOOLEAN:
                    boolean b = element.getBooleanValue(evaluator);
                    while (!result && itInList.hasNext()) {
                        result = (b == itInList.next().getBooleanValue(evaluator));
                    }
                    return result;
                case STRING:
                    String s = element.getStringValue(evaluator);
                    if (s != null) {
                        while (!result && itInList.hasNext()) {
                            result = s.equals(itInList.next().getStringValue(evaluator));
                        }
                    }
                    return result;
                case UNDEFINED:
                    throw new IllegalArgumentException("Cannot compare undefined values");
                default:
                    throw new IllegalArgumentException("Cannot compare values of unknown type");
            }
        } catch (GLanguageException gle) {
            gle.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, null));
            throw  gle;
        } catch (Exception e) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, e
                .getMessage()));
        }
    }

    /**
     * Get the return type<br>
     * The return type of this type of formula is always {@link FormulaReturnType#BOOLEAN}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return always {@link FormulaReturnType#BOOLEAN}
     */
    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return BOOLEAN;
    }

    /**
     * Type of the elements<br>
     * All element's types are supposed to be in accordance<br>
     * The only remaining case is mixed integer and numeric types (in accordance to each other),
     * numeric type prevails
     *
     * @param evaluator the evalautor
     * @return The type of the elements
     */
    private FormulaReturnType getElementsType(Evaluator evaluator) throws GLanguageException {
        Set<FormulaReturnType> returnTypes = getParameters().stream().map(p -> p.getReturnType(evaluator)).distinct()
                .collect(Collectors.toSet());
        if (returnTypes.size() == 1) {
            return returnTypes.iterator().next();
        } else {
            return NUMERIC;
        }
    }

    @Override
    public String asText() {
        AbstractFormula element = getParameters().get(0);
        Iterator<AbstractFormula> itInList = getParameters().listIterator(1);
        StringBuilder sb = new StringBuilder();
        sb.append(element.asText());
        sb.append(" in (");
        while (itInList.hasNext()) {
            sb.append(itInList.next().asText());

            if (itInList.hasNext()) {
                sb.append("; ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
