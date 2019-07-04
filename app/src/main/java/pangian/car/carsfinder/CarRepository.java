package pangian.car.carsfinder;

import android.app.Application;
import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pangian.car.carsfinder.Retrofit.CarsApi;
import pangian.car.carsfinder.Retrofit.RemoteDataSource;
import pangian.car.carsfinder.Retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarRepository {

    CarDatabase database;
    NetworkChecker networkChecker;
    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;

    CarDao carDao;
//    LiveData<List<Car>> getAllCars;
//    LiveData<List<Car>> getAllCarsByHp;
//    LiveData<List<Car>> getAllCarsByModel;

    private MutableLiveData<List<Car>> mutableLiveData = new MutableLiveData<>();

    public CarRepository(Application application) {

        networkChecker = new NetworkChecker(application);//?
        remoteDataSource = new RemoteDataSource();

         database = CarDatabase.getInstance(application);
        carDao = database.carDao();//it was instantiated cause since we create the NoteDatabase instance.build ROOM also generates this method
        localDataSource = new LocalDataSource(carDao);
    }

    public LiveData<List<Car>> getAllCars() {
        return carDao.getAllCars();
    }

    public LiveData<List<Car>> getAllCarsByHp() {
        return carDao.getAllCarsByHp();
    }

    public LiveData<List<Car>> getAllCarsByModel() {
        return carDao.getAllCarsByModel();
    }


    public LiveData<List<Car>> loadAllCars() {
     if(networkChecker.isNetworkAvailable()) {
         localDataSource.save(remoteDataSource.getCars());
         return remoteDataSource.getCars();
     }
     else{

         return getAllCars();
     }

    }




}
