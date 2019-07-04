package pangian.car.carsfinder.AdpaterHolder;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = "MyAdapter";
    private List<Car> cars = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Car currentNote = cars.get(position);
        holder.brandTxt.setText(currentNote.getBrand());
        holder.modelTxt.setText(currentNote.getModel());
        holder.typeTxt.setText(currentNote.getModel());
        holder.ccTxt.setText(currentNote.getModel());
        holder.gearsTxt.setText(currentNote.getModel());
        holder.hpTxt.setText(currentNote.getModel());


        String carImgUrl = cars.get(position).getUrl();

        //paint the favorite cars
        if (cars.get(position).getFavorite()) {
            holder.favButton.setColorFilter(Color.argb(255, 255, 255, 255)); // white tint
            Log.d(TAG, "onBindViewHolder: RecView found favorite " + cars.get(position).getBrand() + "at position :" + cars.get(position));
        } else {
            holder.favButton.setColorFilter(Color.argb(255, 25, 25, 255));//blue tint
        }

        if (carImgUrl != null) {
            Picasso.with(holder.itemView.getContext())
                    .load(carImgUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imgView);

        }
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    public Car getCarAt(int position)//now we can get the note of the adapter to the outside
    {
        return cars.get(position);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}