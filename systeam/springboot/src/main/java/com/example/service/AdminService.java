package com.example.service;

import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public List<Admin> findAll(){
        return adminDao.selectAll();
    }
    public PageInfo<Admin> findBySearch(Params params){
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开的分页设置来查询
        List<Admin> list= adminDao.findBySearch(params);
        //除了裸数据，还包含了分页信息
        return PageInfo.of(list);
    }

    public void add(Admin admin) {
        //1.用户名非空判断，用户名一定要有
        if(admin.getName()==null||"".equals(admin.getName())){//没输入或者输入空格
            throw new CustomException("用户名不能为空");
        }
        //2.进行重复性判断，不允许出现重复的用户名,只要根据用户名去数据库查询就可以了
        Admin user=adminDao.findByName(admin.getName());
        if(user!=null){
            //说明已经有了
            //提示前台不允许新增
            //返回的是void 如何告诉前台不允许新增？用全局异常处理=>GlobalExceptionHandler,不能返回就抛异常
            throw new CustomException("该用户名已存在");
        }
        //初始化一个密码
        if(admin.getPassword()==null){
            admin.setPassword("123456");
        }
        if(admin.getAvatar()==null){
            admin.setAvatar("avatar.jpg");
        }
        adminDao.insertSelective(admin);//快捷插入数据
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);//快捷更新数据
    }

    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);//快速删除数据，根据主键删除
    }

    public Admin login(Admin admin) {
        //1.进行一些非空判断
        if(admin.getName()==null||"".equals(admin.getName())){//没输入或者输入空格
            throw new CustomException("用户名不能为空");
        }
        if(admin.getPassword()==null||"".equals(admin.getPassword())){//没输入或者输入空格
            throw new CustomException("密码不能为空");
        }
        //2.从数据库里面根据用户输入的用户名和密码查询对应的管理员信息
        Admin user=adminDao.findByNameAndPassword(admin.getName(),admin.getPassword());
        //如果查不出来，说明输入的用户名或密码有误，提醒用户
        if(user==null){
            throw new CustomException("用户名或密码错误");
        }
        //若查出来

        //生成该登录用户对应的token,然后跟着user一起返回到前台
        String token=JwtTokenUtils.genToken(user.getId().toString(),user.getPassword());
        user.setToken(token);
        return user;
    }

    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }

    public Admin findByName(String name) {
        return adminDao.findByName(name);
    }


    public void pass(Admin admin) {
       Admin data=adminDao.selectByPrimaryKey(admin.getId());
       data.setPassword(admin.getPassword());
       adminDao.updateByPrimaryKeySelective(data);
    }

    public Admin findId(Integer id) {
        return adminDao.findId(id);
    }
}
