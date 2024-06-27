package priv.nick.cbs.topgun.dto.response;

/**
 * @author nick.sui
 * @since 06/25/2024
 */
public class ExceptionDTO {
    private int code;
    private String message;
    private String additionalData;

    public ExceptionDTO(int code, String message, String additionalData) {
        this.code = code;
        this.message = message;
        this.additionalData = additionalData;
    }
    public ExceptionDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public ExceptionDTO() {}

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getAdditionalData() {
        return additionalData;
    }
    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
}
