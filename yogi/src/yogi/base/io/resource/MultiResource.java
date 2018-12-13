package yogi.base.io.resource;

public interface MultiResource extends Resource{
	boolean hasNext();
	Resource next();
}
