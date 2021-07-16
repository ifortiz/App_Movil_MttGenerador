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

import n.com.mantenimientoelectrico.model.Unidad;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.listener.spinners.SpinnersActions;

public class SpinnerAdapterUnidad extends ArrayAdapter<Unidad> {
    LayoutInflater layoutInflater;
    SpinnersActions listener;
    public SpinnerAdapterUnidad(@NonNull Context context, int resource, @NonNull List<Unidad> unidads, SpinnersActions listener) {
        super(context, resource, unidads);
        layoutInflater=LayoutInflater.from(context);
        this.listener=listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView=layoutInflater.inflate(R.layout.adapter_spinner_myselected_item,null,true);
        Unidad unidad=getItem(position);
        TextView textView=rootView.findViewById(R.id.name_spinner);
        textView.setText(parent.getContext().getString(R.string.spiner_unidad_name_selected,unidad.getNombre()));
        listener.onUnidadSelected(unidad.getId());

        return rootView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=layoutInflater.inflate(R.layout.adapter_dropdown_item,parent,false);
        Unidad unidad=getItem(position);
        TextView textView=convertView.findViewById(R.id.name_spinner);

        textView.setText(parent.getContext().getString(R.string.spiner_unidad_name,(position+1),unidad.getNombre()));
        return convertView;
    }
}
