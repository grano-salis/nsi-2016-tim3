package domain;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Adna on 11/12/2016.
 */
@Entity
@Table(name = "COMPONENT", schema = "NSI03", catalog = "")
public class ComponentEntity {
    private long id;
    private long userid;
    private String title;
    private Time updated;
    private String additionalinfo;
    private String data;
    private Long componenttype;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERID")
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "UPDATED")
    public Time getUpdated() {
        return updated;
    }

    public void setUpdated(Time updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "ADDITIONALINFO")
    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    @Basic
    @Column(name = "DATA")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "COMPONENTTYPE")
    public Long getComponenttype() {
        return componenttype;
    }

    public void setComponenttype(Long componenttype) {
        this.componenttype = componenttype;
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
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (componenttype != null ? !componenttype.equals(that.componenttype) : that.componenttype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userid ^ (userid >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (additionalinfo != null ? additionalinfo.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (componenttype != null ? componenttype.hashCode() : 0);
        return result;
    }
}
