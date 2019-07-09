package pangian.car.carsfinder;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Car.class,version = 1)//?
public abstract class CarDatabase extends RoomDatabase {


    //Singleton
    public static CarDatabase instance;

    public abstract CarDao carDao();

    public static synchronized CarDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),CarDatabase.class,"car_database")
              .fallbackToDestructiveMigration()
              .addCallback(roomCallback)
              .build();
        }
        return instance;
    }

    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbASyncTask(instance).execute();
        }
    };
    private static class PopulateDbASyncTask extends AsyncTask<Void,Void,Void>
    {
        private CarDao carDao;
        private PopulateDbASyncTask(CarDatabase db)
        {
            carDao = db.carDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            //Default cars can be created here
            return null;
        }
    }










}
