package eu.galkina.zonky.mpchecker.job.quartz;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.galkina.zonky.mpchecker.integration.LoanResource;
import eu.galkina.zonky.mpchecker.model.Loan;
import eu.galkina.zonky.mpchecker.writer.LoanWriter;

import static eu.galkina.zonky.mpchecker.job.quartz.QuartzScheduler.checkIntervalMinutes;
import static eu.galkina.zonky.mpchecker.util.DateUtil.toISO8601;
import static java.lang.String.format;

/**
 * Downloads the list of newly added {@link Loan}
 * in regular intervals using {@link LoanResource}
 * and outputs the newly synchronized loans using
 * {@link LoanWriter}
 */
public class LoanJob implements Job {

    static final String LOAN_WRITER = "loanWriter";
    private static final Logger logger = LoggerFactory.getLogger(LoanJob.class);

    private final LoanResource loanResource;
    private LocalDateTime lastRunTime;

    public LoanJob() {
        loanResource = new LoanResource();
    }

    public void execute(JobExecutionContext context) {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        LoanWriter loanWriter = (LoanWriter) dataMap.get(LOAN_WRITER);
        if (loanWriter == null) {
            throw new IllegalStateException("Specify implementation of LoanWriter " +
                    "interface in a JobDataMap for this job");
        }

        if (lastRunTime == null) {
            lastRunTime = context.getFireTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } else {
            lastRunTime = lastRunTime.plusMinutes(checkIntervalMinutes);
        }
        LocalDateTime sinceDateTime = lastRunTime.minusMinutes(checkIntervalMinutes);
        logger.debug(format("Executing LoanJob synchronization since %s", toISO8601(lastRunTime)));
        Set<Loan> loansBatch = loanResource.sync(sinceDateTime);
        loanWriter.writeLoans(loansBatch);
    }
}
