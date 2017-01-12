package ba.unsa.etf.nsi.charlie.modelx;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "LOG", schema = "NSI03", catalog = "")
public class LogEntity {
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    private Long userId;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private String logText;
    private Date created;

    public void setCreated(Time created) {
        this.created = created;
    }

    private transient UserEntity userByUserId;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "LOG_TEXT", nullable = false, length = 200)
    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    @Basic
    @Column(name = "CREATED", nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEntity logEntity = (LogEntity) o;

        if (id != logEntity.id) return false;
        if (userId != logEntity.userId) return false;
        if (logText != null ? !logText.equals(logEntity.logText) : logEntity.logText != null) return false;
        if (created != null ? !created.equals(logEntity.created) : logEntity.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + userId.intValue();
        result = 31 * result + (logText != null ? logText.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
