package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import n.com.mantenimientoelectrico.model.Actividad;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.network.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecyclerEquipoAdapter extends RecyclerView.Adapter<RecyclerEquipoAdapter.ViewHolder> {
    Context context;
    List<Actividad> listActividades;

    public RecyclerEquipoAdapter(List<Actividad> listActividades, Context context) {
        this.context=context;
        this.listActividades=listActividades;
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_recycler_actividades,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Actividad actividad = listActividades.get(position);
        holder.txt_unidad.setText(context.getString(R.string.recycler_unidad,actividad.getEquipoGeneral().getUnidad().getNombre()));
        holder.txt_num_ot.setText(context.getString(R.string.recycler_ot, actividad.getEquipoGeneral().getOt()));
        holder.txt_fecha_ot.setText(actividad.getEquipoGeneral().getFecha());
        holder.txt_responsable.setText(context.getString(R.string.recycler_responsable, actividad.getEquipoGeneral().getUsuario().getNombre(), actividad.getEquipoGeneral().getUsuario().getApellido()));
        if (actividad.getEquipoGeneral().getReporte() != null) {
            holder.txt_condiciones.setText(actividad.getEquipoGeneral().getReporte().getCondicion());
            holder.txt_modificaciones.setText(actividad.getEquipoGeneral().getReporte().getModificacion());
            holder.txt_pendientes.setText(actividad.getEquipoGeneral().getReporte().getPendiente());
        }


        holder.cardView.setOnClickListener(v -> {

            APIClient.getClient().createServiceApi().listarActividadesByOt(actividad.getEquipoGeneral().getOt()).enqueue(new Callback<List<Actividad>>() {
                @Override
                public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        RecyclerAdapterActividades adapter = new RecyclerAdapterActividades(response.body(), context);
                        holder.recyclerActvidades.setLayoutManager(linearLayoutManager);
                        holder.recyclerActvidades.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Actividad>> call, Throwable t) {

                }
            });
            if (!actividad.isShowExpandable()) {
                holder.constraintLayout.setVisibility(View.VISIBLE);
                actividad.setShowExpandable(true);
            } else {
                holder.constraintLayout.setVisibility(View.GONE);
                actividad.setShowExpandable(false);
            }
        });
         }



    @Override
    public int getItemCount() {
        return listActividades.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_num_ot,txt_fecha_ot,txt_unidad,txt_responsable,txt_modificaciones,txt_condiciones,txt_pendientes;
        ConstraintLayout constraintLayout;
        CardView cardView;
        RecyclerView recyclerActvidades;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_num_ot=itemView.findViewById(R.id.num_ot);
            txt_fecha_ot=itemView.findViewById(R.id.fecha);
            txt_unidad=itemView.findViewById(R.id.unidad);
            txt_responsable=itemView.findViewById(R.id.responsable);
            txt_modificaciones=itemView.findViewById(R.id.text_modificaciones);
            txt_condiciones=itemView.findViewById(R.id.text_condiciones);
            txt_pendientes=itemView.findViewById(R.id.text_pendientes);
            constraintLayout=itemView.findViewById(R.id.showExpandable);
            cardView=itemView.findViewById(R.id.clickview);
            recyclerActvidades=itemView.findViewById(R.id.recyclerActvidades);
        }
    }
}
