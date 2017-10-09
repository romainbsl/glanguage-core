package be.groups.glanguage.core.business.factory;

import be.groups.glanguage.core.business.plan.Plan;
import be.groups.glanguage.core.business.universe.Universe;
import be.groups.glanguage.core.entities.ruleset.RuleSetVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This factory allows to get {@link Plan plans}<br>
 * It has a cache to keep all already asked {@link Plan plans} available
 *
 * @author michotte
 */
@Component
public class PlanFactory {

    // TODO see for Cache or FactoryBean
    private final Map<PlanIdentification, Plan> plans = new HashMap<>();
    @Autowired
    private Universe universe;

    /**
     * Get the {@link Plan} corresponding to a {@code ruleSetVersionId} and a {@code effectiveDate}<br>
     * Get it from the cache if it exists or delegate to {@link Universe#getPlan(Long, LocalDate)} and add it to
     * the cache
     *
     * @param ruleSetVersionId the identifier of the {@link RuleSetVersion} to be used to create the {@link Plan} to
     *                         be returned
     * @param effectiveDate    the {@link LocalDate} to be used to create {@link Plan} to be returned
     * @return the {@link Plan} corresponding to a {@code ruleSetVersionId} and a {@code effectiveDate}
     * @see Universe#getPlan(Long, LocalDate)
     */
    public Plan getPlan(Long ruleSetVersionId, LocalDate effectiveDate) {
        PlanIdentification key = new PlanIdentification(ruleSetVersionId, effectiveDate);
        if (!plans.containsKey(key)) {
            plans.put(key, universe.getPlan(ruleSetVersionId, effectiveDate));
        }
        return plans.get(key);
    }

}
