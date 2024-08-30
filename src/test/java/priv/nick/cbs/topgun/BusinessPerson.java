package priv.nick.cbs.topgun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class BusinessPerson implements Person{
    @Autowired
    private Animal animal;

    @Override
    public void order() {
        animal.service();
    }
}
