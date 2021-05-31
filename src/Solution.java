
import java.util.ArrayList;
import java.util.List;


public class Solution {

    public List<Element> list = new ArrayList<>();

    public boolean isValid() {
        return true;
    }

    public Solution() {

    }

    public void print() {
        this.list.forEach(value -> {
            value.print();
        });
    }
}

