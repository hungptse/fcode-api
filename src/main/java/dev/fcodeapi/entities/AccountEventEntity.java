package dev.fcodeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "AccountEvent", schema = "dbo", catalog = "Fcode")
@JsonIgnoreProperties({"event"})
public class AccountEventEntity {
    private Boolean status;
    private int accountEvent;
    private EventEntity event;
    private AccountEntity account;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    @ManyToOne
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false)
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
