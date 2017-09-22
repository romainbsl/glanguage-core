package be.groups.glanguage.glanguage.api.dao.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.formula.description.combination.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link FormulaParameterCombinationItem} <p>
 */
@Repository
public interface FormulaParameterCombinationItemDao extends
                                                    JpaRepository<FormulaParameterCombinationItem,
                                                        Long> {}
