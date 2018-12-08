package dev.fcodeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Event", schema = "dbo", catalog = "Fcode")
@JsonIgnoreProperties({"eventDetailsByEventId"})
public class EventEntity {
    private int eventId;
    private String eventName;
    private String location;
    private String description;
    private String banner;
    private Timestamp dateBegin;
    private Timestamp dateEnd;
    private Timestamp dateCreated;
    private Boolean isSign;
    private String manageBy;
    private Boolean isPublish;
//    private Collection<AccountEventEntity> accountEventsByEventId;
    private EventTypeEntity type;
//    private Collection<EventDetailEntity> listDetail;
    private Collection<EventDetailEntity> eventDetailsByEventId;

    @Id
    @Column(name = "EventId")
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "EventName")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Banner")
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Basic
    @Column(name = "DateBegin")
    public Timestamp getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Timestamp dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Basic
    @Column(name = "DateEnd")
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Basic
    @Column(name = "DateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "isSign")
    public Boolean getSign() {
        return isSign;
    }

    public void setSign(Boolean sign) {
        isSign = sign;
    }

    @Basic
    @Column(name = "ManageBy")
    public String getManageBy() {
        return manageBy;
    }

    public void setManageBy(String postBy) {
        this.manageBy = postBy;
    }

    @Basic
    @Column(name = "isPublish")
    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return eventId == that.eventId &&
                Objects.equals(eventName, that.eventName) &&
                Objects.equals(location, that.location) &&
                Objects.equals(description, that.description) &&
                Objects.equals(banner, that.banner) &&
                Objects.equals(dateBegin, that.dateBegin) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(isSign, that.isSign) &&
                Objects.equals(manageBy, that.manageBy) &&
                Objects.equals(isPublish, that.isPublish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, location, description, banner, dateBegin, dateEnd, dateCreated, isSign, manageBy, isPublish);
    }

//    @OneToMany(mappedBy = "eventByEventId")
//    public Collection<AccountEventEntity> getAccountEventsByEventId() {
//        return accountEventsByEventId;
//    }
//
//    public void setAccountEventsByEventId(Collection<AccountEventEntity> accountEventsByEventId) {
//        this.accountEventsByEventId = accountEventsByEventId;
//    }
//
    @ManyToOne
    @JoinColumn(name = "TypeID", referencedColumnName = "TypeID")
    public EventTypeEntity getType() {
        return type;
    }

    public void setType(EventTypeEntity type) {
        this.type = type;
    }

//
//
//    @OneToMany(mappedBy = "eventByEventId")
//    public Collection<EventDetailEntity> getListDetail() {
//        return listDetail;
//    }
//
//    public void setListDetail(Collection<EventDetailEntity> listDetail) {
//        this.listDetail = listDetail;
//    }

    @OneToMany(mappedBy = "eventByEventId", fetch = FetchType.EAGER)
    public Collection<EventDetailEntity> getEventDetailsByEventId() {
        return eventDetailsByEventId;
    }

    public void setEventDetailsByEventId(Collection<EventDetailEntity> eventDetailsByEventId) {
        this.eventDetailsByEventId = eventDetailsByEventId;
    }
}
