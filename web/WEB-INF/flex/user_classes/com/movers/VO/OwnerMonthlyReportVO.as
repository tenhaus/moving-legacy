class com.movers.VO.OwnerMonthlyReportVO
{
  public function OwnerMonthlyReportVO()
  {
    _remoteClass = "com.movers.VO.OwnerMonthlyReportVO";
  
    _agent_id = 0;      
    _agent = "";
    _month               = 0;
    _year                = 0;

    _insurance           = 0;
    _material            = 0;
    _truck               = 0;
    _royalties           = 0;
    _monthly_fee         = 0;
    
    _other_1 = 0;
    _other_2 = 0;
    _other_3 = 0;
  }

  public var _remoteClass:String;

  public var _agent_id            : Number;
  public var _agent               : String;
  public var _month               : Number;
  public var _year                : Number;
  
  public var _insurance           : Number;
  public var _material            : Number;
  public var _truck               : Number;
  public var _royalties           : Number;
  public var _monthly_fee         : Number;
  public var _other_1             : Number;
  public var _other_2             : Number;
  public var _other_3             : Number;

}
