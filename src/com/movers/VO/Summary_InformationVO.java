/*
 * Summary_InformationVO.java
 *
 * Created on June 21, 2005, 10:19 AM
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
public class Summary_InformationVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public Number _storage_fee;
    public Number _extra_stops;
    public Number _access;
    public Number _bulky_items;
    public Number _bulky_items_charge;
    public Number _accessorial;
    public Number _piano_charge;
    public Number _cleaning_fee;
    public Number _flat_rate;
    public Number _extra_weight;
    public Number _extra_rate;
    public Number _materials_sale;
    public Number _extra_materials;
    public String _add_in_one_text;
    public String _add_in_two_text;
    public Number _add_in_one;
    public Number _add_in_two;
    public Number _subtotal;
    public Number _discount;
    public Number _total;
    

    
    /** Creates a new instance of Summary_InformationVO */
    public Summary_InformationVO() {
        _storage_fee = 0;
        _extra_stops = 0;
        _access = 0;
        _bulky_items = 0;
        _bulky_items_charge = 0;
        _accessorial = 0;
        _piano_charge = 0;
        _cleaning_fee = 0;
        _flat_rate = 0;
        _extra_weight = 0;
        _extra_rate = 0;
        _materials_sale = 0;
        _extra_materials = 0;
        _add_in_one_text = "";
        _add_in_two_text = "";
        _add_in_one = 0;
        _add_in_two = 0;
        _subtotal = 0;
        _discount = 0;
        _total = 0;

    }
    
    public void print() {
        System.out.println( "_storage_fee " + _storage_fee );
        System.out.println( "_extra_stops " + _extra_stops );
        System.out.println( "_access " + _access );
        System.out.println( "_bulky_items " + _bulky_items );
        System.out.println( "_bulky_items_charge " + _bulky_items_charge );
        System.out.println( "_accessorial " + _accessorial );
        System.out.println( "_piano_charge " + _piano_charge );
        System.out.println( "_cleaning_fee " + _cleaning_fee );
        System.out.println( "_flat_rate " + _flat_rate );
        System.out.println( "_extra_weight " + _extra_weight );
        System.out.println( "_extra_rate " + _extra_rate );
        System.out.println( "_materials_sale " + _materials_sale );
        System.out.println( "_extra_materials " + _extra_materials );
        System.out.println( "_add_in_one_text " + _add_in_one_text );
        System.out.println( "_add_in_two_text " + _add_in_two_text );
        System.out.println( "_add_in_one " + _add_in_one );
        System.out.println( "_add_in_two " + _add_in_two );
        System.out.println( "_subtotal " + _subtotal );
        System.out.println( "_discount " + _discount );
        System.out.println( "_total " + _total );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from summary_info where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
        
    public void commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        /*
        String sql = "";
        Statement stmt = conn.createStatement();
        
        sql = "insert into summary_info values (";
        sql += "'"+job_id+"',";
        sql += "'"+_storage_fee.doubleValue()+"',";
        sql += "'"+_extra_stops.longValue()+"',";
        sql += "'"+_access.doubleValue()+"',";
        sql += "'"+_bulky_items.longValue()+"',";
        sql += "'"+_bulky_items_charge.doubleValue()+"',";
        sql += "'"+_accessorial.doubleValue()+"',";
        sql += "'"+_piano_charge.doubleValue()+"',";
        sql += "'"+_add_in_one_text+"',";
        sql += "'"+_add_in_one.doubleValue()+"',";
        sql += "'"+_add_in_two_text+"',";
        sql += "'"+_add_in_two.doubleValue()+"',";
        sql += "'"+_flat_rate.doubleValue()+"',";
        sql += "'"+_cleaning_fee.doubleValue()+"',";
        sql += "'"+_extra_weight.longValue()+"',";
        sql += "'"+_extra_rate.doubleValue()+"',";
        sql += "'"+_materials_sale.doubleValue()+"',";
        sql += "'"+_extra_materials.doubleValue()+"',";
        sql += "'"+_subtotal.doubleValue()+"',";
        sql += "'"+_discount.doubleValue()+"',";
        sql += "'"+_total.doubleValue()+"',";
        sql += "'"+company_id+"' );";        
        stmt.executeUpdate( sql );
         */
        PreparedStatement stmt = conn.prepareStatement( "insert into summary_info values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        stmt.setLong  ( 1 , job_id );
        stmt.setDouble( 2 , _storage_fee.doubleValue() );
        stmt.setLong  ( 3 , _extra_stops.longValue() );
        stmt.setDouble( 4 , _access.doubleValue() );
        stmt.setLong  ( 5 , _bulky_items.longValue() );
        stmt.setDouble( 6 , _bulky_items_charge.doubleValue() );
        stmt.setDouble( 7 , _accessorial.doubleValue() );
        stmt.setDouble( 8 , _piano_charge.doubleValue() );
        stmt.setString( 9 , _add_in_one_text );
        stmt.setDouble( 10, _add_in_one.doubleValue() );
        stmt.setString( 11, _add_in_two_text );
        stmt.setDouble( 12, _add_in_two.doubleValue() );
        stmt.setDouble( 13, _flat_rate.doubleValue() );
        stmt.setDouble( 14, _cleaning_fee.doubleValue() );
        stmt.setLong  ( 15, _extra_weight.longValue() );
        stmt.setDouble( 16, _extra_rate.doubleValue() );
        stmt.setDouble( 17, _materials_sale.doubleValue() );
        stmt.setDouble( 18, _extra_materials.doubleValue() );
        stmt.setDouble( 19, _subtotal.doubleValue() );
        stmt.setDouble( 20, _discount.doubleValue() );
        stmt.setDouble( 21, _total.doubleValue() );
        stmt.setLong  ( 22, company_id );        
        

        
        
        stmt.executeUpdate();
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        result.setCommand( "select * from summary_info where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        while( result.next() ) {
            _storage_fee = result.getDouble( "storagefee" );
            _extra_stops = result.getLong( "extrastops" );
            _access = result.getDouble( "access" );
            _bulky_items = result.getLong( "bulkyitems" );
            _bulky_items_charge = result.getDouble( "bulkyitemsprice" );            
            _accessorial = result.getDouble( "accessorial" );
            _piano_charge = result.getDouble( "pianocharge" );
            _add_in_one_text = result.getString( "addinonedesc" );
            _add_in_one = result.getDouble( "addinone" );
            _add_in_two_text = result.getString( "addintwodesc" );
            _add_in_two = result.getDouble( "addintwo" );
            _flat_rate = result.getDouble( "flat_rate" );
            _cleaning_fee = result.getDouble( "cleaning_total" );
            _extra_weight = result.getLong( "extraweight" );            
            _extra_rate = result.getDouble( "extrarate" );
            _materials_sale = result.getDouble( "materialssale" );
            _extra_materials = result.getDouble( "extramaterials" );
            _subtotal = result.getDouble( "subtotal" );
            _discount = result.getDouble( "discount" );
            _total = result.getDouble( "total" );
            

        }          
    }
}
