package gamari.app.features.books.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import gamari.app.features.books.models.Memo;

@Mapper
public interface MemoMapper {
    @Insert("INSERT INTO memos (id, reading_book_id, content, author, created_at) VALUES (#{id}, #{readingBookId}, #{content}, #{author}, #{createdAt})")
    void save(Memo memo);

    @Select("SELECT * FROM memos WHERE reading_book_id = #{readingBookId} ORDER BY created_at DESC")
    List<Memo> findByReadingBookId(String readingBookId);
}
