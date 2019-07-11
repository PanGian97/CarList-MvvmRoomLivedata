package pangian.car.carsfinder.Retrofit;

import androidx.lifecycle.LiveData;

import java.util.List;

import pangian.car.carsfinder.Car;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarsApi {
    @GET("/api/items/e232d7d6-f948-4a95-8e89-292cd9e3a5d8") //password P...json!....

    Call<List<Car>> getAllCarsFromServer();
}
