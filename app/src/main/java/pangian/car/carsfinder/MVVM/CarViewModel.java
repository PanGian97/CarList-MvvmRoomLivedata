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

        byId = repository.getAllCars();//so the activity will have  a reference to viewmodel only and not the repository
        byHp = repository.getAllCarsByHp();
        byModel = repository.getAllCarsByModel();
    }

    public void saveCarsFromServer() {
         repository.saveCarsFromServer();
    }


    public void carSortBy(int position) {

        resetSources();

        switch (position){
            case 0:defaultListSorter();break;
            case 1:listSorterByHp();break;
            case 2:listSorterByModel();break;
        }
    }

    private void resetSources() {
        getDataMerger().removeSource(byId);
        getDataMerger().removeSource(byModel);
        getDataMerger().removeSource(byHp);
    }


    public void defaultListSorter() {
        getDataMerger().addSource(byId,cars ->
                getDataMerger().setValue(cars));
    }

    public void listSorterByHp() {

        getDataMerger().addSource(byHp,cars ->
                getDataMerger().setValue(cars));

    }

    public void listSorterByModel() {

        getDataMerger().addSource(byModel,
                cars ->
                        getDataMerger().setValue(cars));


    }


    public MediatorLiveData<List<Car>> getDataMerger() {
        return dataMerger;
    }

    public void goToFavorites() {
    }

    public void updateFavoriteStatus(Car car) {
        repository.updateFavoriteStatus(car);
    }
}
