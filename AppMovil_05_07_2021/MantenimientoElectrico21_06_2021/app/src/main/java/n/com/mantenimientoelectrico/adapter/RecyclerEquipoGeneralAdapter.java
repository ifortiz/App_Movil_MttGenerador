package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.R;

public class RecyclerEquipoGeneralAdapter extends RecyclerView.Adapter<RecyclerEquipoGeneralAdapter.ViewHolder> {
    Context context;
    List<Actividad> listActividades;
    List<Actividad> listActividadesRecyclerInner;

    public RecyclerEquipoGeneralAdapter(List<Actividad> listActividades, Context context) {
        this.context=context;
        this.listActividades=listActividades;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_recycler_actividades_equipogeneral,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Actividad actividad=listActividades.get(position);
        String fecha=actividad.getFcreacion().substring(0,10);
        if(actividad!=null && actividad.getDescripcion()!=null){
            holder.txt_actividad_descripcion.setText(context.getString(R.string.recycler_activiad_descripcion,actividad.getDescripcion()));
            holder.txt_actividad_permitivo.setText(context.getString(R.string.recycler_actividad_permitivo,actividad.getPermitivo()));
            holder.txt_actividad_numeroactividad.setText(context.getString(R.string.recycler_numeroactividad,actividad.getNumeroAct()));
            holder.txt_actividad_fecha.setText(fecha);

        }else{
            holder.cardView.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return listActividades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_actividad_descripcion,txt_actividad_permitivo,txt_actividad_numeroactividad,txt_actividad_fecha;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_actividad_descripcion=itemView.findViewById(R.id.txt_actividad_descripcion);
            txt_actividad_permitivo=itemView.findViewById(R.id.txt_actividad_permitivo);
            txt_actividad_numeroactividad=itemView.findViewById(R.id.txt_actividad_numeroactividad);
            txt_actividad_fecha=itemView.findViewById(R.id.txt_actividad_fecha);
            cardView=itemView.findViewById(R.id.clickview);
        }
    }
}
