package pangian.car.carsfinder;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class CarDaoTest {

   private CarDatabase  carDatabase;

   private CarDao carDao;

   @Before
   public void initDb()throws Exception{

       //carDatabase= Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), CarDatabase.class).build();
       carDatabase= Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),CarDatabase.class).build();
       carDao = carDatabase.carDao();
   }

   @After
    public void closeDb(){
       carDatabase.close();
   }
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();
   @Test
    public void insertACarAndThenReadIt()
   {
       Car car = new Car("1", "mitsos", "mitsos","mitsos","mitsos","mitsos","mitsos","mitsos","false");

       carDao.insert(car);

       carDao.getAllCars(false,"id")
               .observeForever(carList -> {
                   assert(car.getId().equals(carList.get(0).getId()));
               });
   }

   @Test
    public void getAllCarsByHp()
   {    List<Car> cars= new ArrayList<>();

       Car car = new Car("1", "mitsos", "panais","mitsos","mitsos","mitsos","100","mitsos","false");
       cars.add(car);
       Car car2 = new Car("2", "mitsos", "mitsos","mitsos","mitsos","mitsos","1","mitsos");
       cars.add(car2);
       Car car3 = new Car("3", "mitsos", "alekos","mitsos","mitsos","mitsos","55","mitsos");
       cars.add(car3);
       carDao.insertAll(cars);
       carDao.getAllCars(false,"model")
               .observeForever(carList -> {
                   if(Integer.parseInt(carList.get(0).getHorsepower()) != 100 )
                        assertEquals(55, Integer.parseInt(carList.get(0).getHorsepower()));
               });   }

}
