package priv.nick.cbs.topgun;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import priv.nick.cbs.topgun.model.Group;
import priv.nick.cbs.topgun.model.SetMenu;

@SpringBootTest
public class TestComponentAnnotation {
    @Autowired
    //@Qualifier("businessPerson")
    private Person person;

    @Test
    public void order(){
        person.order();
    }
}
