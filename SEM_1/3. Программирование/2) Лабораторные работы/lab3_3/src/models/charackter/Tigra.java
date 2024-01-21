package models.charackter;

import enums.*;

public class Tigra extends AbsCharacter {
    public static String stcName = "Тигра";

    public Tigra() {
        super("Тигра");
    }
//    public String back(MeanTime meantime,  Condition happy, Verb jump, Direction ahead, MeanTime everyminute, Circumstsnces returning, Verb ask, Things where, Verb go){
//        return stcName + meantime.getName() + happy.getName() + jump.getName() + ahead.getName() + ", " + everyminute.getName() + returning.getName() + ask.getName() + ": " + where.getName() + go.getName();
//    }
    public String back(MeanTime meantime,  Condition happy, Verb jump, Direction ahead, MeanTime everyminute, Circumstsnces returning){
        return stcName+" "+meantime.getName()+" "+happy.getName()+" "+jump.getName()+" "+ahead.getName()+", "+everyminute.getName()+" "+returning.getName();
    }
    public String back2( Verb ask, Things where, Verb go){
        return  ask.getName()+": "+where.getName()+" "+go.getName()+"?";
    }
    public String rushed(Verb rushed, Circumstsnces how, Pronouns pronoun) {
        return stcName+" "+rushed.getName()+" "+how.getName()+" к "+pronoun.getName();
    }
}


