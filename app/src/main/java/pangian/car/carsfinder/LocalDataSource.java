package pangian.car.carsfinder;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource {
    private CarDao carDao;
    public MutableLiveData<Boolean> isDataLoaded = new MutableLiveData<>();



    public LocalDataSource(CarDao carDao) {
        this.carDao = carDao;

    }


    public void save(List<Car> carList) {

        new SaveToDbASyncTask(carDao, carList).execute();//?
    }


    private static class SaveToDbASyncTask extends AsyncTask<Void, Void, Void> {
        private CarDao carDao;
        private List<Car> carList;


        private SaveToDbASyncTask(CarDao carDaoDb, final List<Car> carList) {
            carDao = carDaoDb;
            this.carList = carList;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //carDao.deleteAll();

            insertOrUpdate(carList);


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }

        private void insertOrUpdate(List<Car> carList) {


            if (carDao.getDataSize() == 0) {
                carDao.insertAll(carList);
            } else {
                for (int i = 0; i < carList.size(); i++) {
                    Car car = carList.get(i);

                    carDao.updateCars(car.getId(), car.getCc(), car.getModel(), car.getType(), car.getHorsepower(), car.getBrand(), car.getUrl());
                }
            }

        }

    }
    public void dataStateChange(boolean state){
        if(state=false) {
            isDataLoaded.setValue(false);
        }else{isDataLoaded.setValue(true);}
    }
    public LiveData<Boolean> isDataLoaded() {
        return isDataLoaded;
    }

}

