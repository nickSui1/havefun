package priv.nick.cbs.topgun.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FormatUtils {
    public List<String> convertMapToList(Map<String,List<String>> map){
        List<String> list = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry:map.entrySet()){
            String key = entry.getKey();
            List<String> value = entry.getValue();
            List<String> temp = new ArrayList<>(value);
            temp.add(0,key);
            list.addAll(temp);
        }
        return list;
    }
}
