package com.mkdk.schedule.mapper;

import com.mkdk.schedule.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GroupMapper {

  @Select("SELECT * FROM `groups`")
  List<Group> findAll();

  @Select("SELECT * FROM `groups` WHERE id = #{groupId}")
  Optional<Group> findById(int groupId);

  @Insert("INSERT INTO `groups` (id, name, code, password) VALUES (#{groupId}, #{groupName}, #{groupCode}, #{groupPassword})")
  @Options(useGeneratedKeys = true, keyProperty = "groupId")
  void create(Group group);

  @Update("UPDATE `groups` SET name = #{groupName}, code = #{groupCode}, password = #{groupPassword} WHERE id = #{groupId}")
  void update(Group group);

  @Delete("DELETE FROM `groups` WHERE id = #{groupId}")
  void delete(int groupId);
  
}
