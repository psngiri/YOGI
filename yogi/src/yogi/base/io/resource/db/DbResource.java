package yogi.base.io.resource.db;

import java.sql.Connection;

import yogi.base.io.resource.Resource;

public interface DbResource extends Resource{
	Connection getConnection();
}
