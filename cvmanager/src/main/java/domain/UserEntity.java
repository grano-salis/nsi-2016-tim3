package domain;

import javax.persistence.*;

/**
 * Created by Adna on 11/12/2016.
 */
@Entity
@Table(name = "User", schema = "NSI03", catalog = "")
public class UserEntity {
    private long id;
    private String username;
    private String email;
    private String emailconfirmed;
    private String passwordhash;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "EMAILCONFIRMED")
    public String getEmailconfirmed() {
        return emailconfirmed;
    }

    public void setEmailconfirmed(String emailconfirmed) {
        this.emailconfirmed = emailconfirmed;
    }

    @Basic
    @Column(name = "PASSWORDHASH")
    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (emailconfirmed != null ? !emailconfirmed.equals(that.emailconfirmed) : that.emailconfirmed != null)
            return false;
        if (passwordhash != null ? !passwordhash.equals(that.passwordhash) : that.passwordhash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (emailconfirmed != null ? emailconfirmed.hashCode() : 0);
        result = 31 * result + (passwordhash != null ? passwordhash.hashCode() : 0);
        return result;
    }
}
