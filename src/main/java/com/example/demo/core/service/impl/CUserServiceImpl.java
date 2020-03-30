package com.example.demo.core.service.impl;

import java.util.List;

import com.example.demo.core.domain.CUser;
import com.example.demo.core.mapper.CUserMapper;
import com.example.demo.core.service.ICUserService;
import com.example.demo.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-27
 */
@Service
public class CUserServiceImpl implements ICUserService {
    @Autowired
    private CUserMapper cUserMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CUser selectCUserById(Long id) {
        return cUserMapper.selectCUserById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param cUser 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CUser> selectCUserList(CUser cUser) {
        return cUserMapper.selectCUserList(cUser);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param cUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCUser(CUser cUser) {
        return cUserMapper.insertCUser(cUser);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param cUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCUser(CUser cUser) {
        return cUserMapper.updateCUser(cUser);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCUserByIds(String ids) {
        return cUserMapper.deleteCUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCUserById(Long id) {
        return cUserMapper.deleteCUserById(id);
    }

    @Override
    public CUser selectCUserByUserName(String username) {
        return cUserMapper.selectCUserByUserName(username);
    }
}
