import com.sun.javafx.fxml.builder.JavaFXFontBuilder;
import com.sun.javafx.scene.control.skin.TooltipSkin;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import pojo.YandexAdrees;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaFX {
    public static Integer inMinutes = 5;

    public Stage getStage(Stage primaryStage){
        BorderPane borderPane = new BorderPane();

        String style =
//                "-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 1;" +
//                "-fx-border-insets: 5;" +
                        "-fx-border-radius: 5;" +
//                "-fx-border-color: blue;" +
                        "";
        borderPane.setStyle(style);



        FlowPane flowPaneLeft = new FlowPane();
        HBox hBoxLeftTop = new HBox(5);
//        VBox vBoxLeftLabel = new VBox(5);
//        VBox vBoxLeftFields = new VBox(5);
        GridPane gridPaneLeft = new GridPane();
        gridPaneLeft.setAlignment(Pos.CENTER);
        gridPaneLeft.setGridLinesVisible(true);
        ColumnConstraints columnConstraints1 = new ColumnConstraints(250, 250, Double.MAX_VALUE);//
        columnConstraints1.setHgrow(Priority.NEVER);
        columnConstraints1.setHalignment(HPos.CENTER);
        gridPaneLeft.getColumnConstraints().add(columnConstraints1);
        ColumnConstraints columnConstraints2 = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        columnConstraints2.setHgrow(Priority.NEVER);
        columnConstraints2.setHalignment(HPos.LEFT);
        gridPaneLeft.getColumnConstraints().add(columnConstraints2);


        Label labelLeftTop = new Label("Введи координаты: ");
        Separator separator = new Separator();
        separator.setPrefWidth(1);


        Label labelFieldName = new Label("Название маршрута*: ");
        TextField textFieldName = new TextField();
        Tooltip tooltipName = new Tooltip("Имя должно быть уникальным");
        textFieldName.setTooltip(tooltipName);
//        textFieldName.setPrefColumnCount(20);
//
//        TextField textFieldEmpty = new TextField();
////        textFieldEmpty.setPrefColumnCount(20);
//        textFieldEmpty.setDisable(true);

        textFieldName.setPromptText("Пример: На Работу");
//        HBox hBoxName = new HBox(textFieldName,textFieldEmpty);

        Label labelSite = new Label("Вставьте ссылку на маршрут: " );
        TextField textFieldSite = new TextField();
        textFieldSite.setPromptText("https://yandex.ru/maps/213/moscow/?clid=1923017&mode=routes&rtext=55.871425%2C37.686868~55.764374%2C37.800604&rtt=auto");


        Tooltip tooltipSite = new Tooltip("Ссылка должна содержать аттрибут rtext= С координатами отправки и назначения");

        textFieldSite.setTooltip(tooltipSite);

//
//        Label labelCoordA = new Label("Координаты Откуда*: ");
//        TextField textFieldCoordsA_X = new TextField();
//        textFieldCoordsA_X.setPromptText("Широта: 50.123456");
////        textFieldCoordsA_X.setPrefColumnCount(20);
//        TextField textFieldCoordsA_Y = new TextField();
//        textFieldCoordsA_Y.setPromptText("Долгота: 25.654321");
////        textFieldCoordsA_Y.setPrefColumnCount(20);
//        HBox hBoxCoordA = new HBox(textFieldCoordsA_X,textFieldCoordsA_Y);
//
//        Label labelCoordB = new Label("Промежуточная точка: ");
//        TextField textFieldCoordsB_X = new TextField();
//        textFieldCoordsB_X.setPromptText("Широта: 50.123456");
//        TextField textFieldCoordsB_Y = new TextField();
//        textFieldCoordsB_Y.setPromptText("Долгота: 25.654321");
//        HBox hBoxCoordB = new HBox(textFieldCoordsB_X,textFieldCoordsB_Y);
//
//        Label labelCoordC = new Label("Координаты Куда*: ");
//        TextField textFieldCoordsC_X = new TextField();
//        textFieldCoordsC_X.setPromptText("Широта: 50.123456");
//        TextField textFieldCoordsC_Y = new TextField();
//        textFieldCoordsC_Y.setPromptText("Долгота: 25.654321");
//        HBox hBoxCoordC = new HBox(textFieldCoordsC_X,textFieldCoordsC_Y);

//        HBox hBoxLeft = new HBox();
//        vBoxLeftLabel.getChildren().addAll(hBoxLeftTop,separator,labelFieldName,labelCoordA,labelCoordB,labelCoordC);
//        vBoxLeftFields.getChildren().addAll(hBoxName,hBoxCoordA,hBoxCoordB,hBoxCoordC);
//        hBoxLeft.getChildren().addAll(vBoxLeftLabel,vBoxLeftFields);

        gridPaneLeft.add(labelFieldName,0,0);
        gridPaneLeft.add(labelSite,0,1);
//        gridPaneLeft.add(labelCoordB,0,2);
//        gridPaneLeft.add(labelCoordC,0,3);
//        gridPaneLeft.add(btntestsftp,0,4);

        gridPaneLeft.add(textFieldName,1,0);
        gridPaneLeft.add(textFieldSite,1,1);
//        gridPaneLeft.add(hBoxCoordB,1,2);
//        gridPaneLeft.add(hBoxCoordC,1,3);

        hBoxLeftTop.getChildren().addAll(labelLeftTop);
        Button buttonSaveRoute = new Button("Сохранить маршрут");
        Button buttonInBuffer = new Button("Копировать ссылку");
        buttonInBuffer.setDisable(true);
        Button buttonDeleteRoute = new Button("Удалить маршрут");
        buttonDeleteRoute.setDisable(true);
        HBox hBoxButton = new HBox(buttonSaveRoute,buttonInBuffer,buttonDeleteRoute);
//        HBox.setHgrow(buttonDeleteRoute,);
        HBox.setMargin(buttonDeleteRoute,new Insets(0, 0, 0, 130));
//        TextField textFieldCoordsA_X = new TextField();
//
//        vBoxLeft.getChildren().addAll(labelLeftTop,separator,hBoxName,hBoxCoordA
//        ,hBoxCoordB
//        ,hBoxCoordC);


        TableView<YandexAdrees> tableView = new TableView<>();

        TableColumn<YandexAdrees,String> columnName = new TableColumn<>();
        columnName.setText("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<YandexAdrees, String>("name"));

        TableColumn<YandexAdrees,String> columnCoord = new TableColumn<>();
        columnCoord.setPrefWidth(350);
        columnCoord.setText("Site");
        columnCoord.setCellValueFactory(new PropertyValueFactory<YandexAdrees, String>("site"));

//        TableColumn<YandexAdrees,String> columnSite = new TableColumn<>();
//        columnSite.setText("Site");
//        columnSite.setCellValueFactory(new PropertyValueFactory<YandexAdrees, String>("site"));
//        columnSite.setEditable(true);

//        TableColumn<YandexAdrees,String> columnCount = new TableColumn<>();
//        columnCount.setText("DataCount");
//        columnCount.setCellValueFactory(new PropertyValueFactory<YandexAdrees, String>("count"));

        TableColumn<YandexAdrees,String> columnStatus = new TableColumn<>();
        columnStatus.setText("Status");
        columnStatus.setCellValueFactory(new PropertyValueFactory<YandexAdrees, String>("status"));

        VBox vBoxLeft = new VBox();
        tableView.getColumns().addAll(columnName,columnStatus,columnCoord);
//,columnSite ,columnCount
        tableView.getSelectionModel().setCellSelectionEnabled(false);

        JavaFX.updateTable(tableView);

        buttonSaveRoute.setOnAction(event -> {
            if(textFieldName.getText().equals("") || textFieldSite.getText().equals("")){
                return;
            }
            YandexAdrees yandexAdrees = new YandexAdrees();
            yandexAdrees.setName(textFieldName.getText());
            yandexAdrees.setSite(textFieldSite.getText());

            textFieldName.setText("");
            textFieldSite.setText("");
//            tableView.getItems().add(yandexAdrees);
            yandexAdrees.saveToDisk();
            JavaFX.updateTable(tableView);

//            tableView.setItems();
        });
        buttonInBuffer.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                String site = tableView.getSelectionModel().getSelectedItem().getSite();
                StringSelection ss = new StringSelection(site);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
            }
        });

        buttonDeleteRoute.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                YandexAdrees yandexAdrees = tableView.getSelectionModel().getSelectedItem();
                yandexAdrees.deleteRoute();
                JavaFX.updateTable(tableView);
            }
        });

        textFieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                for (int i = 0; i < tableView.getItems().size(); i++) {
                    if(newValue.equals(tableView.getItems().get(i).getName())){
                        buttonSaveRoute.setDisable(true);
                        return;
                    }else{
                        buttonSaveRoute.setDisable(false);
                    }
                }
            }
        });



        Button buttonStart = new Button("Start");
        buttonStart.setDisable(true);

