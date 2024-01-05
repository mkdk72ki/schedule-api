package com.mkdk.schedule;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ScheduleMapper {
  @Select("SELECT * FROM schedule")
  List<Schedule> findAll();

  @Insert("INSERT INTO schedule (schedule_id, user_id, group_id, title, schedule_date, start_time, end_time, comment) VALUES (#{scheduleId}, #{userId}, #{groupId}, #{title}, #{scheduleDate}, #{startTime}, #{endTime}, #{comment})")
  @Options(useGeneratedKeys = true, keyProperty = "scheduleId")
  void create(Schedule schedule);

  @Update("UPDATE schedule SET group_id = #{groupId}, title = #{title}, schedule_date = #{scheduleDate}, start_time = #{startTime}, end_time = #{endTime}, comment = #{comment}")
  void update(Schedule schedule);

  @Delete("DELETE FROM schedule WHERE schedule_id = #{scheduleId}")
  void delete(Integer scheduleId);

}
