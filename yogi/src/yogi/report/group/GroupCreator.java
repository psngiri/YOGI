package yogi.report.group;

import java.util.List;

public interface GroupCreator<T> {
	List<Group<T>> create(Group<T> group);
}
