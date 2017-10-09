package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.entities.rule.RuleDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RuleDescription} <p>
 */
@Repository
public interface RuleDescriptionDao extends JpaRepository<RuleDescription, Long> {}
