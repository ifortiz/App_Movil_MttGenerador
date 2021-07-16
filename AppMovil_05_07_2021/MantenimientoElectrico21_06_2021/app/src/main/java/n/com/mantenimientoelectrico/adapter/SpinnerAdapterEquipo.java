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

import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.listener.spinners.EquipoListener;

public class SpinnerAdapterEquipo extends ArrayAdapter<Equipo> {
    LayoutInflater layoutInflater;
    EquipoListener listener;
    public SpinnerAdapterEquipo(@NonNull Context context, int resource, @NonNull List<Equipo> equipos, EquipoListener listener) {
        super(context, resource, equipos);
        layoutInflater=LayoutInflater.from(context);
        this.listener=listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView=layoutInflater.inflate(R.layout.adapter_spinner_myselected_item,null,true);

        Equipo equipo=getItem(position);
        TextView textView=rootView.findViewById(R.id.name_spinner);
        textView.setText(parent.getContext().getString(R.string.spiner_unidad_name_selected,equipo.getDescripcion()));
        listener.onEquipoSelected(equipo.getIdcod());

        return rootView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=layoutInflater.inflate(R.layout.adapter_dropdown_item,parent,false);
        Equipo equipo=getItem(position);
        TextView textView=convertView.findViewById(R.id.name_spinner);

        textView.setText(parent.getContext().getString(R.string.spiner_unidad_name,equipo.getIdcod(),equipo.getDescripcion()));
        return convertView;
    }
}

