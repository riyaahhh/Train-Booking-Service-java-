package org.example.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Train;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class trainService {
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "../localDB/trains.json";
    public static void addTrain(Train newtrain){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(TRAIN_DB_PATH);
        try{
            List<Train> trainList;
            if(file.exists()){
                trainList= objectMapper.readValue(file, new TypeReference<List<Train>>() { });
            }
            else {
                trainList = new ArrayList<>();
            }
            trainList.add(newtrain);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,trainList);
        }catch (IOException ex){
            System.out.println("Failed to add train");
            ex.printStackTrace();
        }
    }
    public void updateTrain(Train updatedtrain){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(TRAIN_DB_PATH);
        try{
            List<Train> trainList;
            if(file.exists()){
                trainList= objectMapper.readValue(file, new TypeReference<List<Train>>() { });
            }
            else {
                System.out.println("No such train found in database");
                return;
            }
            boolean found = false;
            for(int i=0;i<trainList.size();i++){
                if(trainList.get(i).getTrainId().equals(updatedtrain.getTrainId())){
                    trainList.set(i,updatedtrain);
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println("No such train found in database");
                return;
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,trainList);
            System.out.println("Successfully updated train");
        }catch (IOException ex){
            System.out.println("Failed to update train");
            ex.printStackTrace();

        }
    }
}
