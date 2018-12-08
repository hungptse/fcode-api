package dev.fcodeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Attendance", schema = "dbo", catalog = "Fcode")
@JsonIgnoreProperties("eventDetailByEventDetail")
public class AttendanceEntity {
    private int attendanceId;
    private Boolean isPresent;
    private String note;
    private String studentId;
    private EventDetailEntity eventDetailByEventDetail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttendanceId")
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    @Basic
    @Column(name = "isPresent")
    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    @Basic
    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "StudentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceEntity that = (AttendanceEntity) o;
        return attendanceId == that.attendanceId &&
                Objects.equals(isPresent, that.isPresent) &&
                Objects.equals(note, that.note) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceId, isPresent, note, studentId);
    }

    @ManyToOne
    @JoinColumn(name = "EventDetail", referencedColumnName = "EventDetail")
    public EventDetailEntity getEventDetailByEventDetail() {
        return eventDetailByEventDetail;
    }

    public void setEventDetailByEventDetail(EventDetailEntity eventDetailByEventDetail) {
        this.eventDetailByEventDetail = eventDetailByEventDetail;
    }
}
