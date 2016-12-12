package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "SSO_USER", schema = "NSI03", catalog = "")
public class UserEntity {
    private long id;
    private String username;
    private String email;
    private String emailconfirmed;
    private String passwordhash;
    private Collection<ComponentEntity> componentsById;
    private Collection<ComponentDraftEntity> componentdraftsById;
    private Collection<LogEntity> logsById;
    private UserInfoEntity userinfoById;
    private Collection<UserRoleEntity> userrolesById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "EMAILCONFIRMED", nullable = true, length = 1)
    public String getEmailconfirmed() {
        return emailconfirmed;
    }

    public void setEmailconfirmed(String emailconfirmed) {
        this.emailconfirmed = emailconfirmed;
    }

    @Basic
    @Column(name = "PASSWORDHASH", nullable = true, length = 256)
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

    @OneToMany(mappedBy = "userByUserid")
    public Collection<ComponentEntity> getComponentsById() {
        return componentsById;
    }

    public void setComponentsById(Collection<ComponentEntity> componentsById) {
        this.componentsById = componentsById;
    }

    @OneToMany(mappedBy = "userByUserid")
    public Collection<ComponentDraftEntity> getComponentdraftsById() {
        return componentdraftsById;
    }

    public void setComponentdraftsById(Collection<ComponentDraftEntity> componentdraftsById) {
        this.componentdraftsById = componentdraftsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<LogEntity> getLogsById() {
        return logsById;
    }

    public void setLogsById(Collection<LogEntity> logsById) {
        this.logsById = logsById;
    }

    @OneToOne(mappedBy = "userById")
    public UserInfoEntity getUserinfoById() {
        return userinfoById;
    }

    public void setUserinfoById(UserInfoEntity userinfoById) {
        this.userinfoById = userinfoById;
    }

    @OneToMany(mappedBy = "userByUserid")
    public Collection<UserRoleEntity> getUserrolesById() {
        return userrolesById;
    }

    public void setUserrolesById(Collection<UserRoleEntity> userrolesById) {
        this.userrolesById = userrolesById;
    }
}
