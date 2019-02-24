package android.example.homejoy;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button mAddSupply;
    ListView listSupply;
    DatabaseReference myRef;
    List<Supply> supplyList ;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRef = FirebaseDatabase.getInstance().getReference("supply");
        listSupply = findViewById(R.id.listsupply);
        mAddSupply = findViewById(R.id.addsupply);
        supplyList = new ArrayList<>();
        mAddSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragment();
            }
        });
        // Write a message to the database

    }

    public void displayFragment(){
        AddSupply addsupply = AddSupply.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.fragment,
                addsupply).addToBackStack(null).commit();
        // Set boolean flag to indicate fragment is open.
    }

    public void onStart(){
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                supplyList.clear();
                for (DataSnapshot supplySnapshot : dataSnapshot.getChildren()){
                    Supply supply = supplySnapshot.getValue(Supply.class);
                    supplyList.add(supply);
                }

                SupplyList adapter = new SupplyList(MainActivity.this,supplyList);
                listSupply.setAdapter(adapter);
                listSupply.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
