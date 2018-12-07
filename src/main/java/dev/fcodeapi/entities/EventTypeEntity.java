package dev.fcodeapi.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "EventType", schema = "dbo", catalog = "Fcode")
public class EventTypeEntity {
    private int typeId;
    private String typeName;
//    private Collection<EventEntity> eventsByTypeId;

    @Id
    @Column(name = "TypeID")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "TypeName")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventTypeEntity that = (EventTypeEntity) o;
        return typeId == that.typeId &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }

//    @OneToMany(mappedBy = "eventTypeByTypeId")
//    public Collection<EventEntity> getEventsByTypeId() {
//        return eventsByTypeId;
//    }
//
//    public void setEventsByTypeId(Collection<EventEntity> eventsByTypeId) {
//        this.eventsByTypeId = eventsByTypeId;
//    }
}
