class com.movers.VO.TripVO
{
  public function TripVO()
  {
    _remoteClass = "com.movers.VO.TripVO";

    _lot_number     = "";
    _color          = "";
    _start_number   = "";
    _end_number     = "";
    _driver_name    = "";
    _helper_name    = "";
    _delivery_date  = "";
    _approve_date   = "";
    _approve_by     = "";
    _prior          = "";
    _carrier        = "";    
  }

  public var _remoteClass:String;

  public var _job_id         : Number;
  public var _company_id     : Number;

  public var _carrierList    : Array;
  
  public var _lot_number     :String;  
  public var _color          :String;
  public var _start_number   :String;
  public var _end_number     :String;
  public var _driver_name    :String;
  public var _helper_name    :String;
  public var _delivery_date  :String;
  public var _approve_date   :String;
  public var _approve_by     :String;
  public var _prior          :String;
  public var _carrier        :String;
}
