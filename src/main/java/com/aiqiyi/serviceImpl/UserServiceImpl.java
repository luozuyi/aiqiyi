package com.aiqiyi.serviceImpl;

import com.aiqiyi.entity.User;
import com.aiqiyi.mapper.UserMapper;
import com.aiqiyi.service.UserService;
import com.aiqiyi.utils.CommonUtil;
import com.aiqiyi.utils.Constants;
import com.aiqiyi.utils.PatternUtil;
import com.aiqiyi.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result regist(User user,String surePassword) {
        Result result = new Result();
        String msg = "初始化";
        String code = Constants.FAIL;
        try {
            if(StringUtils.isBlank(user.getUsername())){
                code = "-3";
                msg = "用户名不能为空";
            }else if(StringUtils.isBlank(user.getPassword())){
                code = "-4";
                msg = "密码不能为空";
            }else if(StringUtils.isBlank(user.getNickname())){
                code = "-5";
                msg = "昵称不能为空";
            }else if(StringUtils.isBlank(user.getEmail())){
                code = "-6";
                msg = "邮箱不能为空";
            }else if(StringUtils.isBlank(surePassword)){
                code = "-7";
                msg = "确认密码不能为空";
            }else if(!PatternUtil.patternString(user.getUsername(),"username")){
                code = "-8";
                msg = "用户名格式不正确";
            }else if(!PatternUtil.patternString(user.getPassword(),"password")){
                code = "-9";
                msg = "密码格式不正确";
            }else if(!PatternUtil.patternString(user.getEmail(),"email")){
                code = "-10";
                msg = "邮箱格式不正确";
            }else if(!PatternUtil.patternString(user.getNickname(),"username")){
                code = "-11";
                msg = "昵称格式不正确";
            }else if(!user.getPassword().equals(surePassword)){
                code = "-12";
                msg = "两次密码不一致";
            }else{
               List<User> users = userMapper.selectByUsername(user.getUsername());
               if(!users.isEmpty()){
                   code = "-13";
                   msg = "该用户已经被注册过";
               }else{
                   user.setId(CommonUtil.getUUID());
                   user.setCreateTime(new Date());
                   userMapper.insertSelective(user);
                   code = Constants.SUCCESS;
                   msg = "注册成功";
               }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            code = Constants.ERROR;
            msg = "系统繁忙";
            e.printStackTrace();
        }
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }
}
