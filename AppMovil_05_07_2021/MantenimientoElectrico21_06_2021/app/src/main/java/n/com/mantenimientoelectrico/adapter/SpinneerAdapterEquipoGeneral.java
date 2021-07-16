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
import n.com.mantenimientoelectrico.model.EquipoGeneral;
import n.com.mantenimientoelectrico.R;

public class SpinneerAdapterEquipoGeneral  extends ArrayAdapter<EquipoGeneral> {
    LayoutInflater layoutInflater;
    List<EquipoGeneral> listEquipogeneral;

    public SpinneerAdapterEquipoGeneral(@NonNull Context context, @NonNull List<EquipoGeneral> listEquipos) {
        super(context, 0, listEquipos);
        listEquipogeneral = new ArrayList<>(listEquipos);
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
        EquipoGeneral equipoGeneral=getItem(position);
        if(equipoGeneral!=null){
            String ot=equipoGeneral.getOt()+"";
            textView.setText(ot);
        }


        return convertView;

    }

    private Filter equipoFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<EquipoGeneral> suggestion = new ArrayList<>();
            if (constraint == null || constraint.length() ==0) {
                Log.d("data","null");
                suggestion.addAll(listEquipogeneral);
            } else {
                String filterPattern = constraint.toString();
                for (EquipoGeneral item : listEquipogeneral) {
                    if (Long.toString(item.getOt()).contains(filterPattern)) {
                        suggestion.add(item);
                        Log.d("data",item.getOt()+"");
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
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {

            return Long.toString(((EquipoGeneral) resultValue).getOt() );
        }
    };
}
