package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michotte on 5/12/2016.
 */
public class PlanFactory {

    private static final Map<PlanIdentification, Plan> plans = new HashMap<>();

    public static Plan getPlan(Integer ruleSetVersionId, LocalDate effectivityDate) {
        PlanIdentification key = new PlanIdentification(ruleSetVersionId, effectivityDate);
        if (!plans.containsKey(key)) {
            plans.put(key, Universe.getPlan(ruleSetVersionId, effectivityDate));
        }
        return plans.get(key);
    }

}
