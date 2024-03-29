package pangian.car.carsfinder.AdpaterHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pangian.car.carsfinder.Car;
import pangian.car.carsfinder.CarClickListener;
import pangian.car.carsfinder.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView brandTxt;
    TextView modelTxt;
    TextView typeTxt;
    TextView ccTxt;
    TextView gearsTxt;
    TextView hpTxt;
    ImageButton favButton;
    ImageView imgView;
    private Car car;

    public MyViewHolder(@NonNull View itemView , CarClickListener carClickListener) {

        super(itemView);
        brandTxt = (TextView)itemView.findViewById(R.id.title);
        modelTxt = (TextView)itemView.findViewById(R.id.description);
        imgView = (ImageView)itemView.findViewById(R.id.imageView);
        typeTxt=(TextView)itemView.findViewById(R.id.type);
        ccTxt=(TextView)itemView.findViewById(R.id.cc);
        gearsTxt=(TextView)itemView.findViewById(R.id.gears);
        hpTxt=(TextView)itemView.findViewById(R.id.hp);
        favButton=(ImageButton)itemView.findViewById(R.id.add_to_fav);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    carClickListener.onCarClicked(car);
            }
        });
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
