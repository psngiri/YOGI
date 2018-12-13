package yogi.base.io.resource.db;

import yogi.base.io.resource.MultiResource;


public interface MultiDbResource extends MultiResource{
	DbResource next();
}
