import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import pojo.YandexAdrees;

public class SimpleTriggerRunner {
    private Scheduler scheduler;
    private SchedulerFactory schedulerFactory;

    public SimpleTriggerRunner() throws SchedulerException {
    }

    public void task(YandexAdrees yandexAdrees) throws SchedulerException
    {
        schedulerFactory = new StdSchedulerFactory();

        // Retrieve a scheduler from schedule factory
        scheduler = schedulerFactory.getScheduler();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("yandexAdrees",yandexAdrees);
        String triggerName = yandexAdrees.getName().trim().replaceAll("\\s","");
        System.out.println("triggerName - "+triggerName);
        JobDetail jobDetail = JobBuilder.newJob(SimpleQuartzJob.class).withIdentity(triggerName, "group")
                .usingJobData(jobDataMap)
//                .usingJobData(,yandexAdrees)
//                .usingJobData("vsh", 0)
//                .usingJobData("spravka", 0)
//                .usingJobData("myFloatValue", 3.141f)
                .build();//.build()

        JobDetail jobDetailState = JobBuilder.newJob(SimpleQuartzJobState.class).withIdentity(triggerName, "groupCheck")
                .usingJobData(jobDataMap)
//                .usingJobData(,yandexAdrees)
//                .usingJobData("vsh", 0)
//                .usingJobData("spravka", 0)
//                .usingJobData("myFloatValue", 3.141f)
                .build();

        SimpleTrigger checkStateTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, "groupCheck")
//                .startAt()
                .forJob(jobDetailState)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())//.withRepeatCount(4))
                .build();

        scheduler.scheduleJob(jobDetailState,checkStateTrigger);

        System.out.println("jobDetail.getKey() = "+jobDetail.getKey());
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, "group")//timer."+
//                .startAt()
//                .forJob(jobDetail.getKey())
                .forJob(jobDetail)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(10)
                        .withIntervalInMinutes(JavaFX.inMinutes)
                        .repeatForever())//.withRepeatCount(4))
                .build();

        scheduler.scheduleJob(jobDetail,simpleTrigger);

        scheduler.start();

    }

    public void stop() throws SchedulerException {
        this.scheduler.shutdown();
//        this.scheduler.
    }


}
