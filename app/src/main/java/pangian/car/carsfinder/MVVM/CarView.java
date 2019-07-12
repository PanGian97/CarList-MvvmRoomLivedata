package pangian.car.carsfinder.MVVM;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
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

import java.io.Serializable;
import java.util.List;

import pangian.car.carsfinder.AdpaterHolder.MyAdapter;
import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarClickListener;
import pangian.car.carsfinder.MainActivity;
import pangian.car.carsfinder.R;

public class CarView implements CarClickListener {

    private CarViewModel carViewModel;

    private View rootView;
    private LinearLayoutManager layoutManager;
    private ViewGroup linearTransContainer;


    public MyAdapter adapter;
    public Spinner spinner;
    public ImageButton favImage;
    public SwipeRefreshLayout swipeContainer;
    public ImageButton settingsButton;

    public CarView(LayoutInflater inflater, ViewGroup viewGroup, CarViewModel carViewModel) {

        this.carViewModel=carViewModel;

        rootView = inflater.inflate(R.layout.activity_main,viewGroup,false);

        spinner = (Spinner) findViewById(R.id.filter_options);
        favImage=(ImageButton) findViewById(R.id.fav_img);
        settingsButton = (ImageButton) findViewById(R.id.settings);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);

        linearTransContainer=(LinearLayout)findViewById(R.id.spinner_sort_layout);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);


        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        adapter = new MyAdapter(this);
        rv.setAdapter(adapter);


        favImage.setOnClickListener(myClickListener);
        settingsButton.setOnClickListener(myClickListener);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),R.array.filters,R.layout.spinner_selected_text_color);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_color);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // carViewModel.carSortBy(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
           carViewModel.saveCarsFromServer();


          }
      });
    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()) {
               case R.id.fav_img:
                   carViewModel.favoriteButtonClicked();

                   break;

               case R.id.settings:
                   carViewModel.onClicked();
                   break;
           }
        }
    };

    public void showHideMenu(Boolean isOpen) {
        if(isOpen){
            linearTransContainer.setVisibility(View.GONE);
            favImage.setVisibility(View.GONE);
        }else{
            linearTransContainer.setVisibility(View.VISIBLE);
            favImage.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onCarFavBtnClicked(Car car) {
       carViewModel.updateFavoriteStatus(car);
    }

    @Override
    public void onCarClicked(Car car) {
        Intent intent = new Intent(getContext(),CarDetailsActivity.class);
//        intent.putExtra("brand",car.getBrand());
//        intent.putExtra("model",car.getModel());
//        intent.putExtra("type",car.getType());
//        intent.putExtra("cc",car.getCc());
//        intent.putExtra("gears",car.getGears());
//        intent.putExtra("img_url",car.getUrl());
        intent.putExtra("car", (Serializable) car);
        getContext().startActivity(intent);
    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }


    public View getRootView() {
        return rootView;
    }

    public void render(List<Car> carList) {
        if(swipeContainer.isRefreshing()) {
            swipeContainer.setRefreshing(false);
        }
        adapter.setCars(carList);
    }

    public void refresh() {

    }

    public void isOnFavorites(Boolean isShowingFavCars) {
        //paint the favorite cars
        if (isShowingFavCars) {
            favImage.setColorFilter(Color.argb(255, 255, 255, 255)); // white tint
        } else if(!isShowingFavCars){
          favImage.setColorFilter(Color.argb(255, 25, 25, 255));//blue tint
        }
    }


//    public void goToFavorites() {
//        Intent intent = new Intent(getContext(), FavActivity.class);
//        getContext().startActivity(intent);
//    }
}
