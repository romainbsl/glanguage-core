package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue(FormulaType.Values.F_IN)
public class FormulaIn extends AbstractNonTerminalFormula {

    protected FormulaIn() {
        super();
    }

    public FormulaIn(FormulaDescription description, AbstractFormula element, List<AbstractFormula> inList) throws GLanguageException {
		super(description, getParametersAsList(element, inList));

        if (element == null) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 0));
        }
        if (inList == null) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
        }
        this.parameters = new ArrayList<>();
        this.parameters.add(element);
        this.parameters.addAll(inList);
    }

    @Transient
    @JsonIgnore
    private static List<AbstractFormula> getParametersAsList(AbstractFormula element, List<AbstractFormula> inList) {
		List<AbstractFormula> list = new ArrayList<>();
		list.add(element);
		list.addAll(inList);
		return list;
	}

    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
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
                throw new IllegalArgumentException("Cannot compare undefined values in " + this.getClass()
                        .getName() + " object");
            default:
                throw new IllegalArgumentException("Cannot compare unknown values in " + this.getClass()
                        .getName() + " object");
        }
    }

    @Transient
    @Override
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        /*
         * WORKAROUND
         * It is not allowed to have checked exceptions thrown within a lambda expression without catching it within
         * the lambda expression -> Blame Oracle for that !
         * Therefore, the workaround consists in catching the checked exception inside of the lambda expression,
         * wrapping it into an unchecked exception (e.g. RuntimeException), throwing it, surrounding the whole lambda
         * into another try-catch block, catching the unchecked exception outside of the lambda expression and
         * finally handling it
         */
        try {
            FormulaReturnType elementReturnType = parameters.get(0).getReturnType(null);
            List<FormulaReturnType> listReturnTypes = parameters.subList(1, parameters.size()).stream().map(p -> {
                try {
                    return p.getReturnType(null);
                } catch (GLanguageException e) {
                    throw new RuntimeException(e);
                }
            }).distinct().collect(Collectors.toList());

            if (listReturnTypes.size() == 1) {
                return elementReturnType.equals(listReturnTypes.get(0));
            } else {
                List<FormulaReturnType> authorizedParametersTypes = Arrays.asList(FormulaReturnType.INTEGER,
                                                                                  FormulaReturnType.NUMERIC);
                return listReturnTypes.stream()
                        .allMatch(authorizedParametersTypes::contains) && authorizedParametersTypes.contains(
                        elementReturnType);
            }
        } catch (Exception e) {
            if (e.getCause() instanceof GLanguageException) {
                GLanguageException gLanguageException = (GLanguageException) e.getCause();
                gLanguageException.getError()
                        .setOuterError(new FormulaInnerError(GLanguageErrorRegistry.FORMULA_INNER_ERROR,
                                                             this,
                                                             null,
                                                             "isValid",
                                                             null));
                throw gLanguageException;
            }
            throw e;
        }
    }

    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.BOOLEAN;
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
        /*
         * WORKAROUND
         * It is not allowed to have checked exceptions thrown within a lambda expression without catching it within
         * the lambda expression -> Blame Oracle for that !
         * Therefore, the workaround consists in catching the checked exception inside of the lambda expression,
         * wrapping it into an unchecked exception (e.g. RuntimeException), throwing it, surrounding the whole lambda
         * into another try-catch block, catching the unchecked exception outside of the lambda expression and
         * finally handling it
         */
        try {
            Set<FormulaReturnType> returnTypes = parameters.stream().map(p -> {
                try {
                    return p.getReturnType(evaluator);
                } catch (GLanguageException e) {
                    throw new RuntimeException(e);
                }
            }).distinct().collect(Collectors.toSet());
            if (returnTypes.size() == 1) {
                return returnTypes.iterator().next();
            } else {
                return FormulaReturnType.NUMERIC;
            }
        } catch (Exception e) {
            if (e.getCause() instanceof GLanguageException) {
                GLanguageException gLanguageException = (GLanguageException) e.getCause();
                gLanguageException.getError()
                        .setOuterError(new FormulaInnerError(GLanguageErrorRegistry.FORMULA_INNER_ERROR,
                                                             this,
                                                             null,
                                                             "isValid",
                                                             null));
                throw gLanguageException;
            }
            throw e;
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
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
