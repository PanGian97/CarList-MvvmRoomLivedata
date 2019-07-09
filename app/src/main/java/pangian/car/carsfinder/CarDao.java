package pangian.car.carsfinder;


import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

//
//    @Update
//    void update(Car car);
//
//    @Delete
//    void delete(Car car);
//
//    @Query("DELETE FROM car_table")
//    void deleteAllNotes();
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(List<Car> carList);

    @Update
    void updateAll(List<Car> carList);


    @Query("DELETE FROM car_table")
    void deleteAll();

    //SORTING ORDER
    @Query("SELECT * FROM car_table ORDER BY id ASC")
    LiveData<List<Car>> getAllCars();

    @Query("SELECT * FROM car_table ORDER BY horsepower  ASC")
    LiveData<List<Car>> getAllCarsByHp();

    @Query("SELECT * FROM car_table ORDER BY model ASC")
    LiveData<List<Car>> getAllCarsByModel();


    @Query("SELECT * FROM car_table WHERE isFavorite='true' ORDER BY id ASC")
    LiveData<List<Car>> getAllFavCars();

    @Query("UPDATE car_table SET isFavorite='true' WHERE id=:carId")
    void favoriteCar(int carId)  ;

}
