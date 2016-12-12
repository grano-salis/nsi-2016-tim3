package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "USERROLE", schema = "NSI03", catalog = "")
public class UserRoleEntity {
    private long userid;
    private long roleid;
    private long id;
    private transient UserEntity userByUserid;
    private transient RoleEntity roleByRoleid;

    @Basic
    @Column(name = "USERID", nullable = false, precision = 0)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "ROLEID", nullable = false, precision = 0)
    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleEntity that = (UserRoleEntity) o;

        if (userid != that.userid) return false;
        if (roleid != that.roleid) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + (int) (roleid ^ (roleid >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
    public UserEntity getUserByUserid() {
        return userByUserid;
    }

    public void setUserByUserid(UserEntity userByUserid) {
        this.userByUserid = userByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "ROLEID", referencedColumnName = "ID", nullable = false)
    public RoleEntity getRoleByRoleid() {
        return roleByRoleid;
    }

    public void setRoleByRoleid(RoleEntity roleByRoleid) {
        this.roleByRoleid = roleByRoleid;
    }
}
