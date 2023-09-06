package gamari.app.features.dashboard.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        DateRange dateRange = new DateRange(start, end);
        Map<String, Activity> activitiesFromDb = fetchActivitiesFromDb(dateRange, user);
        return fillActivities(dateRange, activitiesFromDb);
    }

    private Map<String, Activity> fetchActivitiesFromDb(DateRange dateRange, User user) {
        List<Activity> activities = memoMapper.countDailyMemosWithinDateRange(
                dateRange.getStart(), dateRange.getEnd(), user.getId());

        Map<String, Activity> activitiesMap = new HashMap<>();
        for (Activity activity : activities) {
            activitiesMap.put(activity.getDate(), activity);
            System.out.println(activity.getDate());
        }
        return activitiesMap;
    }

    private List<Activity> fillActivities(DateRange dateRange, Map<String, Activity> activitiesFromDb) {
        List<Activity> fullActivitiesList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Date date : dateRange.toIterable()) {
            String dateStr = sdf.format(date);
            Activity activity = activitiesFromDb.getOrDefault(dateStr, new Activity(0, dateStr));

            SimpleDateFormat sdf2 = new SimpleDateFormat("d");
            activity.setDate(sdf2.format(date));
            fullActivitiesList.add(activity);
        }

        return fullActivitiesList;
    }
}
