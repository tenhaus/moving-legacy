/*
 * TripVO.java
 *
 * Created on July 6, 2005, 9:58 PM
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
public class TripVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public ArrayList _carrierList;
    
    public String _lot_number;
    public String _color;
    public String _start_number;
    public String _end_number;
    public String _driver_name;
    public String _helper_name;
    public String _delivery_date;
    public String _approve_date;
    public String _approve_by;
    public String _prior;
    public String _carrier;
    
    /** Creates a new instance of TripVO */
    public TripVO() 
    {
        _lot_number     = "";
        _color          = "";
        _start_number   = "";
        _end_number     = "";
        _driver_name    = "";
        _helper_name    = "";
        _delivery_date  = "";
        _approve_date   = "";
        _approve_by     = "";
        _prior          = "";
        _carrier        = "";
    }
    
    public void print() 
    {
        System.out.println( _lot_number );
        System.out.println( _color );
        System.out.println( _start_number );
        System.out.println( _end_number );
        System.out.println( _driver_name );
        System.out.println( _helper_name );
        System.out.println( _delivery_date );
        System.out.println( _approve_date );
        System.out.println( _approve_by );
        System.out.println( _prior );
        System.out.println( _carrier );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from trip_info where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception 
    {
        /*
        String sql;
        Statement stmt;
        
        stmt = conn.createStatement();
        sql = "";
        
        sql = "insert into trip_info values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += ",";
        sql += "'"+_lot_number+"'";
        sql += ",";
        sql += "'"+_color+"'";
        sql += ",";
        sql += "'"+_start_number+"'";
        sql += ",";
        sql += "'"+_end_number+"'";
        sql += ",";
        sql += "'"+_driver_name+"'";
        sql += ",";
        sql += "'"+_helper_name+"'";
        sql += ",";
        sql += "'"+_delivery_date+"'";
        sql += ",";
        sql += "'"+_approve_date+"'";
        sql += ",";
        sql += "'"+_approve_by+"'";
        sql += ",";
        sql += "'"+_prior+"'";
        sql += ",";
        sql += "'"+_carrier+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );
        */
        
        PreparedStatement stmt = conn.prepareStatement( "insert into trip_info values( ?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        stmt.setLong( 1, job_id );
        stmt.setLong( 2, company_id );
        stmt.setString( 3, _lot_number );
        stmt.setString( 4, _color );
        stmt.setString( 5, _start_number );
        stmt.setString( 6, _end_number );
        stmt.setString( 7, _driver_name );
        stmt.setString( 8, _helper_name );
        stmt.setString( 9, _delivery_date );
        stmt.setString( 10, _approve_date );
        stmt.setString( 11, _approve_by );
        stmt.setString( 12, _prior );
        stmt.setString( 13, _carrier );
        
        stmt.executeUpdate();
        
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception 
    {
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        
        result.setCommand( "select * from trip_info where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );        
        
        while( result.next() ) {                   
            _lot_number     = result.getString( "lot_num" );
            _color          = result.getString( "color" );
            _start_number   = result.getString( "start_num" );
            _end_number     = result.getString( "end_num" );
            _driver_name    = result.getString( "driver_name" );
            _helper_name    = result.getString( "helper_name" );
            _approve_by     = result.getString( "approve_by" );
            _prior          = result.getString( "prior" );
            _carrier        = result.getString( "carrier" );
            
            _delivery_date  = result.getString( "delivery_date" );
            _approve_date   = result.getString( "approve_date" );
        }
        
        _carrierList = new ArrayList();
        
        result = new CachedRowSetImpl();
        result.setCommand( "select name from carrier order by name asc" );
        result.execute( conn );   
        
        while( result.next() ) {
            _carrierList.add( result.getString("name") );            
        }
    }
}
