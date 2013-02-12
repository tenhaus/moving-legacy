/*
 * EstimateVO.java
 *
 * Created on June 22, 2005, 2:38 PM
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
public class EstimateVO {
    public ContactVO contactVO;
    public JobVO jobVO;
    public Additional_ContactVO additional_contactVO;
    public CommentVO commentVO;
    public Local_SummaryVO local_summaryVO;
    public LD_SummaryVO ld_summaryVO;
    public Summary_InformationVO summary_informationVO;
    
    public MaterialsVO materialsVO;
    public InventoryVO inventoryVO;
    
    /** Creates a new instance of EstimateVO */
    public EstimateVO() {
    }
    
    public Long commit() {
        
        String sql = "";
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        
        Long job_id     = new Long(0);
        Long company_id = new Long(0);
        
        try {
            conn = openConnection();
            stmt = conn.createStatement();
            
            sql = "select company_id from employee where username = '"+jobVO._booked_by+"' ";
            rs = stmt.executeQuery(sql);
            rs.next();
            company_id = rs.getLong( "company_id" );
            
            /* one day make this dynamic */
            if( company_id == 1 ) {                
                sql = "select nextval( 'giant_job_ids' )";
                rs = stmt.executeQuery( sql );
                rs.next();
                job_id = rs.getLong( "nextval" );
            }
            if( company_id == 2 ) {                
                sql = "select nextval( 'chesapeake_jobids' )";
                rs = stmt.executeQuery( sql );
                rs.next();
                job_id = rs.getLong( "nextval" );
            }
            if( company_id == 3 ){                
                sql = "select nextval( 'job_id' )";
                rs = stmt.executeQuery( sql );
                rs.next();
                job_id = rs.getLong( "nextval" );
            }
            if( company_id == 4 ){                
                sql = "select nextval( 'epic_job_ids' )";
                rs = stmt.executeQuery( sql );
                rs.next();
                job_id = rs.getLong( "nextval" );
            }
            if( company_id == 5 ){                
                sql = "select nextval( 'paramount_job_ids' )";
                rs = stmt.executeQuery( sql );
                rs.next();
                job_id = rs.getLong( "nextval" );
            }
            conn.setAutoCommit( false );
            
            jobVO.commit( conn, job_id, company_id );
            commentVO.commit( conn, job_id, company_id );
            additional_contactVO.commit( conn, job_id, company_id );
            contactVO.commit( conn, job_id, company_id );
            summary_informationVO.commit( conn, job_id, company_id );
            local_summaryVO.commit( conn, job_id, company_id );
            ld_summaryVO.commit( conn, job_id, company_id );
            inventoryVO.commit( conn, job_id, company_id );
            materialsVO.commit( conn, job_id, company_id );
                   
            stmt.executeUpdate( "insert into estimate_dates values ( '"+company_id+"', '"+job_id+"', default )" );
            OwnerReportVO vo = new OwnerReportVO();
            
            vo._job_id     = job_id;
            vo._company_id = company_id;
            
            vo.commit( conn, job_id, company_id );
            
            conn.commit();            
        }
        catch( Exception e ) {
            e.printStackTrace();
            return( null );
        }
        finally {
            try {conn.close();} catch(Exception e) {}
            System.out.println( "closing connection" );
        }
        return( job_id );
    }
    
    public Boolean load( Long job_id, Long company_id )
    {
        System.out.println( "loading job " + job_id );
        
        Connection conn = null;
        
        commentVO = new CommentVO();   
        additional_contactVO = new Additional_ContactVO();
        contactVO = new ContactVO();
        inventoryVO = new InventoryVO();
        materialsVO = new MaterialsVO();
        ld_summaryVO = new LD_SummaryVO();
        local_summaryVO = new Local_SummaryVO();
        summary_informationVO = new Summary_InformationVO();
        jobVO = new JobVO();
        try
        {
            conn = openConnection();
            commentVO.load( conn, job_id, company_id );
            additional_contactVO.load( conn, job_id, company_id );
            contactVO.load( conn, job_id, company_id );
            inventoryVO.load( conn, job_id, company_id );
            materialsVO.load( conn, job_id, company_id );
            ld_summaryVO.load( conn, job_id, company_id );
            local_summaryVO.load( conn, job_id, company_id );
            summary_informationVO.load( conn, job_id, company_id );
            jobVO.load( conn, job_id, company_id );
        }
        catch( Exception e ) {
            e.printStackTrace();
            return(false);
        }
        finally {
            try {conn.close();} catch(Exception e) {}
            System.out.println( "closing connection" );
        }
        return( true );
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
}
