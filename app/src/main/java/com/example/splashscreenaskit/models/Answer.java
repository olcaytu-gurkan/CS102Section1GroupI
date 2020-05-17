package com.example.splashscreenaskit.models;

public class Answer
{

    //Properties
    String answer;
    String ansNum;

    //Constructor
    public Answer( String answer, String ansNum)
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

    public String getAnsNum()
    {
        return ansNum;
    }

    public void setAnsNum(String ansNum)
    {
        this.ansNum = ansNum;
    }
}
