package pangian.car.carsfinder.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarRepository;

public class CarViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> menuState = new MutableLiveData<>();
    private MutableLiveData<Boolean> favState = new MutableLiveData<>();

    public void onClicked() {
        Boolean previous = menuState.getValue();

        if (previous != null) {
            menuState.setValue(!previous);
        } else {
            menuState.setValue(true);
        }
    }

    public void favoriteButtonClicked() {
        Boolean previous = favState.getValue();

        if (previous != null) {
            favState.setValue(!previous);
        } else {
            favState.setValue(true);
        }

    }

    public LiveData<List<Car>> cars() {

        return Transformations.switchMap(favState, showFavorites -> {
            if(showFavorites)
                return repository.getAllFavCars();
            else
                return repository.getAllCars();
        });
    }

    private CarRepository repository;


    public CarViewModel(@NonNull Application application) {//difference is that we pass application in constructor
        //which we can use for whenever application context is needed
        //but we never store a context of an activity or a view that references an activity in ViewModel
        //cause we learned the ViewModel is designed to outlift an activity after is destroyed and if we hold a reference to an already destroyed activity
        //we have a memory leak
        super(application);

        repository = new CarRepository(application);


    }

    public void saveCarsFromServer() {
        repository.saveCarsFromServer();
    }

    public LiveData<Boolean> menuState() {
        return menuState;

    }

    public void updateFavoriteStatus(Car car) {
        repository.updateFavoriteStatus(car);
    }

    public void defaultState() {
        menuState.setValue(false);
        favState.setValue(false);
    }
}
