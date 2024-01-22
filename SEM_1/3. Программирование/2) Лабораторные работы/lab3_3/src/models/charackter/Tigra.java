package models.charackter;

import enums.*;

public class Tigra extends AbsCharacter {
    public static String stcName = "Тигра";
    public int y_coordinate = 0;
    public int energy = 100;
    public EnergyLevel energyLevel = EnergyLevel.Energetic;
    public Tigra() {
        super("Тигра");
    }
    public String jump(MeanTime meantime, Condition happy, Verb jump, Direction ahead, MeanTime everyminute, Circumstsnces returning){
        energy -= 10;
        if(energy == 50) {
            energyLevel = EnergyLevel.Normal;
        }else if(energy <= 50) {
            energyLevel = EnergyLevel.Tired;
        }
        return stcName+" "+meantime.getName()+" "+happy.getName()+" "+jump.getName()+" "+ahead.getName()+", "+everyminute.getName()+" "+returning.getName();
    }
    public String back2( Verb ask, Things where, Verb go){
        return  ask.getName()+": "+where.getName()+" "+go.getName()+"?";
    }
    public String rushed(Verb rushed, Circumstsnces how, Pronouns pronoun) {
        return stcName+" "+rushed.getName()+" "+how.getName()+" к "+pronoun.getName();
    }
}

enum EnergyLevel {
    Tired, Energetic, Normal;
}
