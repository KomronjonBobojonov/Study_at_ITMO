package models.charackter;

import enums.Locations;
import environment.Person;
import interfaces.LookAfterable;
import interfaces.Stretchedable;

import java.util.List;
import java.util.Objects;

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
    public void lookafterable(List<Person> passengers) {
        Integer count_of_passengers = 0;
        for(int i = 0; i < passengers.size(); i++){
            if(Objects.equals(passengers.get(i).getName(),"коротышка") || Objects.equals(passengers.get(i).getName(), "Проводник")){
                count_of_passengers++;
            }
        }
        if(count_of_passengers >= 4){
            System.out.println("принялись наблюдать украдкой за прибывающими пассажирами.");
        }else{
            System.out.println("незнали что делать, так как людей в вагоне было мало.");
        }
    }
}
