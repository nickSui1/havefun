package priv.nick.cbs.topgun.dto.setmenu;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SetMenuAddDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6841710987071548154L;

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
}
