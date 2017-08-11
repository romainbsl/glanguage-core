package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import org.springframework.data.jpa.repository.*;

/**
 * DAO for {@link FormulaDescription} <p>
 *
 * @author michotte
 */
public interface FormulaDescriptionDao extends JpaRepository<FormulaDescription, Integer> {}
