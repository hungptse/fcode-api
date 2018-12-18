package dev.fcodeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "EventDetail", schema = "dbo", catalog = "Fcode")
@JsonIgnoreProperties({"eventByEventId","attendanceList"})
public class EventDetailEntity {

    private int eventDetail;
    private String detailName;
    private Timestamp dateEvent;
    private Collection<AttendanceEntity> attendanceList;
//    private EventEntity event;

//    @Basic
//    @Column(name = "EventId")
//    public int getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(int eventId) {
//        this.eventId = eventId;
//    }
    private EventEntity eventByEventId;

    @Id
    @Column(name = "EventDetail")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "eventDetailByEventDetail",fetch = FetchType.EAGER)
    public Collection<AttendanceEntity> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(Collection<AttendanceEntity> attendancesByEventDetail) {
        this.attendanceList = attendancesByEventDetail;
    }

//    @ManyToOne
//    @JoinColumn(name = "EventId", referencedColumnName = "EventId", nullable = false)
//    public EventEntity getEvent() {
//        return event;
//    }
//
//    public void setEvent(EventEntity event) {
//        this.event = event;
//    }

    @ManyToOne
    @JoinColumn(name = "EventId", referencedColumnName = "EventId", nullable = false)
    public EventEntity getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(EventEntity eventByEventId) {
        this.eventByEventId = eventByEventId;
    }
}
