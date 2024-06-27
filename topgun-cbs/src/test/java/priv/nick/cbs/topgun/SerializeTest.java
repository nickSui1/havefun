package priv.nick.cbs.topgun;


import java.io.*;

public class SerializeTest {
    public static void main(String[] args) throws IOException {
        File file = new File("pojo.txt");

        serialize(file);
        deserialize(file);
    }

    public static void serialize(File file) throws IOException {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            Pojo pojo = new Pojo(1,"2");
            out.writeObject(pojo);
            out.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void deserialize(File file) throws IOException {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Pojo pojo = (Pojo) in.readObject();
            in.close();
            System.out.println(pojo.toString());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static class Pojo implements Serializable {
        @Serial
        private static final long serialVersionUID = -396986385718965941L;
        private int id;
        private String name;
        private int age;

        public Pojo(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Pojo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}


