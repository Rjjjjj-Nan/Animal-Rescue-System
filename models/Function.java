package models;

public abstract class Function {
    
    // abstract
    // reason: abstract class eh parang pinaka blueprint ng other classes
    // structure: puro lang mga method names makikita inde nakakapag implement dito ng mga kung pano nag wowork yung methods
    // most commonly nicoconsider sya as parent class ng other classes kasi sya niiinherit
    // pinaka hint para malaman na abstract class is sa class meron nakalagay na (absrtact) keyword
    public abstract void addNewAnimal();
    public abstract void updateAnimalInformation();
    public abstract void updateAnimalStatus();
    public abstract void updateReportStatus();
    public abstract void applicationDecision();
    public abstract void reviewRequest();
    
}
