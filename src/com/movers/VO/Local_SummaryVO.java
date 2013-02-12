/*
 * Local_SummaryVO.java
 *
 * Created on June 20, 2005, 7:42 PM
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
 * @author Christopher Hayen
 */
public class Local_SummaryVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public Number _movers;
    public Number _trucks;
    public Number _per_hour;
    public Number _total_hours;
    public Number _travel_time;
    public Number _travel_rate;
    public Number _travel_fee;
    public Number _subtotal;
    public Number _discount;
    public Number _total;
    
    /** Creates a new instance of Local_SummaryVO */
    public Local_SummaryVO() {
        _movers = 0;
        _trucks = 0;
        _per_hour = 0;
        _total_hours = 0;
        _travel_time = 0;
        _travel_rate = 0;
        _travel_fee = 0;
        _subtotal = 0;
        _discount = 0;
        _total = 0;
    }
    
    public void print() {
        System.out.println( "_movers " + _movers );
        System.out.println( "_trucks " + _trucks );
        System.out.println( "_per_hour " + _per_hour );
        System.out.println( "_total_hours " + _total_hours );
        System.out.println( "_travel_time " + _travel_time );
        System.out.println( "_travel_rate " + _travel_rate );
        System.out.println( "_travel_fee " + _travel_fee );
        System.out.println( "_subtotal " + _subtotal );
        System.out.println( "_discount " + _discount );
        System.out.println( "_total " + _total );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from local_summary where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public void commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        PreparedStatement stmt = conn.prepareStatement( "insert into local_summary values( ?,?,?,?,?,?,?,?,?,?,?,? )" );
        
        stmt.setLong( 1, job_id );
        stmt.setLong( 2, _movers.longValue() );
        stmt.setLong( 3, _trucks.longValue() );
        stmt.setDouble( 4, _per_hour.doubleValue() );
        stmt.setDouble( 5, _total_hours.doubleValue() );
        stmt.setDouble( 6, _travel_time.doubleValue() );
        stmt.setDouble( 7, _travel_rate.doubleValue() );
        stmt.setDouble( 8, _travel_fee.doubleValue() );
        stmt.setDouble( 9, _subtotal.doubleValue() );
        stmt.setDouble( 10, _discount.doubleValue() );
        stmt.setDouble( 11, _total.doubleValue() );
        stmt.setLong( 12, company_id );
        
        stmt.executeUpdate();
        /*
        String sql = "";
        Statement stmt = conn.createStatement();
        
        sql = "insert into local_summary values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_movers.longValue()+"'";
        sql += ",";
        sql += "'"+_trucks.longValue()+"'";
        sql += ",";
        sql += "'"+_per_hour.doubleValue()+"'";
        sql += ",";
        sql += "'"+_total_hours.doubleValue()+"'";
        sql += ",";
        sql += "'"+_travel_time.doubleValue()+"'";
        sql += ",";
        sql += "'"+_travel_rate.doubleValue()+"'";
        sql += ",";
        sql += "'"+_travel_fee.doubleValue()+"'";
        sql += ",";
        sql += "'"+_subtotal.doubleValue()+"'";
        sql += ",";
        sql += "'"+_discount.doubleValue()+"'";
        sql += ",";
        sql += "'"+_total.doubleValue()+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );
         */
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        
        result.setCommand( "select * from local_summary where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        
        while( result.next() ) {
            _movers = result.getLong( "movers" );            
            _trucks = result.getLong( "trucks" );
            _per_hour = result.getDouble( "perhour" );
            
            _total_hours = result.getDouble( "total_hour" );
            _travel_time = result.getDouble( "travel_time" );
            _travel_rate = result.getDouble( "travel_rate" );
            _travel_fee = result.getDouble( "travel_fee" );
            _subtotal = result.getDouble( "subtotal" );
            _discount = result.getDouble( "discount" );
            _total = result.getDouble( "total" );
        }        
    }
}
