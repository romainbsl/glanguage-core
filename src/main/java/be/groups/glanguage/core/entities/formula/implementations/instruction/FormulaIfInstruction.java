package be.groups.glanguage.core.entities.formula.implementations.instruction;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Formula implementing the "if-then-else" instruction
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.I_IF)
public class FormulaIfInstruction extends AbstractNonTerminalFormula {

    public FormulaIfInstruction() {
        super();
    }

    public FormulaIfInstruction(FormulaDescription description,
                                AbstractFormula condition,
                                AbstractFormula ifStatement,
                                AbstractFormula elseStatement,
                                Evaluator evaluator) throws GLanguageException {
        super(description, Arrays.asList(condition, ifStatement, elseStatement), evaluator);
        this.parameters = new ArrayList<>();
        parameters.add(condition);
        parameters.add(ifStatement);
        parameters.add(elseStatement);
    }

    /**
     * Get the value of the expression in the branch which condition is true as {@link Integer}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the expression in the branch which condition is true as {@link Integer}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            expression to be evaluated doesn't implement the
     *                            {@link AbstractFormula#getIntegerValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getBooleanValue(evaluator)) {
            return getParameters().get(1).getIntegerValue(evaluator);
        } else {
            if (getParameters().size() > 2) {
                return getParameters().get(2).getIntegerValue(evaluator);
            } else {
                return 0;
            }
        }
    }

    /**
     * Get the value of the expression in the branch which condition is true as {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the expression in the branch which condition is true as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            expression to be evaluated doesn't implement the
     *                            {@link AbstractFormula#getNumericValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getBooleanValue(evaluator)) {
            return getParameters().get(1).getNumericValue(evaluator);
        } else {
            if (getParameters().size() > 2) {
                return getParameters().get(2).getNumericValue(evaluator);
            } else {
                return 0.0;
            }
        }
    }

    /**
     * Get the value of the expression in the branch which condition is true as {@link String}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the expression in the branch which condition is true as {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            expression to be evaluated doesn't implement the
     *                            {@link AbstractFormula#getStringValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getBooleanValue(evaluator)) {
            return getParameters().get(1).getStringValue(evaluator);
        } else {
            if (getParameters().size() > 2) {
                return getParameters().get(2).getStringValue(evaluator);
            } else {
                return "";
            }
        }
    }

    /**
     * Get the value of the expression in the branch which condition is true as {@link Boolean}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the expression in the branch which condition is true as {@link Boolean}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            expression to be evaluated doesn't implement the
     *                            {@link AbstractFormula#getBooleanValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getBooleanValue(evaluator)) {
            return getParameters().get(1).getBooleanValue(evaluator);
        } else {
            if (getParameters().size() > 2) {
                return getParameters().get(2).getBooleanValue(evaluator);
            } else {
                return false;
            }
        }
    }

    /**
     * Get the value of the expression in the branch which condition is true as {@link LocalDate}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the expression in the branch which condition is true as {@link LocalDate}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            expression to be evaluated doesn't implement the
     *                            {@link AbstractFormula#getDateValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getBooleanValue(evaluator)) {
            return getParameters().get(1).getDateValue(evaluator);
        } else {
            if (getParameters().size() > 2) {
                return getParameters().get(2).getDateValue(evaluator);
            } else {
                throw new GLanguageException(new FormulaEvaluateTypeInnerError(this,
                                                                               evaluator,
                                                                               ErrorMethod.DATE,
                                                                               "Else statement needed, no default " +
                                                                                       "value of type DATE"));
            }
        }
    }

    /**
     * Get the value of the expression in the branch which condition is true as {@link Duration}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the expression in the branch which condition is true as {@link Duration}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            expression to be evaluated doesn't implement the
     *                            {@link AbstractFormula#getDurationValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        if (getParameters().get(0).getBooleanValue(evaluator)) {
            return getParameters().get(1).getDurationValue(evaluator);
        } else {
            if (getParameters().size() > 2) {
                return getParameters().get(2).getDurationValue(evaluator);
            } else {
                return Duration.ZERO;
            }
        }
    }

    @Override
    public String asText() {
        return asText(false);
    }

    protected String asText(boolean fromIf) {
        boolean elseIf = false;
        StringBuilder sb = new StringBuilder();
        if (fromIf) {
            sb.append("elseif ");
        } else {
            sb.append("if ");
        }
        sb.append(getParameters().get(0).asText());
        sb.append(" then");
        sb.append("\n\t");
        sb.append(getParameters().get(1).asText());
        sb.append("\n");
        if (getParameters().size() > 2) {
            if (getParameters().get(2) instanceof FormulaIfInstruction) {
                elseIf = true;
                sb.append(((FormulaIfInstruction) getParameters().get(2)).asText(true));
            } else {
                sb.append("else");
                sb.append("\n\t");
                sb.append(getParameters().get(2).asText());
                sb.append("\n");
            }
        }
        // If second parameter is another if instruction, "end" has already been appended
        if (!elseIf) {
            sb.append("end");
        }
        return sb.toString();
    }

}
