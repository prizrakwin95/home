import org.quartz.*;
//import org.slf4j.MDC;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import pojo.YandexAdrees;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleQuartzJobState  implements Job{

    public SimpleQuartzJobState() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        YandexAdrees yandexAdrees = (YandexAdrees) context.getJobDetail().getJobDataMap().get("yandexAdrees");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try {
            Boolean exist = schedulerFactory.getScheduler().checkExists(new TriggerKey(context.getTrigger().getKey().getName(),"group"));
            System.out.println(context.getTrigger().getKey().getName()+" = "+exist);
            if(exist){
                yandexAdrees.setStatus("PROCESSING");
            }else{
                yandexAdrees.setStatus("STOPED");
                context.getScheduler().unscheduleJob(context.getTrigger().getKey());
            }
//            Trigger trigger = schedulerFactory.getScheduler().getTrigger(new TriggerKey(context.getTrigger().getKey().getName(),"group"));

//            System.out.println(trigger.getKey()+" = "+trigger.mayFireAgain());
//            System.out.println(trigger.getKey()+" = "+trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(yandexAdrees.getStatus());

    }

}
