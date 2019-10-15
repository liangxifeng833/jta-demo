package com.mybatis.jta.demo.service.group;

import com.mybatis.jta.demo.entity.car.MessagePackageNo;
import com.mybatis.jta.demo.entity.test.User;
import com.mybatis.jta.demo.service.car.MessagePackageNoService;
import com.mybatis.jta.demo.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 测试service在该service中组合其他service
 * 测试分布式事务
 * Create by liangxifeng on 19-9-27
 */
@Service
public class IndexService {
    @Autowired
    private MessagePackageNoService messagePackageNoService;

    @Autowired
    private UserService userService;

    /**
     * 保存 db_car.messagePackageNo表数据
     * 保存 db_user.tb_user表数据，跨库分布式事务
     * @param messagePackageNo
     * @param user
     */
    @Transactional
    public void save(MessagePackageNo messagePackageNo, User user) {
        messagePackageNoService.insert(messagePackageNo);
        userService.insert(user);
        //int i = 4 / 0; // 除0异常,测试事务
        //手动回滚事务
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

    }

    /**
     *
     * @param user
     */
    @Transactional
    public void save2( User user) {
        userService.insert(user);
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        //int i = 4 / 0; // 除0异常,测试事务
    }

    /**
     * @method: findByNo
     * @param: [no]
     * @description: 根据no查询
     * @author: Administrator
     * @date: 2017/9/28
     * @time: 13:21
     * @return: com.lcj.web.entity.car.MessagePackageNo
     */
//    public MessagePackageNo findByNo(int no) {
//        return messagePackageNoService.selectOne(new EntityWrapper<MessagePackageNo>().eq("no",no));
//    }
}
