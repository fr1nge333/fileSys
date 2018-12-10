package cn.lhs.filesys.entity;

import java.util.List;

public class ResponseMsg {
    private int code;
    private String msg;
    private int count;
    private List<FileMsg> data;

    public ResponseMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseMsg(int code, String msg, int count, List<FileMsg> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FileMsg> getData() {
        return data;
    }

    public void setData(List<FileMsg> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
