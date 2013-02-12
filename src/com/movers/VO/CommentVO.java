/*
 * CommentVO.java
 *
 * Created on June 20, 2005, 2:35 PM
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
public class CommentVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public String _comment_job;
    public String _comment_office;
    public String _comment_delivery;
    
    /** Creates a new instance of CommentVO */
    public CommentVO() {
        _comment_job      = "";
        _comment_office   = "";
        _comment_delivery = "";
    }
    
    public void print() {
        System.out.println( "_comment_job: " + _comment_job );
        System.out.println( "_comment_office: " + _comment_office );
        System.out.println( "_comment_delivery: " + _comment_delivery );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from comment where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        /*
        String sql;
        Statement stmt;
        
        stmt = conn.createStatement();
        sql = "";
        
        sql += "insert into comment values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_comment_job+"'";
        sql += ",";
        sql += "'"+_comment_office+"'";
        sql += ",";
        sql += "'"+_comment_delivery+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );
        */
        
        PreparedStatement stmt = conn.prepareStatement( "insert into comment values ( ?,?,?,?,? )" );
        stmt.setLong  ( 1, job_id );
        stmt.setString( 2, _comment_job );
        stmt.setString( 3, _comment_office );
        stmt.setString( 4, _comment_delivery );
        stmt.setLong  ( 5, company_id );
        
        stmt.executeUpdate();
        
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {                
        
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        result.setCommand( "select * from comment where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        while( result.next() )
        {        
            _comment_job = result.getString( "comment" );
            _comment_office = result.getString( "internal" );
            _comment_delivery = result.getString( "delivery" );
        }                
    }
}
