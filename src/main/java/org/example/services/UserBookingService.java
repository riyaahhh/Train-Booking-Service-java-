package org.example.services;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Ticket;
import org.example.Train;
import org.example.user;
import org.example.util4.UserServiceUtil;

import static jdk.dynalink.StandardNamespace.findFirst;

public class UserBookingService {
    private user user1;
    private List<user> userList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String USERS_PATH = "/home/riya/Desktop/app/src/main/java/org/example/localDb/users/users.json";

    public UserBookingService(user user1) throws IOException { //constructor
        this.user1 = user1;
        loadUsers();
    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public void loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        objectMapper.readValue(users, new TypeReference<List<user>>() {
        });
    }

    public Boolean logInUser(user user1) {
        Optional<user> foundUser = userList.stream()
                .filter(u ->
                        u.getName().equals(user1.getName()) &&
                                UserServiceUtil.checkPassword(user1.getPassword(), user1.getHashPassword())
                )
                .findFirst();
        if (foundUser.isPresent()) {
            this.user1 = foundUser.get();
            return foundUser.isPresent();
        }
        return false;
    }

    public Boolean signUpUser(user user1) {
        try {
            userList.add(user1);
            this.user1 = user1;
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile, userList);
    }

    public void fetchBooking() {
        Optional<user> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user1.getName()) && UserServiceUtil.checkPassword(user1.getPassword(), user1.getHashPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTicketBooked();
        }
    }

    public Boolean cancelBooking(String ticketId) {
        Optional<Ticket> ticketToCancel = user1.getTicketBooked().stream().filter(b -> b.user1.getTicketBooked().equals(ticketId)).findFirst();
        if (ticketToCancel.isPresent()) {
            userList.remove(ticketToCancel.get());
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    public Boolean fetchTrains (String source, String destination) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("/home/riya/Desktop/app/src/main/java/org/example/localDb/users/train.json");
            List<Train> trains = objectMapper.readValue(file, new TypeReference<List<Train>>() {});
            for(Train train : trains){
                if(train.getStationTime().containsKey(source) && train.getStationTime().containsKey(destination)){
                    return true;
                }
            }
        }catch (IOException e){
            System.out.println("Failed to fetch trains");
            return Boolean.FALSE;
        }
        return Boolean.FALSE;
    }

    public void bookTicket(String source, String destination, Integer rowNumber , Integer colNumber) {
        if(fetchTrains(source, destination)==true){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                File file = new File("/home/riya/Desktop/app/src/main/java/org/example/localDb/users/train.json");
                List<Train> trains = objectMapper.readValue(file, new TypeReference<List<Train>>() {});
                for(Train train : trains){
                    List<List<Integer>> seats = train.getTrainSeats();
                    Boolean booked = false;
                    if(seats.get(rowNumber).get(colNumber)==0){
                        System.out.println("The seat has been booked!");
                        seats.get(rowNumber).set(colNumber,1);
                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, trains);
                        return;
                    }
                    else {
                        System.out.println("The seat has been already booked! Please try another seat");
                        return;
                    }
                }
            }catch ( IOException e){
                System.out.println("Failed to fetch trains");
                return;
            }

        }
        else {
            System.out.println("Train Seat unavailable");
            return;
        }
        return;
    }

}
