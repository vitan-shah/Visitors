package visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorList {
	private List<Visitor> visitors;

public VisitorList() {
    visitors = new ArrayList<>();
}

public void addVisitor(Visitor visitor) {
    visitors.add(visitor);
}

public List<Visitor> getVisitors() {
    return visitors;
}
}
