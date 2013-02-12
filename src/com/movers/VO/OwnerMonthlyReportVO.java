/*
 * OwnerMonthlyReportVO.java
 *
 * Created on September 29, 2005, 2:52 PM
 */

package com.movers.VO;

import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.naming.*;
import com.sun.rowset.CachedRowSetImpl;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author chris
 */
public class OwnerMonthlyReportVO 
{
    public Number _agent_id;
    public String _agent;
    public Number _month;
    public Number _year;
    public Number _insurance;
    public Number _material;
    public Number _truck;
    public Number _royalties;
    public Number _monthly_fee;
    public Number _other_1;
    public Number _other_2;
    public Number _other_3;
    
    
    /** Creates a new instance of OwnerMonthlyReportVO */
    public OwnerMonthlyReportVO()
    {
        _agent_id = 0;
        _agent = "";
        _month               = 0;
        _year                = 0;
        _insurance           = 0;
        _material            = 0;
        _truck               = 0;
        _royalties           = 0;
        _monthly_fee         = 0;
        _other_1             = 0;
        _other_2             = 0;
        _other_3             = 0;
    }
    
    public void print() 
    {
        System.out.println( _agent_id );
        System.out.println( _agent );
        System.out.println( _month );
        System.out.println( _year );
        System.out.println( _insurance );
        System.out.println( _material );
        System.out.println( _truck );
        System.out.println( _royalties );
        System.out.println( _monthly_fee );
        System.out.println( _other_1 );
        System.out.println( _other_2 );
        System.out.println( _other_3 );
    }
    
    public void update( Connection conn ) throws Exception 
    {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from owner_monthly_deductions where year = '"+_year.longValue()+"' and month = '"+_month.longValue()+"' and agent_id = '"+_agent_id.longValue()+"'" );
        commit( conn );
    }
    
    public Boolean commit( Connection conn ) throws Exception 
    {
        
        PreparedStatement stmt = conn.prepareStatement( "insert into owner_monthly_deductions values ( ?,?,?,?,?,?,?,?,?,?,?,? )" );
        
        stmt.setString( 1 , _agent );
        stmt.setLong  ( 2 , _agent_id.longValue() );
        stmt.setLong  ( 3 , _month.longValue() );
        stmt.setLong  ( 4 , _year.longValue() );
        stmt.setDouble( 5 , _insurance.doubleValue() );        
        stmt.setDouble( 6 , _material.doubleValue() );
        stmt.setDouble( 7 , _truck.doubleValue() );
        stmt.setDouble( 8 , _royalties.doubleValue() );
        stmt.setDouble( 9 , _monthly_fee.doubleValue() );
        stmt.setDouble( 10, _other_1.doubleValue() );
        stmt.setDouble( 11, _other_2.doubleValue() );
        stmt.setDouble( 12, _other_3.doubleValue() );
        
        stmt.executeUpdate();
        
        return( true );
    }
    
    public void load( Connection conn, Number agent_id, Number month, Number year ) throws Exception {
        
        this._agent_id  = agent_id.longValue();
        this._month     = month.longValue();
        this._year      = year.longValue();
        
        CachedRowSet result = new CachedRowSetImpl();
        result.setCommand( "select * from owner_monthly_deductions where agent_id = '"+_agent_id.longValue()+"' and month = '"+_month.longValue()+"' and year = '"+_year.longValue()+"';" );
        result.execute( conn );
        while( result.next() )
        {   
            _agent = result.getString( "agent" );
            _insurance = result.getDouble( "insurance" );
            _material = result.getDouble( "material" );
            _truck = result.getDouble( "truck" );
            _royalties = result.getDouble( "royalties" );
            _monthly_fee = result.getDouble( "monthly_fee" );
            _other_1 = result.getDouble( "other_1" );
            _other_2 = result.getDouble( "other_2" );
            _other_3 = result.getDouble( "other_3" );
        }
    }
}
