package pangian.car.carsfinder.MVVM;

import android.app.Application;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarRepository;
import pangian.car.carsfinder.MainActivity;
import pangian.car.carsfinder.R;

public class CarViewModel extends AndroidViewModel {


    private CarRepository repository;

    private MediatorLiveData<List<Car>> dataMerger = new MediatorLiveData<List<Car>>();

    public CarViewModel(@NonNull Application application) {//difference is that we pass application in constructor
        //which we can use for whenever application context is needed
        //but we never store a context of an activity or a view that references an activity in ViewModel
        //cause we learned the ViewModel is designed to outlift an activity after is destroyed and if we hold a reference to an already destroyed activity
        //we have a memory leak
        super(application);


        repository = new CarRepository(application);

       // allCars = repository.getAllCars();//so the activity will have  a reference to viewmodel only and not the repository

    }



    public LiveData<List<Car>> getGetAllCarsByHp() {
        return repository.getAllCarsByHp();
    }
    public LiveData<List<Car>> getGetAllCarsByModel() {
        return repository.getAllCarsByModel();
    }
    public LiveData<List<Car>> getAllCars(){
        return  repository.loadAllCars(); }



    public void carSortBy(int position) {
        switch (position){
            case 0:defaultListSorter();break;
            case 1:listSorterByHp();break;
            case 2:listSorterByModel();break;
        }
    }


    public void defaultListSorter() {

        getDataMerger().addSource(getAllCars(),cars ->getDataMerger().setValue(cars));
      //  dataMerger.removeSource(getAllCars());
    }
    public void listSorterByHp() {

        getDataMerger().addSource(getGetAllCarsByHp(),cars ->getDataMerger().setValue(cars));
      //  dataMerger.removeSource(getGetAllCarsByHp());
    }
    public void listSorterByModel() {

        getDataMerger().addSource(getGetAllCarsByModel(),cars ->getDataMerger().setValue(cars));
       // dataMerger.removeSource(getGetAllCarsByModel());
    }


    public MediatorLiveData<List<Car>> getDataMerger() {
        return dataMerger;
    }
}
