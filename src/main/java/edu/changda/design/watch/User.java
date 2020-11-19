package edu.changda.design.watch;

/**
 * @program: Designer
 * @classname: User
 * @description:
 * @author: 朱林
 * @create: 2020-01-04 18:50
 **/
public class User implements Observer {

    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read(message);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public void read(String message){
        System.out.println(toString());
    }
}
