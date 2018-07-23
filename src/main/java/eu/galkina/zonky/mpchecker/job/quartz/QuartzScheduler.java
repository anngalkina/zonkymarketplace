package eu.galkina.zonky.mpchecker.job.quartz;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.galkina.zonky.mpchecker.job.LoansCheckerScheduler;
import eu.galkina.zonky.mpchecker.model.Loan;
import eu.galkina.zonky.mpchecker.writer.StandardOutLoanWriter;

import static eu.galkina.zonky.mpchecker.job.quartz.LoanJob.LOAN_WRITER;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Uses Quartz to schedule the downloading of {@link Loan}
 * in the regular intervals
 */
public class QuartzScheduler implements LoansCheckerScheduler {

    private static final Logger logger = LoggerFactory.getLogger(QuartzScheduler.class);
    static final int checkIntervalMinutes = 5;

    @Override
    public void scheduleCheckNewLoansJob(Date startDate) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(LOAN_WRITER, new StandardOutLoanWriter());
            JobDetail loanJob = newJob(LoanJob.class)
                    .withIdentity("loanJob", "marketplace")
                    .usingJobData(jobDataMap)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("loanTrigger", "marketplace")
                    .startAt(startDate)
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(checkIntervalMinutes)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(loanJob, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("Exception while scheduling job", e);
        }
    }
}
