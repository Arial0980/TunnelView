package com.example.myapplication;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.atomic.AtomicReference;

public class FireBaseController {
    private static FireBaseController instance;
    private boolean alreadyAutoControlled=false;
    private FirebaseDatabase database= FirebaseDatabase.getInstance("https://esp-connection-to-altera-default-rtdb.europe-west1.firebasedatabase.app/");
    private FireBaseController() {
    }
    public static FireBaseController getInstance() {
        if (instance == null) {
            instance = new FireBaseController();
        }
        return instance;
    }

    public void getVideo() {
        database.getReference("video").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String video = task.getResult().getValue(String.class);
            }
        });

    }
    public void sendAutoControl(){

        if (alreadyAutoControlled) {
            database.getReference("to_altera").setValue(0);
            alreadyAutoControlled=false;
        }
        else {
            database.getReference("to_altera").setValue(32);
            alreadyAutoControlled=true;
        }
    }

    public Long getStraightCoordinate() {
        long straightCoordinate ;
        DatabaseReference myRef = database.getReference("dis1");
        Task<DataSnapshot> task = myRef.get();
        straightCoordinate=task.getResult().getValue(Long.class);
        return straightCoordinate;
    }

    public Long getLeftCoordinate() {
        long leftCoordinate;
        DatabaseReference myRef = database.getReference("dis2");
        Task<DataSnapshot> task = myRef.get();
        leftCoordinate=task.getResult().getValue(Long.class);
        return leftCoordinate;
    }
    public Long getRightCoordinate() {
        long rightCoordinate;
        DatabaseReference myRef = database.getReference("dis3");
        Task<DataSnapshot> task = myRef.get();
        rightCoordinate=task.getResult().getValue(Long.class);
        return rightCoordinate;
    }

}
