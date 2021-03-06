package com.uber.api.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Auditable {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "idsequence", allocationSize = 10)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(nullable = false, updatable = true)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Auditable) {
            return ((Auditable) obj).getId().equals(getId());
        }
        return super.equals(obj);
    }
}
