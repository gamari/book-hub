package gamari.app.features.dashboard.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.base.libs.DateRange;
import gamari.app.features.books.mappers.MemoMapper;
import gamari.app.features.memo.models.Activity;
import gamari.app.features.users.models.User;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private MemoMapper memoMapper;

    @Override
    public List<Activity> calculateActivities(Date start, Date end, User user) {
        DateRange range = new DateRange(start, end);

        List<Activity> activitiesFromDb = memoMapper.countDailyMemosWithinDateRange(
                range.getStart(), range.getEnd(), user.getId());

        Map<String, Activity> activitiesMap = new HashMap<>();
        for (Activity activity : activitiesFromDb) {
            activitiesMap.put(activity.getDate(), activity);
        }

        List<Activity> fullActivitiesList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(range.getStart());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (!cal.getTime().after(range.getEnd())) {
            String dateStr = sdf.format(cal.getTime());
            Activity activity = activitiesMap.getOrDefault(
                    dateStr, new Activity(0, dateStr));
            fullActivitiesList.add(activity);

            cal.add(Calendar.DATE, 1);
        }

        return fullActivitiesList;
    }
}
