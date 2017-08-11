package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.formula.*;
import org.springframework.data.jpa.repository.*;

/**
 * DAO for {@link AbstractFormula} <p>
 *
 * @author michotte
 */
@Repository
public interface FormulaDao extends JpaRepository<AbstractFormula, Integer> {
  /**
   * Find an {@link AbstractFormula} by {@code id}
   *
   * @param id the identifier of the {@link AbstractFormula} to be returned
   * @return the {@link AbstractFormula} identified by the {@code id}, or null if it doesn't exists
   */
  AbstractFormula findById(int id);
}
