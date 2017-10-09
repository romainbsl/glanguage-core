package be.groups.glanguage.core.dao.formula.description.combination;

import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link FormulaParameterCombinationItem} <p>
 */
@Repository
public interface FormulaParameterCombinationItemDao extends
                                                    JpaRepository<FormulaParameterCombinationItem,
                                                        Long> {}
