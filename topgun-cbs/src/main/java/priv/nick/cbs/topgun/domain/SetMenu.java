package priv.nick.cbs.topgun.domain;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;

/**
 * @since 2024-4-18 16:52
 * @author nicksy
 */
@Data
public class SetMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = -2472511482328990273L;
    /** Merchant id */
    private Long propertyId;

    /** Set Menu code */
    private String code;

    /** Name */
    private String name;

    /** Description */
    private String description;

    /** Type of service(charge per table/charge per person) */
    private String serviceType;

    /** Sort No */
    private Integer sortNo;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
