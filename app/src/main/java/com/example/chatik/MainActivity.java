package com.example.chatik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.net.HttpCookie;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://chatik-1326b-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("messages");
    EditText ET;
    Button b;
    RecyclerView RV;
    ArrayList<String> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET = findViewById(R.id.MI);
        b = findViewById(R.id.MB);
        RV = findViewById(R.id.MR);
        RV.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter dataAdapter = new DataAdapter(this,messages);
        RV.setAdapter(dataAdapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = ET.getText().toString();
                if(msg.equals("")){
                    Toast.makeText(getApplicationContext(),"Введите сообщение!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(msg.length()>150){
                    Toast.makeText(getApplicationContext(),"Сообщение слишком длинное!",Toast.LENGTH_SHORT).show();
                    return;
                }
                myRef.push().setValue(msg);
                ET.setText("");
            }
        });
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String msg = snapshot.getValue(String.class);
                        messages.add(msg);
                        dataAdapter.notifyDataSetChanged();
                        RV.smoothScrollToPosition(messages.size());
                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}