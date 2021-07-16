package n.com.mantenimientoelectrico.adapter;


import android.graphics.pdf.PdfDocument;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

import n.com.mantenimientoelectrico.R;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>{
    List<String>titles,descriptions;
    public ViewPagerAdapter(List<String> titles, List<String> descriptions){
        this.titles=titles;
        this.descriptions=descriptions;
    }
    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sugerencias_viewpager,parent,false);
        return new PagerViewHolder(view
               );
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
        holder.txt_description.setText(descriptions.get(position));
        holder.txt_title.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class PagerViewHolder extends ViewHolder {
        TextView txt_title,txt_description;
            public PagerViewHolder(@NonNull View itemView) {
                super(itemView);
                txt_title=itemView.findViewById(R.id.title);
                txt_description=itemView.findViewById(R.id.body);
            }

        }



}