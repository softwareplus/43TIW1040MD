package com.example.belisolbokregistratieapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Client> listItems;
    private Context context;
    private ProgressDialog dialog;


    public MyAdapter(List<Client> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView uid;
        public TextView name;
        public TextView address;
        public TextView phone;
        public ImageView LandLogo;

        public CardView card_view;
        public ViewHolder(View itemView ) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            phone = (TextView) itemView.findViewById(R.id.phone);
            LandLogo = (ImageView) itemView.findViewById(R.id.landlogo);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Client listItem = listItems.get(position);
        holder.id.setText(listItem.getId());
        holder.name.setText(listItem.getName());
        holder.address.setText(listItem.getAddress());
        holder.phone.setText(listItem.getPhone());


        //TE HERSCHRIJVEN ASAP!!!
        //CODE TREKT OP NIKS

        if(listItem.getLand().equals("be"))
        {
            holder.LandLogo.setImageResource(R.drawable.be);
        }
        if(listItem.getLand().equals("nl"))
        {
            holder.LandLogo.setImageResource(R.drawable.nl);
        }
        if(listItem.getLand().equals("fr"))
        {
            holder.LandLogo.setImageResource(R.drawable.fr);
        }




        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent;

                Intent intent2 = new Intent(view.getContext(), EditActivity.class);
                intent2.putExtra("id", listItem.getId());
                intent2.putExtra("name",listItem.getName());
                intent2.putExtra("address",listItem.getAddress());
                intent2.putExtra("phone", listItem.getPhone());
                intent2.putExtra("opmerking", listItem.getOpmerking());
                view.getContext().startActivity(intent2);

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final ProgressDialog dialog = new ProgressDialog(view.getContext());
                dialog.setMessage("Loading Delete Data");
                final CharSequence[] dialogitem = {"Lezen","Wijzigen"};
                builder.setTitle(listItem.getName());
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            //bekijk fiche
                            case 0 :
                                Intent intent = new Intent(view.getContext(), DetailData.class);
                                //intent.putExtra("id", listItem.getId());
                                intent.putExtra("name",listItem.getName());
                                intent.putExtra("address",listItem.getAddress());
                                intent.putExtra("phone", listItem.getPhone());
                                intent.putExtra("opmerking", listItem.getOpmerking());
                                view.getContext().startActivity(intent);
                                break;
                            //edit fiche
                            //bedoeling is dat de expediteur wekelijks een update doorgeeft per kantoor
                            case 1 :
                                Intent intent2 = new Intent(view.getContext(), EditActivity.class);
                                intent2.putExtra("id", listItem.getId());
                                intent2.putExtra("name",listItem.getName());
                                intent2.putExtra("address",listItem.getAddress());
                                intent2.putExtra("phone", listItem.getPhone());
                                intent2.putExtra("opmerking", listItem.getOpmerking());
                                view.getContext().startActivity(intent2);
                                break;

                        }
                    }
                });



                builder.create().show();

                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
