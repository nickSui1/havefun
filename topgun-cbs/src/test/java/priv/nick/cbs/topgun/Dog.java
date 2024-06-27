package priv.nick.cbs.topgun;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class Dog implements Animal{
    @Override
    public void service() {
        System.out.println("The dog can guard the door");
    }
}
