/*
 * DailyIncome.java
 *
 * Created on June 16, 2005, 1:32 PM
 */

package com.movers.reports;

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
public class DailyIncome {
    
    /** Creates a new instance of DailyIncome */
    public DailyIncome() {
    }
    
    public Connection openConnection() throws Exception {
        
        System.out.println( "opening connection" );
        
        Context initContext = null;
        Context envContext = null;
        DataSource ds = null;
        Connection conn = null;
        
        initContext = new InitialContext();
        envContext  = (Context)initContext.lookup("java:/comp/env");
        ds = (DataSource)envContext.lookup("jdbc/postgres");
        conn = ds.getConnection();
        return( conn );
    }
    
    public CachedRowSet getDailyIncome( String beg, String end, Long company_id ) throws Exception {
        Connection conn = null;
        try {
            String sql = "";
            if( company_id == 657676 )
            {
                sql = " SELECT job.company_id, job.job_id as job,name,status,fromdate as date, estimate_totals.total as estimated, summary_info.total  ";
                sql += "from job,fromcontact,summary_info,estimate_totals where ";                
                sql += "job.job_id = estimate_totals.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and fromdate between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and status != 'Cancelled' and status != 'Estimate' order by job.company_id asc;";
            }
            else {
                sql = " SELECT job.company_id, job.job_id as job,name,status,fromdate as date,estimate_totals.total as estimated, summary_info.total  ";
                sql += "from job,fromcontact,summary_info,estimate_totals where ";
                sql += "estimate_totals.job_id = job.job_id and job.company_id = '"+company_id+"' and fromcontact.company_id = '"+company_id+"' and summary_info.company_id = '"+company_id+"' ";
                sql += "and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and fromdate between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and status != 'Cancelled' and status != 'Estimate' order by job.job_id asc;";
            }
            
            conn = openConnection();
            CachedRowSet result = new CachedRowSetImpl();
                        
            System.out.println( sql );
            
            result.setCommand(sql);
            result.execute( conn );
            return( result );
        } 
        finally {
            System.out.println( "in finally for getDailyIncome" );
            try {conn.close();} catch(Exception e) {}
        }        
    }
    
    public CachedRowSet getDailyIncomeBySalesman( String salesman, String beg, String end, Long company_id ) throws Exception {
        Connection conn = null;
        try {
            String sql = "";
            if( company_id == 657676 )
            {
                sql = " SELECT job.company_id, job.job_id as job,name,status, job.jobtype as type, fromdate as date,estimate_totals.total as estimated, summary_info.total  ";
                sql += "from estimate_totals,job,fromcontact,summary_info where ";                
                sql += "estimate_totals.job_id = job.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and fromdate between date '"+beg+"' and date '"+end+"' and job.booked_by = '"+salesman+"' and jobtype != 'Training' and status != 'Cancelled' and status != 'Estimate' order by job.fromdate asc;";
            }
            else {
                sql = " SELECT job.company_id, job.job_id as job,name,status,job.jobtype as type,fromdate as date,estimate_totals.total as estimated, summary_info.total  ";
                sql += "from estimate_totals,job,fromcontact,summary_info where ";
                sql += "estimate_totals.job_id = job.job_id and job.company_id = '"+company_id+"' and fromcontact.company_id = '"+company_id+"' and summary_info.company_id = '"+company_id+"' ";
                sql += "and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and fromdate between date '"+beg+"' and date '"+end+"' and job.booked_by = '"+salesman+"' and jobtype != 'Training' and status != 'Cancelled' and status != 'Estimate' order by job.fromdate asc;";
            }
            
            conn = openConnection();
            CachedRowSet result = new CachedRowSetImpl();
                        
            System.out.println( sql );
            
            result.setCommand(sql);
            result.execute( conn );
            return( result );
        } 
        finally {
            System.out.println( "in finally for getDailyIncome" );
            try {conn.close();} catch(Exception e) {}
        }        
    }
}
