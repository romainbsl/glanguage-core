package be.groups.glanguage.glanguage.api.dao.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.formula.description.combination.*;
import org.springframework.data.jpa.repository.*;

public interface FormulaParameterCombinationDao extends
                                                JpaRepository<FormulaParameterCombination,
                                                    Integer> {}
