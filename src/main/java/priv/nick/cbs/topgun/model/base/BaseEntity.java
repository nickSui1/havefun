package priv.nick.cbs.topgun.model.base;

import lombok.Data;

import java.time.Instant;

/**
 * @since 2024-4-19 21:30
 * @author nicksy
 */
@Data
public class BaseEntity {
    /**
     * snowflakeId
     */
    private Long id;

    private Long tenantId;

    private int revision;

    /**
     * Optimistic lock /乐观锁
     */
    private int version;

    private Instant createTime;

    private Instant updateTime;

    private String createdBy;

    private String updatedBy;
}
