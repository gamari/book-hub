package gamari.app.features.books.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import gamari.app.features.books.models.Book;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM books WHERE title LIKE #{keyword}")
    List<Book> findByTitleContaining(@Param("keyword") String keyword);
}
