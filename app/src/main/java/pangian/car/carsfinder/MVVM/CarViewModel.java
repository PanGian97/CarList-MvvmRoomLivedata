package pangian.car.carsfinder.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarRepository;

public class CarViewModel extends AndroidViewModel {


    private final LiveData<List<Car>> byId;
    private final LiveData<List<Car>> byHp;
    private final LiveData<List<Car>> byModel;
    private final LiveData<List<Car>> favbyId;
    private final LiveData<List<Car>> favbyHp;
    private final LiveData<List<Car>> favbyModel;


    public static final int ALL_CARS=0;
    public static final int FAV_CARS=1;

    private CarRepository repository;

    private MediatorLiveData<List<Car>> dataMerger = new MediatorLiveData<List<Car>>();

    public LiveData<List<Car>> getAllFavCars() {
        return repository.getAllFavCars();
    }

    public CarViewModel(@NonNull Application application) {//difference is that we pass application in constructor
        //which we can use for whenever application context is needed
        //but we never store a context of an activity or a view that references an activity in ViewModel
        //cause we learned the ViewModel is designed to outlift an activity after is destroyed and if we hold a reference to an already destroyed activity
        //we have a memory leak
        super(application);


        repository = new CarRepository(application);

        byId = repository.getAllCars();
        byHp = repository.getAllCarsByHp();
        byModel = repository.getAllCarsByModel();
        favbyId = repository.getAllFavCars();
        favbyHp = repository.getAllFavCarsByHp();
        favbyModel = repository.getAllFavCarsByModel();
    }

    public void saveCarsFromServer() {
         repository.saveCarsFromServer();
    }


    public void carSortBy(int position, int state) {

        if (state==0)resetSources(0);
        else if(state==1)resetSources(1);

        switch (position){
            case 0:defaultListSorter(state);break;
            case 1:listSorterByHp(state);break;
            case 2:listSorterByModel(state);break;
        }
    }

    private void resetSources(int selectedSortedList) {
        if(selectedSortedList==0) {
            getDataMerger().removeSource(byId);
            getDataMerger().removeSource(byModel);
            getDataMerger().removeSource(byHp);
        }else if(selectedSortedList== 1){
            getDataMerger().removeSource(favbyId);
            getDataMerger().removeSource(favbyModel);
            getDataMerger().removeSource(favbyHp);
        }
    }


    public void defaultListSorter(int state) {
        if(state==0)
        getDataMerger().addSource(byId,cars -> getDataMerger().setValue(cars));
        else if(state==1)
            getDataMerger().addSource(favbyId,cars -> getDataMerger().setValue(cars));
    }

    public void listSorterByHp(int state) {

       if(state==0) getDataMerger().addSource(byHp,cars -> getDataMerger().setValue(cars));
        else if(state==1)getDataMerger().addSource(favbyHp,cars -> getDataMerger().setValue(cars));
    }

    public void listSorterByModel(int state) {
       if(state==0) getDataMerger().addSource(byModel, cars -> getDataMerger().setValue(cars));
       else if (state==1) getDataMerger().addSource(favbyModel, cars -> getDataMerger().setValue(cars));
    }


    public MediatorLiveData<List<Car>> getDataMerger() {
        return dataMerger;
    }



    public void updateFavoriteStatus(Car car) {
        repository.updateFavoriteStatus(car);
    }
public void selectedListToBeSorted(int position,int selectedList){
 if(selectedList==ALL_CARS) {
        carSortBy(position, ALL_CARS);
    }else if(selectedList==FAV_CARS) {
    carSortBy(position, FAV_CARS);
 }
    }
}
