package be.groups.glanguage.core.entities.formula.description.combination;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.core.entities.utils.Language;
import be.groups.glanguage.core.entities.utils.MultilingualString;
import be.groups.glanguage.core.entities.utils.MultilingualStringItem;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.description.combination.FormulaParameterCombinationItemNullInnerError;
import be.groups.glanguage.core.error.formula.description.combination.FormulaParameterCombinationItemWrongParameterTypeInnerError;
import be.groups.glanguage.core.error.formula.description.combination.FormulaParameterCombinationItemWrongParameterValueInnerError;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by michotte on 3/08/2017.
 */
public class FormulaParameterCombinationTest {

    /**
     * Tests {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} when parameter is null
     */
    @Test
    public void testIsValidNullParameter() {
        FormulaParameterCombinationItem parameterCombinationItem = mock(FormulaParameterCombinationItem.class);

        assertFalse(parameterCombinationItem.isValid(null, null));
    }

    /**
     * Tests {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} when parameter type does not
     * match
     */
    @Test
    public void testIsValidParameterTypeNotMatching() {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.BOOLEAN);
        doReturn(returnTypes).when(parameterCombinationItem).getReturnTypes();

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        assertFalse(parameterCombinationItem.isValid(parameter, null));
    }

    /**
     * Tests {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} when parameter type matches
     */
    @Test
    public void testIsValidParameterTypeMatching() {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.BOOLEAN);
        doReturn(returnTypes).when(parameterCombinationItem).getReturnTypes();

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

        assertTrue(parameterCombinationItem.isValid(parameter, null));
    }

    /**
     * Tests {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} when parameter type matches
     * but value does not
     */
    @Test
    public void testIsValidParameterValueNotMatching() {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.INTEGER);
        returnTypes.add(FormulaReturnType.NUMERIC);
        doReturn(returnTypes).when(parameterCombinationItem).getReturnTypes();

        Set<FormulaParameterCombinationItemValue> values = new HashSet<>();
        FormulaParameterCombinationItemValue value = new FormulaParameterCombinationItemValue();
        value.setId(1L);
        value.setValue("1");
        values.add(value);
        doReturn(values).when(parameterCombinationItem).getValues();

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        try {
            when(parameter.getValue(null)).thenReturn(2.0);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }

        assertFalse(parameterCombinationItem.isValid(parameter, null));
    }

    /**
     * Tests {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} when parameter type and
     * value match
     */
    @Test
    public void testIsValidParameterValueMatching() {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.INTEGER);
        returnTypes.add(FormulaReturnType.NUMERIC);
        doReturn(returnTypes).when(parameterCombinationItem).getReturnTypes();
        Set<FormulaParameterCombinationItemValue> values = new HashSet<>();
        FormulaParameterCombinationItemValue value = new FormulaParameterCombinationItemValue();
        value.setId(1L);
        value.setValue("1");
        values.add(value);
        doReturn(values).when(parameterCombinationItem).getValues();

        AbstractFormula parameter = mock(AbstractFormula.class);
        doReturn(FormulaReturnType.NUMERIC).when(parameter).getReturnType(null);
        try {
            doReturn(1.0).when(parameter).getNumericValue(null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        assertTrue(parameterCombinationItem.isValid(parameter, null));
    }

    /**
     * Tests
     * {@link FormulaParameterCombinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
     * when parameter is null
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNullParameter() throws GLanguageException {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        Set<MultilingualStringItem> names = new HashSet<>();
        MultilingualStringItem name = new MultilingualStringItem();
        name.setId(1L);
        name.setLanguage(Language.EN);
        name.setText("parameterName");
        names.add(name);
        multilingualName.setItems(names);
        doReturn(multilingualName).when(parameterCombinationItem).getName();
        doReturn(1).when(parameterCombinationItem).getSequenceNumber();

        FormulaUsage usage = mock(FormulaUsage.class);
        doReturn(mock(MultilingualString.class)).when(usage).getParameterName(parameterCombinationItem);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1L);

        try {
            parameterCombinationItem.validate(formula, usage, null, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error after inversion is null", ie);
            assertTrue("inner error not of type FormulaParameterCombinationItemNullInnerError",
                       ie instanceof FormulaParameterCombinationItemNullInnerError);
            throw e;
        }
    }

    /**
     * Tests
     * {@link FormulaParameterCombinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
     * when {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} is false
     * and {@link FormulaParameterCombinationItem#isValidType(AbstractFormula, Evaluator)} is false
     * and parameter is not null
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidType() throws GLanguageException {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        Set<MultilingualStringItem> names = new HashSet<>();
        MultilingualStringItem name = new MultilingualStringItem();
        name.setId(1L);
        name.setLanguage(Language.EN);
        name.setText("parameterName");
        names.add(name);
        multilingualName.setItems(names);
        doReturn(multilingualName).when(parameterCombinationItem).getName();
        doReturn(1).when(parameterCombinationItem).getSequenceNumber();

        FormulaUsage usage = mock(FormulaUsage.class);
        doReturn(mock(MultilingualString.class)).when(usage).getParameterName(parameterCombinationItem);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1L);
        AbstractFormula parameter = mock(AbstractFormula.class);

        doReturn(false).when(parameterCombinationItem).isValid(parameter, null);
        doReturn(false).when(parameterCombinationItem).isValidType(parameter, null);
        try {
            parameterCombinationItem.validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error after inversion is null", ie);
            assertTrue("inner error not of type FormulaParameterCombinationItemWrongParameterTypeInnerError",
                       ie instanceof FormulaParameterCombinationItemWrongParameterTypeInnerError);
            throw e;
        }
    }

    /**
     * Tests
     * {@link FormulaParameterCombinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
     * when {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} is false
     * when {@link FormulaParameterCombinationItem#isValidType(AbstractFormula, Evaluator)} is true
     * when {@link FormulaParameterCombinationItem#isValidValue(AbstractFormula, Evaluator)} is false
     * and parameter is not null
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidValue() throws GLanguageException {
        FormulaParameterCombinationItem parameterCombinationItem = spy(FormulaParameterCombinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        Set<MultilingualStringItem> names = new HashSet<>();
        MultilingualStringItem name = new MultilingualStringItem();
        name.setId(1L);
        name.setLanguage(Language.EN);
        name.setText("parameterName");
        names.add(name);
        multilingualName.setItems(names);
        doReturn(multilingualName).when(parameterCombinationItem).getName();
        doReturn(1).when(parameterCombinationItem).getSequenceNumber();

        FormulaUsage usage = mock(FormulaUsage.class);
        doReturn(mock(MultilingualString.class)).when(usage).getParameterName(parameterCombinationItem);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1L);
        AbstractFormula parameter = mock(AbstractFormula.class);

        doReturn(false).when(parameterCombinationItem).isValid(parameter, null);
        doReturn(true).when(parameterCombinationItem).isValidType(parameter, null);
        doReturn(false).when(parameterCombinationItem).isValidValue(parameter, null);
        try {
            parameterCombinationItem.validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error after inversion is null", ie);
            assertEquals("inner error not of type FormulaParameterCombinationItemWrongParameterValueInnerError",
                         FormulaParameterCombinationItemWrongParameterValueInnerError.class, ie.getClass());
            throw e;
        }
    }

}
