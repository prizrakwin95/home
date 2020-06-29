package pojo;

import org.apache.commons.io.FileUtils;
import xml.config.DataType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Objects;
import java.util.Observable;

public class YandexAdrees  {
    private String name;
    private String site;
//    private String centerMap;
//    private String coordinatA_X;
//    private String coordinatA_Y;
//    private String coordinatB_X;
//    private String coordinatB_Y;
//    private String coordinatC_X;
//    private String coordinatC_Y;
    private String status = "STOPED";

    public YandexAdrees() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YandexAdrees(String name, String site) {
        this.name = name;
        this.site = site;
//        this.centerMap = centerMap;
//        this.coordinatA_X = coordinatA_X;
//        this.coordinatA_Y = coordinatA_Y;
//        this.coordinatB_X = coordinatB_X;
//        this.coordinatB_Y = coordinatB_Y;
//        this.coordinatC_X = coordinatC_X;
//        this.coordinatC_Y = coordinatC_Y;
    }

//    public String getCenterMap() {
//        return centerMap;
//    }
//
//    public void setCenterMap(String centerMap) {
//        this.centerMap = centerMap;
//    }

//    public String getCoordinatA_X() {
//        return coordinatA_X;
//    }
//
//    public void setCoordinatA_X(String coordinatA_X) {
//        this.coordinatA_X = coordinatA_X;
//    }
//
//    public String getCoordinatA_Y() {
//        return coordinatA_Y;
//    }
//
//    public void setCoordinatA_Y(String coordinatA_Y) {
//        this.coordinatA_Y = coordinatA_Y;
//    }
//
//    public String getCoordinatB_X() {
//        return coordinatB_X;
//    }
//
//    public void setCoordinatB_X(String coordinatB_X) {
//        this.coordinatB_X = coordinatB_X;
//    }
//
//    public String getCoordinatB_Y() {
//        return coordinatB_Y;
//    }
//
//    public void setCoordinatB_Y(String coordinatB_Y) {
//        this.coordinatB_Y = coordinatB_Y;
//    }
//
//    public String getCoordinatC_X() {
//        return coordinatC_X;
//    }
//
//    public void setCoordinatC_X(String coordinatC_X) {
//        this.coordinatC_X = coordinatC_X;
//    }
//
//    public String getCoordinatC_Y() {
//        return coordinatC_Y;
//    }
//
//    public void setCoordinatC_Y(String coordinatC_Y) {
//        this.coordinatC_Y = coordinatC_Y;
//    }

//    public String getCoords(){
//        String coords = "";
//
//
//        return coords;
//    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void saveToDisk(){
        String userDir = FileUtils.getUserDirectory().getAbsolutePath()+ File.separator+"Yandex.Monitoring"+File.separator;
        String filename = "config.xml";
        File configFile = new File(userDir,filename);
        File path = new File(userDir);
        if(!path.exists()){
            path.mkdirs();
        }

        JAXBContext jaxbContext = null;
        xml.config.MainType jaxbmessage = new xml.config.MainType();
        try {
            jaxbContext = JAXBContext.newInstance(xml.config.MainType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            return;
        }

        if(configFile.exists()){
            try {
                Unmarshaller un = jaxbContext.createUnmarshaller();
                jaxbmessage = (xml.config.MainType) un.unmarshal(configFile);
            }catch (Exception e){
                System.out.println("Не получилось прочитать файл, файл будет стёрт и обновлён");
            }
        }
        DataType dataType = new DataType();
        dataType.setName(this.name);
        dataType.setSite(this.getSite());
        jaxbmessage.getData().add(dataType);

        Marshaller mar = null;
        try {

            mar = jaxbContext.createMarshaller();
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(jaxbmessage, configFile);
            System.out.println("Сохранил Маршрут - "+dataType.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteRoute(){
        String userDir = FileUtils.getUserDirectory().getAbsolutePath()+ File.separator+"Yandex.Monitoring"+File.separator;
        String filename = "config.xml";
        File configFile = new File(userDir,filename);
        File path = new File(userDir);
        if(!path.exists()){
            path.mkdirs();
        }

        JAXBContext jaxbContext = null;
        xml.config.MainType jaxbmessage = new xml.config.MainType();
        try {
            jaxbContext = JAXBContext.newInstance(xml.config.MainType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            return;
        }

        if(configFile.exists()){
            try {
                Unmarshaller un = jaxbContext.createUnmarshaller();
                jaxbmessage = (xml.config.MainType) un.unmarshal(configFile);
            }catch (Exception e){
                System.out.println("Не получилось прочитать файл, файл будет стёрт и обновлён");
            }
        }
        DataType dataType = new DataType();
        dataType.setName(this.getName());
        dataType.setSite(this.getSite());
        for (int i = 0; i < jaxbmessage.getData().size(); i++) {
            if (jaxbmessage.getData().get(i).equals(dataType)){
                System.out.println("Есть совпадение, Удаляю");
                jaxbmessage.getData().remove(i);
            }else{
                System.out.println("Не нашел такой объект");
            }
        }
        Marshaller mar = null;
        try {

            mar = jaxbContext.createMarshaller();
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(jaxbmessage, configFile);
//            System.out.println("Сохранил Маршрут - "+dataType.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public String toString() {
        return "YandexAdrees{" +
                "name='" + name + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YandexAdrees that = (YandexAdrees) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