//        tableView.getItems().addListener(new ListChangeListener<YandexAdrees>() {
//            @Override
//            public void onChanged(Change<? extends YandexAdrees> c) {
//                System.out.println(c);
//            }
//        });
//        tableView.getItems().

//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        try {
//            SchedulListner schedulerListener = new SchedulListner();
//            schedulerFactory.getScheduler().getListenerManager().addSchedulerListener(schedulerListener);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
        Button buttonStop = new Button("Stop");
        buttonStop.setDisable(true);

        buttonStart.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                SimpleTriggerRunner simpleTriggerRunner = null;
                try {
                    simpleTriggerRunner = new SimpleTriggerRunner();
                    simpleTriggerRunner.task(tableView.getSelectionModel().getSelectedItem());
                    tableView.getSelectionModel().getSelectedItem().setStatus("PROCESSING");
                    buttonStart.setDisable(true);
                    buttonStop.setDisable(false);
                    tableView.refresh();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }

        });


        buttonStop.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                try {
//                    System.out.println(new Date());
//                    schedulerFactory.getScheduler().getTrigger(
//                            new TriggerKey(tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""),"group")
//                    );
//                    schedulerFactory.getScheduler().getJobDetail(
//                            new JobKey(tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""),"group")
//                    );
                    schedulerFactory.getScheduler().unscheduleJob(
                            new TriggerKey(tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""),"group")
                    );
                    tableView.getSelectionModel().getSelectedItem().setStatus("STOPED");
                    buttonStop.setDisable(true);
                    buttonStart.setDisable(false);
                    tableView.refresh();

