package com.mkdk.schedule;

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

  @Insert("INSERT INTO schedule (schedule_id, user_id, group_id, title, schedule_date, start_time, end_time, comment) VALUES (#{schedule_id}, #{user_id}, #{group_id}, #{title}, #{schedule_date}, #{start_time}, #{end_time}, #{comment})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void create(Schedule schedule);

  @Update("UPDATE schedule SET group_id = #{group_id}, title = #{title}, schedule_date = #{schedule_date}, start_time = #{start_time}, end_time = #{end_time}, comment = #{comment}")
  void update(Schedule schedule);

}
