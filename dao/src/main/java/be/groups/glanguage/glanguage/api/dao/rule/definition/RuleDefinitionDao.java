package be.groups.glanguage.glanguage.api.dao.rule.definition;

import be.groups.glanguage.glanguage.api.entities.rule.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link RuleDefinition} <p>
 */
@Repository
public interface RuleDefinitionDao extends JpaRepository<RuleDefinition, Long> {}
