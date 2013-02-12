class com.movers.VO.Local_SummaryVO
{
  public function Local_SummaryVO()
  {
    _remoteClass = "com.movers.VO.Local_SummaryVO";

    _movers       = 0;
    _trucks       = 0;
    _per_hour     = 0;
    _total_hours  = 0;
    _travel_time  = 0;
    _travel_rate  = 0;
    _travel_fee   = 0;
    _subtotal     = 0;
    _discount     = 0;
    _total        = 0;
  }

  public var _remoteClass:String;

  public var _job_id       : Number;
  public var _company_id   : Number;
      

  public var _movers       : Number;
  public var _trucks       : Number;
  public var _per_hour     : Number;
  public var _total_hours  : Number;
  public var _travel_time  : Number;
  public var _travel_rate  : Number;
  public var _travel_fee   : Number;
  public var _subtotal     : Number;
  public var _discount     : Number;
  public var _total        : Number;
}
