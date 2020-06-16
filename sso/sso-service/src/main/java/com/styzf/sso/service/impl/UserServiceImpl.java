package com.styzf.sso.service.impl;

import com.styzf.core.common.util.ConvertUtil;
import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.dto.user.UserDTO;
import com.styzf.sso.dto.user.UserExt;
import com.styzf.sso.mapper.CompanyUserMapper;
import com.styzf.sso.mapper.MenuMapper;
import com.styzf.sso.mapper.UserMapper;
import com.styzf.sso.po.MyUser;
import com.styzf.sso.service.RoleService;
import com.styzf.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private RoleService roleService;
    
    //根据账号查询xcUser信息
    @Override
    public UserDTO findUserByUsername(String username){
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        Weekend<MyUser> weekend = Weekend.of(MyUser.class);
        weekend.weekendCriteria().andEqualTo(MyUser::getUsername, username);
    
        List<MyUser> userList = userMapper.selectByExample(weekend);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        MyUser user = userList.get(0);
        return ConvertUtil.convert(user, UserDTO.class);
    }

    //根据账号查询用户信息
    @Override
    public UserExt getUserExt(String username){
        //根据账号查询xcUser信息
        UserDTO user = this.findUserByUsername(username);
        if(user == null){
            return null;
        }
        Long userId = user.getId();
    
        UserExt xcUserExt = ConvertUtil.convert(user, UserExt.class);
        //设置权限
        List<RoleDTO> roleList = roleService.getByUserId(userId);
        if (CollectionUtils.isEmpty(roleList)) {
            return xcUserExt;
        }
        List<Long> roleIdList = roleList.stream().map(RoleDTO::getId).collect(Collectors.toList());
    
        //查询用户所有权限
        List<MenuDTO> menuList = menuMapper.selectPermissionByUserId(roleIdList);
        xcUserExt.setPermissions(menuList);
        xcUserExt.setRoleList(roleList);

        //根据用户id查询用户所属公司id 公司相关的先不做处理
//        XcCompanyUser xcCompanyUser = xcCompanyUserRepository.findByUserId(userId);
//        //取到用户的公司id
//        String companyId = null;
//        if(xcCompanyUser!=null){
//            companyId = xcCompanyUser.getCompanyId();
//        }

        return xcUserExt;

    }

}
