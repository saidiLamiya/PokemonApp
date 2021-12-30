package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.Interface.ItemClickListener;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonByType;

import java.util.ArrayList;
import java.util.Collection;

public class PokemonByTypeListAdapter extends RecyclerView.Adapter<PokemonByTypeListAdapter.ViewHolder> implements Filterable{
    private ArrayList<Pokemon> dataset2;
    private ArrayList<PokemonByType> dataset;
    private Context context;
    private ArrayList<PokemonByType> PokemonListAll;
    public PokemonByTypeListAdapter(Context context){
        dataset = new ArrayList<>();
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonByType pk = dataset.get(position);
        Pokemon p = pk.getPokemon();

        holder.nombreTextView.setText(p.getName());
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onPokemonClick(View view, int position) {
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num", dataset.get(position).getPokemon().getNumber() ));
            }
        });
   }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    //ajouter la list de pokemon par leurs type
    public void AddByType(ArrayList<PokemonByType> listPokemon){
        dataset.addAll(listPokemon);
        this.PokemonListAll = new ArrayList<>(listPokemon);
        notifyDataSetChanged();
    }



    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<PokemonByType> filteredListByType = new ArrayList<>();
            ArrayList<Pokemon> filteredList =new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredListByType.addAll(PokemonListAll);
            } else {

                for (PokemonByType pokemon : PokemonListAll) {
                     Pokemon pokemons = pokemon.getPokemon();
                    if (pokemons.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(pokemons);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataset.clear();
            dataset.addAll((Collection<? extends PokemonByType>) filterResults.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView fotoImageView;
        private TextView nombreTextView;
        ItemClickListener itemClickListener;


        @Override
        public void onClick(View view) {
            itemClickListener.onPokemonClick(view, getAdapterPosition());
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ViewHolder(View itemView){
            super(itemView);
            fotoImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            itemView.setOnClickListener(this);
        }
    }
}
