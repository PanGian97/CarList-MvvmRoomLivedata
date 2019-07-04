package pangian.car.carsfinder.AdpaterHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Car> cars = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item,parent,false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Car currentNote = cars.get(position);
        holder.titleTxt.setText(currentNote.getTitle());
        holder.descTxt.setText(currentNote.getDescription());
        String priorityIdToString = Integer.toString(currentNote.getPriority());
        holder.priorityId.setText(priorityIdToString);
    }

    public void setCars(List<Car>cars){
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