package android.example.homejoy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSupply extends Fragment {
    public DatabaseReference myRef;
    public EditText supply_name,supply_amount;
    public Button mSubmit;
    public AddSupply() {
        // Required empty public constructor
    }

    public static AddSupply newInstance() {
        return new AddSupply();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_supply, container, false);
        myRef = FirebaseDatabase.getInstance().getReference("supply");
        mSubmit = view.findViewById(R.id.submit);
        supply_name = view.findViewById(R.id.supply_name);
        supply_amount = view.findViewById(R.id.amount);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSupply();
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    public void saveSupply(){
        String name = supply_name.getText().toString().trim();
        String amount = supply_amount.getText().toString().trim();
        Integer amount1 = Integer.parseInt(amount);
        String id = myRef.push().getKey();
        Supply supply = new Supply(id,name,amount1);
        myRef.child(id).setValue(supply);
        Toast.makeText(getActivity(),"Supply Saved!",Toast.LENGTH_LONG).show();
    }
}
