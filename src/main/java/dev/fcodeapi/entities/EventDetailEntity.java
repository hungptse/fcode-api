package dev.fcodeapi.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "EventDetail", schema = "dbo", catalog = "Fcode")
public class EventDetailEntity {

    private int eventDetail;
    private String detailName;
    private Timestamp dateEvent;
    private Collection<AttendanceEntity> attendancesByEventDetail;
    private EventEntity eventByEventId;

//    @Basic
//    @Column(name = "EventId")
//    public int getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(int eventId) {
//        this.eventId = eventId;
//    }

    @Id
    @Column(name = "EventDetail")
    public int getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(int eventDetail) {
        this.eventDetail = eventDetail;
    }

    @Basic
    @Column(name = "DetailName")
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    @Basic
    @Column(name = "DateEvent")
    public Timestamp getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Timestamp dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDetailEntity that = (EventDetailEntity) o;
        return
                eventDetail == that.eventDetail &&
                Objects.equals(detailName, that.detailName) &&
                Objects.equals(dateEvent, that.dateEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventDetail, detailName, dateEvent);
    }

    @OneToMany(mappedBy = "eventDetailByEventDetail")
    public Collection<AttendanceEntity> getAttendancesByEventDetail() {
        return attendancesByEventDetail;
    }

    public void setAttendancesByEventDetail(Collection<AttendanceEntity> attendancesByEventDetail) {
        this.attendancesByEventDetail = attendancesByEventDetail;
    }

    @ManyToOne
    @JoinColumn(name = "EventId", referencedColumnName = "EventId", nullable = false)
    public EventEntity getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(EventEntity eventByEventId) {
        this.eventByEventId = eventByEventId;
    }
}
