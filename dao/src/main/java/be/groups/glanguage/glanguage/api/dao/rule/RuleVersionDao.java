package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.entities.rule.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link RuleVersion} <p>
 */
@Repository
public interface RuleVersionDao extends JpaRepository<RuleVersion, Integer> {}