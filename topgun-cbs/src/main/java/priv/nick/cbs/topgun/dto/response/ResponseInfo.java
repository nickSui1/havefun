package priv.nick.cbs.topgun.dto.response;


import java.io.Serializable;

public class ResponseInfo implements Serializable {
    private String message;
    private Object data;

    public ResponseInfo(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    public ResponseInfo(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
