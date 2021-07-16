package n.com.mantenimientoelectrico.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.List;

import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.Utils.Util;
import n.com.mantenimientoelectrico.model.Equipo;
import n.com.mantenimientoelectrico.model.Tablero;
import n.com.mantenimientoelectrico.model.UnidadesMedida;

public class RecyclerAdapterTermico extends RecyclerView.Adapter<RecyclerAdapterTermico.ViewHolder> {
    private Context context;
    private List<Tablero> listTableros;
    private List<Equipo>  listEquipo;
    private static final String NAME_IDMOTOR = "idMotor";
    private static final String NAME_MARCA = "marca";
    private static final String NAME_RANGO_DESDE = "Rango desde";
    private static final String NAME_RANGO_HASTA = "Rango hasta";
    private static final String NAME_CAL_ANTERIOR = "Calculo anterior";
    private static final String NAME_CAL_ACTUAL = "Calculo actual";
    private static final String NAME_VECES_IR_150 = "Veces ir (50%)";
    private static final String NAME_VECES_IR_200 = "Veces ir (100%)";
    private static final String NAME_TIEMPO_150 = "Tiempo de disparo (50%)";
    private static final String NAME_TIEMPO_200 = "Tiempo de disparo (100%)";

    public RecyclerAdapterTermico(List<Tablero> listTableros,List<Equipo>equipos, Context context) {
        this.context=context;
        this.listTableros=listTableros;
        this.listEquipo = equipos;
    }

    @NonNull
    @Override
    public RecyclerAdapterTermico.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_termico_motor,parent,false);
        return new RecyclerAdapterTermico.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTermico.ViewHolder holder, int position) {
        if(listEquipo.size() > 0  ) {
            SpinnerAdapterReporteEquipo adapterEquipo = new SpinnerAdapterReporteEquipo(context, listEquipo);
            holder.editCodigoEquipo.setAdapter(adapterEquipo);
            holder.editCodigoEquipo.requestFocus();
            holder.editCodigoEquipo.showDropDown();
        }

        holder.fabSave.setOnClickListener(v -> {
            Util.showAlertDialogWithOnlyOk("Guardado","Se ha guardado correctamente ",context);
            listTableros.get(position).setSave(true);
            holder.cardItem.setLayoutParams (new RecyclerView.LayoutParams(0, 0));
            holder.cardItem.setVisibility(View.GONE);

        });
        holder.editCodigoEquipo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listTableros.get(position).setIdMotor(s.toString(),NAME_IDMOTOR); }
            @Override
            public void afterTextChanged(Editable s) { }});

        holder.editMarca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listTableros.get(position).setMarca(s.toString(),NAME_MARCA);
            }
            @Override
            public void afterTextChanged(Editable s) { }});

        holder.editRangoDesde.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listTableros.get(position).setRangoDesde(context.getString(R.string.detalleactividad_unidad_medida,s.toString(),
                                                                                    UnidadesMedida.Amperios),NAME_RANGO_DESDE);
            }
            @Override
            public void afterTextChanged(Editable s) { }});

        holder.editRangoAsta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listTableros.get(position).setRangoHasta(context.getString(R.string.detalleactividad_unidad_medida,s.toString(),
                                                                                    UnidadesMedida.Amperios),NAME_RANGO_HASTA);
            }
            @Override
            public void afterTextChanged(Editable s) { }});

        holder.editCalAnterior.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {   }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listTableros.get(position).setCalAnterior(context.getString(R.string.detalleactividad_unidad_medida,s.toString(),
                                                                                        UnidadesMedida.Amperios),NAME_CAL_ANTERIOR);
            }
            @Override
            public void afterTextChanged(Editable s) {  }});

        holder.editCalActual.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String data = s.toString().trim();
                if ( !data.isEmpty()) {
                    listTableros.get(position).setCalActual(context.getString(R.string.detalleactividad_unidad_medida,data
                                                                                        ,UnidadesMedida.Amperios), NAME_CAL_ACTUAL);
                    String data150 = String.valueOf(Double.parseDouble(data) * 1.5);
                    String data200 = String.valueOf(Double.parseDouble(data) * 2);

                    holder.editVecesIr150.setText(data150 );
                    holder.editVecesIr200.setText(data200 );
                    listTableros.get(position).setVecesIr150(context.getString(R.string.detalleactividad_unidad_medida,data150
                                                                                                ,UnidadesMedida.Amperios),NAME_VECES_IR_150);
                    listTableros.get(position).setVecesIr200(context.getString(R.string.detalleactividad_unidad_medida,data200
                            ,                                                                   UnidadesMedida.Amperios),NAME_VECES_IR_200);
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }});

        holder.editTiempoDisparo150.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    listTableros.get(position).setTiempoDisparo150(context.getString(R.string.detalleactividad_unidad_medida, s.toString()
                            , UnidadesMedida.segundos), NAME_TIEMPO_150);
                    if (Double.parseDouble(s.toString()) > 240 || Double.parseDouble(s.toString()) == 0)
                        holder.editVecesIr150.setBackgroundColor(context.getResources().getColor(R.color.editext_error));
                    else
                        holder.editVecesIr150.setBackgroundColor(context.getResources().getColor(R.color.editext_success));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {   }});

        holder.editTiempoDisparo200.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    listTableros.get(position).setTiempoDisparo200(context.getString(R.string.detalleactividad_unidad_medida, s.toString()
                            , UnidadesMedida.segundos), NAME_TIEMPO_200);
                    if (Double.parseDouble(s.toString()) > 60 || Double.parseDouble(s.toString()) == 0)
                        holder.editVecesIr200.setBackgroundColor(context.getResources().getColor(R.color.editext_error));
                    else
                        holder.editVecesIr200.setBackgroundColor(context.getResources().getColor(R.color.editext_success));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {   }});
    }
    public List<Tablero> getItems(){
    return listTableros;
    }

    @Override
    public int getItemCount() {
        return listTableros.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        EditText editMarca,editRangoDesde,editRangoAsta,editCalAnterior,editCalActual;
        EditText editVecesIr150,editVecesIr200,editTiempoDisparo150,editTiempoDisparo200;
        MaterialAutoCompleteTextView editCodigoEquipo;
        ConstraintLayout cardItem;
        FloatingActionButton fabSave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        editCodigoEquipo = itemView.findViewById(R.id.codigo_de_equipo);
        editMarca = itemView.findViewById(R.id.marca_de_termico);
        editRangoDesde = itemView.findViewById(R.id.valoDesde_rango);
        editRangoAsta = itemView.findViewById(R.id.valorAsta_rango);
        editCalAnterior = itemView.findViewById(R.id.valor_calAnt);
        editCalActual = itemView.findViewById(R.id.valor_CalAct);
        editVecesIr150 = itemView.findViewById(R.id.valor_50_corriente);
        editVecesIr200 = itemView.findViewById(R.id.valor_100_corriente);
        editTiempoDisparo150 = itemView.findViewById(R.id.valor_50_Tiempo);
        editTiempoDisparo200 = itemView.findViewById(R.id.valor_100_Tiempo);
        cardItem = itemView.findViewById(R.id.card_item);
        fabSave = itemView.findViewById(R.id.fab_save);
        }
    }
}
