package pangian.car.carsfinder;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepository {

    CarDao carDao;
    LiveData<List<Car>>getAllCars;
    LiveData<List<Car>>getAllCarsByHp;
    LiveData<List<Car>>getAllCarsByModel;

    public CarRepository(Application application) {
        CarDatabase database = CarDatabase.getInstance(application);
       carDao = database.carDao();//it was instantiated cause since we create the NoteDatabase instance.build ROOM also generates this method
    }
    public LiveData<List<Car>> getAllCars(){
        return carDao.getAllCars();
    }
    public LiveData<List<Car>> getAllCarsByHp(){
        return carDao.getAllCarsByHp();
    }
    public LiveData<List<Car>> getAllCarsByModel(){
        return carDao.getAllCarsByModel();
    }
}
