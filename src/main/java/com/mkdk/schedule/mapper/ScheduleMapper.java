package com.mkdk.schedule.mapper;

import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.Schedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ScheduleMapper {
  @Select("SELECT s.id, u.name as user_name, g.name as group_name, s.title, s.skd_date, s.s_time, s.e_time, s.comment FROM schedule s JOIN users u ON s.user_id = u.id JOIN `groups` g ON s.group_id = g.id  ORDER BY s.skd_date")
  List<Schedule> findAll();

  @Select("SELECT * FROM `groups`")
  List<Group> findAllGroups();

  @Select("SELECT s.id, u.name as user_name, g.name as group_name, s.title, s.skd_date, s.s_time, s.e_time, s.comment FROM schedule s JOIN users u ON s.user_id = u.id JOIN `groups` g ON s.group_id = g.id WHERE g.name LIKE CONCAT(#{groupName})")
  List<Schedule> findByGroup(String groupName);

  @Select("SELECT s.id, u.name as user_name, g.name as group_name, s.title, s.skd_date, s.s_time, s.e_time, s.comment FROM schedule s JOIN users u ON s.user_id = u.id JOIN `groups` g ON s.group_id = g.id WHERE skd_date = #{scheduleDate}")
  List<Schedule> findByDate(LocalDate scheduleDate);

  @Select("SELECT * FROM schedule WHERE id = #{scheduleId}")
  Optional<Schedule> findById(int scheduleId);

  @Select("SELECT id FROM users WHERE code = #{userCode}")
  Integer findByUserCode(String userCode);

  @Insert("INSERT INTO schedule (id, user_id, group_id, title, skd_date, s_time, e_time, comment) VALUES (#{scheduleId}, #{userName}, #{groupName}, #{title}, #{scheduleDate}, #{startTime}, #{endTime}, #{comment})")
  @Options(useGeneratedKeys = true, keyProperty = "scheduleId")
  void create(Schedule schedule);

  @Update("UPDATE schedule SET title = #{title}, skd_date = #{scheduleDate}, s_time = #{startTime}, e_time = #{endTime}, comment = #{comment} WHERE id = #{scheduleId}")
  void update(Schedule schedule);

  @Delete("DELETE FROM schedule WHERE id = #{scheduleId}")
  void delete(Integer scheduleId);

}
