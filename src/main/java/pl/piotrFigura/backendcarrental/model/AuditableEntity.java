package pl.piotrFigura.backendcarrental.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class AuditableEntity {

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @PrePersist
    void prePersist() {
        creationDate = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updateDate = LocalDateTime.now();
    }
}
