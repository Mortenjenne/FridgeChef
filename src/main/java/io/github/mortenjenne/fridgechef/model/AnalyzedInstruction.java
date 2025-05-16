package io.github.mortenjenne.fridgechef.model;

import java.util.List;

public class AnalyzedInstruction {
    private String name;
    private List<InstructionStep> steps;

    public void setName(String name){
        this.name = name;
    }

    public void setSteps(List<InstructionStep> steps){
        this.steps = steps;
    }

    public String getName(){
        return this.name;
    }

    public List<InstructionStep> getSteps(){
        return this.steps;
    }

}
