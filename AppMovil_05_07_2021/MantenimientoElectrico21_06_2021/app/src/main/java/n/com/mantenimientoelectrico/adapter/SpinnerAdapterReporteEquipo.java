package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.R;


public class SpinnerAdapterReporteEquipo  extends ArrayAdapter<Equipo> {
    LayoutInflater layoutInflater;

    List<Equipo> listEquipos;

    public SpinnerAdapterReporteEquipo(@NonNull Context context, @NonNull List<Equipo> listEquipos) {
        super(context, 0, listEquipos);
        this.listEquipos = new ArrayList<>(listEquipos);

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return equipoFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.item_listequipogeneral_reporte,parent,false);
        TextView textView=convertView.findViewById(R.id.name_spinner);
        Equipo equipo=getItem(position);
        if(equipo!=null){ ;
            textView.setText(parent.getContext().getString(R.string.spinner_equipogeneral_ot_filter,equipo.getIdcod(),equipo.getDescripcion()));
        }


        return convertView;

    }

    private Filter equipoFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Equipo> suggestion = new ArrayList<>();
            if (constraint == null || constraint.length() ==0) {
                Log.d("data","null");
                suggestion.addAll(listEquipos);
            } else {
                String filterPattern = constraint.toString();
                for (Equipo item : listEquipos) {
                    if (Long.toString(item.getIdcod()).contains(filterPattern)) {
                        suggestion.add(item);
                        Log.d("data",item.getIdcod()+"");
                    }
                }
                if(suggestion.size()==0){
                    for (Equipo item : listEquipos) {
                        if (item.getDescripcion().contains(filterPattern)) {
                            suggestion.add(item);
                            Log.d("data",item.getIdcod()+"");
                        }
                    }
                }
            }
            results.values = suggestion;
            results.count = suggestion.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            if(results.values!=null){
                addAll((List) results.values);
                notifyDataSetChanged();
            }

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {

            return Long.toString(((Equipo) resultValue).getIdcod());
        }
    };
}
