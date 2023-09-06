package gamari.app.features.dashboard.services;

import java.util.Date;
import java.util.List;

import gamari.app.features.memo.models.Activity;
import gamari.app.features.users.models.User;

public interface DashboardService {
    List<Activity> calculateActivities(Date start, Date end, User user);
}
