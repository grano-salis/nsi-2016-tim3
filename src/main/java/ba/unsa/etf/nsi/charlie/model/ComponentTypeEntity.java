package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by koljenovic on 10/01/2017.
 */
@Entity
@Table(name = "COMPONENTTYPE", schema = "NSI03", catalog = "")
public class ComponentTypeEntity {
    private long id;
    private String componenttype;
    private Collection<ComponentEntity> componentsById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "sequence", allocationSize = 20, sequenceName = "SEQ_COMPONENTYPE")
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (componenttype != null ? componenttype.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "componentTypeByComponentType")
    public Collection<ComponentEntity> getComponentsById() {
        return componentsById;
    }

    public void setComponentsById(Collection<ComponentEntity> componentsById) {
        this.componentsById = componentsById;
    }
}
