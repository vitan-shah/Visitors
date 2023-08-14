package visitor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class VisitorTrackingService {
    private Map<Integer, House> housesMap;
    private Map<Integer, Visitor> visitorsMap;

    public VisitorTrackingService() {
        housesMap = new HashMap<>();
        visitorsMap = new HashMap<>();
    }

    // Other methods from previous implementation...

    private boolean isSameDay(long timestamp, Date date) {
        // Implementation to check if a given timestamp is on the same day as the provided date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(date);
        int givenYear = calendar.get(Calendar.YEAR);
        int givenMonth = calendar.get(Calendar.MONTH);
        int givenDay = calendar.get(Calendar.DAY_OF_MONTH);

        return year == givenYear && month == givenMonth && day == givenDay;
    }

    private boolean isWithinDateRange(long timestamp, Date dateFrom, Date dateTo) {
        // Implementation to check if a given timestamp falls within the provided date range
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp));

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(dateFrom);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(dateTo);

        return calendar.compareTo(fromCalendar) >= 0 && calendar.compareTo(toCalendar) <= 0;
    }

    public Map<Integer, House> getHousesMap() {
        return housesMap;
    }

    public VisitorList getVisitorsUnApprovedLeft(Date dateFrom, Date dateTo) {
        VisitorList visitorsUnApprovedLeft = new VisitorList();
        for (Visitor visitor : visitorsMap.values()) {
            if (!visitor.isApproved() && visitor.getExitTime() != 0 && isWithinDateRange(visitor.getExitTime(), dateFrom, dateTo)) {
                visitorsUnApprovedLeft.addVisitor(visitor);
            }
        }
        return visitorsUnApprovedLeft;
    }

    public VisitorList getVisitorsPendingApproval(Date dateFrom, Date dateTo) {
        VisitorList visitorsPendingApproval = new VisitorList();
        for (Visitor visitor : visitorsMap.values()) {
            if (!visitor.isApproved() && isWithinDateRange(visitor.getEntryTime(), dateFrom, dateTo)) {
                visitorsPendingApproval.addVisitor(visitor);
            }
        }
        return visitorsPendingApproval;
    }

    public VisitorList getVisitorsDay(Date today) {
        VisitorList visitorsDay = new VisitorList();
        for (Visitor visitor : visitorsMap.values()) {
            if (isSameDay(visitor.getEntryTime(), today)) {
                visitorsDay.addVisitor(visitor);
            }
        }
        return visitorsDay;
    }

    public void approve(int visitorId) throws VisitorNotFoundException {
        Visitor visitor = visitorsMap.get(visitorId);
        if (visitor != null) {
            visitor.setApproved(true);
        } else {
            throw new VisitorNotFoundException("Visitor with ID " + visitorId + " not found.");
        }
    }

    public void addVisitor(Visitor visitor) throws HouseNotFoundException {
        visitorsMap.put(visitor.getId(), visitor);
        House house = housesMap.get(visitor.getHouseNo());

        if (house != null) {
            // Send approval request to the house
            // Assume there is a method sendMessage() to send messages
            // Wait for a response and set the visitor's approval status accordingly
        } else {
            throw new HouseNotFoundException("House with number " + visitor.getHouseNo() + " not found.");
        }
    }


    public Map<Integer, House> getVisitorsMap() {
        return housesMap;
    }

}
