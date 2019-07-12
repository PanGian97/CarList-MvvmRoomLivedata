package pangian.car.carsfinder.MVVM;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import pangian.car.carsfinder.AdpaterHolder.MyAdapter;
import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarClickListener;
import pangian.car.carsfinder.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CarDetailsView {
    private CarViewModel carViewModel;

    private View rootView;


    public CarDetailsView(LayoutInflater inflater, ViewGroup viewGroup, CarViewModel carViewModel) {

        this.carViewModel =carViewModel;

    rootView =inflater.inflate(R.layout.activity_car_details,viewGroup,false);

}
        private Context getContext () {
            return getRootView().getContext();
        }

        private <T extends View > T findViewById( int id){
            return getRootView().findViewById(id);
        }


        public View getRootView () {
            return rootView;
        }

    public void getIncomingData() {
        Intent intent = getIntent().getSerializableExtra("car");
    }
}



