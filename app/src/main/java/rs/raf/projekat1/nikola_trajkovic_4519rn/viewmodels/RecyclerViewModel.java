package rs.raf.projekat1.nikola_trajkovic_4519rn.viewmodels;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import rs.raf.projekat1.nikola_trajkovic_4519rn.model.Ticket;

public class RecyclerViewModel extends ViewModel {

    private static MutableLiveData<List<Ticket>> tiketiToDo = new MutableLiveData<>();
    private static MutableLiveData<List<Ticket>> tiketiInProgress = new MutableLiveData<>();
    private static MutableLiveData<List<Ticket>> tiketiDone = new MutableLiveData<>();

    private static MutableLiveData<List<Ticket>> tiketiToDoFilter = new MutableLiveData<>();
    private static MutableLiveData<List<Ticket>> tiketiInProgressFilter = new MutableLiveData<>();
    private static MutableLiveData<List<Ticket>> tiketiDoneFilter = new MutableLiveData<>();

    public static ArrayList<Ticket>tiketiToDoList = new ArrayList<>();
    public static ArrayList<Ticket>tiketiDoneList = new ArrayList<>();
    public static ArrayList<Ticket>tiketiInProgressList = new ArrayList<>();

    public ArrayList<Ticket>tiketiToDoListFilter = new ArrayList<>();
    public ArrayList<Ticket>tiketiDoneListFilter = new ArrayList<>();
    public ArrayList<Ticket>tiketiInProgressListFilter = new ArrayList<>();



    public RecyclerViewModel() {

        Random random = new Random();
        int n;
        for(int i=0; i<10; i++){

            n= random.nextInt(2);
            if(n == 0){

                Ticket ticket = new Ticket(i,"nmp", "Bug " + i,"Ovo je neki opis","bug","major",5,0);
                tiketiToDoList.add(ticket);


            }else{

                Ticket ticket = new Ticket(i,"nmp", "Enchantment " + i,"Ovo je neki opis","enchantment","Low",4,0);
                tiketiToDoList.add(ticket);


            }

        }
        ArrayList<Ticket> ticketsToDoSubmit = new ArrayList<>(tiketiToDoList);
        tiketiToDo.setValue(ticketsToDoSubmit);
        tiketiToDoFilter.setValue(ticketsToDoSubmit);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterTicketsTodo(String filter) {
        List<Ticket> filteredList = tiketiToDoList.stream().filter(ticket -> ticket.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        tiketiToDoFilter.setValue(filteredList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterTicketsInProgress(String filter) {
        List<Ticket> filteredList = tiketiInProgressList.stream().filter(ticket -> ticket.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        tiketiInProgressFilter.setValue(filteredList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterTicketsDone(String filter) {
        List<Ticket> filteredList = tiketiDoneList.stream().filter(ticket -> ticket.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        tiketiDoneFilter.setValue(filteredList);
    }

    private int counterTodo = 101;
    private int counterInProgress = 101;
    private int counterDone = 101;

    public int addTicketTodo(String naslov, String opis, String type, String priority, Integer est) {
        int id = counterTodo++;
        Ticket ticket = new Ticket(id,"nmp",naslov,opis,type,priority,est,0);
        tiketiToDoList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(tiketiToDoList);
        tiketiToDo.setValue(listToSubmit);
        tiketiToDoFilter.setValue(listToSubmit);
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int addTicketTodoProgress(int id) {

        Optional<Ticket> ticketObject = tiketiInProgressList.stream().filter(car -> car.getId() == id).findFirst();
        if(ticketObject.isPresent()){
            tiketiToDoList.add(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(tiketiToDoList);
            tiketiToDo.setValue(listToSubmit);
            tiketiToDoFilter.setValue(listToSubmit);
        }

        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeTicketTodo(int id) {
        Optional<Ticket> ticketObject = tiketiToDoList.stream().filter(car -> car.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            tiketiToDoList.remove(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(tiketiToDoList);
            tiketiToDo.setValue(listToSubmit);
            tiketiToDoFilter.setValue(listToSubmit);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int addTicketInProgress(int id) {

        Optional<Ticket> ticketObject = tiketiToDoList.stream().filter(car -> car.getId() == id).findFirst();
        if(ticketObject.isPresent()){
            tiketiInProgressList.add(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(tiketiInProgressList);
            tiketiInProgress.setValue(listToSubmit);
            tiketiInProgressFilter.setValue(listToSubmit);
        }

        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeTicketInProgress(int id) {
        Optional<Ticket> ticketObject = tiketiInProgressList.stream().filter(car -> car.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            tiketiInProgressList.remove(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(tiketiInProgressList);
            tiketiInProgress.setValue(listToSubmit);
            tiketiInProgressFilter.setValue(listToSubmit);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int addTicketDone(int id) {
        Optional<Ticket> ticketObject = tiketiInProgressList.stream().filter(car -> car.getId() == id).findFirst();
        if(ticketObject.isPresent()){
            tiketiDoneList.add(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(tiketiDoneList);
            tiketiDone.setValue(listToSubmit);
            tiketiDoneFilter.setValue(listToSubmit);
        }
        return id;
    }


    public LiveData<List<Ticket>> getTiketiToDo() {
        return tiketiToDo;
    }

    public LiveData<List<Ticket>> getTiketiInProgress() {
        return tiketiInProgress;
    }

    public LiveData<List<Ticket>> getTiketiDone() {
        return tiketiDone;
    }

    public LiveData<List<Ticket>> getTiketiToDoFilter() {
        return tiketiToDoFilter;
    }

    public LiveData<List<Ticket>> getTiketiInProgressFilter() {
        return tiketiInProgressFilter;
    }

    public LiveData<List<Ticket>> getTiketiDoneFilter() {
        return tiketiDoneFilter;
    }
}
