package pangian.car.carsfinder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "car_table")
public class Car {//ROOM will generate columns for these fields



    @PrimaryKey(autoGenerate = true)
    private int auto_id;

    private String id;

    private String cc;

    private String horsepower;

    private String model;

    private String type;

    private String brand;

    private String gears;

    private String url;

    private boolean isFavorite;


    public Car(String id, String brand, String model, String type, String cc, String gears, String horsepower, String url,boolean isFavorite) {

        this.cc = cc;
        this.horsepower = horsepower;
        this.model = model;
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.gears = gears;
        this.url = url;
        this.isFavorite=isFavorite;
        setAuto_id(Integer.parseInt(id));
    }

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }



    public String getCc ()
    {
        return cc;
    }

    public void setCc (String cc)
    {
        this.cc = cc;
    }

    public String getHorsepower ()
    {
        return horsepower;
    }

    public void setHorsepower (String horsepower)
    {
        this.horsepower = horsepower;
    }

    public String getModel ()
    {
        return model;
    }

    public void setModel (String model)
    {
        this.model = model;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getBrand ()
    {
        return brand;
    }

    public void setBrand (String brand)
    {
        this.brand = brand;
    }

    public String getGears ()
    {
        return gears;
    }

    public void setGears (String gears)
    {
        this.gears = gears;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cc = "+cc+", horsepower = "+horsepower+", model = "+model+", id = "+id+", type = "+type+", brand = "+brand+", gears = "+gears+", url = "+url+"]";
    }
}
