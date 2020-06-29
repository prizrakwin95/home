import org.apache.commons.io.FileUtils;
import org.quartz.*;
//import org.slf4j.MDC;
import pojo.YandexAdrees;
import xml.saveData.DataType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleQuartzJob  implements Job{

    public SimpleQuartzJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap data = context.getJobDetail().getJobDataMap();

        YandexAdrees yandexAdrees = (YandexAdrees) data.get("yandexAdrees");

        try(SeleniumCode seleniumCode = new SeleniumCode()){
            LocalTime localTime = seleniumCode.getTimeToDestination(yandexAdrees);
            saveToDisk(yandexAdrees,localTime);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void saveToDisk(YandexAdrees yandexAdrees, LocalTime localTime){
        String dir = yandexAdrees.getName();
        String userDir = FileUtils.getUserDirectory().getAbsolutePath()+ File.separator+"Yandex.Monitoring"+File.separator+dir+File.separator;
        String filename = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))+".xml";
        File configFile = new File(userDir,filename);
        File path = new File(userDir);
        if(!path.exists()){
            path.mkdirs();
        }
        JAXBContext jaxbContext = null;
        xml.saveData.MainType jaxbmessage = new xml.saveData.MainType();
        try {
            jaxbContext = JAXBContext.newInstance(xml.saveData.MainType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            return;
        }

        if(configFile.exists()){
            try {
                Unmarshaller un = jaxbContext.createUnmarshaller();
                jaxbmessage = (xml.saveData.MainType) un.unmarshal(configFile);
            }catch (Exception e){
                System.out.println("Не получилось прочитать файл, файл будет стёрт и обновлён");
            }
        }
        DataType dataType = new DataType();
        dataType.setLocalTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm:ss")));
        dataType.setTimeToDestination(localTime.toString());
        jaxbmessage.getData().add(dataType);
        Marshaller mar = null;
        try {

            mar = jaxbContext.createMarshaller();
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(jaxbmessage, configFile);
            System.out.println("Сохранил Время на маршруте - "+yandexAdrees.getName()+" - "+localTime);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
