package com.example.splashscreenaskit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreenaskit.models.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private Context context;//
    private ArrayList<Question> mDataset;//

    //Constructor
    public QuestionAdapter(Context c, ArrayList<Question> questions )
    {
        this.context = c;
        this.mDataset = questions;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Question, QuestNum, NumOfAns,tags;

        public MyViewHolder(View v) {
            super(v);
            Question = itemView.findViewById(R.id.Question);
            QuestNum = itemView.findViewById(R.id.QuestNum);
            tags = itemView.findViewById(R.id.tags);
            NumOfAns = itemView.findViewById(R.id.NumOfAns);

           v.setOnClickListener( this);
        }
        // When a question is clicked the user is directed to the question
        // screen displaying that question with its answers
        public void onClick( View v)
        {
            Intent intent = new Intent( context, QuestionScreen.class);
            intent.putExtra( "Questions", QuestNum.getText().toString());
            context.startActivity( intent);
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflator = LayoutInflater.from(context);
        // create a new view
        View v =  inflator.inflate(R.layout.activity_question, parent, false);
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
        holder.NumOfAns.setText( Integer.toString(newQuestionRecyclerView.getNumOfAns()));
        holder.QuestNum.setText(  newQuestionRecyclerView.getQuestNum());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }
}
