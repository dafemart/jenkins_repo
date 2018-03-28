package com.revature.util;

import java.sql.Connection;

public class ConnectionPool {
    private static Connection conn;
    
    public static void SetConnectionPool(Connection NewConn){
    	conn = NewConn;
    }
    
    public static Connection getPoolConnection(){
    	return conn;
    }
}
