package com.cf.mapper;

import com.cf.pojo.TbLeavework;
import com.cf.pojo.TbLeaveworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbLeaveworkMapper {
    int countByExample(TbLeaveworkExample example);

    int deleteByExample(TbLeaveworkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbLeavework record);

    int insertSelective(TbLeavework record);

    List<TbLeavework> selectByExample(TbLeaveworkExample example);

    TbLeavework selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbLeavework record, @Param("example") TbLeaveworkExample example);

    int updateByExample(@Param("record") TbLeavework record, @Param("example") TbLeaveworkExample example);

    int updateByPrimaryKeySelective(TbLeavework record);

    int updateByPrimaryKey(TbLeavework record);
}