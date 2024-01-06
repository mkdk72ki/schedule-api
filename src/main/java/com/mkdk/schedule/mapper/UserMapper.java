package com.mkdk.schedule.mapper;

import com.mkdk.schedule.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM users")
  List<User> findAll();

  @Insert("INSERT INTO users (user_id, user_name, user_password) VALUES (#{userId}, #{userName}, #{userPassword})")
  @Options(useGeneratedKeys = true, keyProperty = "userId")
  void create(User user);

}
