/*
 * JobVO.java
 *
 * Created on June 20, 2005, 1:25 PM
 */

package com.movers.VO;

import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.io.Serializable;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.naming.*;
import com.sun.rowset.CachedRowSetImpl;

/**
 *
 * @author chris
 */
public class JobVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public ArrayList _agents;
    
    public String _job_type;
    public String _status;
    public String _storage_location;
    public String _from_time;
    public String _to_time;
    public String _from_date;
    public String _to_date;
    public String _agent;
    public String _trip_number;
    public String _truck_number;
    public String _taken_by;
    public String _booked_by;
    public String _confirmed_by;
    public String _adv;
    
    public String _taken_date;      // not in the job column in the db but should be
    
    /** Creates a new instance of JobVO */
    public JobVO() {
        _job_type= "";
        _status= "";
        _storage_location= "";
        _from_time= "";
        _to_time= "";
        _from_date= "";
        _to_date= "";
        _agent= "";
        _trip_number= "";
        _truck_number= "";
        _taken_by= "";
        _booked_by= "";
        _confirmed_by= "";
        _adv= "";
        _taken_date = "";
    }
    
    public void print() {
        System.out.println( "_job_type " + _job_type );
        System.out.println( "_status " + _status );
        System.out.println( "_storage_location " + _storage_location );
        System.out.println( "_from_time " + _from_time );
        System.out.println( "_to_time " + _to_time );
        System.out.println( "_from_date " + _from_date );
        System.out.println( "_to_date " + _to_date );
        System.out.println( "_agent " + _agent );
        System.out.println( "_trip_number " + _trip_number );
        System.out.println( "_truck_number " + _truck_number );
        System.out.println( "_taken_by " + _taken_by );
        System.out.println( "_booked_by " + _booked_by );
        System.out.println( "_confirmed_by " + _confirmed_by );
        System.out.println( "_adv " + _adv );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from job where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        
        String sql;
        Statement statement;
        
        statement = conn.createStatement();
        sql = "";
        
        ResultSet rs;
        sql = "select employee_id from employee where username = '"+_booked_by+"' ";
        rs = statement.executeQuery(sql);
        rs.next();
        Long employee_id = rs.getLong( "employee_id" );
        
        /*
        sql = "insert into job values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_job_type+"'";
        sql += ",";
        sql += "'"+_status+"'";
        sql += ",";
        sql += "'"+_storage_location+"'";
        sql += ",";
        sql += "'"+_from_time+"'";
        sql += ",";
        sql += "'"+_to_time+"'";
        sql += ",";
        sql += "'"+_from_date+"'";
        sql += ",";
        sql += "'"+_to_date+"'";
        sql += ",";
        sql += "'"+_agent+"'";
        sql += ",";
        sql += "'"+_trip_number+"'";
        sql += ",";
        sql += "'"+_truck_number+"'";
        sql += ",";
        sql += "'"+_taken_by+"'";
        sql += ",";
        sql += "'"+_booked_by+"'";
        sql += ",";
        sql += "'"+_confirmed_by+"'";
        sql += ",";
        sql += "'"+_adv+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += ",";
        sql += "'"+employee_id+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );
        */
        PreparedStatement stmt = conn.prepareStatement( "insert into job values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        
        // convert to yyyy-mm-dd
        String[] parts = _from_date.split( "-" );        
        _from_date = parts[2] + "-" + parts[0] + "-" + parts[1];        
        
        parts = _to_date.split( "-" );
        _to_date = parts[2] + "-" + parts[0] + "-" + parts[1];
        
        
        stmt.setLong( 1, job_id );
        stmt.setString( 2, _job_type );
        stmt.setString( 3, _status );
        stmt.setString( 4, _storage_location );
        stmt.setString( 5, _from_time );
        stmt.setString( 6, _to_time );
        stmt.setDate( 7, java.sql.Date.valueOf(_from_date) );
        stmt.setDate( 8, java.sql.Date.valueOf(_to_date) );
        stmt.setString( 9, _agent );
        stmt.setString( 10, _trip_number );
        stmt.setString( 11, _truck_number );
        stmt.setString( 12, _taken_by );
        stmt.setString( 13, _booked_by );
        stmt.setString( 14, _confirmed_by );
        stmt.setString( 15, _adv );
        stmt.setLong( 16, company_id );
        stmt.setLong( 17, employee_id );
        
        stmt.executeUpdate();        
        
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        
        result.setCommand( "select * from job where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );        
        
        while( result.next() ) {
            _job_type = result.getString( "jobtype" );
            _status = result.getString( "status" );
            _storage_location = result.getString( "storage_location" );
            _from_time = result.getString( "fromtime" );
            _to_time = result.getString( "totime" );            
            
            _from_date = result.getDate( "fromdate" ).toString();
            _to_date = result.getDate( "todate" ).toString();
            
            _agent = result.getString( "agent" );            
            _trip_number = result.getString( "trip_num" );            
            _truck_number = result.getString( "truck_num" );            
            _taken_by = result.getString( "taken_by" );            
            _booked_by = result.getString( "booked_by" );            
            _confirmed_by = result.getString( "confirmed_by" );            
            _adv = result.getString( "adv" );                       
        }        
        
        result = new CachedRowSetImpl();
        result.setCommand( "select taken from estimate_dates where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        
        while( result.next() ) {
            _taken_date = result.getDate( "taken" ).toString();
        }
        
        result = new CachedRowSetImpl();
        result.setCommand( "select name from agent order by name asc" );
        result.execute( conn );
        
        _agents = new ArrayList();
        
        while( result.next() ) {
            _agents.add( result.getString( "name" ) );
        }
    }
}
