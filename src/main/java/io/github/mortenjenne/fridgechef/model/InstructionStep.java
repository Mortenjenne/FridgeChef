package io.github.mortenjenne.fridgechef.model;

public class InstructionStep {
    private int number;
    private String step;

    public void setNumber(int number){
        this.number = number;
    }

    public void setStep(String step){
        this.step = step;
    }

    public int getNumber(){
        return this.number;
    }

    public String getStep(){
        return this.step;
    }
}
