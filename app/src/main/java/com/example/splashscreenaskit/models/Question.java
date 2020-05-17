package com.example.splashscreenaskit.models;



import java.util.ArrayList;

public class Question
{
    private String question;
    private ArrayList<Answer> answers;
    private ArrayList<String> tags;

    //Constructor
    public Question(String question, ArrayList<Answer> answers, ArrayList<String> tag )
    {
        this.question = question;
        this.answers = answers;
        this.tags = tag;
    }
    public Question(){
        tags = new ArrayList<>();
        answers = new ArrayList<>();
    }

    //Some get and set methods

    public void setQuestion( String newQuestion )
    {
        question = newQuestion;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getTags()
    {
        //Local variable
        String s;

        s = "";
        if ( tags.size() > 0 )
        {
            for (String st : tags)
            {
                s = s + " " + st;
            }
            return s;
        }
        else return  "";
    }

    public ArrayList<String> getTagsList() {
        return tags;
    }

    public void setTags(ArrayList<String> tags)
    {
        this.tags = tags;
    }

    public ArrayList<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers)
    {
        this.answers = answers;
    }
}
