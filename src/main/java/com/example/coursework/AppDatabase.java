// This is the Abstract Class for the Room database
// Provides a centralised access point for data retrieval
// Implemented as 'read-only' currently, which is intentional
// Provided is a sample of the information that could be included

package com.example.coursework;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.coursework.DaoClass.DriverDao;
import com.example.coursework.DaoClass.TeamDao;
import com.example.coursework.EntityClass.Driver;
import com.example.coursework.EntityClass.Team;

import java.util.concurrent.Executors;

@Database(entities = {Driver.class, Team.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    // abstract methods to retrieve the Dao's
    public abstract DriverDao driverDao();
    public abstract TeamDao teamDao();

    // static instance of the database, created only once; based on the Singleton Design Pattern
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DATABASE")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(SupportSQLiteDatabase db) {
                                // this runs once, the first time the app is loaded
                                super.onCreate(db);
                                // Prepopulate the database with Objects required for the application
                                Executors.newSingleThreadScheduledExecutor().execute(() -> {

                                    // Adding the teams
                                    INSTANCE.teamDao().insert(new Team("Oracle Red Bull Racing", "Christian Horner", "RBPT-004", "Honda", "Milton Keynes, UK", 860, 1, "Red Bull Bio", R.color.Red_Bull_Racing, R.drawable.red_bull_logo, R.drawable.red_bull_car));
                                    INSTANCE.teamDao().insert(new Team("Mercedes AMG Petronas F1 Team", "Toto Wolff", "W14", "Mercedes", "Brackley, UK", 409, 2, "Mercedes Bio", R.color.Mercedes, R.drawable.mercedes_logo, R.drawable.mercedes_car));
                                    INSTANCE.teamDao().insert(new Team("Scuderia Ferrari", "Fred Vasseur", "SF22", "Ferrari", "Maranello, Italy", 406, 3, "Ferrari Bio", R.color.Ferrari, R.drawable.ferrari_logo, R.drawable.ferrari_car));
                                    INSTANCE.teamDao().insert(new Team("Mclaren F1 Team", "Zak Brown", "MCL37", "Mercedes", "Woking, UK", 302, 4, "Mclaren Bio", R.color.Mclaren, R.drawable.mclaren_logo, R.drawable.mclaren_car));
                                    INSTANCE.teamDao().insert(new Team("Aston Martin Aramco Cognizant F1 Team", "Otmar Szafnauer", "AMR23", "Mercedes", "Silverstone, UK", 280, 5, "Aston Martin Bio", R.color.Aston_Martin, R.drawable.aston_martin_logo, R.drawable.aston_martin_car));
                                    INSTANCE.teamDao().insert(new Team("BWT Alpine F1 Team", "Laurent Rossi", "A523", "Renault", "Enstone, UK", 120, 6, "Alpine Bio", R.color.Alpine, R.drawable.alpine_logo, R.drawable.alpine_car));
                                    INSTANCE.teamDao().insert(new Team("Williams Racing", "Jost Capito", "FW45", "Mercedes", "Grove, UK", 28, 7, "Williams Bio", R.color.Williams, R.drawable.williams_logo, R.drawable.williams_car));
                                    INSTANCE.teamDao().insert(new Team("Scuderia AlphaTauri", "Franz Tost", "AT04", "Honda", "Faenza, Italy", 25, 8, "Alpha Tauri Bio", R.color.AlphaTauri, R.drawable.alpha_tauri_logo, R.drawable.alpha_tauri_car));
                                    INSTANCE.teamDao().insert(new Team("Alfa Romeo F1 Team Stake", "Frederic Vasseur", "C43", "Ferrari", "Hinwil, Switzerland", 16, 9, "Alfa Romeo Bio", R.color.Alfa_Romeo, R.drawable.alfa_romeo_logo, R.drawable.alfa_romeo_car));
                                    INSTANCE.teamDao().insert(new Team("MoneyGram Haas F1 Team", "Guenther Steiner", "VF-23", "Ferrari", "Kannapolis, USA", 12, 10, "Haas Bio", R.color.Haas, R.drawable.haas_logo, R.drawable.haas_car));


                                    // Adding the drivers
                                    INSTANCE.driverDao().insert(new Driver("Max", "Verstappen", "Oracle Red Bull Racing", 26, "Netherlands", "1", R.drawable.verstappen, 575, 1, "Max Verstappen Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Sergio", "Perez", "Oracle Red Bull Racing", 34, "Mexico", "11", R.drawable.perez, 285, 2, "Sergio Perez Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Lewis", "Hamilton", "Mercedes AMG Petronas F1 Team", 39, "Great Britain", "44", R.drawable.hamilton, 234, 3, "Lewis Hamilton Bio"));
                                    INSTANCE.driverDao().insert(new Driver("George", "Russell", "Mercedes AMG Petronas F1 Team", 26, "Great Britain", "63", R.drawable.russell, 175, 8, "George Russell Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Charles", "Leclerc", "Scuderia Ferrari", 26, "Monaco", "16", R.drawable.leclerc, 206, 5, "Charles Leclerc Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Carlos", "Sainz", "Scuderia Ferrari", 29, "Spain", "55", R.drawable.sainz, 200, 7, "Carlos Sainz Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Lando", "Norris", "Mclaren F1 Team", 24, "Great Britain", "4", R.drawable.norris, 205, 6, "Lando Norris Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Oscar", "Piastri", "Mclaren F1 Team", 22, "Australia", "81", R.drawable.piastri, 97, 9, "Oscar Piastri Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Fernando", "Alonso", "Aston Martin Aramco Cognizant F1 Team", 42, "Spain", "14", R.drawable.alonso, 206, 4, "Fernando Alonso Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Lance", "Stroll", "Aston Martin Aramco Cognizant F1 Team", 25, "Canada", "18", R.drawable.stroll, 74, 10, "Lance Stroll Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Pierre", "Gasly", "BWT Alpine F1 Team", 28, "France", "10", R.drawable.gasly, 62, 11, "Pierre Gasly Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Esteban", "Ocon", "BWT Alpine F1 Team", 27, "France", "31", R.drawable.ocon, 58, 12, "Esteban Ocon Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Alexander", "Albon", "Williams Racing", 27, "Thailand", "23", R.drawable.albon, 27, 13, "Alexander Albon Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Logan", "Sargeant", "Williams Racing", 23, "USA", "2", R.drawable.sergeant, 1, 20, "Logan Sergeant Bio, he is actually quite cool to be honest"));
                                    INSTANCE.driverDao().insert(new Driver("Yuki", "Tsunoda", "Scuderia AlphaTauri", 23, "Japan", "22", R.drawable.tsunoda, 17, 14, "Yuki Tsunoda Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Daniel", "Ricciardo", "Scuderia AlphaTauri", 34, "Australia", "3", R.drawable.ricciardo, 6, 17, "Daniel Ricciardo Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Valterri", "Bottas", "Alfa Romeo F1 Team Stake", 34, "Finland", "77", R.drawable.bottas, 10, 15, "Valterri Bottas Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Guanyu", "Zhou", "Alfa Romeo F1 Team Stake", 24, "China", "24", R.drawable.zhou, 6, 18, "Zhou Guanyu Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Nico", "Hulkenberg", "MoneyGram Haas F1 Team", 36, "Germany", "27", R.drawable.hulkenberg, 9, 16, "Nico Hulkenberg Bio"));
                                    INSTANCE.driverDao().insert(new Driver("Kevin", "Magnussen", "MoneyGram Haas F1 Team", 31, "Denmark", "20", R.drawable.magnussen, 3, 19, "Kevin Magnussen Bio"));

                                });
                            }
                        })
                        .fallbackToDestructiveMigration() // schema updates don't have to be migrated
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}
