/*
 * DailyBySalesman.java
 *
 * Created on June 7, 2005, 12:27 PM
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
public class DailyBySalesman {
    
    /** Creates a new instance of DailyBySalesman */
    public DailyBySalesman() {
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
    
    public CachedRowSet getDailyReportBySalesman( String salesman, String beg, String end, Long company_id ) throws Exception {
        Connection conn = null;
        try {
            String sql = "";
            if( company_id == 657676 )
            {
                sql = "SELECT job.company_id, estimate_dates.job_id as job,fromcontact.name as name,job.status,job.jobtype as type,fromdate as date,fromtime as time,commision.estimate as estimated,total ";
                sql += "from estimate_dates,job,fromcontact,tocontact,summary_info,commision ";
                sql += "where job.job_id = commision.job_id and job.job_id = estimate_dates.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = tocontact.job_id ";
                sql += " and job.booked_by = '"+salesman+"' and estimate_dates.taken between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and job.status != 'Cancelled' order by job.company_id asc";
            }
            else 
            {
                sql = "SELECT job.company_id, estimate_dates.job_id as job,fromcontact.name as name,job.status,job.jobtype as type,fromdate as date,fromtime as time,commision.estimate as estimated,total ";
                sql += "from estimate_dates,job,fromcontact,tocontact,summary_info,commision ";
                sql += "where job.job_id = commision.job_id  and job.company_id = '"+company_id+"' and job.job_id = estimate_dates.job_id and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = tocontact.job_id ";
                sql += " and job.booked_by = '"+salesman+"' and estimate_dates.taken between date '"+beg+"' and date '"+end+"' and jobtype != 'Training' and job.status != 'Cancelled'";
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
            System.out.println( "in finally for getDailyReportBySalesman" );
            try{conn.close();} catch(Exception e) {}
        }
    }
    
}
