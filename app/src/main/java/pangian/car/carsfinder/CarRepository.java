package pangian.car.carsfinder;

import android.app.Application;
import android.location.Location;
import android.os.AsyncTask;

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
    public LiveData<List<Car>> getAllFavCars() {
        return carDao.getAllFavCars(true);//BUG: if i make it true on Query it doesnt work e.g(isFavorite='true')
    }

    public void saveCarsFromServer() {
     if(networkChecker.isNetworkAvailable()) {

             CarsApi service = RetrofitClientInstance.getRetrofitInstance();
             Call<List<Car>> call = service.getAllCarsFromServer();

             call.enqueue(new Callback<List<Car>>() {
                 @Override
                 public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                     List<Car> carList = response.body();
                     if (carList != null) {
                             localDataSource.save(carList);

                         }

                     }

                 @Override
                 public void onFailure(Call<List<Car>> call, Throwable t) {

                 }
             });

     }
    }

    public void updateFavoriteStatus(Car car) {
        new UpdateCarAsyncTask(carDao).execute(car);
    }

    private static class UpdateCarAsyncTask extends AsyncTask<Car, Void, Void> {
        private CarDao carDao;

        private UpdateCarAsyncTask(CarDao carDao) {
            this.carDao = carDao;
        }

        @Override
        protected Void doInBackground(Car... cars) {
            if(carDao.isCarFavorite(cars[0].getAuto_id(),true)==0) {
                carDao.favoriteCar(Integer.parseInt(cars[0].getId()),true);
            }
            else if(carDao.isCarFavorite(cars[0].getAuto_id(),true)==1)
            {  carDao.favoriteCar(Integer.parseInt(cars[0].getId()),false);}
            return null;
        }
    }


}
