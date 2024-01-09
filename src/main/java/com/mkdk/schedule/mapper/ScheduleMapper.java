package com.mkdk.schedule.mapper;

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
  @Select("SELECT * FROM schedule")
  List<Schedule> findAll();

  @Select("SELECT * FROM schedule WHERE id = #{groupId}")
  List<Schedule> findByGroup(int groupId);

  @Select("SELECT * FROM schedule WHERE skd_date = #{scheduleDate}")
  List<Schedule> findByDate(LocalDate scheduleDate);

  @Select("SELECT * FROM schedule WHERE id = #{scheduleId}")
  Optional<Schedule> findById(int scheduleId);

  @Insert("INSERT INTO schedule (id, user_id, group_id, title, skd_date, s_time, e_time, comment) VALUES (#{scheduleId}, #{userId}, #{groupId}, #{title}, #{scheduleDate}, #{startTime}, #{endTime}, #{comment})")
  @Options(useGeneratedKeys = true, keyProperty = "scheduleId")
  void create(Schedule schedule);

  @Update("UPDATE schedule SET group_id = #{groupId}, title = #{title}, skd_date = #{scheduleDate}, s_time = #{startTime}, e_time = #{endTime}, comment = #{comment} WHERE id = #{scheduleId}")
  void update(Schedule schedule);

  @Delete("DELETE FROM schedule WHERE id = #{scheduleId}")
  void delete(Integer scheduleId);

}
