package com.example.bustec;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
Context mcontext;
List<item> mData;

    public Adapter(Context context, List<item> mData) {
        this.mcontext = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View v=inflater.inflate(R.layout.card_items,viewGroup,false);
        return  new myViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {

        myViewHolder.background_img.setImageResource(mData.get(i).getBackground());
        myViewHolder.profile_photo.setImageResource(mData.get(i).getProfilephoto());
        myViewHolder.tv_title.setText(mData.get(i).getProfileName());
        myViewHolder.tv_nb_follower.setText(mData.get(i).getNbflollowers());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends  RecyclerView.ViewHolder{

        ImageView profile_photo,background_img;
        TextView tv_title,tv_nb_follower;




    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        profile_photo=itemView.findViewById(R.id.profile_img);
        background_img=itemView.findViewById(R.id.card_backgound);
        tv_title=itemView.findViewById(R.id.card_title);
        tv_nb_follower=itemView.findViewById(R.id.card_nb_follower);
    }
}
}
