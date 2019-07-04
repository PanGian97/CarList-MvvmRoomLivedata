package pangian.car.carsfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.List;

import pangian.car.carsfinder.MVVM.CarView;
import pangian.car.carsfinder.MVVM.CarViewModel;

public class MainActivity extends AppCompatActivity {
    private  CarView carView;
    private CarViewModel carViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(carView.getRootView());
        carView = new CarView(LayoutInflater.from(this),null);

        carViewModel = ViewModelProviders.of(this).get(CarViewModel.class);//it will destroy it when activity is destroyed
        carViewModel.getAllCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
              carView.adapter.setCars(cars);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
