package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "COMPONENT", schema = "NSI03", catalog = "")
public class ComponentEntity {
    private long id;
    private long userid;
    private String title;
    private Time updated;
    private String additionalinfo;
    private Long componenttype;
    private String data;
    private transient UserEntity userByUserid;
    private transient ComponentTypeEntity componenttypeByComponenttype;
    private transient Collection<ComponentDraftEntity> componentdraftsById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERID", nullable = false, precision = 0)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "UPDATED", nullable = true)
    public Time getUpdated() {
        return updated;
    }

    public void setUpdated(Time updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "ADDITIONALINFO", nullable = true)
    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    @Basic
    @Column(name = "COMPONENTTYPE", nullable = true, precision = 0)
    public Long getComponenttype() {
        return componenttype;
    }

    public void setComponenttype(Long componenttype) {
        this.componenttype = componenttype;
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

        ComponentEntity that = (ComponentEntity) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;
        if (additionalinfo != null ? !additionalinfo.equals(that.additionalinfo) : that.additionalinfo != null)
            return false;
        if (componenttype != null ? !componenttype.equals(that.componenttype) : that.componenttype != null)
            return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userid ^ (userid >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (additionalinfo != null ? additionalinfo.hashCode() : 0);
        result = 31 * result + (componenttype != null ? componenttype.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public UserEntity getUserByUserid() {
        return userByUserid;
    }

    public void setUserByUserid(UserEntity userByUserid) {
        this.userByUserid = userByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "COMPONENTTYPE", referencedColumnName = "ID")
    public ComponentTypeEntity getComponenttypeByComponenttype() {
        return componenttypeByComponenttype;
    }

    public void setComponenttypeByComponenttype(ComponentTypeEntity componenttypeByComponenttype) {
        this.componenttypeByComponenttype = componenttypeByComponenttype;
    }

    @OneToMany(mappedBy = "componentByComponentid")
    public Collection<ComponentDraftEntity> getComponentdraftsById() {
        return componentdraftsById;
    }

    public void setComponentdraftsById(Collection<ComponentDraftEntity> componentdraftsById) {
        this.componentdraftsById = componentdraftsById;
    }
}
