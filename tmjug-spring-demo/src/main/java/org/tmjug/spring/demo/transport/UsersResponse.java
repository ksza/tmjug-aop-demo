package org.tmjug.spring.demo.transport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UsersResponse extends GenericResponse {

    private List<UserTO> users;

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    public List<UserTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserTO> users) {
        this.users = users;
    }
}
