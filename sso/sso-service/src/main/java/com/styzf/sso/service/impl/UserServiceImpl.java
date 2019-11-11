package com.styzf.sso.service.impl;

import com.styzf.core.common.util.ConvertUtil;
import com.styzf.finance.dto.user.MenuDTO;
import com.styzf.finance.dto.user.UserDTO;
import com.styzf.finance.dto.user.UserExt;
import com.styzf.sso.mapper.CompanyUserMapper;
import com.styzf.sso.mapper.MenuMapper;
import com.styzf.sso.mapper.UserMapper;
import com.styzf.sso.po.User;
import com.styzf.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyUserMapper companyUserMapper;

    @Autowired
    private MenuMapper menuMapper;

    //根据账号查询xcUser信息
    public UserDTO findXcUserByUsername(String username){
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        Weekend<User> weekend = Weekend.of(User.class);
        weekend.weekendCriteria().andEqualTo(User::getName, username);
    
        List<User> userList = userMapper.selectByExample(weekend);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        User user = userList.get(0);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    //根据账号查询用户信息
    public UserExt getUserExt(String username){
        //根据账号查询xcUser信息
        UserDTO user = this.findXcUserByUsername(username);
        if(user == null){
            return null;
        }
        //用户id
        String userId = user.getId();
        //查询用户所有权限
        List<MenuDTO> menuList = menuMapper.selectPermissionByUserId(userId);

        //根据用户id查询用户所属公司id 公司相关的先不做处理
//        XcCompanyUser xcCompanyUser = xcCompanyUserRepository.findByUserId(userId);
//        //取到用户的公司id
//        String companyId = null;
//        if(xcCompanyUser!=null){
//            companyId = xcCompanyUser.getCompanyId();
//        }
        UserExt xcUserExt = ConvertUtil.convert(user, UserExt.class);
        //设置权限
        xcUserExt.setPermissions(menuList);
        return xcUserExt;

    }

}
