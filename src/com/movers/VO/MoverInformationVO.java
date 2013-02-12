/*
 * MoverInformationVO.java
 *
 * Created on July 25, 2005, 1:57 PM
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
public class MoverInformationVO {
    
    /** Creates a new instance of MoverInformationVO */
    public MoverInformationVO() {
        _name            = "";
        _address_1       = "";
        _address_2       = "";
        _city            = "";
        _state           = "";
        _zip             = "";
        _home_phone      = "";
        _cell_phone      = "";
        _other_phone     = "";
        _email           = "";
        _ssn             = "";
        _license         = "";
        _rate_f          = 0;
        _rate_d          = 0;
        _rate_h          = 0;
        _rate_s          = 0;
        _commision       = 0;
    }
    
    public void print()
    {    
        System.out.println( _name );
        System.out.println( _address_1 );
        System.out.println( _address_2 );
        System.out.println( _city );
        System.out.println( _state );
        System.out.println( _zip );
        System.out.println( _home_phone );
        System.out.println( _cell_phone );
        System.out.println( _other_phone );
        System.out.println( _email );
        System.out.println( _ssn );
        System.out.println( _license );
        System.out.println( _rate_f );
        System.out.println( _rate_d );
        System.out.println( _rate_h );
        System.out.println( _rate_s );
        System.out.println( _commision );
        System.out.println( _company_id );
        System.out.println( _mover_id );
    }
    
    public Boolean commit( Connection conn ) throws Exception {
    
        PreparedStatement stmt = conn.prepareStatement( "insert into movers_list values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        
        stmt.setString  ( 1 , _name );
        stmt.setString  ( 2 , _address_1 );
        stmt.setString  ( 3 , _address_2 );
        stmt.setString  ( 4 , _city );
        stmt.setString  ( 5 , _state );
        stmt.setString  ( 6 , _zip );
        stmt.setString  ( 7 , _home_phone );
        stmt.setString  ( 8 , _cell_phone );
        stmt.setString  ( 9 , _other_phone );
        stmt.setString  ( 10, _ssn );
        stmt.setString  ( 11, _email );
        stmt.setString  ( 12, _license );
        stmt.setDouble  ( 13, _rate_f.doubleValue() );
        stmt.setDouble  ( 14, _rate_d.doubleValue() );
        stmt.setDouble  ( 15, _rate_h.doubleValue() );
        stmt.setDouble  ( 16, _rate_s.doubleValue() );
        stmt.setDouble  ( 17, _commision.doubleValue() );
        stmt.setDouble  ( 18, _commision.doubleValue() );
        stmt.setDouble  ( 19, _commision.doubleValue() );
        stmt.setDouble  ( 20, _commision.doubleValue() );
        stmt.setLong    ( 21, _company_id.longValue() );
        // stmt.setLong    ( 22, _mover_id.longValue() );
        
        stmt.executeUpdate();
        
        return( true );
    }
    
    
    public Boolean commitUpdate( Connection conn ) throws Exception {
    
        PreparedStatement stmt = conn.prepareStatement( "insert into movers_list values ( initcap(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        
        stmt.setString  ( 1 , _name );
        stmt.setString  ( 2 , _address_1 );
        stmt.setString  ( 3 , _address_2 );
        stmt.setString  ( 4 , _city );
        stmt.setString  ( 5 , _state );
        stmt.setString  ( 6 , _zip );
        stmt.setString  ( 7 , _home_phone );
        stmt.setString  ( 8 , _cell_phone );
        stmt.setString  ( 9 , _other_phone );
        stmt.setString  ( 10, _ssn );
        stmt.setString  ( 11, _email );
        stmt.setString  ( 12, _license );
        stmt.setDouble  ( 13, _rate_f.doubleValue() );
        stmt.setDouble  ( 14, _rate_d.doubleValue() );
        stmt.setDouble  ( 15, _rate_h.doubleValue() );
        stmt.setDouble  ( 16, _rate_s.doubleValue() );
        stmt.setDouble  ( 17, _commision.doubleValue() );
        stmt.setDouble  ( 18, _commision.doubleValue() );
        stmt.setDouble  ( 19, _commision.doubleValue() );
        stmt.setDouble  ( 20, _commision.doubleValue() );
        
        stmt.setLong    ( 21, _company_id.longValue() );
        stmt.setLong    ( 22, _mover_id.longValue() );
        
        stmt.executeUpdate();
        
        return( true );
    }
    
    public void update( Connection conn ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from movers_list where mover_id = '"+_mover_id.longValue()+"'");
        commitUpdate( conn );
    }
    
    public void load( Connection conn, Long mover_id, Long company_id ) throws Exception {                
        
        this._mover_id   = mover_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        result.setCommand( "select * from movers_list where mover_id = '"+_mover_id+"'" );
        result.execute( conn );
        
        while( result.next() )
        {
            _name = result.getString( "name" );
            _address_1 = result.getString( "address1" );
            _address_2 = result.getString( "address2" );
            _city = result.getString( "city" );
            _state = result.getString( "state" );
            _zip = result.getString( "zip" );
            _home_phone = result.getString( "home_phone" );
            _cell_phone = result.getString( "cell_phone" );
            _other_phone = result.getString( "other_phone" );
            _email = result.getString( "ssn" );
            _ssn = result.getString( "email" );
            _license = result.getString( "license" );
            _rate_f = result.getDouble( "rate_f" );
            _rate_d = result.getDouble( "rate_d" );
            _rate_h = result.getDouble( "rate_h" );
            _rate_s = result.getDouble( "rate_s" );
            _commision = result.getDouble( "comm_f" );
        }                
    }
    
    public String _name;
    public String _address_1;
    public String _address_2;
    public String _city;
    public String _state;
    public String _zip;
    public String _home_phone;
    public String _cell_phone;
    public String _other_phone;
    public String _email;
    public String _ssn;
    public String _license;
    
    public Number _rate_f;
    public Number _rate_d;
    public Number _rate_h;
    public Number _rate_s;
    public Number _commision;    
    
    public Number _company_id;
    public Number _mover_id;
}
