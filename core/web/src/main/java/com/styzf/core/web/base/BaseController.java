//package com.styzf.core.web.base;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.styzf.core.common.base.BaseDTO;
//import com.styzf.core.common.base.BaseService;
//import com.styzf.core.common.response.Response;
//import com.styzf.core.common.response.SuccessResponseData;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.MyStringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.io.Serializable;
//
///**
// * @author yangzf
// */
//@Slf4j
//@RestController
//public abstract class BaseController<D extends BaseDTO> {
//
//    @Reference(check = false)
//    private BaseService<D> baseService;
//
//    @GetMapping("/find/{id}")
//    public Response getById(@PathVariable Serializable id) {
//        return SuccessResponseData.newInstance(id);
////        D d = baseService.baseGetById(id);
////        d = afterGetById(id, d);
////        return SuccessResponseData.newInstance();
//    }
//
//    private D afterGetById(Serializable id, D d) {
//        return d;
//    }
//}
