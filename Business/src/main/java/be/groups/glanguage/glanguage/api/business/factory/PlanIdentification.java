package be.groups.glanguage.glanguage.api.business.factory;

import java.time.LocalDate;

/**
 * Created by michotte on 8/12/2016.
 */
public class PlanIdentification {

    private Integer ruleSetVersionId;
    private LocalDate effectivityDate;

    public PlanIdentification(Integer ruleSetVersionId, LocalDate effectivityDate) {
        this.ruleSetVersionId = ruleSetVersionId;
        this.effectivityDate = effectivityDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanIdentification)) return false;

        PlanIdentification that = (PlanIdentification) o;

        if (!ruleSetVersionId.equals(that.ruleSetVersionId)) return false;
        return effectivityDate.equals(that.effectivityDate);
    }

    @Override
    public int hashCode() {
        int result = ruleSetVersionId.hashCode();
        result = 31 * result + effectivityDate.hashCode();
        return result;
    }
}
