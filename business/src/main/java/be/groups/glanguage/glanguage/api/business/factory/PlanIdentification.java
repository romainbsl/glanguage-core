package be.groups.glanguage.glanguage.api.business.factory;

import java.time.LocalDate;

/**
 * Identification of a {@link be.groups.glanguage.glanguage.api.business.plan.Plan}
 *
 * @author michotte
 */
public class PlanIdentification {

    private Long ruleSetVersionId;
    private LocalDate effectiveDate;

    public PlanIdentification(Long ruleSetVersionId, LocalDate effectiveDate) {
        this.ruleSetVersionId = ruleSetVersionId;
        this.effectiveDate = effectiveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanIdentification)) return false;

        PlanIdentification that = (PlanIdentification) o;

        if (!ruleSetVersionId.equals(that.ruleSetVersionId)) return false;
        return effectiveDate.equals(that.effectiveDate);
    }

    @Override
    public int hashCode() {
        int result = ruleSetVersionId.hashCode();
        result = 31 * result + effectiveDate.hashCode();
        return result;
    }
}
