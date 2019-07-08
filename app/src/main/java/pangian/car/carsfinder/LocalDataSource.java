package pangian.car.carsfinder;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource {
    private CarDao carDao;
    List<Car> carListFromDb = new ArrayList<>();

    public LocalDataSource(CarDao carDao) {
        this.carDao = carDao;

    }


//    public boolean hasDataChanged(List<Car> carList) {
//
//
//        if(carList!=carListFromDb){
//        return true;}
//        else{
//            return false;}
//    }

    public void save(List<Car> carList) {

        new SaveToDbASyncTask(carDao,carList ).execute();//?
    }



    private static class SaveToDbASyncTask extends AsyncTask<Void, Void, Void> {
        private CarDao carDao;
        private List<Car> carList;
        private SaveToDbASyncTask(CarDao carDaoDb, final List<Car> carList) {
            carDao = carDaoDb;
            this.carList=carList;


        }

        @Override
        protected Void doInBackground(Void... voids) {
                carDao.deleteAll();
                carDao.insertAll(carList);
            return null;
        }

    }
}

