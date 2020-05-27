package com.example.splashscreenaskit.models;

public class Answer
{
    //Properties
    private String answer;
    private int ansNum;

    //Constructor
    public Answer( String answer, int ansNum)
    {
        this.answer = answer;
        this.ansNum = ansNum;
    }

    //Some get and set methods
    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public int getAnsNum()
    {
        return ansNum;
    }

    public void setAnsNum( int ansNum)
    {
        this.ansNum = ansNum;
    }
}
