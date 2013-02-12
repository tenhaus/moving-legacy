/*
 * EmployeeInformationVO.java
 *
 * Created on September 8, 2005, 11:40 AM
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
public class EmployeeInformationVO {
    
    /** Creates a new instance of EmployeeInformationVO */
    public EmployeeInformationVO() {
        _username        = "";
        _password        = "";
        _name            = "";
        _address_1       = "";
        _address_2       = "";
        _city            = "";
        _state           = "";
        _zip             = "";
        _home_phone      = "";
        _email           = "";
        _ssn             = "";        
        _ext             = "";
        _role            = "";
        _hourly_rate     = 0;
    }
    
    public void print()
    {    
        System.out.println( _name );
        System.out.println( _username );
        System.out.println( _password );
        System.out.println( _address_1 );
        System.out.println( _address_2 );
        System.out.println( _city );
        System.out.println( _state );
        System.out.println( _zip );
        System.out.println( _home_phone );    
        System.out.println( _email );
        System.out.println( _ssn );
        System.out.println( _ext );
        System.out.println( _role );
        System.out.println( _hourly_rate );
        
        System.out.println( _company_id );
        System.out.println( _employee_id );
    }

    
    public Boolean commit( Connection conn ) throws Exception {
    
        CachedRowSet res = new CachedRowSetImpl();
        res.setCommand( "select 1 from employee where username = '"+_username+"'" );
        res.execute(conn);
        
        System.out.println( "res.size is " + res.size() );
        if( res.size() != 0 )
        {
            System.out.println( "throwing exception" );            
            throw new Exception( "That username already exists.  Please try another username." );   
        }
        
        
        PreparedStatement stmt = conn.prepareStatement( "insert into employee values ( default, ?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        
        stmt.setLong    ( 1 , _company_id.longValue() );
        stmt.setString  ( 2 , _username );
        stmt.setString  ( 3 , _password );
        stmt.setString  ( 4 , _name );
        stmt.setString  ( 5 , _address_1 );
        stmt.setString  ( 6 , _city );
        stmt.setString  ( 7 , _state );
        stmt.setString  ( 8 , _zip );
        stmt.setString  ( 9 , _ssn );
        stmt.setDouble  ( 10, _hourly_rate.doubleValue() );
        stmt.setString  ( 11, _role );
        stmt.setString  ( 12, _ext );
        stmt.setString  ( 13, _email );
        
        stmt.executeUpdate();
        
        return( true );
    }
    
    public String _username;
    public String _password;
    public String _name;
    public String _address_1;
    public String _address_2;
    public String _city;
    public String _state;
    public String _zip;
    public String _home_phone;    
    public String _email;
    public String _ssn;
    public String _role;
    public String _ext;
    public Number _hourly_rate;
    
    public Number _company_id;
    public Number _employee_id;
    
}
