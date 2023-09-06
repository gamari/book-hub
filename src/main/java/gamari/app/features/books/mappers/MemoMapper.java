package gamari.app.features.books.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import gamari.app.features.books.models.Memo;
import gamari.app.features.memo.models.Activity;

@Mapper
public interface MemoMapper {
    @Insert("INSERT INTO memos (id, reading_book_id, content, author, created_at) VALUES (#{id}, #{readingBookId}, #{content}, #{author}, #{createdAt})")
    void save(Memo memo);

    @Select("SELECT * FROM memos WHERE reading_book_id = #{readingBookId} ORDER BY created_at DESC")
    List<Memo> findByReadingBookId(String readingBookId);

    @Select("SELECT COUNT(*) as count, date(created_at/1000, 'unixepoch') as target FROM memos WHERE author = #{userId} AND created_at BETWEEN #{startDate, jdbcType=TIMESTAMP} AND #{endDate, jdbcType=TIMESTAMP} GROUP BY target ORDER BY target")
    List<Activity> countDailyMemosWithinDateRange(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("userId") String userId);
}
