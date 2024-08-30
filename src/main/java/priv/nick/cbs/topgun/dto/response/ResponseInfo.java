package priv.nick.cbs.topgun.dto.response;


import java.io.Serializable;

public class ResponseInfo implements Serializable {
    private String message;
    private Object data;
    private Integer status;

    public ResponseInfo(){}

    public ResponseInfo(String message) {
        this.message = message;
    }

    public ResponseInfo(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseInfo(String message, Object data, Integer status) {
        this.message = message;
        this.data = data;
        this.status = status;
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

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
