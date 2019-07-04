package pangian.car.carsfinder.MVVM;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import pangian.car.carsfinder.AdpaterHolder.MyAdapter;
import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.R;

public class CarView {

    private View rootView;
    private LinearLayoutManager layoutManager;
    private ViewGroup linearTransContainer;


    public MyAdapter adapter;
    public Spinner spinner;
    public ImageButton favImage;
    public SwipeRefreshLayout swipeContainer;
    public ImageButton settingsButton;

    public CarView(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.activity_main,viewGroup,false);

        spinner = (Spinner) findViewById(R.id.filter_options);
        favImage=(ImageButton) findViewById(R.id.fav_img);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        linearTransContainer=(LinearLayout)findViewById(R.id.linear_layout);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        settingsButton = (ImageButton) findViewById(R.id.settings);
        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        adapter = new MyAdapter();

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),R.array.filters,R.layout.spinner_selected_text_color);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_color);

        spinner.setAdapter(spinnerAdapter);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public void swipeRefreshState(boolean state){
        if(state)swipeContainer.setRefreshing(false);
        else swipeContainer.setRefreshing(true);
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
//    public void goToFavorites() {
//        Intent intent = new Intent(getContext(), FavActivity.class);
//        getContext().startActivity(intent);
//    }
}
