package org.tmjug.spring.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(
        name = "User.byUserName",
        query = "SELECT user FROM User user WHERE user.userName = :userName"
    ),

    @NamedQuery(
        name = "User.byName",
        query = "SELECT user FROM User user WHERE lower(user.firstName) LIKE :name OR lower(user.lastName) LIKE :name"
    )
})
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String userName;

    @Column
    private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
