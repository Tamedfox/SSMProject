package com.cf.mapper;

import com.cf.pojo.TbBbsReply;
import com.cf.pojo.TbBbsReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBbsReplyMapper {
    int countByExample(TbBbsReplyExample example);

    int deleteByExample(TbBbsReplyExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(TbBbsReply record);

    int insertSelective(TbBbsReply record);

    List<TbBbsReply> selectByExample(TbBbsReplyExample example);

    TbBbsReply selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") TbBbsReply record, @Param("example") TbBbsReplyExample example);

    int updateByExample(@Param("record") TbBbsReply record, @Param("example") TbBbsReplyExample example);

    int updateByPrimaryKeySelective(TbBbsReply record);

    int updateByPrimaryKey(TbBbsReply record);
}