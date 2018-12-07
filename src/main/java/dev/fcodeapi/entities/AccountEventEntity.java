package dev.fcodeapi.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "AccountEvent", schema = "dbo", catalog = "Fcode")
public class AccountEventEntity {
    private Boolean status;
    private int accountEvent;
    private EventEntity eventByEventId;
//    private AccountEntity accountByStudentId;

    @Basic
    @Column(name = "Status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Id
    @Column(name = "AccountEvent")
    public int getAccountEvent() {
        return accountEvent;
    }

    public void setAccountEvent(int accountEvent) {
        this.accountEvent = accountEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEventEntity that = (AccountEventEntity) o;
        return accountEvent == that.accountEvent &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, accountEvent);
    }

    @ManyToOne
    @JoinColumn(name = "EventId", referencedColumnName = "EventId", nullable = false)
    public EventEntity getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(EventEntity eventByEventId) {
        this.eventByEventId = eventByEventId;
    }

//    @ManyToOne
//    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false)
//    public AccountEntity getAccountByStudentId() {
//        return accountByStudentId;
//    }
//
//    public void setAccountByStudentId(AccountEntity accountByStudentId) {
//        this.accountByStudentId = accountByStudentId;
//    }
}
