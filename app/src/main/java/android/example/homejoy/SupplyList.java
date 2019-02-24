package android.example.homejoy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SupplyList extends ArrayAdapter<Supply> {

    private Activity context;
    private List<Supply> supplyList;

    public SupplyList(Activity context, List<Supply> supplyList){
        super(context, R.layout.list_supply,supplyList);
        this.context = context;
        this.supplyList = supplyList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_supply,null,true);
        TextView supplyName = listViewItem.findViewById(R.id.suppName);
        TextView supplyAmount = listViewItem.findViewById(R.id.suppAmount);

        Supply supply = supplyList.get(position);

        supplyName.setText(supply.getSupp_name());
        supplyAmount.setText(supply.getSupply_amount());

        return listViewItem;
    }
}
