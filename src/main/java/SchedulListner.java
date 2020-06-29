import org.quartz.*;

public class SchedulListner implements SchedulerListener {

    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("jobScheduled - "+trigger.getKey()+" - "+trigger.getJobKey());

    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("jobUnscheduled - "+triggerKey);

    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("triggerFinalized - "+trigger.getKey()+" - "+trigger.getJobKey());

    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {

    }

    @Override
    public void triggersPaused(String s) {

    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {

    }

    @Override
    public void triggersResumed(String s) {

    }

    @Override
    public void jobAdded(JobDetail jobDetail) {

    }

    @Override
    public void jobDeleted(JobKey jobKey) {

    }

    @Override
    public void jobPaused(JobKey jobKey) {

    }

    @Override
    public void jobsPaused(String s) {

    }

    @Override
    public void jobResumed(JobKey jobKey) {

    }

    @Override
    public void jobsResumed(String s) {

    }

    @Override
    public void schedulerError(String s, SchedulerException e) {

    }

    @Override
    public void schedulerInStandbyMode() {

    }

    @Override
    public void schedulerStarted() {

    }

    @Override
    public void schedulerStarting() {

    }

    @Override
    public void schedulerShutdown() {
        System.out.println("schedulerShutdown - schedulerShutdown");

    }

    @Override
    public void schedulerShuttingdown() {
        System.out.println("schedulerShuttingdown - schedulerShuttingdown");

    }

    @Override
    public void schedulingDataCleared() {

    }
}
