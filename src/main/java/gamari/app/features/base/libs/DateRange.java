package gamari.app.features.base.libs;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DateRange {
    private Date start;
    private Date end;

    public DateRange(Date start, Date end) {
        this.start = normalizeStart(start);
        this.end = normalizeEnd(end);
    }

    private Date normalizeStart(Date start) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }

    private Date normalizeEnd(Date end) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        return cal.getTime();
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    public Iterable<Date> toIterable() {
        return new Iterable<Date>() {
            @Override
            public Iterator<Date> iterator() {
                return new Iterator<Date>() {
                    private final Calendar currentCal = Calendar.getInstance();
                    {
                        currentCal.setTime(start);
                    }

                    @Override
                    public boolean hasNext() {
                        return currentCal.getTime().compareTo(end) <= 0;
                    }

                    @Override
                    public Date next() {
                        Date currentDate = currentCal.getTime();
                        currentCal.add(Calendar.DATE, 1);
                        return currentDate;
                    }
                };
            }
        };
    }
}
