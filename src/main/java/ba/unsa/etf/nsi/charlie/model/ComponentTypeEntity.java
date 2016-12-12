package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "COMPONENTTYPE", schema = "NSI03", catalog = "")
public class ComponentTypeEntity {
    private int id;
    private String componenttype;
    private transient Collection<ComponentEntity> componentsById;
    private transient Collection<ComponentDraftEntity> componentdraftsById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPONENTTYPE", nullable = false, length = 35)
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

        ComponentTypeEntity that = (ComponentTypeEntity) o;

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

    @OneToMany(mappedBy = "componenttypeByComponenttype")
    public Collection<ComponentEntity> getComponentsById() {
        return componentsById;
    }

    public void setComponentsById(Collection<ComponentEntity> componentsById) {
        this.componentsById = componentsById;
    }

    @OneToMany(mappedBy = "componenttypeByComponenttype")
    public Collection<ComponentDraftEntity> getComponentdraftsById() {
        return componentdraftsById;
    }

    public void setComponentdraftsById(Collection<ComponentDraftEntity> componentdraftsById) {
        this.componentdraftsById = componentdraftsById;
    }
}
