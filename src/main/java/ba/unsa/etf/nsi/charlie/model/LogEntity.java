package ba.unsa.etf.nsi.charlie.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by koljenovic on 12/12/2016.
 */
@Entity
@Table(name = "LOG", schema = "NSI03", catalog = "")
public class LogEntity {
    private int id;
    private int userId;
    private String logText;
    private Time created;
    private transient UserEntity userByUserId;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
    public Time getCreated() {
        return created;
    }

    public void setCreated(Time created) {
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
        int result = id;
        result = 31 * result + userId;
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
