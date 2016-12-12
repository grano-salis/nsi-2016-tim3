package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "USERINFO", schema = "NSI03", catalog = "")
public class UserInfoEntity {
    private long id;
    private String firstname;
    private String lastname;
    private UserEntity userById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, length = 20)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, length = 20)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoEntity that = (UserInfoEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    public UserEntity getUserById() {
        return userById;
    }

    public void setUserById(UserEntity userById) {
        this.userById = userById;
    }
}
