/*
 * DailyReport.java
 *
 * Created on June 15, 2005, 1:11 PM
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
public class DailyReport {
    
    /** Creates a new instance of DailyReport */
    public DailyReport() {
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
    
    public CachedRowSet getDailyReport( String beg, String end, Long company_id ) throws Exception {
        Connection conn = null;
        try {
            String sql = "";
            if( company_id == 657676 )
            {
                sql =  "SELECT job.company_id, estimate_dates.job_id as job,fromcontact.name as name,status,job.jobtype as type,fromdate as date,fromtime as time, job.booked_by as salesman,total,fromcontact.city as from,tocontact.city as to ";
                sql += "from estimate_dates,job,fromcontact,tocontact,summary_info ";
                sql += "where job.job_id = estimate_dates.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = tocontact.job_id ";
                sql += "and estimate_dates.taken between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and status != 'Cancelled' order by job.company_id asc";
            }
            else
            {
                sql =  "SELECT job.company_id, estimate_dates.job_id as job,fromcontact.name as name,status,job.jobtype as type,fromdate as date,fromtime as time, job.booked_by as salesman,total,fromcontact.city as from,tocontact.city as to ";
                sql += "from estimate_dates,job,fromcontact,tocontact,summary_info ";
                sql += "where job.company_id = '"+company_id+"' and job.job_id = estimate_dates.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = tocontact.job_id ";
                sql += "and estimate_dates.taken between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and status != 'Cancelled'";
            }
            conn = openConnection();            
            CachedRowSet result = new CachedRowSetImpl();            
            
            //System.out.println( sql );
            
            result.setCommand(sql);
            result.execute( conn );
            conn.close();
            return( result );
        } 
        finally {
            System.out.println( "in finally for getDailyReport" );
            try{conn.close();} catch(Exception e) {}
        }
    }
    
    public CachedRowSet getDailyReport2( String beg, String end, Long company_id ) throws Exception {
        Connection conn = null;
        try {
            String sql = "";
            if( company_id == 657676 )
            {
                // sql = "select * from commision where taken_date between date '"+beg+"' and date '"+end+"' order by company_id asc";
                
                sql = "SELECT  job.company_id, estimate_dates.job_id as job,fromcontact.name as name,job.status,job.jobtype as type,fromdate as date,fromtime as time, job.booked_by as salesman,estimate as estimated, total ";
                sql += "from commision,estimate_dates,job,fromcontact,tocontact,summary_info ";
                sql += "where commision.job_id = job.job_id and job.job_id = estimate_dates.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = tocontact.job_id ";
                sql += "and estimate_dates.taken between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and job.status != 'Cancelled' order by job.company_id asc";
            }
            else
            {
                //sql = "select * from commision where company_id = '"+company_id+"' and taken_date between date '"+beg+"' and date '"+end+"'";
                
                sql = "SELECT  job.company_id, estimate_dates.job_id as job,fromcontact.name as name,job.status,job.jobtype as type,fromdate as date,fromtime as time, job.booked_by as salesman,estimate as estimated, total ";
                sql += "from commision,estimate_dates,job,fromcontact,tocontact,summary_info ";
                sql += "where job.company_id = '"+company_id+"' and commision.job_id = job.job_id and job.job_id = estimate_dates.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = tocontact.job_id ";
                sql += "and estimate_dates.taken between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and job.status != 'Cancelled' order by job.company_id asc";
            }
            conn = openConnection();
            CachedRowSet result = new CachedRowSetImpl();
            
            
            System.out.println( sql );
            result.setCommand(sql);
            result.execute( conn );
            conn.close();
            return( result );
        } 
        finally {
            System.out.println( "in finally for getDailyReport2" );
            try{conn.close();} catch(Exception e) {}
        }
    }
}
