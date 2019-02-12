package com.lixiaozhuo.house.base.response;

/**
 * Datatable API响应结构
 */
public class APIDataTableResponse extends APIResponse {
    //
    private int draw;
    //记录个数
    private long recordsTotal;
    //记录
    private long recordsFiltered;

    public APIDataTableResponse(APIResponse.Status status) {
        this(status.getCode(), status.getMessage(), null);
    }

    public APIDataTableResponse(int code, String message, Object data) {
        super(code, message, data);
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
