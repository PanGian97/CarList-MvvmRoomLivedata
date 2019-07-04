package pangian.car.carsfinder.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarRepository;

public class CarViewModel extends AndroidViewModel {

    private CarRepository repository;

    private LiveData<List<Car>> allCars;
    private LiveData<List<Car>> allCarsByHp;
    private LiveData<List<Car>> allCarsByModel;


    public CarViewModel(@NonNull Application application) {//difference is that we pass application in constructor
        //which we can use for whenever application context is needed
        //but we never store a context of an activity or a view that references an activity in ViewModel
        //cause we learned the ViewModel is designed to outlift an activity after is destroyed and if we hold a reference to an already destroyed activity
        //we have a memory leak
        super(application);

        repository = new CarRepository(application);

       // allCars = repository.getAllCars();//so the activity will have  a reference to viewmodel only and not the repository
        allCarsByHp = repository.getAllCarsByHp();
        allCarsByModel = repository.getAllCarsByModel();

        allCars = repository.loadAllCars();
    }



    public LiveData<List<Car>> getGetAllCarsByHp() {
        return allCarsByHp;
    }

    public LiveData<List<Car>> getGetAllCarsByModel() {
        return allCarsByModel;
    }
    public LiveData<List<Car>> getAllCars(){
        return allCars;
    }
}
