package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import n.com.mantenimientoelectrico.model.DetalleActividad;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.network.APIClient;
import n.com.mantenimientoelectrico.viewmodel.EquipoGeneralModel;
import n.com.mantenimientoelectrico.viewmodel.EquipoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterDetalleActividad extends RecyclerView.Adapter<RecyclerAdapterDetalleActividad.ViewHolder> {
    Context context;
    List<DetalleActividad> listDetalleActividad;


    public RecyclerAdapterDetalleActividad(List<DetalleActividad> listDetalleActividad, Context context) {
        this.context=context;
        this.listDetalleActividad=listDetalleActividad;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_recycler_detalleactividad,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetalleActividad detalleActividad=listDetalleActividad.get(position);

        if(detalleActividad!=null){
            String [] data;
            if(detalleActividad.getNombre().contains("@")){
                data=detalleActividad.getNombre().split("@");
                holder.txt_descripcion_detalleactividad.setText(context.getString(R.string.recycler_detalleactiviad_descripcion,data[0]));
                holder.txt_valor_detalleactividad.setText(context.getString(R.string.recycler_detalleactividad_valor_prefijo, data[1],detalleActividad.getValorPrueba()));
            }else {
                if( detalleActividad.getNombre().equals("idMotor")){
                    Toast.makeText(context,detalleActividad.getValorPrueba(),Toast.LENGTH_SHORT).show();
                    APIClient.getClient().createServiceApi().checkEquipoExits(Long.parseLong(detalleActividad.getValorPrueba())).enqueue(new Callback<Equipo>() {
                        @Override
                        public void onResponse(Call<Equipo> call, Response<Equipo> response) {
                            if (response.isSuccessful() && response.body() != null) {
                            holder.txt_descripcion_detalleactividad.setText("EL SIGUIENTE TERMICO  PERTENECE AL MOTOR :"+response.body().getDescripcion());
                            holder.txt_valor_detalleactividad.setText("modelo: "+response.body().getModelo() +" potencia: "+response.body().getPotencia()+" serie: "+response.body().getSerie());
                            }
                        }

                        @Override
                        public void onFailure(Call<Equipo> call, Throwable t) { }});

                }else {
                    holder.txt_descripcion_detalleactividad.setText(context.getString(R.string.recycler_detalleactiviad_descripcion, detalleActividad.getNombre()));
                    holder.txt_valor_detalleactividad.setText(context.getString(R.string.recycler_detalleactividad_valor, detalleActividad.getValorPrueba()));
                }
            }

        }

    }

    @Override
    public int getItemCount() {
        return listDetalleActividad.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_descripcion_detalleactividad,txt_valor_detalleactividad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_descripcion_detalleactividad=itemView.findViewById(R.id.txt_descripcion_detalleactividad);
            txt_valor_detalleactividad=itemView.findViewById(R.id.txt_valor_detalleactividad);


        }
    }
}
