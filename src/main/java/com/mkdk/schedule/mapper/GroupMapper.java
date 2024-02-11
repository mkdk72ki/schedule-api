package com.mkdk.schedule.mapper;

import com.mkdk.schedule.entity.Belonging;
import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.User;
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

  @Select("SELECT * FROM `groups` WHERE code = #{groupCode}")
  Optional<Group> findByCode(String groupCode);

  @Select("SELECT code FROM `groups` WHERE id = #{groupId}")
  String findCode(int groupId);

  @Select("SELECT * FROM `groups` WHERE BINARY code = #{groupCode} AND BINARY password = #{groupPassword}")
  Optional<Group> findGroup(String groupCode, String groupPassword);

  @Select("SELECT * FROM users u INNER JOIN belonging b ON u.id = b.user_id INNER JOIN `groups` g ON b.group_id = g.id WHERE b.group_id = #{groupId}")
  List<User> belongingUser(int groupId);

  @Select("SELECT * FROM `groups` g INNER JOIN belonging b ON g.id = b.group_id INNER JOIN users u ON b.user_id = u.id WHERE b.user_id = #{userId}")
  List<Group> belongingGroup(int userId);

  @Insert("INSERT INTO `groups` (id, name, code, password) VALUES (#{groupId}, #{groupName}, #{groupCode}, #{groupPassword})")
  @Options(useGeneratedKeys = true, keyProperty = "groupId")
  void create(Group group);

  @Update("UPDATE `groups` SET name = #{groupName}, code = #{groupCode}, password = #{groupPassword} WHERE id = #{groupId}")
  void update(Group group);

  @Delete("DELETE FROM `groups` WHERE id = #{groupId}")
  void delete(int groupId);

  // belonging

  @Select("SELECT * FROM belonging WHERE user_id = #{userId} AND group_id = ${groupId}")
  Optional<Belonging> findGroupId(int userId, int groupId);

  @Insert("INSERT INTO belonging (user_id, group_id) VALUES (#{userId},#{groupId})")
  void belong(Belonging belonging);

  @Delete("DELETE FROM belonging WHERE user_id = #{userId} AND group_id = #{groupId}")
  void leave(int userId, int groupId);

}
