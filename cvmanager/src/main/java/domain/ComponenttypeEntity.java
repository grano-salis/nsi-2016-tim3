package domain;

import javax.persistence.*;

/**
 * Created by Adna on 11/12/2016.
 */
@Entity
@Table(name = "COMPONENTTYPE", schema = "NSI03", catalog = "")
public class ComponenttypeEntity {
    private int id;
    private String componenttype;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPONENTTYPE")
    public String getComponenttype() {
        return componenttype;
    }

    public void setComponenttype(String componenttype) {
        this.componenttype = componenttype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponenttypeEntity that = (ComponenttypeEntity) o;

        if (id != that.id) return false;
        if (componenttype != null ? !componenttype.equals(that.componenttype) : that.componenttype != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (componenttype != null ? componenttype.hashCode() : 0);
        return result;
    }
}
