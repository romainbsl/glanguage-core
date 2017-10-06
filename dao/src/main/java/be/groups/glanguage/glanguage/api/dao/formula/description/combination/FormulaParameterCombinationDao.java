package be.groups.glanguage.glanguage.api.dao.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.formula.description.combination.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link FormulaParameterCombination} <p>
 */
@Repository
public interface FormulaParameterCombinationDao extends
                                                JpaRepository<FormulaParameterCombination,
                                                    Long> {}
