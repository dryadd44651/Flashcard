import java.util.ArrayList;

public class Scheduler {

    public Strategies strategies;
    int getNextCardNum(ArrayList<Integer> list,int index){
        return strategies.nextCard(list, index);
    }
}
class orderSchedule extends Scheduler{
    public orderSchedule(){
        strategies = new order();
    }
}

class randomSchedule extends Scheduler{
    public randomSchedule(){
        strategies = new random();
    }
}
class reviewSchedule extends Scheduler{
    public reviewSchedule(){
        strategies = new review();
    }

}