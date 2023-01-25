package dynamic;

import java.util.*;
public class InsultServiceImp implements InsultService{
    private List<String> insults;

    public InsultServiceImp() {
        insults = new ArrayList<String>();
    }

    public void addInsult(String insult) {
        insults.add(insult);
    }

    public String getInsult() {
        return insults.size() > 0 ?  insults.get((int)(Math.random() * (insults.size() - 1))):null;
    }

    public List<String> getAllInsults() {
        return insults;
    }
}
