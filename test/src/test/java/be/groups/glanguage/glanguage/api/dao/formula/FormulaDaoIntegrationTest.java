package be.groups.glanguage.glanguage.api.dao.formula;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDaoIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDao formulaDao;

  @Test
  public void testFindById() {
    AbstractFormula formula = formulaDao.findById(-900000);
    assertNotNull(formula);
    assertEquals(-900000, formula.getId());
  }
}
