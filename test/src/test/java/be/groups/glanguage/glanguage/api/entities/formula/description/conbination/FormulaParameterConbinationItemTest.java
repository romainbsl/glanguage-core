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
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		
		assertEquals("operand", parameter.getName());
		assertEquals("Objet à nier", parameter.getDescription(Language.FR));
		assertEquals("Object te ontkennen", parameter.getDescription(Language.NL));
		assertEquals("Item to negate", parameter.getDescription(Language.EN));
		assertNull(parameter.getDescription(Language.X));

		assertEquals(1, parameter.getTypes().size());
		assertTrue(parameter.getTypes().contains(FormulaReturnType.BOOLEAN));
		
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
	 * Tests
	 * {@link FormulaParameterConbinationItem#validate(AbstractFormula, FormulaUsage, AbstractFormula, Evaluator)}
	 * when parameter is null
	 */
	@Test(expected = GLanguageException.class)
	public void testValidateNullParameter() throws GLanguageException {
		FormulaParameterConbinationItem parameterConbinationItem = mock(FormulaParameterConbinationItem.class);
		MultilingualString multilingualName = new MultilingualString();
		Set<MultilingualStringItem> names = new HashSet<>();
		MultilingualStringItem name = new MultilingualStringItem();
		name.setLanguage(Language.EN);
		name.setText("parameterName");
		names.add(name);
		multilingualName.setItems(names);
		when(parameterConbinationItem.getName()).thenReturn(multilingualName);
		when(parameterConbinationItem.getSequenceNumber()).thenReturn(1);

		FormulaUsage usage = mock(FormulaUsage.class);

		AbstractFormula formula = mock(AbstractFormula.class);
		when(formula.getId()).thenReturn(1);

		try {
			parameterConbinationItem.validate(formula, usage, null, null);
		} catch (GLanguageException e) {
			// TODO check inner error type is FormulaParameterConbinationItemNullInnerError
			InnerError ie = e.getError().getInnerError();
			assertNotNull("inner error is null", ie);
			e.getError().invertInnerError();
			ie = e.getError().getInnerError();
			assertNotNull("inner error after inversion is null", ie);
			assertTrue("inner error not of type FormulaParameterConbinationItemNullInnerError", ie instanceof
					FormulaParameterConbinationItemNullInnerError);
			throw e;
		}
	}

	/**
	 * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter type does not
	 * match
	 */
	@Test
	public void testIsValidParameterTypeNotMatching() {
		FormulaParameterConbinationItem parameterConbinationItem = mock(FormulaParameterConbinationItem.class);
		Set<FormulaParameterConbinationItemType> returnTypes = new HashSet<>();
		FormulaParameterConbinationItemType returnType = new FormulaParameterConbinationItemType();
		returnType.setReturnType(FormulaReturnType.BOOLEAN);
		returnTypes.add(returnType);
		when(parameterConbinationItem.getTypes()).thenReturn(returnTypes);

		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		assertFalse(parameterConbinationItem.isValid(parameter, null));
	}

	/**
	 * Tests {@link FormulaParameterConbinationItem#isValid(AbstractFormula, Evaluator)} when parameter type matches
	 */
	@Test
	public void testIsValidParameterTypeMatching() {
		FormulaParameterConbinationItem parameterConbinationItem = mock(FormulaParameterConbinationItem.class);
		Set<FormulaParameterConbinationItemType> returnTypes = new HashSet<>();
		FormulaParameterConbinationItemType returnType = new FormulaParameterConbinationItemType();
		returnType.setReturnType(FormulaReturnType.BOOLEAN);
		returnTypes.add(returnType);
		when(parameterConbinationItem.getTypes()).thenReturn(returnTypes);

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
		FormulaParameterConbinationItem parameterConbinationItem = mock(FormulaParameterConbinationItem.class);
		Set<FormulaParameterConbinationItemType> returnTypes = new HashSet<>();
		FormulaParameterConbinationItemType returnType1 = new FormulaParameterConbinationItemType();
		returnType1.setReturnType(FormulaReturnType.INTEGER);
		returnTypes.add(returnType1);
		FormulaParameterConbinationItemType returnType2 = new FormulaParameterConbinationItemType();
		returnType2.setReturnType(FormulaReturnType.NUMERIC);
		returnTypes.add(returnType2);
		when(parameterConbinationItem.getTypes()).thenReturn(returnTypes);
		Set<FormulaParameterConbinationItemValue> values = new HashSet<>();
		FormulaParameterConbinationItemValue value = new FormulaParameterConbinationItemValue();
		value.setValue("1");
		values.add(value);
		when(parameterConbinationItem.getValues()).thenReturn(values);

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
		FormulaParameterConbinationItem parameterConbinationItem = mock(FormulaParameterConbinationItem.class);
		Set<FormulaParameterConbinationItemType> returnTypes = new HashSet<>();
		FormulaParameterConbinationItemType returnType1 = new FormulaParameterConbinationItemType();
		returnType1.setReturnType(FormulaReturnType.INTEGER);
		returnTypes.add(returnType1);
		FormulaParameterConbinationItemType returnType2 = new FormulaParameterConbinationItemType();
		returnType2.setReturnType(FormulaReturnType.NUMERIC);
		returnTypes.add(returnType2);
		when(parameterConbinationItem.getTypes()).thenReturn(returnTypes);
		Set<FormulaParameterConbinationItemValue> values = new HashSet<>();
		FormulaParameterConbinationItemValue value = new FormulaParameterConbinationItemValue();
		value.setValue("1");
		values.add(value);
		when(parameterConbinationItem.getValues()).thenReturn(values);

		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		try {
			when(parameter.getValue(null)).thenReturn(1.0);
		} catch (GLanguageException e) {
			fail("Exception thrown : " + e);
		}

		assertTrue(parameterConbinationItem.isValid(parameter, null));
	}

}
