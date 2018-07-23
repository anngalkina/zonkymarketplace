package eu.galkina.zonky.mpchecker.writer;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.galkina.zonky.mpchecker.model.Loan;

/**
 * Trivial implementation that writes the loans to the logger output
 */
public class StandardOutLoanWriter implements LoanWriter {

    private static final Logger logger = LoggerFactory.getLogger(StandardOutLoanWriter.class);

    @Override
    public void writeLoans(Set<Loan> loans) {
        if (CollectionUtils.isEmpty(loans)) {
            logger.info("No new loans in the last 5 minutes");
        } else {
            loans.forEach(loan -> logger.info(loan.toString()));
        }
    }
}
