package com.styzf.finance.dto.response;

/**
 * @author yangzf
 * @date 2019-09-23
 */
public interface ResultCode {
    //操作是否成功,true为成功，false操作失败
    boolean success();
    //操作代码
    int code();
    //提示信息
    String message();

}
