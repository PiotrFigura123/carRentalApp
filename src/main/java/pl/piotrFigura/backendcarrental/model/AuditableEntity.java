package pl.piotrFigura.backendcarrental.model;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class AuditableEntity {

    private LocalDateTime creationDate;
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