//                    schedulerFactory.getScheduler().pauseTrigger(
//                            new TriggerKey(tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""),"group")
//                    );


                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<YandexAdrees>() {
            @Override
            public void changed(ObservableValue<? extends YandexAdrees> observable, YandexAdrees oldValue, YandexAdrees newValue) {
                if(newValue != null){
                    buttonStart.setDisable(false);
                    buttonInBuffer.setDisable(false);
                    buttonDeleteRoute.setDisable(false);
                    if(newValue.getStatus().equals("STOPED")){
                        buttonStop.setDisable(true);
                        buttonStart.setDisable(false);
                    }else{
                        buttonStop.setDisable(false);
                        buttonStart.setDisable(true);
                    }
                }
            }
        });

        DatePicker date = new DatePicker();
        date.setValue(LocalDate.now());
        date.setOnAction(event -> {
            System.out.println(date.getValue());
        });

        HBox hBoxStartStop = new HBox(buttonStart,buttonStop);

        Spinner<Integer> spinner = new Spinner<>();
        SpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,60,5);
        spinner.setValueFactory(spinnerValueFactory);
        spinner.setPrefSize(60,10);
        spinner.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                inMinutes = Integer.valueOf(newValue);
                System.out.println("inMinutes = "+newValue);
            }
        });
        Label label = new Label("раз в минут: ");
        hBoxStartStop.getChildren().addAll(label,spinner);

        vBoxLeft.getChildren().addAll(hBoxLeftTop,separator,gridPaneLeft,hBoxButton,tableView,hBoxStartStop);
        vBoxLeft.setSpacing(5);
        vBoxLeft.setPadding(new Insets(10, 10, 0, 10));

        flowPaneLeft.getChildren().addAll(vBoxLeft);
        FlowPane.setMargin(vBoxLeft,new Insets(10, 10, 0, 10));
        borderPane.setLeft(flowPaneLeft);


        AnchorPane flowPaneCenter = new AnchorPane();

//        flowPaneCenter.autosize();

