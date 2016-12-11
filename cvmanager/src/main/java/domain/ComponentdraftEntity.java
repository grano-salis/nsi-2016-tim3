package domain;

import javax.persistence.*;

/**
 * Created by Adna on 11/12/2016.
 */
@Entity
@Table(name = "COMPONENTDRAFT", schema = "NSI03", catalog = "")
public class ComponentdraftEntity {
    private long id;
    private Long componentid;
    private String data;
    private String status;
    private String additionalinfo;
    private Long userid;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPONENTID")
    public Long getComponentid() {
        return componentid;
    }

    public void setComponentid(Long componentid) {
        this.componentid = componentid;
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
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "USERID")
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentdraftEntity that = (ComponentdraftEntity) o;

        if (id != that.id) return false;
        if (componentid != null ? !componentid.equals(that.componentid) : that.componentid != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (additionalinfo != null ? !additionalinfo.equals(that.additionalinfo) : that.additionalinfo != null)
            return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (componentid != null ? componentid.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (additionalinfo != null ? additionalinfo.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}
