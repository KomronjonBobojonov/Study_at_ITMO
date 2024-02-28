package models.charackter;

import enums.Locations;
import environment.Person;
import interfaces.LookAfterable;
import interfaces.Stretchedable;
import exception.PassengersNotFoundException;

import java.util.List;

public class Group implements Stretchedable, LookAfterable {
    List<Person> particiepents;
    public Group(List<Person> particiepents){
        this.particiepents = particiepents;
    }


    @Override
    public void streched(List<Person> people) {
        System.out.print(particiepents.get(0).getName() + " и " + particiepents.get(1).getName());
        if(particiepents.get(0).location == Locations.VAGON){
            System.out.print(" с удобством растянулись на них и ");
        }else{
            System.out.print("С огорчением ушли домой.");
        }
    }

    @Override
    public void lookafterable(List<Person> passengers) throws PassengersNotFoundException{
        Integer count_of_passengers = 0;
        for(int i = 0; i < passengers.size(); i++){
            if(passengers.get(i).getName() == "коротышка" || passengers.get(i).getName() == "Проводник"){
                count_of_passengers++;
            }
        }
        if(count_of_passengers >= 2){
            System.out.println("принялись наблюдать украдкой за прибывающими пассажирами.");
        }else{

            throw new PassengersNotFoundException("Что-то пошло не так!");
        }
    }
}