//        flowPaneCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox vBoxCenter = new VBox();
        Button buttonRefresh = new Button("Обновить График");
        buttonRefresh.setDisable(true);
        buttonRefresh.setVisible(false);
        buttonRefresh.setLineSpacing(20);
        hBoxStartStop.getChildren().add(buttonRefresh);
        HBox.setMargin(buttonRefresh,new Insets(0, 0, 0, 50));
        hBoxStartStop.getChildren().add(date);


//        vBoxLeft.getChildren().add(buttonRefresh);
//        vBoxCenter.getChildren().addAll(buttonRefresh);

//        vBoxCenter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        flowPaneCenter.getChildren().add(vBoxCenter);
//        flowPaneCenter.
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Время дня");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Минут в пути");
        LineChart<String,Number> lineChart = new LineChart<String, Number>(xAxis,yAxis);
//        lineChart.autosize();
        lineChart.setAnimated(false);
//        primaryStage
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        JFreeChart chart = ChartFactory.createLineChart("Титл-Хуитл","Время дня","Время в пути",dataset, PlotOrientation.HORIZONTAL,true,true,true);
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Время в пути");
//        lineChart.getData().add(series);

        lineChart.getXAxis().animatedProperty().setValue(false);
        lineChart.getXAxis().setTickLabelGap(1);
        xAxis.setTickLabelRotation(90);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<YandexAdrees>() {
            @Override
            public void changed(ObservableValue<? extends YandexAdrees> observable, YandexAdrees oldValue, YandexAdrees newValue) {
                if(newValue == null){
                    buttonDeleteRoute.setDisable(true);
                    buttonInBuffer.setDisable(true);
                    buttonStart.setDisable(true);
                    buttonStop.setDisable(true);
                }
                lineChartUpdate(lineChart, newValue, date.getValue());
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                try {
                    if(oldValue != null){
                        schedulerFactory.getScheduler().getListenerManager().removeJobListener(oldValue.getName());
                    }
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
                JobListener jobListener = new JobListener() {
                    @Override
                    public String getName() {
                        return tableView.getSelectionModel().getSelectedItem().getName();
                    }

                    @Override
                    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
                        if(jobExecutionContext.getJobDetail().getKey().toString().equals("group."+tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""))){
                            System.out.println("jobToBeExecuted = "+jobExecutionContext.getJobDetail().getKey());
                        }
                    }

                    @Override
                    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
                        if(jobExecutionContext.getJobDetail().getKey().toString().equals("group."+tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""))){
                            System.out.println("jobExecutionVetoed = "+jobExecutionContext.getJobDetail().getKey());
                        }
                    }

                    @Override
                    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
                        System.out.println();
                        if(jobExecutionContext.getJobDetail().getKey().toString().equals("group."+tableView.getSelectionModel().getSelectedItem().getName().replaceAll("\\s",""))){
                            System.out.println("jobWasExecuted = "+jobExecutionContext.getJobDetail().getKey());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    lineChartUpdate(lineChart,tableView.getSelectionModel().getSelectedItem(),date.getValue());
                                }
                            });
                        }

                    }
                };
                try {
                    schedulerFactory.getScheduler().getListenerManager().addJobListener(jobListener);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }

                //XYChart.Series series1 =
//                lineChart.getData().addAll(series1);



            }
        });
        buttonRefresh.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                YandexAdrees yandexAdrees = tableView.getSelectionModel().getSelectedItem();
                lineChart.getData().clear();
                lineChartUpdate(lineChart, yandexAdrees, date.getValue());
            }
        });

        date.setOnAction(event -> {
            if(tableView.getSelectionModel().getSelectedItem() != null){
                YandexAdrees yandexAdrees = tableView.getSelectionModel().getSelectedItem();
                lineChart.getData().clear();
                lineChartUpdate(lineChart, yandexAdrees, date.getValue());
            }
        });



//        vBoxCenter.getChildren().add(lineChart);
        flowPaneCenter.getChildren().addAll(lineChart);
        borderPane.setCenter(flowPaneCenter);
        AnchorPane.setLeftAnchor(lineChart, 30.0);
        AnchorPane.setTopAnchor(lineChart, 30.0);
        AnchorPane.setRightAnchor(lineChart, 10.0);
        AnchorPane.setBottomAnchor(lineChart, 10.0);

