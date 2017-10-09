package be.groups.glanguage.core.dao.rule.definition;

import be.groups.glanguage.core.entities.rule.RuleDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RuleDefinition} <p>
 */
@Repository
public interface RuleDefinitionDao extends JpaRepository<RuleDefinition, Long> {}
