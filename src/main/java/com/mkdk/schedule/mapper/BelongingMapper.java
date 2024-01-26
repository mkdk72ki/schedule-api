package com.mkdk.schedule.mapper;

import com.mkdk.schedule.entity.Belonging;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface BelongingMapper {

  @Select("SELECT * FROM belonging WHERE user_id = #{userId} AND group_id = ${groupId}")
  Optional<Belonging> findGroupId(int userId, int groupId);

  @Insert("INSERT INTO belonging (user_id, group_id) VALUES (#{userId},#{groupId})")
  void belong(Belonging belonging);

  @Delete("DELETE FROM belonging WHERE user_id = #{userId} AND group_id = #{groupId}")
  void leave(int userId, int groupId);

}
