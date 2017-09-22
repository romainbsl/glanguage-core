package be.groups.glanguage.glanguage.api.dao.formula;

import be.groups.glanguage.glanguage.api.entities.formula.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link AbstractFormula} <p>
 *
 * @author michotte
 */
@Repository
public interface FormulaDao extends JpaRepository<AbstractFormula, Long> {}
