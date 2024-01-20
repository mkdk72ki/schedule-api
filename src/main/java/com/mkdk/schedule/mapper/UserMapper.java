package com.mkdk.schedule.mapper;

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
public interface UserMapper {

  @Select("SELECT * FROM users")
  List<User> findAll();

  @Select("SELECT * FROM users WHERE id = #{userId}")
  Optional<User> findById(int userId);

  @Select("SELECT * FROM users WHERE code = #{userCode}")
  Optional<User> findByCode(String userCode);

  @Select("SELECT id FROM users WHERE code = #{userCode}")
  Integer findIdByCode(String userCode);

  @Insert("INSERT INTO users (id, name, code, password, authority) VALUES (#{userId}, #{userName}, #{userCode}, #{userPassword}, #{authority})")
  @Options(useGeneratedKeys = true, keyProperty = "userId")
  void create(User user);

  @Update("UPDATE users SET name = #{userName}, code = #{userCode}, password = #{userPassword}, authority = #{authority} WHERE id = #{userId}")
  void update(User user);

  @Delete("DELETE FROM users WHERE id = #{userId}")
  void delete(int userId);

}
