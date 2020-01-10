import java.util.ArrayList;
import java.util.Random;

public interface Strategies {
    int nextCard(ArrayList<Integer> list,int index);
}
class order implements Strategies{

    public int nextCard(ArrayList<Integer> list,int index) {
        int number = 1;
        for (int i = index;i<list.size();i++){
            if(list.get(i)<3) {
                number = i;
                break;
            }
        }
        //table id start from 1
        return number+1;
    }
}
class random implements Strategies{

    public int nextCard(ArrayList<Integer> list,int index) {
        int number = 1;
        Random r = new Random();
        number = r.nextInt(list.size());
        return number+1;
    }
}
class review implements Strategies{

    public int nextCard(ArrayList<Integer> list,int index) {
        int number = 1;
        for (int i = index;i<list.size();i++){
            if(list.get(i)>0&&list.get(i)<5) {
                number = i;
                break;
            }
        }
        return number+1;
    }
}