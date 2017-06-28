package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.combination
        .FormulaParameterCombinationItemUnableToValidateInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.combination
        .FormulaParameterCombinationUnableToValidateInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.combination
        .FormulaParameterCombinationUnreachableParametersInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.combination
        .FormulaParameterCombinationWrongParameterNumberInnerError;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author michotte
 */
public class FormulaParameterCombinationTest extends BaseDatabaseTest {

    /*
     * Tests
	 */

    /**
     * Tests {@link FormulaParameterCombination} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaParameterCombination parameterCombination = getEntityManager().find(FormulaParameterCombination.class,
                                                                                   9);

		/* Checking entity */
        assertNotNull(parameterCombination);
        assertEquals(Integer.valueOf(9), parameterCombination.getId());

		/* Checking relationships */
        assertNotNull(parameterCombination.getParameters());
        assertEquals(2, parameterCombination.getParameters().size());
        assertEquals(2, parameterCombination.getParameters().stream().map(d -> d.getId()).distinct().count());
    }

    /**
     * Tests {@link FormulaParameterCombination#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValidParameterNumber(List)} is false
     */
    @Test
    public void testIsValidNotValidParameterNumber() {
        FormulaParameterCombination formulaParameterCombination = mock(FormulaParameterCombination.class);
        when(formulaParameterCombination.isValidParameterNumber(null)).thenReturn(false);

        when(formulaParameterCombination.isValid(null, null)).thenCallRealMethod();
        assertFalse(formulaParameterCombination.isValid(null, null));
    }

    /**
     * Tests {@link FormulaParameterCombination#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValidParameterNumber(List)} is true
     * and parameter list is null
     */
    @Test
    public void testIsValidValidParameterNumberNullParameterList() {
        FormulaParameterCombination formulaParameterCombination = mock(FormulaParameterCombination.class);
        when(formulaParameterCombination.isValidParameterNumber(null)).thenReturn(true);

        when(formulaParameterCombination.isValid(null, null)).thenCallRealMethod();
        assertTrue(formulaParameterCombination.isValid(null, null));
    }

    /**
     * Tests {@link FormulaParameterCombination#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValidParameterNumber(List)} is true
     * and parameter list is not null and empty
     */
    @Test
    public void testIsValidValidParameterNumberEmptyParameterList() {
        List<AbstractFormula> parameters = new ArrayList<>();
        FormulaParameterCombination formulaParameterCombination = mock(FormulaParameterCombination.class);
        when(formulaParameterCombination.isValidParameterNumber(parameters)).thenReturn(true);

        when(formulaParameterCombination.isValid(parameters, null)).thenCallRealMethod();
        assertTrue(formulaParameterCombination.isValid(parameters, null));
    }

    /**
     * Tests {@link FormulaParameterCombination#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValidParameterNumber(List)} is true
     * and parameter list is not null and not empty
     * and {@link FormulaParameterCombination#isValidParameters(List, Evaluator)} is false
     */
    @Test
    public void testIsValidValidParameterNumberNotValidParameters() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterCombination formulaParameterCombination = mock(FormulaParameterCombination.class);
        when(formulaParameterCombination.isValidParameterNumber(parameters)).thenReturn(true);
        when(formulaParameterCombination.isValidParameters(parameters, null)).thenReturn(false);

        when(formulaParameterCombination.isValid(parameters, null)).thenCallRealMethod();
        assertFalse(formulaParameterCombination.isValid(parameters, null));
    }

    /**
     * Tests {@link FormulaParameterCombination#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValidParameterNumber(List)} is true
     * and parameter list is not null and not empty
     * and {@link FormulaParameterCombination#isValidParameters(List, Evaluator)} is true
     */
    @Test
    public void testIsValidValidParameterNumberValidParameters() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterCombination formulaParameterCombination = mock(FormulaParameterCombination.class);
        when(formulaParameterCombination.isValidParameterNumber(parameters)).thenReturn(true);
        when(formulaParameterCombination.isValidParameters(parameters, null)).thenReturn(true);

        when(formulaParameterCombination.isValid(parameters, null)).thenCallRealMethod();
        assertTrue(formulaParameterCombination.isValid(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is null
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 0
     */
    @Test
    public void testIsValidParameterNumberNullParameterListMinimumParameterNumber0() {
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(0).when(formulaParameterCombination).getParametersMinimumNumber();

        assertTrue(formulaParameterCombination.isValidParameterNumber(null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is not null and empty
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 0
     */
    @Test
    public void testIsValidParameterNumberEmptyParameterListMinimumParameterNumber0() {
        List<AbstractFormula> parameters = new ArrayList<>();
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(0).when(formulaParameterCombination).getParametersMinimumNumber();

        assertTrue(formulaParameterCombination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is null
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 1
     */
    @Test
    public void testIsValidParameterNumberNullParameterListMinimumParameterNumber1() {
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(1).when(formulaParameterCombination).getParametersMinimumNumber();

        assertFalse(formulaParameterCombination.isValidParameterNumber(null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is not null and empty
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 1
     */
    @Test
    public void testIsValidParameterNumberEmptyParameterListMinimumParameterNumber1() {
        List<AbstractFormula> parameters = new ArrayList<>();
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(1).when(formulaParameterCombination).getParametersMinimumNumber();

        assertFalse(formulaParameterCombination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is not null and not empty (size = 1)
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 0
     * and {@link FormulaParameterCombination#getParametersMaximumNumber()} equal 0
     */
    @Test
    public void testIsValidParameterNumberNotEmptyParameterListSizeExceedMaximumParameterNumber() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(0).when(formulaParameterCombination).getParametersMinimumNumber();
        doReturn(0).when(formulaParameterCombination).getParametersMaximumNumber();

        assertFalse(formulaParameterCombination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is not null and not empty (size = 1)
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 0
     * and {@link FormulaParameterCombination#getParametersMaximumNumber()} equal 1
     */
    @Test
    public void testIsValidParameterNumberNotEmptyParameterListSizeDoNotExceedMaximumParameterNumber() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(0).when(formulaParameterCombination).getParametersMinimumNumber();
        doReturn(1).when(formulaParameterCombination).getParametersMaximumNumber();

        assertTrue(formulaParameterCombination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameterNumber(List)}
     * when parameter list is not null and not empty (size = 1)
     * and {@link FormulaParameterCombination#getParametersMinimumNumber()} equal 2
     */
    @Test
    public void testIsValidParameterNumberNotEmptyParameterListSizeIsInferiorToMinimumParameterNumber() {
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(2).when(formulaParameterCombination).getParametersMinimumNumber();

        assertFalse(formulaParameterCombination.isValidParameterNumber(parameters));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid
     * ([parameter], null)} is true
     */
    @Test
    public void testIsValidParameters1Parameter1CombinationParameterValid() {
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter, null);
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is false
     */
    @Test
    public void testIsValidParameters1Parameter1CombinationParameterNotValidNotOptional() {
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(false).when(combinationParamater).isValid(parameter, null);
        doReturn(false).when(combinationParamater).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true
     */
    @Test
    public void testIsValidParameters1Parameter1CombinationParameterNotValidOptional() {
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(false).when(combinationParamater).isValid(parameter, null);
        doReturn(true).when(combinationParamater).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters1Parameter2CombinationParameterFirstValidSecondNotValidOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater1]
     */
    @Test
    public void testIsValidParameters1Parameter2CombinationParameterFirstNotValidOptionalSecondValid() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(false).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).getOptional();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).isValid(parameter1, null);
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters1Parameter2CombinationParameterFirstValidSecondNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(false).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 element {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2CombinationParameterFirstValidSecondNotValidOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(false).when(combinationParamater2).isValid(parameter2, null);
        doReturn(true).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 element {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater1]
     */
    @Test
    public void testIsValidParameters2Parameter2CombinationParameterFirstValidSecondNotValidNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(false).when(combinationParamater1).isValid(parameter1, null);
        doReturn(false).when(combinationParamater1).getOptional();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).isValid(parameter2, null);
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is false
     */
    @Test
    public void testIsValidParameters2Parameter1CombinationParameterValidNotRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter1, null);
        doReturn(false).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true
     */
    @Test
    public void testIsValidParameters2Parameter1CombinationParameterValidRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter1, null);
        doReturn(true).when(combinationParamater).isValid(parameter2, null);
        doReturn(true).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true
     */
    @Test
    public void testIsValidParameters2Parameter1CombinationParameterValidNotValidRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter1, null);
        doReturn(false).when(combinationParamater).isValid(parameter2, null);
        doReturn(true).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2CombinationParameterFirstValidRepeatableSecondOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2CombinationParameterFirstValidRepeatableSecondValidNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).isValid(parameter2, null);
        doReturn(false).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2CombinationParameterFirstValidRepeatableSecondNotValidNotOptional() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(false).when(combinationParamater2).isValid(parameter2, null);
        doReturn(false).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertFalse(combination.isValidParameters(parameters, null));
    }


    /**
     * Test {@link FormulaParameterCombination#isValidParameters(List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testIsValidParameters2Parameter2CombinationParameterFirstValidRepeatableSecondOptionalRepeatable() {
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).getOptional();
        doReturn(true).when(combinationParamater2).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();

        assertTrue(combination.isValidParameters(parameters, null));
    }

    /**
     * Tests {@link FormulaParameterCombination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when {@link FormulaParameterCombination#isValid(List, Evaluator)} is false
     * and {@link FormulaParameterCombination#isValidParameterNumber(List)} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidParameterNumber() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        List<AbstractFormula> parameters = Collections.emptyList();
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(false).when(formulaParameterCombination).isValid(parameters, null);
        doReturn(false).when(formulaParameterCombination).isValidParameterNumber(parameters);
        doReturn(0).when(formulaParameterCombination).getParametersMinimumNumber();
        doReturn(0).when(formulaParameterCombination).getParametersMaximumNumber();
        try {
            formulaParameterCombination.validate(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue("inner error not of type FormulaParameterCombinationUnableToValidateInnerError",
                       ie instanceof FormulaParameterCombinationUnableToValidateInnerError);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error is null after inverting", ie);
            assertTrue("inner error not of type FormulaParameterCombinationWrongParameterNumberInnerError",
                       ie instanceof FormulaParameterCombinationWrongParameterNumberInnerError);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaParameterCombination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when {@link FormulaParameterCombination#isValid(List, Evaluator)} is false
     * and {@link FormulaParameterCombination#isValidParameterNumber(List)} is true
     * and {@link FormulaParameterCombination#isValidParameters(List, Evaluator)} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNotValidParameters() throws GLanguageException {
        List<AbstractFormula> parameters = Collections.emptyList();
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(false).when(formulaParameterCombination).isValid(parameters, null);
        doReturn(true).when(formulaParameterCombination).isValidParameterNumber(parameters);
        doReturn(false).when(formulaParameterCombination).isValidParameters(parameters, null);
        doReturn(0).when(formulaParameterCombination).getParametersMinimumNumber();
        doReturn(0).when(formulaParameterCombination).getParametersMaximumNumber();

        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        FormulaParameterCombinationItem combinationItem = mock(FormulaParameterCombinationItem.class);
        MultilingualString multilingualString = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString).asText(Language.EN);
        doReturn(multilingualString).when(usage).getParameterName(combinationItem);
        doThrow(new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(formula,
                                                                                                     usage,
                                                                                                     combinationItem,
                                                                                                     "",
                                                                                                     null))).when(
                formulaParameterCombination).validateParameters(formula, usage, parameters, null);
        try {
            formulaParameterCombination.validate(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue("inner error not of type FormulaParameterCombinationUnableToValidateInnerError",
                       ie instanceof FormulaParameterCombinationUnableToValidateInnerError);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error is null after inverting", ie);
            assertTrue("inner error not of type FormulaParameterCombinationItemUnableToValidateInnerError",
                       ie instanceof FormulaParameterCombinationItemUnableToValidateInnerError);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaParameterCombination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when {@link FormulaParameterCombination#isValid(List, Evaluator)} is true
     */
    @Test
    public void testValidateIsValid() {
        List<AbstractFormula> parameters = Collections.emptyList();
        FormulaParameterCombination formulaParameterCombination = spy(FormulaParameterCombination.class);
        doReturn(true).when(formulaParameterCombination).isValid(parameters, null);
        try {
            formulaParameterCombination.validate(null, null, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid
     * ([parameter], null)} is true
     */
    @Test
    public void testValidateParameters1Parameter1CombinationParameterValid() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter, null);
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters1Parameter1CombinationParameterNotValidNotOptional() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(false).when(combinationParamater).isValid(parameter, null);
        doReturn(false).when(combinationParamater).getOptional();
        MultilingualString multilingualString = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString).asText(Language.EN);
        doReturn(multilingualString).when(usage).getParameterName(combinationParamater);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(
                formula,
                usage,
                combinationParamater,
                "",
                null));
        try {
            doThrow(exception).when(combinationParamater).validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid
     * ([parameter], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters1Parameter1CombinationParameterNotValidOptional() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(false).when(combinationParamater).isValid(parameter, null);
        doReturn(true).when(combinationParamater).getOptional();
        MultilingualString multilingualString = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString).asText(Language.EN);
        doReturn(multilingualString).when(usage).getParameterName(combinationParamater);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(
                formula,
                usage,
                combinationParamater,
                "",
                null));
        try {
            doThrow(exception).when(combinationParamater).validate(formula, usage, parameter, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertNotEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testValidateParameters1Parameter2CombinationParameterFirstValidSecondNotValidOptional() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]} <br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater1]
     */
    @Test
    public void testValidateParameters1Parameter2CombinationParameterFirstNotValidOptionalSecondValid() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(false).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).getOptional();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).isValid(parameter1, null);
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 1 element {@code [parameter]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater2]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters1Parameter2CombinationParameterFirstValidSecondNotOptional() throws
                                                                                                   GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(false).when(combinationParamater2).getOptional();
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(combinationParamater2);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(
                formula,
                usage,
                combinationParamater2,
                "",
                null));
        try {
            doThrow(exception).when(combinationParamater2).validate(formula, usage, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 element {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter2CombinationParameterFirstValidSecondNotValidOptional() throws
                                                                                                        GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(false).when(combinationParamater2).isValid(parameter2, null);
        doReturn(true).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue(ie instanceof FormulaParameterCombinationUnreachableParametersInnerError);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 element {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater1]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter2CombinationParameterFirstNotValidNotOptionalSecondValid() throws
                                                                                                           GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(false).when(combinationParamater1).isValid(parameter1, null);
        doReturn(false).when(combinationParamater1).getOptional();
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(combinationParamater1);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(
                formula,
                usage,
                combinationParamater1,
                "",
                null));
        try {
            doThrow(exception).when(combinationParamater1).validate(formula, usage, parameter1, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).isValid(parameter2, null);
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is false
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter1CombinationParameterValidNotRepeatable() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter1, null);
        doReturn(false).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue(ie instanceof FormulaParameterCombinationUnreachableParametersInnerError);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true
     */
    @Test
    public void testValidateParameters2Parameter1CombinationParameterValidRepeatable() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter1, null);
        doReturn(true).when(combinationParamater).isValid(parameter2, null);
        doReturn(true).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 element {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 1 element {@code
     * [combinationParmater]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter1CombinationParameterValidNotValidRepeatable() throws
                                                                                               GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(true).when(combinationParamater).isValid(parameter1, null);
        doReturn(false).when(combinationParamater).isValid(parameter2, null);
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(combinationParamater);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(
                formula,
                usage,
                combinationParamater,
                "",
                null));
        try {
            doThrow(exception).when(combinationParamater).validate(formula, usage, parameter2, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        doReturn(true).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue(ie instanceof FormulaParameterCombinationUnreachableParametersInnerError);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testValidateParameters2Parameter2CombinationParameterFirstValidRepeatableSecondOptional() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater2]
     */
    @Test
    public void testValidateParameters2Parameter2CombinationParameterFirstValidRepeatableSecondValidNotOptional() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).isValid(parameter2, null);
        doReturn(false).when(combinationParamater2).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater2]
     * .isValid([parameter2], null)} is false <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is false for [combinationParmater2]
     */
    @Test(expected = GLanguageException.class)
    public void testValidateParameters2Parameter2CombinationParameterFirstValidRepeatableSecondNotValidNotOptional()
            throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        MultilingualString multilingualString1 = mock(MultilingualString.class);
        doReturn("parameter1").when(multilingualString1).asText(Language.EN);
        doReturn(multilingualString1).when(usage).getParameterName(combinationParamater1);
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(false).when(combinationParamater2).isValid(parameter2, null);
        doReturn(false).when(combinationParamater2).getOptional();
        MultilingualString multilingualString2 = mock(MultilingualString.class);
        doReturn("parameter2").when(multilingualString2).asText(Language.EN);
        doReturn(multilingualString2).when(usage).getParameterName(combinationParamater2);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationItemUnableToValidateInnerError(
                formula,
                usage,
                combinationParamater2,
                "",
                null));
        try {
            doThrow(exception).when(combinationParamater2).validate(formula, usage, parameter2, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Test {@link FormulaParameterCombination#validateParameters(AbstractFormula, FormulaUsage, List, Evaluator)}
     * when parameter list is not null and count 2 elements {@code [parameter1]} and {@code [parameter2]}<br/>
     * and {@link FormulaParameterCombination#getParameters()} returns a list of 2 elements {@code
     * [combinationParmater1]} and {@code [combinationParameter2]} <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter1], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)} {@code [combinationParmater1]
     * .isValid([parameter2], null)} is true <br/>
     * and {@link FormulaParameterCombinationItem#getRepeatable()} is true for [combinationParmater1]
     * and {@link FormulaParameterCombinationItem#getOptional()} is true for [combinationParmater2]
     */
    @Test
    public void testValidateParameters2Parameter2CombinationParameterFirstValidRepeatableSecondOptionalRepeatable() {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        AbstractFormula parameter1 = mock(AbstractFormula.class);
        AbstractFormula parameter2 = mock(AbstractFormula.class);
        List<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        FormulaParameterCombinationItem combinationParamater1 = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater1).getSequenceNumber();
        doReturn(true).when(combinationParamater1).isValid(parameter1, null);
        doReturn(true).when(combinationParamater1).isValid(parameter2, null);
        doReturn(true).when(combinationParamater1).getRepeatable();
        FormulaParameterCombinationItem combinationParamater2 = mock(FormulaParameterCombinationItem.class);
        doReturn(2).when(combinationParamater2).getSequenceNumber();
        doReturn(true).when(combinationParamater2).getOptional();
        doReturn(true).when(combinationParamater2).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater1);
        combinationParameters.add(combinationParamater2);

        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        try {
            combination.validateParameters(formula, usage, parameters, null);
        } catch (GLanguageException e) {
            fail("Exception thrown : " + e);
        }
    }

    /**
     * Test {@link FormulaParameterCombination#getParametersMinimumNumber()}
     * when there is no combination parameter
     */
    @Test
    public void testGetParametersMinimumNumberNoCombinationParameter() {
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        assertEquals(Integer.valueOf(0), combination.getParametersMinimumNumber());
    }

    /**
     * Test {@link FormulaParameterCombination#getParametersMinimumNumber()}
     * when there is 1 combination parameter optional
     */
    @Test
    public void testGetParametersMinimumNumber1CombinationParameterOptional() {
        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater).getSequenceNumber();
        doReturn(true).when(combinationParamater).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        combinationParameters.add(combinationParamater);
        assertEquals(Integer.valueOf(0), combination.getParametersMinimumNumber());
    }

    /**
     * Test {@link FormulaParameterCombination#getParametersMinimumNumber()}
     * when there is 1 combination parameter not optional
     */
    @Test
    public void testGetParametersMinimumNumber1CombinationParameterNotOptional() {
        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater).getSequenceNumber();
        doReturn(false).when(combinationParamater).getOptional();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);
        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        assertEquals(Integer.valueOf(1), combination.getParametersMinimumNumber());
    }

    /**
     * Test {@link FormulaParameterCombination#getParametersMaximumNumber()}
     * when there is no combination parameter
     */
    @Test
    public void testGetParametersMaximumNumberNoCombinationParameter() {
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        assertEquals(Integer.valueOf(0), combination.getParametersMaximumNumber());
    }

    /**
     * Test {@link FormulaParameterCombination#getParametersMaximumNumber()}
     * when there is 1 combination parameter repeatable
     */
    @Test
    public void testGetParametersMaximumNumber1CombinationParameterRepeatable() {
        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater).getSequenceNumber();
        doReturn(true).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);
        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), combination.getParametersMaximumNumber());
    }

    /**
     * Test {@link FormulaParameterCombination#getParametersMaximumNumber()}
     * when there is 1 combination parameter not repeatable
     */
    @Test
    public void testGetParametersMaximumNumber1CombinationParameterNotRepeatable() {
        FormulaParameterCombinationItem combinationParamater = mock(FormulaParameterCombinationItem.class);
        doReturn(1).when(combinationParamater).getSequenceNumber();
        doReturn(false).when(combinationParamater).getRepeatable();
        SortedSet<FormulaParameterCombinationItem> combinationParameters = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationParameters.add(combinationParamater);
        FormulaParameterCombination combination = spy(FormulaParameterCombination.class);
        doReturn(combinationParameters).when(combination).getParameters();
        assertEquals(Integer.valueOf(1), combination.getParametersMaximumNumber());
    }

}
