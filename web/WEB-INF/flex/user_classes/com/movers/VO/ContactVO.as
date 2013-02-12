class com.movers.VO.ContactVO
{
  public function ContactVO()
  {
    _remoteClass = "com.movers.VO.ContactVO";

    _from_name          = "";
    _from_address_1     = "";
    _from_address_2     = "";
    _from_city          = "";
    _from_county        = "";
    _from_state         = "";
    _from_zip           = "";
    _from_cross_street  = "";
    _from_email         = "";
    _from_home_phone    = "";
    _from_fax           = "";
    _from_work_phone    = "";
    _from_cell_phone    = "";
    
    _to_name            = "";
    _to_address_1       = "";
    _to_address_2       = "";
    _to_city            = "";
    _to_county          = "";
    _to_state           = "";
    _to_zip             = "";
    _to_cross_street    = "";
    _to_email           = "";
    _to_home_phone      = "";
    _to_fax             = "";
    _to_work_phone      = "";
    _to_cell_phone      = "";
  }

  public var _remoteClass:String;
  
  public var _job_id              : Number;
  public var _company_id          : Number;
  
  public var _employees           : Array;
  public var _adv                 : Array;
      
  public var _from_name           : String; 
  public var _from_address_1      : String;
  public var _from_address_2      : String;
  public var _from_city	          : String;
  public var _from_county         : String;
  public var _from_state          : String;
  public var _from_zip            : String;
  public var _from_cross_street   : String;
  public var _from_email          : String;
  public var _from_home_phone	  : String;
  public var _from_fax	          : String;
  public var _from_work_phone	  : String;
  public var _from_cell_phone	  : String;
  
  public var _to_name		  : String;
  public var _to_address_1    	  : String;
  public var _to_address_2     	  : String;
  public var _to_city             : String;
  public var _to_county      	  : String;
  public var _to_state      	  : String;
  public var _to_zip      	  : String;
  public var _to_cross_street     : String;
  public var _to_email            : String;
  public var _to_home_phone       : String;
  public var _to_fax              : String;
  public var _to_work_phone       : String;
  public var _to_cell_phone       : String;
}
