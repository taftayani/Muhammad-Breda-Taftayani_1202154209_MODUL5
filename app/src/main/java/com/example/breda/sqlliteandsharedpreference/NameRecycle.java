package com.example.breda.sqlliteandsharedpreference;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.zip.Inflater;

/**
 * Created by Breda on 3/25/2018.
 */

public class NameRecycle extends RecyclerView.Adapter<NameRecycle.NameHolder> {

    private LinkedList<sqlModel> model;

    private LayoutInflater inflater;

    public NameRecycle(MainActivity mainActivity, LinkedList<sqlModel> sqlList) {
        inflater=LayoutInflater.from(mainActivity);
        this.model=sqlList;
    }

    @Override
    public NameRecycle.NameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem=inflater.inflate(R.layout.item_menu,parent,false);
        return new NameHolder(viewItem,this);
    }

    @Override
    public void onBindViewHolder(NameRecycle.NameHolder holder, int position) {
        holder.name.setText(model.get(position).getName());
        holder.desc.setText(model.get(position).getDesc());
        holder.prior.setText(model.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    class NameHolder extends RecyclerView.ViewHolder {
        public final TextView name,desc,prior;
        final NameRecycle list_name;


        public NameHolder(View itemView,NameRecycle list) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txtTitle);
            desc=(TextView)itemView.findViewById(R.id.txtDesc);
            prior=(TextView)itemView.findViewById(R.id.txtPriority);
            this.list_name=list;
        }
    }
}
