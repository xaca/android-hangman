package com.xacarana.hangman.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xacarana.hangman.R;
import com.xacarana.hangman.util.ListaItem;

public class ListaCategoriasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ListaItem[] items;
    private OnClickListener listener;
    public ListaCategoriasAdapter(ListaItem[] items) {
       this.items = items;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onClick(int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv;
        protected TextView tv;

        public ViewHolder(View view) {
            super(view);
             iv = (ImageView) view.findViewById(R.id.logo_categoria);
             tv = (TextView) view.findViewById(R.id.texto_categoria);
        }

        public TextView getTextView() {
            return tv;
        }
        public ImageView getImageView(){
            return iv;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = ((ViewHolder)holder);
        vh.setIsRecyclable(false);
        vh.getTextView().setText(items[position].getNombre());
        vh.getImageView().setImageResource(items[position].getImagen_id());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(vh.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
