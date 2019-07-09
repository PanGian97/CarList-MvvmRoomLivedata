package pangian.car.carsfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.sax.TemplatesHandler;

import pangian.car.carsfinder.MVVM.CarView;
import pangian.car.carsfinder.MVVM.CarViewModel;

public class MainActivity extends AppCompatActivity {
    private  CarView carView;
    private CarViewModel carViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        carViewModel = ViewModelProviders.of(this).get(CarViewModel.class);//it will destroy it when activity is destroyed

        carView = new CarView(LayoutInflater.from(this),null,carViewModel);
        setContentView(carView.getRootView());



    }

    @Override
    protected void onStart() {
        super.onStart();
        carViewModel.saveCarsFromServer();
        carViewModel.getDataMerger().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> carList) {
                carView.render(carList);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }




}
