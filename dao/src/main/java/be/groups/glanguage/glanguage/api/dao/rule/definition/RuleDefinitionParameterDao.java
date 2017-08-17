package be.groups.glanguage.glanguage.api.dao.rule.definition;

import be.groups.glanguage.glanguage.api.entities.rule.definition.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface RuleDefinitionParameterDao extends JpaRepository<RuleDefinitionParameter,
    RuleDefinitionParameterId> {}
