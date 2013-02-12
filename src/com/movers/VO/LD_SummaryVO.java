/*
 * LD_SummaryVO.java
 *
 * Created on June 21, 2005, 10:21 AM
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
public class LD_SummaryVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public Number _total_cubic_feet;
    public Number _weight;
    public Number _price_per_pound;
    public Number _price_per_cubic_feet;
    public Number _subtotal;
    public Number _discount;
    public Number _total;
    
    // new
    public Number _minimum_weight;
    public Number _minimum_price;
    public Number _adjusted_weight;
    
    
    /** Creates a new instance of LD_SummaryVO */
    public LD_SummaryVO() {
        _total_cubic_feet = 0;
        _weight = 0;
        _price_per_pound = 0;
        _price_per_cubic_feet = 0;
        _subtotal = 0;
        _discount = 0;
        _total = 0;
        _minimum_weight = 0;
        _minimum_price = 0;
        _adjusted_weight = 0;
    }
    
    public void print() {
        System.out.println( "_total_cubic_feet " + _total_cubic_feet );
        System.out.println( "_weight " + _weight );
        System.out.println( "_price_per_pound " + _price_per_pound );
        System.out.println( "_price_per_cubic_feet " + _price_per_cubic_feet );
        System.out.println( "_subtotal " + _subtotal );
        System.out.println( "_discount " + _discount );
        System.out.println( "_total " + _total );
    }

    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from long_distance_summary where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public void commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        PreparedStatement stmt = conn.prepareStatement( "insert into long_distance_summary values( ?,?,?,?,?,?,?,?,?,?,?,? )" );
        stmt.setLong( 1, job_id );
        stmt.setLong( 2, _total_cubic_feet.longValue() );
        stmt.setLong( 3, _weight.longValue() );
        stmt.setDouble( 4, _price_per_pound.doubleValue() );
        stmt.setDouble( 5, _price_per_cubic_feet.doubleValue() );
        stmt.setDouble( 6, _subtotal.doubleValue() );
        stmt.setDouble( 7, _discount.doubleValue() );
        stmt.setDouble( 8, _total.doubleValue() );
        stmt.setLong( 9, company_id );
        stmt.setLong  ( 10, _minimum_weight.longValue() );
        stmt.setDouble( 11, _minimum_price.doubleValue() );
        stmt.setLong  ( 12, _adjusted_weight.longValue() );
        
        stmt.executeUpdate();
        
        /*
        String sql = "";
        Statement stmt = conn.createStatement();
        sql = "insert into long_distance_summary values ( ";
        
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_total_cubic_feet.longValue()+"'";
        sql += ",";
        sql += "'"+_weight.longValue()+"'";
        sql += ",";
        sql += "'"+_price_per_pound.doubleValue()+"'";
        sql += ",";
        sql += "'"+_price_per_cubic_feet.doubleValue()+"'";
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
        result.setCommand( "select * from long_distance_summary where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        while( result.next() ) {
            
            _total_cubic_feet = result.getLong( "totalcubicfeet" );
            _weight = result.getLong( "weight" );
            _price_per_pound = result.getDouble( "priceperpound" );
            
            _price_per_cubic_feet = result.getDouble( "pricepercubicfeet" );
            _subtotal = result.getDouble( "subtotal" );
            _discount = result.getDouble( "discount" );
            _total = result.getDouble( "total" );
            
            _minimum_price = result.getDouble( "minimum_price" );
            _minimum_weight = result.getLong( "minimum_weight" );
            _adjusted_weight = result.getLong( "adjusted_weight" );
        }        
    }
}
