package be.groups.glanguage.glanguage.api.entities.formula.description.conbination;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualStringItem;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationItemNullInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationItemWrongParameterTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationItemWrongParameterValueInnerError;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaParameterConbinationItem}
 *
 * @author DUPIREFR
 */
public class FormulaParameterConbinationItemTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaParameterConbinationItem} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaParameterConbinationItem parameter = getEntityManager().find(FormulaParameterConbinationItem.class, 1);
		
		/* Checking entity */
        assertNotNull(parameter);

        assertEquals(Integer.valueOf(1), parameter.getId());

        assertEquals("integer", parameter.getName(Language.EN));
        assertEquals("expression de type INTEGER", parameter.getDescription(Language.FR));
        assertEquals("expressie van type INTEGER", parameter.getDescription(Language.NL));
        assertEquals("expression of type INTEGER", parameter.getDescription(Language.EN));
        assertEquals("", parameter.getDescription(Language.X));

        assertEquals(1, parameter.getTypes().size());
        assertTrue(parameter.getReturnTypes().contains(FormulaReturnType.INTEGER));
		
		/* Checking relationships */
        assertNotNull(parameter.getConbination());
        assertEquals(Integer.valueOf(1), parameter.getConbination().getId());
    }

    /**
     * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter is null
     */
    @Test
    public void testIsValidNullParameter() {
        FormulaParameterConbinationItem parameterConbinationItem = mock(FormulaParameterConbinationItem.class);

        assertFalse(parameterConbinationItem.isValid(null, null));
    }

    /**
     * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter type does not
     * match
     */
    @Test
    public void testIsValidParameterTypeNotMatching() {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.BOOLEAN);
        doReturn(returnTypes).when(parameterConbinationItem).getReturnTypes();

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        assertFalse(parameterConbinationItem.isValid(parameter, null));
    }

    /**
     * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter type matches
     */
    @Test
    public void testIsValidParameterTypeMatching() {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.BOOLEAN);
        doReturn(returnTypes).when(parameterConbinationItem).getReturnTypes();

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

        assertTrue(parameterConbinationItem.isValid(parameter, null));
    }

    /**
     * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter type matches
     * but value does not
     */
    @Test
    public void testIsValidParameterValueNotMatching() {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.INTEGER);
        returnTypes.add(FormulaReturnType.NUMERIC);
        doReturn(returnTypes).when(parameterConbinationItem).getReturnTypes();

        Set<FormulaParameterConbinationItemValue> values = new HashSet<>();
        FormulaParameterConbinationItemValue value = new FormulaParameterConbinationItemValue();
        value.setId(1);
        value.setValue("1");
        values.add(value);
        doReturn(values).when(parameterConbinationItem).getValues();

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        try {
            when(parameter.getValue(null)).thenReturn(2.0);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }

        assertFalse(parameterConbinationItem.isValid(parameter, null));
    }

    /**
     * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter type and
     * value match
     */
    @Test
    public void testIsValidParameterValueMatching() {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        List<FormulaReturnType> returnTypes = new ArrayList<>();
        returnTypes.add(FormulaReturnType.INTEGER);
        returnTypes.add(FormulaReturnType.NUMERIC);
        doReturn(returnTypes).when(parameterConbinationItem).getReturnTypes();
        Set<FormulaParameterConbinationItemValue> values = new HashSet<>();
        FormulaParameterConbinationItemValue value = new FormulaParameterConbinationItemValue();
        value.setId(1);
        value.setValue("1");
        values.add(value);
        doReturn(values).when(parameterConbinationItem).getValues();

        AbstractFormula parameter = mock(AbstractFormula.class);
        doReturn(FormulaReturnType.NUMERIC).when(parameter).getReturnType(null);
        try {
            doReturn(1.0).when(parameter).getNumericValue(null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        assertTrue(parameterConbinationItem.isValid(parameter, null));
    }

    /**
     * Tests
     * {@link FormulaParameterConbinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
     * when parameter is null
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNullParameter() throws GLanguageException {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        Set<MultilingualStringItem> names = new HashSet<>();
        MultilingualStringItem name = new MultilingualStringItem();
        name.setId(1);
        name.setLanguage(Language.EN);
        name.setText("parameterName");
        names.add(name);
        multilingualName.setItems(names);
        doReturn(multilingualName).when(parameterConbinationItem).getName();
        doReturn(1).when(parameterConbinationItem).getSequenceNumber();

        FormulaUsage usage = mock(FormulaUsage.class);
        doReturn(mock(MultilingualString.class)).when(usage).getParameterName(parameterConbinationItem);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1);

        try {
            parameterConbinationItem.validate(formula, usage, null, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error after inversion is null", ie);
            assertTrue("inner error not of type FormulaParameterConbinationItemNullInnerError",
                       ie instanceof FormulaParameterConbinationItemNullInnerError);
            throw e;
        }
    }

    /**
     * Tests
     * {@link FormulaParameterConbinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
     * when {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} is false
     * and {@link FormulaParameterConbinationItem#isValidType(AbstractFormula, Evaluator)} is false
     * and parameter is not null
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidType() throws GLanguageException {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        Set<MultilingualStringItem> names = new HashSet<>();
        MultilingualStringItem name = new MultilingualStringItem();
        name.setId(1);
        name.setLanguage(Language.EN);
        name.setText("parameterName");
        names.add(name);
        multilingualName.setItems(names);
        doReturn(multilingualName).when(parameterConbinationItem).getName();
        doReturn(1).when(parameterConbinationItem).getSequenceNumber();

        FormulaUsage usage = mock(FormulaUsage.class);
        doReturn(mock(MultilingualString.class)).when(usage).getParameterName(parameterConbinationItem);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1);
        AbstractFormula parameter = mock(AbstractFormula.class);

        doReturn(false).when(parameterConbinationItem).isValid(parameter, null);
        doReturn(false).when(parameterConbinationItem).isValidType(parameter, null);
        try {
            parameterConbinationItem.validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error after inversion is null", ie);
            assertTrue("inner error not of type FormulaParameterConbinationItemWrongParameterTypeInnerError",
                       ie instanceof FormulaParameterConbinationItemWrongParameterTypeInnerError);
            throw e;
        }
    }

    /**
     * Tests
     * {@link FormulaParameterConbinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
     * when {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} is false
     * when {@link FormulaParameterConbinationItem#isValidType(AbstractFormula, Evaluator)} is true
     * when {@link FormulaParameterConbinationItem#isValidValue(AbstractFormula, Evaluator)} is false
     * and parameter is not null
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidValue() throws GLanguageException {
        FormulaParameterConbinationItem parameterConbinationItem = spy(FormulaParameterConbinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        Set<MultilingualStringItem> names = new HashSet<>();
        MultilingualStringItem name = new MultilingualStringItem();
        name.setId(1);
        name.setLanguage(Language.EN);
        name.setText("parameterName");
        names.add(name);
        multilingualName.setItems(names);
        doReturn(multilingualName).when(parameterConbinationItem).getName();
        doReturn(1).when(parameterConbinationItem).getSequenceNumber();

        FormulaUsage usage = mock(FormulaUsage.class);
        doReturn(mock(MultilingualString.class)).when(usage).getParameterName(parameterConbinationItem);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1);
        AbstractFormula parameter = mock(AbstractFormula.class);

        doReturn(false).when(parameterConbinationItem).isValid(parameter, null);
        doReturn(true).when(parameterConbinationItem).isValidType(parameter, null);
        doReturn(false).when(parameterConbinationItem).isValidValue(parameter, null);
        try {
            parameterConbinationItem.validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error after inversion is null", ie);
            assertEquals("inner error not of type FormulaParameterConbinationItemWrongParameterValueInnerError",
                         FormulaParameterConbinationItemWrongParameterValueInnerError.class, ie.getClass());
            throw e;
        }
    }

}
