package be.groups.glanguage.core.dao.rule.definition;

import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RuleDefinitionParameter} <p>
 */
@Repository
public interface RuleDefinitionParameterDao extends JpaRepository<RuleDefinitionParameter,
    RuleDefinitionParameterId> {}
