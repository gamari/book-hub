package gamari.app.features.dashboard.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.MemoMapper;
import gamari.app.features.memo.models.Activity;
import gamari.app.features.users.models.User;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private MemoMapper memoMapper;

    @Override
    public List<Activity> calculateActivities(Date start, Date end, User user) {
        // TODO リファクタリングする
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);

        Date targetStart = cal.getTime();

        cal.setTime(end);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        Date targetEnd = cal.getTime();

        List<Activity> activities = memoMapper.countDailyMemosWithinDateRange(targetStart, targetEnd, user.getId());

        // TODO 変換処理

        return activities;
    }
}
