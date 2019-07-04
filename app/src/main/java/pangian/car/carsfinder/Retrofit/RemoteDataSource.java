package pangian.car.carsfinder.Retrofit;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pangian.car.carsfinder.Car;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {


    private MutableLiveData<List<Car>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Car>> getCars(){

        CarsApi service = RetrofitClientInstance.getRetrofitInstance();
        Call<List<Car>> call = service.getAllCarsFromServer();

        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                List<Car> carList = response.body();
                if (carList != null) {
                     mutableLiveData.setValue(carList);//mutableLiveData gets a list from server(Comment cause we will need it in case only if we dont want to store it)

                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {

            }
        });
            return mutableLiveData;
    }
}
