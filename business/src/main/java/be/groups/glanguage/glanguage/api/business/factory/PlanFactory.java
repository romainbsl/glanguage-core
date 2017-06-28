package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This factory allows to get {@link Plan plans}<br>
 * It has a cache to keep all already asked {@link Plan plans} available
 *
 * Created by michotte on 8/12/2016.
 */
public class PlanFactory {

    private static final Map<PlanIdentification, Plan> plans = new HashMap<>();

    /**
     * Get the {@link Plan} corresponding to a {@code ruleSetVersionId} and a {@code effectiveDate}<br>
     * Get it from the cache if it exists or delegate to {@link Universe#getPlan(Integer, LocalDate)} and add it to
     * the cache
     *
     * @param ruleSetVersionId the identifier of the {@link RuleSetVersion} to be used to create the {@link Plan} to
     *                         be returned
     * @param effectiveDate    the {@link LocalDate} to be used to create {@link Plan} to be returned
     * @return the {@link Plan} corresponding to a {@code ruleSetVersionId} and a {@code effectiveDate}
     * @see Universe#getPlan(Integer, LocalDate)
     */
    public static Plan getPlan(Integer ruleSetVersionId, LocalDate effectiveDate) {
        PlanIdentification key = new PlanIdentification(ruleSetVersionId, effectiveDate);
        if (!plans.containsKey(key)) {
            plans.put(key, Universe.getPlan(ruleSetVersionId, effectiveDate));
        }
        return plans.get(key);
    }

}
