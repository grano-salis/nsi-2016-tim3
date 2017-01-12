package ba.unsa.etf.nsi.charlie.modelx;

import javax.persistence.*;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "COMPONENTDRAFT", schema = "NSI03")
public class ComponentDraftEntity {
    private long id;
    private Long componentid;
    private String status;
    private String additionalinfo;
    private Long userid;
    private Long componenttype;

    public void setComponenttype(Long componenttype) {
        this.componenttype = componenttype;
    }

    private String data;
    private transient ComponentEntity componentByComponentid;
//    private transient UserEntity userByUserid;
    private transient ComponentTypeEntity componenttypeByComponenttype;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPONENTID", nullable = true, precision = 0)
    public Long getComponentid() {
        return componentid;
    }

    public void setComponentid(Long componentid) {
        this.componentid = componentid;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 100)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ADDITIONALINFO", nullable = true, length = 100)
    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    @Basic
    @Column(name = "USERID", nullable = true, precision = 0)
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "COMPONENTTYPE", nullable = true, precision = 0)
    public long getComponenttype() {
        return componenttype;
    }

    @Basic
    @Column(name = "DATA", nullable = true)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentDraftEntity that = (ComponentDraftEntity) o;

        if (id != that.id) return false;
        if (componentid != null ? !componentid.equals(that.componentid) : that.componentid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (additionalinfo != null ? !additionalinfo.equals(that.additionalinfo) : that.additionalinfo != null)
            return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (componenttype != null ? !componenttype.equals(that.componenttype) : that.componenttype != null)
            return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (componentid != null ? componentid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (additionalinfo != null ? additionalinfo.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (componenttype != null ? componenttype.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "COMPONENTID", referencedColumnName = "ID")
    public ComponentEntity getComponentByComponentid() {
        return componentByComponentid;
    }

    public void setComponentByComponentid(ComponentEntity componentByComponentid) {
        this.componentByComponentid = componentByComponentid;
    }

//    @ManyToOne
//    @JoinColumn(name = "USERID", referencedColumnName = "ID")
//    public UserEntity getUserByUserid() {
//        return userByUserid;
//    }

//    public void setUserByUserid(UserEntity userByUserid) {
//        this.userByUserid = userByUserid;
//    }

    @ManyToOne
    @JoinColumn(name = "COMPONENTTYPE", referencedColumnName = "ID")
    public ComponentTypeEntity getComponenttypeByComponenttype() {
        return componenttypeByComponenttype;
    }

    public void setComponenttypeByComponenttype(ComponentTypeEntity componenttypeByComponenttype) {
        this.componenttypeByComponenttype = componenttypeByComponenttype;
    }
}
