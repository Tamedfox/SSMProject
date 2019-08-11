package com.cf.mapper;

import com.cf.pojo.TbBbs;
import com.cf.pojo.TbBbsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBbsMapper {
    int countByExample(TbBbsExample example);

    int deleteByExample(TbBbsExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(TbBbs record);

    int insertSelective(TbBbs record);

    List<TbBbs> selectByExample(TbBbsExample example);

    TbBbs selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") TbBbs record, @Param("example") TbBbsExample example);

    int updateByExample(@Param("record") TbBbs record, @Param("example") TbBbsExample example);

    int updateByPrimaryKeySelective(TbBbs record);

    int updateByPrimaryKey(TbBbs record);
}