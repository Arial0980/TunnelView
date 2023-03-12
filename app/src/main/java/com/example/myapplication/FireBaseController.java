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


}
