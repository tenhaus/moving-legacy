/*
 * Open_JobVO.java
 *
 * Created on July 1, 2005, 12:35 PM
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
public class Open_JobVO {   
    
    public Long job_id;
    public Long company_id;
    
    public SessionVO sessionVO;
    
    public ContactVO contactVO;
    public JobVO jobVO;
    public Additional_ContactVO additional_contactVO;
    public CommentVO commentVO;
    public Local_SummaryVO local_summaryVO;
    public LD_SummaryVO ld_summaryVO;
    public Summary_InformationVO summary_informationVO;
    
    public MaterialsVO materialsVO;
    public InventoryVO inventoryVO;
    
    public PaymentVO  paymentVO;
    public MoversVO   moversVO;
    public TripVO     tripVO;
    
    /** Creates a new instance of Open_JobVO */
    public Open_JobVO() {
    }
    
    public Long update() throws Exception {
        System.out.println( "updating job " + job_id );
        String sql = "";
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        Savepoint sp = null;
        
        try {
            
            conn = openConnection();
            conn.setAutoCommit(false);
                        
            
            jobVO.update( conn, job_id, company_id );
            commentVO.update( conn, job_id, company_id );
            additional_contactVO.update( conn, job_id, company_id );
            contactVO.update( conn, job_id, company_id );
            summary_informationVO.update( conn, job_id, company_id );
            local_summaryVO.update( conn, job_id, company_id );
            ld_summaryVO.update( conn, job_id, company_id );
            materialsVO.update( conn, job_id, company_id );                               
            inventoryVO.update( conn, job_id, company_id );
            paymentVO.update( conn, job_id, company_id );
            
            moversVO.update( conn, job_id, company_id );            
            
            tripVO.update( conn, job_id, company_id );
            
            conn.commit();
        }        
        catch( Exception e )
        {
            e.printStackTrace();
            System.out.println( "rolling back" );                     
            conn.rollback();
            throw( e );
        }
        finally {                       
            conn.close();
            System.out.println( "closing connection" );
        }
        return( job_id );
    }
    
    public Boolean load( Long job_id, Long company_id )
    {
        this.job_id = job_id;
        this.company_id = company_id;
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
        paymentVO = new PaymentVO();
        moversVO = new MoversVO();
        tripVO = new TripVO();
        sessionVO = new SessionVO();
        
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
            paymentVO.load( conn, job_id, company_id );
            moversVO.load( conn, job_id, company_id );
            tripVO.load( conn, job_id, company_id );
            
            // needed to be done after jobVO
            sessionVO.load( conn, jobVO._booked_by );
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
    
    public void print() {
        jobVO.print();
        commentVO.print();
        additional_contactVO.print();
        contactVO.print();
        summary_informationVO.print();
        local_summaryVO.print();
        ld_summaryVO.print();
        materialsVO.print();
        inventoryVO.print();
        paymentVO.print();
        moversVO.print();
        tripVO.print();
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
