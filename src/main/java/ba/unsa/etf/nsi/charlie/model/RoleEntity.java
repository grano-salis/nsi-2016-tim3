package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "ROLE", schema = "NSI03", catalog = "")
public class RoleEntity {
    private long id;
    private String name;
    private transient Collection<UserRoleEntity> userrolesById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleid")
    public Collection<UserRoleEntity> getUserrolesById() {
        return userrolesById;
    }

    public void setUserrolesById(Collection<UserRoleEntity> userrolesById) {
        this.userrolesById = userrolesById;
    }
}
