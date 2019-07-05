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

             CarsApi service = RetrofitClientInstance.getRetrofitInstance();
             Call<List<Car>> call = service.getAllCarsFromServer();

             call.enqueue(new Callback<List<Car>>() {
                 @Override
                 public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                     List<Car> carList = response.body();
                     if (carList != null) {
                         mutableLiveData.setValue(carList);//mutableLiveData gets a list from server(Comment cause we will need it in case only if we dont want to store it)
                         List<Car> carListFromServer = mutableLiveData.getValue();
                   //      if(localDataSource.hasDataChanged(carListFromServer)) {
                             localDataSource.save(carListFromServer);
                         }
                     }
                 //}

                 @Override
                 public void onFailure(Call<List<Car>> call, Throwable t) {

                 }
             });



         return getAllCars();
     }
     else{

         return getAllCars();
     }

    }




}
