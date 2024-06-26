package com.example.dao;

import com.example.domain.Photo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PhotoMapper {

    @Insert("insert into photo (url,pid) values (#{url},#{pid})")
    int addPhoto(Photo photo);

    int deletePhoto(Map<String, Object> map);

    @Update("update photo set url=#{url} where pid=#{pid}")
    int updatePhoto(Photo photo);

    @Select("select * from photo")
    List<Photo> queryAllPhoto();

    @Select("select * from photo where pid=#{pid}")
    List<Photo> queryPhotobypid(Long pid);
}