//        borderPane.getCenter().autosize();
//        borderPane.getCenter().resize(Double.MAX_VALUE,Double.MAX_VALUE);

//        FlowPane flowPaneRight = new FlowPane();
//        borderPane.setRight(flowPaneRight);



        borderPane.getLeft().setStyle(style);
        borderPane.getCenter().setStyle(style);
//        borderPane.getRight().setStyle(style);


        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);// установка сцены для объекта Stage
        primaryStage.setWidth(1200);        // установка ширины окна
        primaryStage.setHeight(670);       // установка длины окна
        primaryStage.setTitle("Yandex.Monitor");
//        primaryStage.show();
        return primaryStage;
    }

    private static void lineChartUpdate(LineChart<String,Number> lineChart, YandexAdrees yandexAdrees,LocalDate localDate){

//        YandexAdrees yandexAdrees = (YandexAdrees) tableView.getSelectionModel().getSelectedItem();
        if (yandexAdrees == null){
            lineChart.getData().clear();
            return;
        }
        System.out.println(yandexAdrees.getName());
        String dir = yandexAdrees.getName();
        String userDir = FileUtils.getUserDirectory().getAbsolutePath()+ File.separator+"Yandex.Monitoring"+File.separator+dir+File.separator;
        String filename = localDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"))+".xml";
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
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName(yandexAdrees.getName()+" - "+localDate.toString());
        if(configFile.exists()){
            try {
                Unmarshaller un = jaxbContext.createUnmarshaller();
                jaxbmessage = (xml.saveData.MainType) un.unmarshal(configFile);

                ObservableList<XYChart.Data<String,Number>> dataObservableList = FXCollections.observableArrayList();
                for (int i = 0; i < jaxbmessage.getData().size(); i++) {
                    XYChart.Data<String,Number> data = new XYChart.Data<>();
                    LocalTime localTimeX = LocalTime.parse(jaxbmessage.getData().get(i).getLocalTime(),DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm:ss"));
                    data.setXValue(localTimeX.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    LocalTime localTime = LocalTime.parse(jaxbmessage.getData().get(i).getTimeToDestination());
                    System.out.println("localTime = "+localTime);
                    int minutes = localTime.getMinute();
                    if(localTime.getHour() != 0){
                        minutes = minutes+(localTime.getHour()*60);
                    }
                    System.out.println("minutes - "+minutes);
                    data.setYValue(minutes);
                    dataObservableList.add(data);

                }
                series.getData().addAll(dataObservableList);
                lineChart.getData().clear();
                lineChart.getData().add(series);

                Font bold = Font.font(Font.getDefault().getFamily(),FontWeight.BOLD, Font.getDefault().getSize());
                lineChart.getXAxis().setTickLabelFont(bold);
//                lineChart.getXAxis().setTickLabelFont(bold).setStyle("-fx-font-size: 14;-fx-font-weight: bolder;");
                lineChart.getData().forEach(c->{
                    c.getData().forEach(b->{
                        Tooltip tooltip = new Tooltip("Минуты: "+b.getYValue());
                        Tooltip.install(b.getNode(),tooltip );
                    });
                });
            }catch (Exception e){
                System.out.println("Не получилось прочитать файл, файл будет стёрт и обновлён");
            }
        }else{
            lineChart.getData().clear();
            System.out.println("File Not Found "+configFile.getAbsoluteFile());
        }
//        return series;


//        return null;
    }

    public static void updateTable(TableView tableView){
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
        ArrayList<YandexAdrees> yandexAdreesArrayList = new ArrayList<>();
        for (int i = 0; i < jaxbmessage.getData().size(); i++) {
            YandexAdrees yandexAdrees = new YandexAdrees();
            yandexAdrees.setName(jaxbmessage.getData().get(i).getName());
            yandexAdrees.setSite(jaxbmessage.getData().get(i).getSite());
            yandexAdreesArrayList.add(yandexAdrees);
        }
        if(tableView.getItems() != null || tableView.getItems().size() >0){
            tableView.getItems().clear();
        }
        ObservableList<YandexAdrees> yandexAdreesObservableList = FXCollections.observableList(yandexAdreesArrayList);
        tableView.getItems().addAll(yandexAdreesObservableList);

    }


}
