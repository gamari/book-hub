package gamari.app.features.books.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.MemoMapper;
import gamari.app.features.books.models.Memo;

@Service
public class MemoService {
    @Autowired
    private MemoMapper memoMapper;

    public void save(Memo memo) {
        memoMapper.save(memo);
    }

    public List<Memo> findByReadingBookId(String readingBookId) {
        return memoMapper.findByReadingBookId(readingBookId);
    }
}
