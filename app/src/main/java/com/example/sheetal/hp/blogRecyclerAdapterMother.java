package com.example.sheetal.hp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class blogRecyclerAdapterMother extends RecyclerView.Adapter<blogRecyclerAdapterMother.ViewHolder> {

    private Context context;
    private EditText registerLoginEmail;
    private List<motherDetails> blogList;

    public blogRecyclerAdapterMother(Context context, List<motherDetails> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mn;
        public TextView ma;
        public TextView mdob;
        public TextView med;
        public TextView md;
        public TextView mfn;

        String userid;

        public ViewHolder(View view,Context ctx ) {
            super(view);
            context = ctx;

            mn = view.findViewById(R.id.MotherdName);
            ma = view.findViewById(R.id.mAddress);
            mdob = view.findViewById(R.id.MDOB);
            med = view.findViewById(R.id.motherexpectedDate);
            md = view.findViewById(R.id.mDescp);
            mfn = view.findViewById(R.id.MotherFatherName);
            registerLoginEmail = view.findViewById(R.id.registerLoginEmail);
            userid = null;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(this, "Hello world!!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    @Override
    public blogRecyclerAdapterMother.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postrowmother,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(blogRecyclerAdapterMother.ViewHolder holder, int position) {

       // blog blogg = blogList.get(position);
        motherDetails details = blogList.get(position);

        holder.mn.setText("Mother Name - " + details.getMotherName());
        holder.mfn.setText("Father Name - " + details.getMotherFatherName());
        holder.mdob.setText("Age - " + details.getMotherAge());
        holder.ma.setText("Address - " + details.getAddress());
        holder.med.setText("Expecting Date - " + details.getExpectingDate());
        holder.md.setText("Email Id - " + details.getDesp());

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
}

