package com.example.demo.core.service;

import java.util.List;

import com.example.demo.core.domain.CUser;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-03-27
 */
public interface ICUserService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CUser selectCUserById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CUser> selectCUserList(CUser cUser);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cUser 【请填写功能名称】
     * @return 结果
     */
    public int insertCUser(CUser cUser);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cUser 【请填写功能名称】
     * @return 结果
     */
    public int updateCUser(CUser cUser);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCUserByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCUserById(Long id);

    /**
     * 查询根据username【请填写功能名称】信息
     *
     * @param username 【请填写功能名称】ID
     * @return 结果
     */
    public CUser selectCUserByUserName(String username);
}
