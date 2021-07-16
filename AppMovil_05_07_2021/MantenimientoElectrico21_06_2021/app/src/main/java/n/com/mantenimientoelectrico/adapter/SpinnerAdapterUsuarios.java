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

import n.com.mantenimientoelectrico.model.Usuario;
import n.com.mantenimientoelectrico.R;
import n.com.mantenimientoelectrico.listener.spinners.SpinnersActions;

public class SpinnerAdapterUsuarios extends ArrayAdapter<Usuario> {
    LayoutInflater layoutInflater;
    SpinnersActions listener;
    public SpinnerAdapterUsuarios(@NonNull Context context, int resource, @NonNull List<Usuario> users,SpinnersActions listener) {
        super(context, resource, users);
        layoutInflater=LayoutInflater.from(context);
        this.listener=listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView=layoutInflater.inflate(R.layout.adapter_spinner_myselected_item,null,true);
        Usuario user=getItem(position);
        TextView textView=rootView.findViewById(R.id.name_spinner);
        //textView.setText(user.getNombre());
        textView.setText(parent.getContext().getString(R.string.spiner_usuarios_name_lastname_selected,user.getNombre(),user.getApellido()));
        listener.onUsuarioSelected(user.getId());
        return rootView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=layoutInflater.inflate(R.layout.adapter_dropdown_item,parent,false);
        Usuario user=getItem(position);
        TextView textView=convertView.findViewById(R.id.name_spinner);

        textView.setText(parent.getContext().getString(R.string.spiner_usuarios_name_lastname,(position+1),user.getNombre(),user.getApellido()));
        return convertView;
    }
}
