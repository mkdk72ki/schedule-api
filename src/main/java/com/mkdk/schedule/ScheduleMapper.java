package com.mkdk.schedule;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper {
  @Select("SELECT * FROM schedule")
  List<Schedule> findAll();

  @Insert("INSERT INTO schedule (schedule_id, user_id, group_id, title, schedule_date, start_time, end_time, comment) VALUES (#{schedule_id}, #{user_id}, #{group_id}, #{title}, #{schedule_date}, #{start_time}, #{end_time}, #{comment})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void create(Schedule schedule);
}
