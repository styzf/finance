package com.styzf.core.common.exception;

public class NoRecordException extends BaseException {
    private static final long serialVersionUID = 1L;

    public NoRecordException(Exception e) {
        super(e);
    }
  
    public NoRecordException() {
        super("no-record", "没有找到对应的记录");
    }
}
