package dev.fcodeapi.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Course", schema = "dbo", catalog = "Fcode")
public class CourseEntity {
    private int courseId;
    private String courseName;
//    private Collection<AccountEntity> accountsByCourseId;

    @Id
    @Column(name = "CourseId")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "CourseName")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return courseId == that.courseId &&
                Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName);
    }

//    @OneToMany(mappedBy = "courseByCourseId")
//    public Collection<AccountEntity> getAccountsByCourseId() {
//        return accountsByCourseId;
//    }
//
//    public void setAccountsByCourseId(Collection<AccountEntity> accountsByCourseId) {
//        this.accountsByCourseId = accountsByCourseId;
//    }
}
