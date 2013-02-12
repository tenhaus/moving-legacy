/*
 * OwnerReportVO.java
 *
 * Created on September 23, 2005, 11:32 AM
 */

package com.movers.VO;

import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.naming.*;
import com.sun.rowset.CachedRowSetImpl;
import java.io.Serializable;
import java.util.*;

import com.movers.session.*;

/**
 *
 * @author chris
 */
public class OwnerReportVO {
    
    public Number _job_id;
    public Number _company_id;
    public Number _agent_id;
    public String _agent;
    
    public Number _labor_charge;    
    public Number _other_1;
    public Number _other_2;
    public Number _other_3;
    public String _paid;
    
    /** Creates a new instance of OwnerReportVO */
    public OwnerReportVO() {
        _job_id = 0;
        _company_id = 0;
        _agent_id = 0;
        _agent = "";
        
        _labor_charge = 0;
        _other_1 = 0;
        _other_2 = 0;
        _other_3 = 0;        
        _paid = "N";
    }
    
    public void print()
    {
        System.out.println( _job_id );
        System.out.println( _company_id );
        System.out.println( _agent_id );
        System.out.println( _agent );
        
        System.out.println( _labor_charge );        
        System.out.println( _other_1 );
        System.out.println( _other_2 );
        System.out.println( _other_3 );
        System.out.println( _paid );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from owner_report where company_id = '"+company_id+"' and job_id = '"+job_id+"'" );
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        PreparedStatement stmt = conn.prepareStatement( "insert into owner_report values ( ?,?,?,?,?,?,?,?,? )" );
        
        stmt.setLong  ( 1 , job_id.longValue() );
        stmt.setLong  ( 2 , company_id.longValue() );
        stmt.setLong  ( 3 , _agent_id.longValue() );
        stmt.setString( 4 , _agent );
        stmt.setDouble( 5 , _labor_charge.doubleValue() );        
        stmt.setDouble( 6 , _other_1.doubleValue() );
        stmt.setDouble( 7 , _other_2.doubleValue() );
        stmt.setDouble( 8 , _other_3.doubleValue() );
        stmt.setString( 9 , _paid );
        
        stmt.executeUpdate();
        
        return( true );
    }
}
