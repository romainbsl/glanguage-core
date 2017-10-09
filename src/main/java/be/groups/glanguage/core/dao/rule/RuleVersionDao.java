package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.entities.rule.RuleVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RuleVersion} <p>
 */
@Repository
public interface RuleVersionDao extends JpaRepository<RuleVersion, Long> {}
