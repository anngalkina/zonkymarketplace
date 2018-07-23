package eu.galkina.zonky.mpchecker;

import java.util.Date;

import eu.galkina.zonky.mpchecker.job.LoansCheckerScheduler;
import eu.galkina.zonky.mpchecker.job.quartz.QuartzScheduler;

/**
 * Creates an instance of {@link LoansCheckerScheduler}
 * and schedules the checker job
 */
public class ZonkyMPCheckerRunner {

    public static void main(String[] args) {
        LoansCheckerScheduler scheduler = new QuartzScheduler();
        //start the job now, but can be scheduled to start in future
        Date date = new Date();
        scheduler.scheduleCheckNewLoansJob(date);
    }

}
