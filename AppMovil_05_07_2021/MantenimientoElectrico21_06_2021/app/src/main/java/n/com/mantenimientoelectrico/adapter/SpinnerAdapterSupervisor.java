package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.listener.spinners.SupervisorListener;

public class SpinnerAdapterSupervisor extends ArrayAdapter<String>{
    LayoutInflater layoutInflater;
    SupervisorListener listener;
    public SpinnerAdapterSupervisor(@NonNull Context context, int resource, @NonNull List<String> supervisores, SupervisorListener listener) {
        super(context, resource, supervisores);
        layoutInflater=LayoutInflater.from(context);
        this.listener=listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView=layoutInflater.inflate(R.layout.adapter_spinner_myselected_item,null,true);
        String supervisor=getItem(position);
        TextView textView=rootView.findViewById(R.id.name_spinner);
        textView.setText(supervisor);
        listener.onSupervisorSelected(supervisor);

        return rootView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=layoutInflater.inflate(R.layout.adapter_dropdown_item,parent,false);
        String supervisor=getItem(position);
        TextView textView=convertView.findViewById(R.id.name_spinner);

        textView.setText(supervisor);
        return convertView;
    }
}



