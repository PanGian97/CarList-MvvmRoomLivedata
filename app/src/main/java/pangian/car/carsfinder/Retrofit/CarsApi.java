package pangian.car.carsfinder.Retrofit;

import androidx.lifecycle.LiveData;

import java.util.List;

import pangian.car.carsfinder.Car;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarsApi {
    @GET("/a722519e2348c489ace5") //password P...json!....

    Call<List<Car>> getAllCarsFromServer();
}
