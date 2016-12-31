package dataServiceImp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;


public final class Database extends UnicastRemoteObject{
private static Database instance;
private static boolean isInstalled=false;

private Connection database_connection;
private Set<String>database_tables;
private Database (Connection connection)throws RemoteException{
	this.database_connection=connection;
	this.database_tables = new java.util.TreeSet<String>();
}
public static Database getInstance() throws DatabaseException{
	if(Database.instance==null){
		init();
//		throw new DatabaseException("not connected");
	}
	
	return Database.instance;
}
public static void install(String driver) throws DatabaseException{
	if(Database.isInstalled)throw new DatabaseException("already installed");
	try {
		Class.forName(driver);
		Database.isInstalled=true;
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (Exception e) {
		Database.isInstalled=false;
		throw new DatabaseException("unable to install");
	}
}
public static void connect(String url,String userName,String password) throws DatabaseException{
	if(!Database.isInstalled) throw new DatabaseException("please install");
	try
	{
		Connection database_connection = DriverManager.getConnection(url, userName, password);
		Database.instance = new Database(database_connection);
	}
	catch(Exception exception)
	{
		throw new DatabaseException("Unable to connect to the database, please validate the url, username or password!", exception);
	}
}
public Statement statement()throws DatabaseException{
	try
	{
		return this.database_connection.createStatement();
	}
	catch(Exception exception)
	{
		throw new DatabaseException("Unable to create statement, please check the current status of the software system.", exception);
	}
}
public PreparedStatement prepared_statement(String sql) throws DatabaseException
{
	try
	{
		return this.database_connection.prepareStatement(sql);
	}
	catch(Exception exception)
	{
		throw new DatabaseException("Unable to create statement, please check the current status of the software system.", exception);
	}
}
public void close() throws DatabaseException
{
	try
	{
		this.database_connection.close();
		Database.instance = null;
		Database.isInstalled = false;
	}
	catch(Exception exception)
	{
		throw new DatabaseException("Unable to close connection, please check the current status of the software system.", exception);
	}
}
public void table_validate(String tablename, String table_definition) throws DatabaseException, SQLException
{
//	try
//	{
	DatabaseMetaData meta = database_connection.getMetaData();
	ResultSet rsTables = meta.getTables(null , null, tablename, null);
	if(rsTables.next()){
	   System.out.println(tablename+" Table exsits.");
	}else{
	   System.out.println("The Table not exsits.");
	
		Statement statement = this.statement();
		
		Set<String> tables = this.database_tables;
		tablename = tablename.toLowerCase();
		if(!tables.contains(tablename))
		{
			statement.execute("create table " + tablename + " (" + table_definition + ")");
			tables.add(tablename);
		}
		statement.close();
		}
//	}
//	catch(Exception exception)
//	{
//		throw new DatabaseException("Unable to validate database catalog and table, please check the current status of the software system.", exception);
//	}
}
public static void init() throws DatabaseException{
	String driver="com.mysql.jdbc.Driver" ;
	String url="jdbc:mysql://localhost:3306/nbainfo";
	String userName="root";
	String password="";
	Database.install(driver);
	Database.connect(url, userName, password);
	//Database.instance.close();
}
}
