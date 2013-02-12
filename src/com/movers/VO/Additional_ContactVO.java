/*
 * Additional_ContactVO.java
 *
 * Created on June 20, 2005, 1:40 PM
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
public class Additional_ContactVO {
    
    public Long   _job_id;
    public Long   _company_id;
    
    public String _additional_contact_name;
    public String _additional_contact_address_1;
    public String _additional_contact_address_2;
    public String _additional_contact_county;
    public String _additional_contact_city;
    public String _additional_contact_state;
    public String _additional_contact_zip;
    public String _additional_contact_cross_street;
    public String _additional_contact_email;
    public String _additional_contact_home_phone;
    public String _additional_contact_home_fax;
    public String _additional_contact_work_phone;
    public String _additional_contact_work_fax;
    
    /** Creates a new instance of Additional_ContactVO */
    public Additional_ContactVO() {
        _additional_contact_name = "";
        _additional_contact_address_1 = "";
        _additional_contact_address_2 = "";
        _additional_contact_county = "";
        _additional_contact_city = "";
        _additional_contact_state = "";
        _additional_contact_zip = "";
        _additional_contact_cross_street = "";
        _additional_contact_email = "";
        _additional_contact_home_phone = "";
        _additional_contact_home_fax = "";
        _additional_contact_work_phone = "";
        _additional_contact_work_fax = "";
    }
    
    public void print() {
        System.out.println( "_additional_contact_name " + _additional_contact_name );
        System.out.println( "_additional_contact_address_1 " + _additional_contact_address_1 );
        System.out.println( "_additional_contact_address_2 " + _additional_contact_address_2 );
        System.out.println( "_additional_contact_county " + _additional_contact_county );
        System.out.println( "_additional_contact_city " + _additional_contact_city );
        System.out.println( "_additional_contact_state " + _additional_contact_state );
        System.out.println( "_additional_contact_zip " + _additional_contact_zip );
        System.out.println( "_additional_contact_cross_street " + _additional_contact_cross_street );
        System.out.println( "_additional_contact_email " + _additional_contact_email );
        System.out.println( "_additional_contact_home_phone " + _additional_contact_home_phone );
        System.out.println( "_additional_contact_home_fax " + _additional_contact_home_fax );
        System.out.println( "_additional_contact_work_phone " + _additional_contact_work_phone );
        System.out.println( "_additional_contact_work_fax " + _additional_contact_work_fax );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from additional_contact where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        /*
        String sql;
        Statement stmt;
        
        stmt = conn.createStatement();
        sql = "";
        
        sql = "insert into additional_contact values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_additional_contact_name+"'";
        sql += ",";
        sql += "'"+_additional_contact_address_1+"'";
        sql += ",";
        sql += "'"+_additional_contact_address_2+"'";
        sql += ",";
        sql += "'"+_additional_contact_city+"'";
        sql += ",";
        sql += "'"+_additional_contact_state+"'";
        sql += ",";
        sql += "'"+_additional_contact_zip+"'";
        sql += ",";
        sql += "'"+_additional_contact_cross_street+"'";
        sql += ",";
        sql += "'"+_additional_contact_email+"'";
        sql += ",";
        sql += "'"+_additional_contact_home_phone+"'";
        sql += ",";
        sql += "'"+_additional_contact_home_fax+"'";
        sql += ",";
        sql += "'"+_additional_contact_work_phone+"'";
        sql += ",";
        sql += "'"+_additional_contact_work_fax+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );        
         */
        
        PreparedStatement eh = conn.prepareStatement( "insert into additional_contact values( ?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        eh.setLong  ( 1, job_id );
        eh.setString( 2, _additional_contact_name );
        eh.setString( 3, _additional_contact_address_1 );
        eh.setString( 4, _additional_contact_address_2 );
        eh.setString( 5, _additional_contact_city );
        eh.setString( 6, _additional_contact_state );
        eh.setString( 7, _additional_contact_zip );
        eh.setString( 8, _additional_contact_cross_street );
        eh.setString( 9, _additional_contact_email );
        eh.setString( 10, _additional_contact_home_phone );
        eh.setString( 11, _additional_contact_home_fax );
        eh.setString( 12, _additional_contact_work_phone );
        eh.setString( 13, _additional_contact_work_fax );        
        eh.setLong  ( 14, company_id );
        eh.executeUpdate();
        
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        result.setCommand( "select * from additional_contact where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        while( result.next() ) {         
            _additional_contact_name = result.getString( "name" );
            _additional_contact_address_1 = result.getString( "address1" );
            _additional_contact_address_2 = result.getString( "address2" );
            _additional_contact_city = result.getString( "city" );
            _additional_contact_state = result.getString( "state" );
            _additional_contact_zip = result.getString( "zip" );
            _additional_contact_cross_street = result.getString( "cross_street" );
            _additional_contact_email = result.getString( "email" );
            _additional_contact_home_phone = result.getString( "homephone" );
            _additional_contact_home_fax = result.getString( "homefax" );
            _additional_contact_work_phone = result.getString( "workphone" );
            _additional_contact_work_fax = result.getString( "workfax" );
        }        
    }
}
