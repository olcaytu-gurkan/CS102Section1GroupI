package com.example.splashscreenaskit;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreenaskit.models.Answer;

import java.util.ArrayList;

/**
 * This is an recycler view adapter for the Answer object which allows the answers to be displayed in a list
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder>
{

    private Context context;
    private ArrayList<Answer> mDataset;

    //Constructor
    public AnswerAdapter(Context c, ArrayList<Answer> questions )
    {
        this.context = c;
        this.mDataset = questions;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView answer, ansNum;

        public MyViewHolder(View v) {
            super(v);
            answer = itemView.findViewById(R.id.answer);
            ansNum = itemView.findViewById(R.id.ansNum);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflator = LayoutInflater.from(context);
        // create a new view
        View v =  inflator.inflate(R.layout.activity_answer_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Answer answers = this.mDataset.get(position);
        holder.answer.setText(answers.getAnswer());
        holder.ansNum.setText(Integer.toString(answers.getAnsNum())); //Error here----------------- fixed
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }
}

