package eu.galkina.zonky.mpchecker.writer;

import java.util.Set;

import eu.galkina.zonky.mpchecker.job.quartz.LoanJob;
import eu.galkina.zonky.mpchecker.model.Loan;

/**
 * Common interface used in the {@link LoanJob}
 * to output the newly synchronized list of {@link Loan}
 */
public interface LoanWriter {

    void writeLoans(Set<Loan> loans);

}
