package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbination;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualStringItem;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.conbination
        .FormulaParameterConbinationUnableToValidateInnerError;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by michotte on 22/05/2017.
 */
public class FormulaUsageTest extends BaseDatabaseTest {

	/*
     * Tests
	 */

    /**
     * Tests {@link FormulaDescription} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaUsage formulaUsage = getEntityManager().find(FormulaUsage.class, 1);

		/* Checking entity */
        assertNotNull(formulaUsage);
        assertEquals(Integer.valueOf(1), formulaUsage.getId());

		/* Checking relationships */
        assertNotNull(formulaUsage.getFormulaDescription());
        assertNotNull(formulaUsage.getParameterConbination());
        assertEquals(Integer.valueOf(1), formulaUsage.getFormulaDescription().getId());
        assertEquals(Integer.valueOf(1), formulaUsage.getParameterConbination().getId());
    }

    /**
     * Tests {@link FormulaUsage#validate(AbstractFormula, List, Evaluator)}
     * when {@link FormulaParameterConbination#validate(AbstractFormula, FormulaUsage, List, Evaluator)} does not
     * throw an exception
     */
    @Test
    public void validateValidConbination() {
        FormulaUsage usage = spy(FormulaUsage.class);
        FormulaParameterConbination conbination = mock(FormulaParameterConbination.class);
        try {
            doNothing().when(conbination).validate(null, usage, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown" + e);
        }
        doReturn(conbination).when(usage).getParameterConbination();
        try {
            usage.validate(null, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown" + e);
        }
    }

    /**
     * Tests {@link FormulaUsage#validate(AbstractFormula, List, Evaluator)}
     * when {@link FormulaParameterConbination#validate(AbstractFormula, FormulaUsage, List, Evaluator)} throws an
     * exception
     */
    @Test(expected = GLanguageException.class)
    public void validateNotValidConbination() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = spy(FormulaUsage.class);
        FormulaParameterConbination conbination = mock(FormulaParameterConbination.class);
        GLanguageException exception = new GLanguageException(new FormulaParameterConbinationUnableToValidateInnerError(
                formula,
                conbination,
                null));
        try {
            doThrow(exception).when(conbination).validate(null, usage, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown" + e);
        }
        doReturn(conbination).when(usage).getParameterConbination();
        try {
            usage.validate(null, null, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaUsage#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValid(List, Evaluator)} is true
     */
    @Test
    public void isValidValidConbination() {
        FormulaParameterConbination conbination = mock(FormulaParameterConbination.class);
        doReturn(true).when(conbination).isValid(null, null);

        FormulaUsage usage = spy(FormulaUsage.class);
        doReturn(conbination).when(usage).getParameterConbination();
        assertTrue(usage.isValid(null, null));
    }

    /**
     * Tests {@link FormulaUsage#isValid(List, Evaluator)}
     * when {@link FormulaParameterConbination#isValid(List, Evaluator)} is false
     */
    @Test
    public void isValidNotValidConbination() {
        FormulaParameterConbination conbination = mock(FormulaParameterConbination.class);
        doReturn(false).when(conbination).isValid(null, null);

        FormulaUsage usage = spy(FormulaUsage.class);
        doReturn(conbination).when(usage).getParameterConbination();
        assertFalse(usage.isValid(null, null));
    }

    /**
     * Test {@link FormulaUsage#getParameterName(FormulaParameterConbinationItem)}
     * when there is no overridden parameter name
     */
    @Test
    public void getParameterNameDescriptionNotOverridden() {
        FormulaParameterConbination conbination = mock(FormulaParameterConbination.class);
        FormulaParameterConbinationItem conbinationItem = mock(FormulaParameterConbinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        MultilingualStringItem multilingualStringItemName = new MultilingualStringItem();
        multilingualStringItemName.setId(1);
        Set<MultilingualStringItem> multilingualStringItemsName = new HashSet<>();
        multilingualStringItemsName.add(multilingualStringItemName);
        multilingualName.setItems(multilingualStringItemsName);
        doReturn(multilingualName).when(conbinationItem).getName();
        MultilingualString multilingualDescription = new MultilingualString();
        MultilingualStringItem multilingualStringItemDescription = new MultilingualStringItem();
        multilingualStringItemDescription.setId(2);
        Set<MultilingualStringItem> multilingualStringItemsDescription = new HashSet<>();
        multilingualStringItemsDescription.add(multilingualStringItemDescription);
        multilingualDescription.setItems(multilingualStringItemsDescription);
        doReturn(multilingualDescription).when(conbinationItem).getDescription();
        SortedSet<FormulaParameterConbinationItem> conbinationItems = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationItems.add(conbinationItem);
        doReturn(conbinationItems).when(conbination).getParameters();

        FormulaUsage usage = spy(FormulaUsage.class);
        doReturn(null).when(usage).getOverriddenParameters();
        assertEquals("name not returned", multilingualName, usage.getParameterName(conbinationItem));
        assertEquals("description not returned",
                     multilingualDescription,
                     usage.getParameterDescription(conbinationItem));
    }

    @Test
    public void getParameterNameDescriptionOverridden() {
        FormulaParameterConbination conbination = mock(FormulaParameterConbination.class);
        FormulaParameterConbinationItem conbinationItem = mock(FormulaParameterConbinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        MultilingualStringItem multilingualStringItemName = new MultilingualStringItem();
        multilingualStringItemName.setId(1);
        Set<MultilingualStringItem> multilingualStringItemsName = new HashSet<>();
        multilingualStringItemsName.add(multilingualStringItemName);
        multilingualName.setItems(multilingualStringItemsName);
        doReturn(multilingualName).when(conbinationItem).getName();
        MultilingualString multilingualDescription = new MultilingualString();
        MultilingualStringItem multilingualStringItemDescription = new MultilingualStringItem();
        multilingualStringItemDescription.setId(2);
        Set<MultilingualStringItem> multilingualStringItemsDescription = new HashSet<>();
        multilingualStringItemsDescription.add(multilingualStringItemDescription);
        multilingualDescription.setItems(multilingualStringItemsDescription);
        doReturn(multilingualDescription).when(conbinationItem).getDescription();
        SortedSet<FormulaParameterConbinationItem> conbinationItems = new TreeSet<>(Comparator.comparing(
                FormulaParameterConbinationItem::getSequenceNumber));
        conbinationItems.add(conbinationItem);
        doReturn(conbinationItems).when(conbination).getParameters();

        FormulaUsage usage = spy(FormulaUsage.class);
        List<FormulaUsageParameterConbinationItem> overriddenParameters = new ArrayList<>();
        FormulaUsageParameterConbinationItem overriddenParameter = mock(FormulaUsageParameterConbinationItem.class);
        doReturn(conbinationItem).when(overriddenParameter).getConbinationParameter();
        MultilingualString overriddenMultilingualName = new MultilingualString();
        MultilingualStringItem overriddenMultilingualStringItemName = new MultilingualStringItem();
        overriddenMultilingualStringItemName.setId(3);
        Set<MultilingualStringItem> overriddenMultilingualStringItemsName = new HashSet<>();
        overriddenMultilingualStringItemsName.add(overriddenMultilingualStringItemName);
        overriddenMultilingualName.setItems(overriddenMultilingualStringItemsName);
        doReturn(overriddenMultilingualName).when(overriddenParameter).getName();
        MultilingualString overriddenMultilingualDescription = new MultilingualString();
        MultilingualStringItem overriddenMultilingualStringItemDescription = new MultilingualStringItem();
        overriddenMultilingualStringItemDescription.setId(4);
        Set<MultilingualStringItem> overriddenMultilingualStringItemsDescription = new HashSet<>();
        overriddenMultilingualStringItemsDescription.add(overriddenMultilingualStringItemDescription);
        overriddenMultilingualDescription.setItems(overriddenMultilingualStringItemsDescription);
        doReturn(overriddenMultilingualDescription).when(overriddenParameter).getDescription();
        overriddenParameters.add(overriddenParameter);
        doReturn(overriddenParameters).when(usage).getOverriddenParameters();
        assertEquals("overridden name not returned",
                     overriddenMultilingualName,
                     usage.getParameterName(conbinationItem));
        assertEquals("overridden description not returned",
                     overriddenMultilingualDescription,
                     usage.getParameterDescription(conbinationItem));
    }


}