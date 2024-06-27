package priv.nick.cbs.topgun.dto.base;

import lombok.Data;

import java.time.Instant;

@Data
public class BaseEntityDTO {
    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
