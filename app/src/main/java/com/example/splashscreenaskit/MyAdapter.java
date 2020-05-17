package com.example.splashscreenaskit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreenaskit.models.Question;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;//
    private ArrayList<Question> mDataset;//

    //Constructor
    public MyAdapter( Context c, ArrayList<Question> questions )
    {
        this.context = c;
        this.mDataset = questions;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Question, QuestNum, NumOfAns,tags;

        public MyViewHolder(View v) {
            super(v);
            Question = itemView.findViewById(R.id.Question);
            QuestNum = itemView.findViewById(R.id.QuestNum);
            tags = itemView.findViewById(R.id.tags);
            NumOfAns = itemView.findViewById(R.id.NumOfAns);
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_question, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Question newQuestionRecyclerView = this.mDataset.get(position);
        holder.Question.setText( newQuestionRecyclerView.getQuestion());
        holder.tags.setText( newQuestionRecyclerView.getTags());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }
}
