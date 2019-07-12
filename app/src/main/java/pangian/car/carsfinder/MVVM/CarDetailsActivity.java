package pangian.car.carsfinder.MVVM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import pangian.car.carsfinder.R;

public class CarDetailsActivity extends AppCompatActivity {


    CarViewModel carViewModel;
    CarDetailsView carDetailsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);


        carViewModel = ViewModelProviders.of(this).get(CarViewModel.class);//it will destroy it when activity is destroyed

        carDetailsView = new CarDetailsView(LayoutInflater.from(this),null,carViewModel);

        carDetailsView.getIncomingData();

    }


}
