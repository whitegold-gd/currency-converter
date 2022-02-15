package com.example.currency.Presentation.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.currency.Presentation.Repository.Room.DAO.CurrencyDAO;
import com.example.currency.Presentation.Repository.Room.DTO.CurrencyDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CurrencyDTO.class}, version = 1, exportSchema = false)
public abstract class CurrencyRoomDatabase extends RoomDatabase {
    public abstract CurrencyDAO currencyDAO();

    private static volatile CurrencyRoomDatabase instance;
    private static final int NUMBER_OF_THREADS = 3;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CurrencyRoomDatabase getDatabase(Context context){
        if (instance == null){
            synchronized (CurrencyRoomDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        CurrencyRoomDatabase.class, "currency_database")
                        .allowMainThreadQueries().addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                            }
                        }).build();
            }
        }
        return instance;
    }
}
