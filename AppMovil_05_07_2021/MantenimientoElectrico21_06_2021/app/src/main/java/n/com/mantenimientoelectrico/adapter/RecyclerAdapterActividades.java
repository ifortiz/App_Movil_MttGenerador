package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.R;


public class RecyclerAdapterActividades extends RecyclerView.Adapter<RecyclerAdapterActividades.ViewHolder> {
    Context context;
    List<Actividad> listActividades;

    public RecyclerAdapterActividades(List<Actividad> listActividades, Context context) {
        this.context=context;
        this.listActividades=listActividades;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_recycler_simplelist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Actividad actividad=listActividades.get(position);
        if(actividad!=null && actividad.getDescripcion()!=null){
            holder.txt_actividad_descripcion.setText(context.getString(R.string.recycler_activiad_descripcion,actividad.getDescripcion()));
            if(actividad.getPermitivo().isEmpty())
                holder.txt_actividad_permitivo.setVisibility(View.GONE);
            else
                 holder.txt_actividad_permitivo.setText(context.getString(R.string.recycler_actividad_permitivo,actividad.getPermitivo()));

            holder.txt_actividad_numeroactividad.setText(context.getString(R.string.recycler_numeroactividad,actividad.getNumeroAct()));

            if(actividad.getDetalleActividad()!=null && actividad.getDetalleActividad().size()>0){

                holder.recyclerViewDetalleactividad.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                RecyclerAdapterDetalleActividad adapter=new RecyclerAdapterDetalleActividad(actividad.getDetalleActividad(),context);
                holder.recyclerViewDetalleactividad.setLayoutManager(linearLayoutManager);
                holder.recyclerViewDetalleactividad.setAdapter(adapter);
            }
        }else{
            holder.constraintLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return listActividades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_actividad_descripcion,txt_actividad_permitivo,txt_actividad_numeroactividad;
        ConstraintLayout constraintLayout;
        RecyclerView recyclerViewDetalleactividad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_actividad_descripcion=itemView.findViewById(R.id.txt_actividad_descripcion);
            txt_actividad_permitivo=itemView.findViewById(R.id.txt_actividad_permitivo);
            txt_actividad_numeroactividad=itemView.findViewById(R.id.txt_actividad_numeroactividad);
            recyclerViewDetalleactividad=itemView.findViewById(R.id.recyclerDetalleActividad);
            constraintLayout=itemView.findViewById(R.id.view);

        }
    }
}
