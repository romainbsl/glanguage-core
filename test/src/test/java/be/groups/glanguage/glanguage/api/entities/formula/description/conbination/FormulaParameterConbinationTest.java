package be.groups.glanguage.glanguage.api.entities.formula.description.conbination;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationItemUnableToValidateInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationUnableToValidateInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationUnreachableParametersInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationWrongParameterNumberInnerError;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by michotte on 9/05/2017.
 */
public class FormulaParameterConbinationTest extends BaseDatabaseTest {

    /*
     * Tests
	 */

    /**
     * Tests {@link FormulaParameterConbination} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaParameterConbination parameterConbination = getEntityManager().find(FormulaParameterConbination.class,
                                                                                   9);

		/* Checking entity */
        assertNotNull(parameterConbination);
        assertEquals(Integer.valueOf(9), parameterConbination.getId());

		/* Checking relationships */
        assertNotNull(parameterConbination.getParameters());
        assertEquals(2, parameterConbination.getParameters().size());
        assertEquals(2, parameterConbination.getParameters().stream().map(d -> d.getId()).distinct().count());
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValidParameterNumber(List)} is false
     */
    @Test
    public void testIsValidNotValidParameterNumber() {
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.isValidParameterNumber(null)).thenReturn(false);

        when(formulaParameterConbination.isValid(null, null)).thenCallRealMethod();
        assertFalse(formulaParameterConbination.isValid(null, null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValidParameterNumber(List)} is true
     * and parameter list is null
     */
    @Test
    public void testIsValidValidParameterNumberNullParameterList() {
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.isValidParameterNumber(null)).thenReturn(true);

        when(formulaParameterConbination.isValid(null, null)).thenCallRealMethod();
        assertTrue(formulaParameterConbination.isValid(null, null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValidParameterNumber(List)} is true
     * and parameter list is not null and empty
     */
    @Test
    public void testIsValidValidParameterNumberEmptyParameterList() {
        List<AbstractFormula> parameters = new ArrayList<>();
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.isValidParameterNumber(parameters)).thenReturn(true);

        when(formulaParameterConbination.isValid(parameters, null)).thenCallRealMethod();
        assertTrue(formulaParameterConbination.isValid(parameters, null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValidParameterNumber(List)} is true
     * and parameter list is not null and not empty
     * and {@link FormulaParameterConbination#isValidParameters(List, Evaluator)} is false
     */
    @Test
    public void testIsValidValidParameterNumberNotValidParameters() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.isValidParameterNumber(parameters)).thenReturn(true);
        when(formulaParameterConbination.isValidParameters(parameters, null)).thenReturn(false);

        when(formulaParameterConbination.isValid(parameters, null)).thenCallRealMethod();
        assertFalse(formulaParameterConbination.isValid(parameters, null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValidParameterNumber(List)} is true
     * and parameter list is not null and not empty
     * and {@link FormulaParameterConbination#isValidParameters(List, Evaluator)} is true
     */
    @Test
    public void testIsValidValidParameterNumberValidParameters() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.isValidParameterNumber(parameters)).thenReturn(true);
        when(formulaParameterConbination.isValidParameters(parameters, null)).thenReturn(true);

        when(formulaParameterConbination.isValid(parameters, null)).thenCallRealMethod();
        assertTrue(formulaParameterConbination.isValid(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is null
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 0
     */
    @Test
    public void testIsValidParameterNumberNullParameterListMinimumParameterNumber0() {
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(0).when(formulaParameterConbination).getParametersMinimumNumber();

        assertTrue(formulaParameterConbination.isValidParameterNumber(null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is not null and empty
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 0
     */
    @Test
    public void testIsValidParameterNumberEmptyParameterListMinimumParameterNumber0() {
        List<AbstractFormula> parameters = new ArrayList<>();
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(0).when(formulaParameterConbination).getParametersMinimumNumber();

        assertTrue(formulaParameterConbination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is null
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 1
     */
    @Test
    public void testIsValidParameterNumberNullParameterListMinimumParameterNumber1() {
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(1).when(formulaParameterConbination).getParametersMinimumNumber();

        assertFalse(formulaParameterConbination.isValidParameterNumber(null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is not null and empty
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 1
     */
    @Test
    public void testIsValidParameterNumberEmptyParameterListMinimumParameterNumber1() {
        List<AbstractFormula> parameters = new ArrayList<>();
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(1).when(formulaParameterConbination).getParametersMinimumNumber();

        assertFalse(formulaParameterConbination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is not null and not empty (size = 1)
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 0
     * and {@link FormulaParameterConbination#getParametersMaximumNumber()} equal 0
     */
    @Test
    public void testIsValidParameterNumberNotEmptyParameterListSizeExceedMaximumParameterNumber() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(0).when(formulaParameterConbination).getParametersMinimumNumber();
        doReturn(0).when(formulaParameterConbination).getParametersMaximumNumber();

        assertFalse(formulaParameterConbination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is not null and not empty (size = 1)
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 0
     * and {@link FormulaParameterConbination#getParametersMaximumNumber()} equal 1
     */
    @Test
    public void testIsValidParameterNumberNotEmptyParameterListSizeDoNotExceedMaximumParameterNumber() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(0).when(formulaParameterConbination).getParametersMinimumNumber();
        doReturn(1).when(formulaParameterConbination).getParametersMaximumNumber();

        assertTrue(formulaParameterConbination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameterNumber(List)}
     * when parameter list is not null and not empty (size = 1)
     * and {@link FormulaParameterConbination#getParametersMinimumNumber()} equal 2
     */
    @Test
    public void testIsValidParameterNumberNotEmptyParameterListSizeIsInferiorToMinimumParameterNumber() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(2).when(formulaParameterConbination).getParametersMinimumNumber();

        assertFalse(formulaParameterConbination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid
     * ([parameter], null)} is true
     */
    @Test
    public void testIsValidParameters1Parameter1ConbinationParameterValid() {
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter, null);
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is false
     */
    @Test
    public void testIsValidParameters1Parameter1ConbinationParameterNotValidNotOptional() {
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(false).when(conbinationParamater).isValid(parameter, null);
        doReturn(false).when(conbinationParamater).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true
     */
    @Test
    public void testIsValidParameters1Parameter1ConbinationParameterNotValidOptional() {
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(false).when(conbinationParamater).isValid(parameter, null);
        doReturn(true).when(conbinationParamater).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters1Parameter2ConbinationParameterFirstValidSecondNotValidOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater1]
     */
    @Test
    public void testIsValidParameters1Parameter2ConbinationParameterFirstNotValidOptionalSecondValid() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(false).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).getOptional();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).isValid(parameter1, null);
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters1Parameter2ConbinationParameterFirstValidSecondNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(false).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 element {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2ConbinationParameterFirstValidSecondNotValidOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(false).when(conbinationParamater2).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 element {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater1]
     */
    @Test
    public void testIsValidParameters2Parameter2ConbinationParameterFirstValidSecondNotValidNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(false).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(false).when(conbinationParamater1).getOptional();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).isValid(parameter2, null);
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is false
     */
    @Test
    public void testIsValidParameters2Parameter1ConbinationParameterValidNotRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter1, null);
        doReturn(false).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true
     */
    @Test
    public void testIsValidParameters2Parameter1ConbinationParameterValidRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true
     */
    @Test
    public void testIsValidParameters2Parameter1ConbinationParameterValidNotValidRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter1, null);
        doReturn(false).when(conbinationParamater).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondValidNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).isValid(parameter2, null);
        doReturn(false).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondNotValidNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(false).when(conbinationParamater2).isValid(parameter2, null);
        doReturn(false).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertFalse(conbination.isValidParameters(parameters, null));
    }


    /**
     * Test {@link FormulaParameterConbination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondOptionalRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).getOptional();
        doReturn(true).when(conbinationParamater2).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();

        assertTrue(conbination.isValidParameters(parameters, null));
    }

    /**
     * Tests {@link FormulaParameterConbination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when {@link FormulaParameterConbination#isValid(List, Evaluator)} is false
     * and {@link FormulaParameterConbination#isValidParameterNumber(List)} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidParameterNumber() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        List<AbstractFormula> parameters = Collections.emptyList();
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(false).when(formulaParameterConbination).isValid(parameters, null);
        doReturn(false).when(formulaParameterConbination).isValidParameterNumber(parameters);
        doReturn(0).when(formulaParameterConbination).getParametersMinimumNumber();
        doReturn(0).when(formulaParameterConbination).getParametersMaximumNumber();
        try {
            formulaParameterConbination.validate(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue("inner error not of type FormulaParameterConbinationUnableToValidateInnerError",
                       ie instanceof FormulaParameterConbinationUnableToValidateInnerError);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error is null after inverting", ie);
            assertTrue("inner error not of type FormulaParameterConbinationWrongParameterNumberInnerError",
                       ie instanceof FormulaParameterConbinationWrongParameterNumberInnerError);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaParameterConbination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when {@link FormulaParameterConbination#isValid(List, Evaluator)} is false
     * and {@link FormulaParameterConbination#isValidParameterNumber(List)} is true
     * and {@link FormulaParameterConbination#isValidParameters(List, Evaluator)} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidParameters() throws GLanguageException {
        List<AbstractFormula> parameters = Collections.emptyList();
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(false).when(formulaParameterConbination).isValid(parameters, null);
        doReturn(true).when(formulaParameterConbination).isValidParameterNumber(parameters);
        doReturn(false).when(formulaParameterConbination).isValidParameters(parameters, null);
        doReturn(0).when(formulaParameterConbination).getParametersMinimumNumber();
        doReturn(0).when(formulaParameterConbination).getParametersMaximumNumber();

        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        FormulaParameterConbinationItem conbinationItem = mock(FormulaParameterConbinationItem.class);
        MultilingualString multilingualString = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString).asText(Language.EN);
        doReturn(multilingualString).when(usage).getParameterName(conbinationItem);
        doThrow(new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(formula,
                                                                                                     usage,
                                                                                                     conbinationItem,
                                                                                                     "",
                                                                                                     null))).when(
                formulaParameterConbination).validateParameters(formula, usage, parameters, null);
        try {
            formulaParameterConbination.validate(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue("inner error not of type FormulaParameterConbinationUnableToValidateInnerError",
                       ie instanceof FormulaParameterConbinationUnableToValidateInnerError);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error is null after inverting", ie);
            assertTrue("inner error not of type FormulaParameterConbinationItemUnableToValidateInnerError",
                       ie instanceof FormulaParameterConbinationItemUnableToValidateInnerError);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaParameterConbination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when {@link FormulaParameterConbination#isValid(List, Evaluator)} is true
     */
    @Test
    public void testValidateIsValid() {
        List<AbstractFormula> parameters = Collections.emptyList();
        FormulaParameterConbination formulaParameterConbination = spy(FormulaParameterConbination.class);
        doReturn(true).when(formulaParameterConbination).isValid(parameters, null);
        try {
            formulaParameterConbination.validate(null, null, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid
     * ([parameter], null)} is true
     */
    @Test
    public void testValidateParameters1Parameter1ConbinationParameterValid() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter, null);
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters1Parameter1ConbinationParameterNotValidNotOptional() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(false).when(conbinationParamater).isValid(parameter, null);
        doReturn(false).when(conbinationParamater).getOptional();
        MultilingualString multilingualString = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString).asText(Language.EN);
        doReturn(multilingualString).when(usage).getParameterName(conbinationParamater);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(
                formula,
                usage,
                conbinationParamater,
                "",
                null));
        try {
            doThrow(exception).when(conbinationParamater).validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters1Parameter1ConbinationParameterNotValidOptional() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(false).when(conbinationParamater).isValid(parameter, null);
        doReturn(true).when(conbinationParamater).getOptional();
        MultilingualString multilingualString = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString).asText(Language.EN);
        doReturn(multilingualString).when(usage).getParameterName(conbinationParamater);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(
                formula,
                usage,
                conbinationParamater,
                "",
                null));
        try {
            doThrow(exception).when(conbinationParamater).validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertNotEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testValidateParameters1Parameter2ConbinationParameterFirstValidSecondNotValidOptional() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater1]
     */
    @Test
    public void testValidateParameters1Parameter2ConbinationParameterFirstNotValidOptionalSecondValid() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(false).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).getOptional();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).isValid(parameter1, null);
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater2]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters1Parameter2ConbinationParameterFirstValidSecondNotOptional() throws
                                                                                                   GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(false).when(conbinationParamater2).getOptional();
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(conbinationParamater2);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(
                formula,
                usage,
                conbinationParamater2,
                "",
                null));
        try {
            doThrow(exception).when(conbinationParamater2).validate(formula, usage, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 element {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter2ConbinationParameterFirstValidSecondNotValidOptional() throws
                                                                                                        GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(false).when(conbinationParamater2).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue(ie instanceof FormulaParameterConbinationUnreachableParametersInnerError);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 element {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater1]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter2ConbinationParameterFirstNotValidNotOptionalSecondValid() throws
                                                                                                           GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(false).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(false).when(conbinationParamater1).getOptional();
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(conbinationParamater1);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(
                formula,
                usage,
                conbinationParamater1,
                "",
                null));
        try {
            doThrow(exception).when(conbinationParamater1).validate(formula, usage, parameter1, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).isValid(parameter2, null);
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter1ConbinationParameterValidNotRepeatable() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter1, null);
        doReturn(false).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue(ie instanceof FormulaParameterConbinationUnreachableParametersInnerError);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true
     */
    @Test
    public void testValidateParameters2Parameter1ConbinationParameterValidRepeatable() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 1 element {@code
     * [conbinationParmater]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter1ConbinationParameterValidNotValidRepeatable() throws
                                                                                               GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(true).when(conbinationParamater).isValid(parameter1, null);
        doReturn(false).when(conbinationParamater).isValid(parameter2, null);
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(conbinationParamater);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(
                formula,
                usage,
                conbinationParamater,
                "",
                null));
        try {
            doThrow(exception).when(conbinationParamater).validate(formula, usage, parameter2, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        doReturn(true).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue(ie instanceof FormulaParameterConbinationUnreachableParametersInnerError);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testValidateParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondOptional() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater2]
     */
    @Test
    public void testValidateParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondValidNotOptional() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).isValid(parameter2, null);
        doReturn(false).when(conbinationParamater2).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater2]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is false for [conbinationParmater2]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondNotValidNotOptional()
            throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter1").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(conbinationParamater1);
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(false).when(conbinationParamater2).isValid(parameter2, null);
        doReturn(false).when(conbinationParamater2).getOptional();
        MultilingualString multilingualString2 = mock(MultilingualString.class);
        doReturn("parameter2").when(multilingualString2).asText(Language.EN);
        doReturn(multilingualString2).when(usage).getParameterName(conbinationParamater2);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationItemUnableToValidateInnerError(
                formula,
                usage,
                conbinationParamater2,
                "",
                null));
        try {
            doThrow(exception).when(conbinationParamater2).validate(formula, usage, parameter2, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterConbination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterConbination#getParameters()} returns a list of 2 elements {@code
     * [conbinationParmater1]} and {@code [conbinationParameter2]} <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} {@code [conbinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterConbinationItem#getRepeatable()} is true for [conbinationParmater1]
     * and {@link FormulaParameterConbinationItem#getOptional()} is true for [conbinationParmater2]
     */
    @Test
    public void testValidateParameters2Parameter2ConbinationParameterFirstValidRepeatableSecondOptionalRepeatable() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterConbinationItem conbinationParamater1 = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater1).getSequenceNumber();
        doReturn(true).when(conbinationParamater1).isValid(parameter1, null);
        doReturn(true).when(conbinationParamater1).isValid(parameter2, null);
        doReturn(true).when(conbinationParamater1).getRepeatable();
        FormulaParameterConbinationItem conbinationParamater2 = mock(FormulaParameterConbinationItem.class);
        doReturn(2).when(conbinationParamater2).getSequenceNumber();
        doReturn(true).when(conbinationParamater2).getOptional();
        doReturn(true).when(conbinationParamater2).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater1);
        conbinationParameters.add(conbinationParamater2);

        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        try {
            conbination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterConbination#getParametersMinimumNumber()}
     * when there is no conbination parameter
     */
    @Test
    public void testGetParametersMinimumNumberNoConbinationParameter() {
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        assertEquals(Integer.valueOf(0), conbination.getParametersMinimumNumber());
    }

    /**
     * Test {@link FormulaParameterConbination#getParametersMinimumNumber()}
     * when there is 1 conbination parameter optional
     */
    @Test
    public void testGetParametersMinimumNumber1ConbinationParameterOptional() {
        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater).getSequenceNumber();
        doReturn(true).when(conbinationParamater).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        conbinationParameters.add(conbinationParamater);
        assertEquals(Integer.valueOf(0), conbination.getParametersMinimumNumber());
    }

    /**
     * Test {@link FormulaParameterConbination#getParametersMinimumNumber()}
     * when there is 1 conbination parameter not optional
     */
    @Test
    public void testGetParametersMinimumNumber1ConbinationParameterNotOptional() {
        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater).getSequenceNumber();
        doReturn(false).when(conbinationParamater).getOptional();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);
        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        assertEquals(Integer.valueOf(1), conbination.getParametersMinimumNumber());
    }

    /**
     * Test {@link FormulaParameterConbination#getParametersMaximumNumber()}
     * when there is no conbination parameter
     */
    @Test
    public void testGetParametersMaximumNumberNoConbinationParameter() {
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        assertEquals(Integer.valueOf(0), conbination.getParametersMaximumNumber());
    }

    /**
     * Test {@link FormulaParameterConbination#getParametersMaximumNumber()}
     * when there is 1 conbination parameter repeatable
     */
    @Test
    public void testGetParametersMaximumNumber1ConbinationParameterRepeatable() {
        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater).getSequenceNumber();
        doReturn(true).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);
        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), conbination.getParametersMaximumNumber());
    }

    /**
     * Test {@link FormulaParameterConbination#getParametersMaximumNumber()}
     * when there is 1 conbination parameter not repeatable
     */
    @Test
    public void testGetParametersMaximumNumber1ConbinationParameterNotRepeatable() {
        FormulaParameterConbinationItem conbinationParamater = mock(FormulaParameterConbinationItem.class);
        doReturn(1).when(conbinationParamater).getSequenceNumber();
        doReturn(false).when(conbinationParamater).getRepeatable();
        SortedSet<FormulaParameterConbinationItem> conbinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationParameters.add(conbinationParamater);
        FormulaParameterConbination conbination = spy(FormulaParameterConbination.class);
        doReturn(conbinationParameters).when(conbination).getParameters();
        assertEquals(Integer.valueOf(1), conbination.getParametersMaximumNumber());
    }

}
