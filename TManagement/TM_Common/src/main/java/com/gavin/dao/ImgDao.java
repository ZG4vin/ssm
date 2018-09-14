package com.gavin.dao;

import com.gavin.bean.Img;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
@Repository
public interface ImgDao {

    @Insert("insert into imgs(imgname) values (#{imgName})")
    void insert(Img img);

    @Delete("delete from imgs")
    void delete();
}
