package com.styzf.finance.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

@Api(value = "测试")
public interface UserControllerDoc {
    
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IUserReadOnlyService userReadOnlyService;
    
    @ApiOperation(value = "测试", notes = "测试1")
    public List hello();
    
    @ApiOperation(value = "测试2", notes = "测试2")
    public List helloRead();
    
    @ApiOperation(value = "测试3", notes = "测试3")
    public Boolean insert();
    
    @ApiOperation(value = "测试4", notes = "测试4")
    public Date test(Date date);
}
