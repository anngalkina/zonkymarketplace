package eu.galkina.zonky.mpchecker.job;

import java.util.Date;

import eu.galkina.zonky.mpchecker.model.Loan;

/**
 * Downloads a list of newly added {@link Loan}
 * in regular intervals.
 */
public interface LoansCheckerScheduler {

    void scheduleCheckNewLoansJob(Date startDate);
}
